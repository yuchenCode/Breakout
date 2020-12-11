package base;

import ui.GameScreen;
import ui.Listener;
import utils.RandomDoubleCreator;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Game implements Runnable{

    private GameScreen gameScreen;
    private Listener keyListener;
    private int live;
    private int score;
    private boolean launch = false;
    private boolean sticky = false;
    private boolean pause = true;
    private Level[] levels;
    private int currentLevel = 0;
    private int destroyedBrick = 0;
    private List<Ball> ballList;
    private Paddle paddle;
    private Bonus bonus;
    private boolean canReleaseBonus = true;
    private Laser laser;
    private double now;
    private double lastUpdateTime;
    private double lastRenderTime;
    private int updateCount;
    private int bonusExistCount;

    public Game (GameScreen gs, Listener lis){
        gameScreen = gs;
        keyListener = lis;
        gs.addGame(this);
        live = 3;
        score = 0;
        levels = new Level[5];
        levels[0] = new Level(1);
        levels[1] = new Level(2);
        levels[2] = new Level(3);
        levels[3] = new Level(4);
        levels[4] = new Level(5);
        ballList = new ArrayList<>();
        ballList.add(new Ball(Settings.BALL_ORIGIN_X, Settings.BALL_ORIGIN_Y, Settings.BALL_DIAMETER, Settings.BALL_DIAMETER));
        paddle = new Paddle(Settings.PADDLE_ORIGIN_X, keyListener);
    }

    public int getLives() {return live;}

    public int getScores() {return score;}

    public int getExistBricks(){
        return levels[currentLevel].getTotalBrick() - destroyedBrick;
    }

    // check if all balls are out
    public boolean allBallOut(){
        boolean out = true;
        for (Ball b : ballList) {
            if (b.isOut() == false) {
                out = false;
                break;
            }
        }
        return out;
    }

    public void run(){
        now = System.nanoTime();
        lastUpdateTime = System.nanoTime();
        lastRenderTime = System.nanoTime();
        bonusExistCount = 0;

        while (live > 0 && destroyedBrick != levels[currentLevel].getTotalBrick() && !allBallOut()) {
            if (!allBallOut() && !pause) {
                updateCount = 0;
                // update game
                while (now - lastUpdateTime > Settings.TIME_BETWEEN_UPDATES && updateCount < Settings.MAX_UPDATES_BEFORE_RENDER) {
                    updateGame();
                    lastUpdateTime += Settings.TIME_BETWEEN_UPDATES;
                    updateCount++;
                    // control bonus remain time
                    if (bonus != null && bonus.isBegin()) {
                        bonusExistCount++;
                    }
                    if (bonusExistCount > Settings.MAX_BONUS_EXIST_TIME) {
                        closeBonus();
                    }
                }
                // paint/render game
                paintGame();
            } else {
                lastUpdateTime = System.nanoTime();
                if (keyListener.isPause()) {
                    pause = false;
                }
                gameScreen.paintImmediately(Settings.GAME_BOUND);
                try {
                    Thread.sleep(5);
                } catch (InterruptedException e) {
                }
            }
        }

        if (live > 0 && destroyedBrick == levels[currentLevel].getTotalBrick()) {
            // pass level
            currentLevel++;
            if (currentLevel < Settings.FINAL_LEVEL) {
                destroyedBrick = 0;
                reset();
                run();
            } else {
                score += live *100;
            }
        } else if (live == 0) {
            // end game
        } else if (allBallOut()) {
            // end live
            reset();
            live--;
            run();
        }
        if (keyListener.isPause()) {
            pause = !pause;
        }
    }

    public void paintGame(){
        gameScreen.paintImmediately(Settings.GAME_BOUND);
        lastRenderTime = now;

        while (now - lastRenderTime < Settings.TARGET_TIME_BETWEEN_RENDERS) {

            try {
                Thread.sleep(1);
            } catch (Exception e) {
            }

            now = System.nanoTime();
        }
        if (keyListener.isPause()) {
            pause = true;
        }
    }

    private void updateGame(){
        if (ballList.size() == 1) {
            moveBall(ballList.get(0));
            hitBrick(ballList.get(0));
            if (bonus != null) {
                moveBonus();
            }
            hitPaddle(ballList.get(0));
            boundBounce(ballList.get(0));
            if (laser != null) {
                fireLaser();
            }
        } else {
            for (Ball b : ballList) {
                moveBall(b);
                hitBrick(b);
                hitPaddle(b);
                boundBounce(b);
            }
        }
    }

    private void moveBall(Ball ball){
        if (keyListener.isLaunch() && !launch) {
            ballList.get(0).launch();
            launch = true;
            sticky = false;
        }
        if (launch) {
            paddle.move();
            if (laser != null && laser.isExist()) {
                moveWithPaddle(laser);
            }
            ball.move();
        }
        if (sticky) {
            paddle.move();
            // sticky ball and laser move with paddle
            moveWithPaddle(ball);
        }
    }

    private void moveWithPaddle(Ball b){
        if (keyListener.isLeft() && paddle.getX() > 0) {
            b.setX(b.getX() - Settings.PADDLE_SPEED);
            b.setHitBox();
        }
        if (keyListener.isRight() && paddle.getX() + paddle.getWidth() < Settings.GAME_WIDTH) {
            b.setX(b.getX() + Settings.PADDLE_SPEED);
            b.setHitBox();
        }
    }

    private void hitBrick(Ball ball){
        if (!allBallOut()) {
            for (Brick b : levels[currentLevel].getBrick()) {
                if (b.isHit(ball) && b.isExist()) {
                    score += Settings.SCORE;
                    destroyedBrick += 1;
                    // create bonus
                    if (b.getBonus() != "null" && canReleaseBonus) {
                        bonus = new Bonus(b.getCenterX(), b.getCenterY(), b.getBonus());
                        canReleaseBonus = false;
                    }
                    b.destroy();
                    int distanceX = Math.abs(b.getCenterX() - ball.getCenterX());
                    int distanceY = Math.abs(b.getCenterY() - ball.getCenterY());
                    if ((Settings.BALL_BRICK_X - distanceX) > (Settings.BALL_BRICK_Y - distanceY)) {
                        ball.bounceV();
                        paddle.setCanHit(true);
                        break;
                    }
                    if ((Settings.BALL_BRICK_X - distanceX) < (Settings.BALL_BRICK_Y - distanceY)) {
                        ball.bounceH();
                        paddle.setCanHit(true);
                        break;
                    }
                    if ((Settings.BALL_BRICK_X - distanceX) == (Settings.BALL_BRICK_Y - distanceY)){
                        ball.bounceV();
                        ball.bounceH();
                        paddle.setCanHit(true);
                        break;
                    }
                }
            }
        }
    }

    private void moveBonus(){
        if (!allBallOut()) {
            if (bonus.isOut()) {
                bonus.destroy();
                canReleaseBonus = true;
            } else if (bonus.isExist()){
                bonus.move();
            }
            if (paddle.isHit(bonus) && bonus.isExist()) {
                score += 10;
                if (bonus.getBonusKind() == "Multi-ball") {
                    ballList.add(new Ball(ballList.get(0).getX(), ballList.get(0).getY(),
                            Settings.BALL_DIAMETER, Settings.BALL_DIAMETER));
                    ballList.add(new Ball(ballList.get(0).getX(), ballList.get(0).getY(),
                            Settings.BALL_DIAMETER, Settings.BALL_DIAMETER));
                    ballList.get(1).setAngle(ballList.get(0).getAngle() + 30 * new Random().nextDouble());
                    ballList.get(1).setSpeed(ballList.get(0).getSpeed());
                    ballList.get(2).setAngle(ballList.get(1).getAngle() + 30 * new Random().nextDouble());
                    ballList.get(2).setSpeed(ballList.get(0).getSpeed());
                    paddle.setSpeed(3);
                } else if (bonus.getBonusKind() == "Wide-Paddle") {
                    paddle.setSuperState();
                    bonus.setBegin();
                } else if (bonus.getBonusKind() == "Sticky-Paddle") {
                    paddle.setSticky();
                } else if (bonus.getBonusKind() == "Laser") {
                    laser = new Laser(paddle.getX() + (paddle.getWidth() - Settings.LASER_WIDTH) / 2,
                            paddle.getY() - Settings.LASER_HEIGHT, Settings.LASER_WIDTH, Settings.LASER_HEIGHT);
                    bonus.setBegin();
                }
                bonus.destroy();
            }
        }
    }

    private void hitPaddle(Ball ball){
        if (!allBallOut()) {
            if (paddle.isHit(ball) && paddle.getCanHit()) {
                if (paddle.isSticky()) {
                    ball.setY(paddle.getY() - 2 * Settings.BALL_RADIUS);
                    ball.setSpeed(0.0);
                    ball.setAngle(0.0);
                    launch = false;
                    sticky = true;
                    closeBonus();
                }
                int leftSide = paddle.getX() + paddle.getSideX();
                int rightSide= paddle.getX() + paddle.getSideX() * 2;
                if (ball.getCenterX() < leftSide) {
                    if (ball.getAngle() <= 60.0) {
                        ball.setAngle(ball.getAngle() + RandomDoubleCreator.randomDouble(10.0, 30.0));
                    } else if (ball.getAngle() > 60.0 && ball.getAngle() <= 70.0) {
                        ball.setAngle(ball.getAngle() + RandomDoubleCreator.randomDouble(10.0, 90.0 - ball.getAngle()));
                    } else if (ball.getAngle() > 70.0 && ball.getAngle() < 90.0) {
                        ball.setAngle(90.0);
                    } else if (ball.getAngle() >= 90.0 && ball.getAngle() <= 100.0) {
                        ball.setAngle(ball.getAngle() + RandomDoubleCreator.randomDouble(20.0, 30.0));
                    } else if (ball.getAngle() > 100 && ball.getAngle() <= 150.0) {
                        ball.setAngle(ball.getAngle() + RandomDoubleCreator.randomDouble(10.0, 30.0));
                    } else if (ball.getAngle() > 150.0 && ball.getAngle() <= 170.0) {
                        ball.setAngle(ball.getAngle() + RandomDoubleCreator.randomDouble(10.0, 180.0 - ball.getAngle()));
                    } else if (ball.getAngle() > 170.0 && ball.getAngle() < 180.0) {
                        ball.setAngle(ball.getAngle() + RandomDoubleCreator.randomDouble(1.0, 180.0 - ball.getAngle()));
                    }
                    ball.bounceV();
                } else if (ball.getCenterX() > rightSide) {
                    if (ball.getAngle() >= 120.0) {
                        ball.setAngle(ball.getAngle() - RandomDoubleCreator.randomDouble(10.0, 30.0));
                    } else if (ball.getAngle() >= 110.0 && ball.getAngle() < 120.0) {
                        ball.setAngle(ball.getAngle() - RandomDoubleCreator.randomDouble(10.0, ball.getAngle() - 90.0));
                    } else if (ball.getAngle() > 90.0 && ball.getAngle() < 110.0) {
                        ball.setAngle(90.0);
                    } else if (ball.getAngle() >= 80.0 && ball.getAngle() <= 90.0) {
                        ball.setAngle(ball.getAngle() - RandomDoubleCreator.randomDouble(20.0, 30.0));
                    } else if (ball.getAngle() >= 30.0 && ball.getAngle() < 80.0) {
                        ball.setAngle(ball.getAngle() - RandomDoubleCreator.randomDouble(10.0, 30.0));
                    } else if (ball.getAngle() >= 10.0 && ball.getAngle() < 30.0) {
                        ball.setAngle(ball.getAngle() - RandomDoubleCreator.randomDouble(10.0, ball.getAngle()));
                    } else if (ball.getAngle() > 0.0 && ball.getAngle() < 10.0) {
                        ball.setAngle(ball.getAngle() - RandomDoubleCreator.randomDouble(0.0, ball.getAngle()));
                    }
                    ball.bounceV();
                } else if (ball.getCenterX() >= leftSide && ball.getCenterX() <= rightSide) {
                    ball.bounceV();
                }
                if (ballList.size() == 1) {
                    paddle.setCanHit(false);
                }
            }
        }
    }

    private void boundBounce(Ball ball){
        if (!allBallOut()) {
            int distanceLeft = ball.getCenterX();
            int distanceRight = Settings.GAME_WIDTH - ball.getCenterX();
            int distanceTop = ball.getCenterY() - Settings.SCREEN_HEIGHT + Settings.GAME_HEIGHT;
            int safeDistanceSide = ball.getIncreaseX() + Settings.BALL_RADIUS;
            int safeDistanceTop = ball.getIncreaseY() + Settings.BALL_RADIUS;
            if (distanceLeft < safeDistanceSide || distanceRight < safeDistanceSide) {
                if (distanceTop < safeDistanceTop) {
                    ball.bounceV();
                }
                ball.bounceH();
                paddle.setCanHit(true);
            }
            if (distanceTop < safeDistanceTop) {
                if (distanceLeft > safeDistanceSide && distanceRight > safeDistanceSide) {
                    ball.bounceV();
                }
                paddle.setCanHit(true);
            }
        }
    }

    private void fireLaser(){
        if (!allBallOut()) {
            if (keyListener.isLaunch() && laser.isExist()) {
                int destroyLine = laser.getX() + Settings.LASER_WIDTH / 2;
                for (Brick b : levels[currentLevel].getBrick()) {
                    if (Math.abs(destroyLine - b.getCenterX()) < Settings.BRICK_WIDTH / 2) {
                        b.destroy();
                    }
                }
                laser.destroy();
            }
        }
    }

    private void closeBonus(){
        bonusExistCount = 0;
        if (bonus != null) {
            bonus.setClose();
        }
        paddle.setNormalState();
        paddle.setClear();
        if (laser != null) {
            laser.destroy();
        }
        canReleaseBonus = true;
    }

    private void reset(){
        ballList = new ArrayList<>();
        ballList.add(new Ball(Settings.BALL_ORIGIN_X, Settings.BALL_ORIGIN_Y, Settings.BALL_DIAMETER, Settings.BALL_DIAMETER));
        paddle = new Paddle(Settings.PADDLE_ORIGIN_X, keyListener);
        if (bonus != null) {
            bonus.destroy();
        }
        closeBonus();
        launch = false;
    }

    public List<Brick> getBrickList(){
        return levels[currentLevel].getBrick();
    }

    public List<Ball> getBallList(){
        return ballList;
    }

    public int getBallX(Ball ball){
        return ball.getX();
    }

    public int getBallY(Ball ball){
        return ball.getY();
    }

    public Rectangle getPaddle(){
        return paddle.getHitBox();
    }

    public Bonus getBonus(){
        return bonus;
    }

    public Laser getLaser(){
        return laser;
    }

    public boolean isPaused(){
        return pause;
    }
}

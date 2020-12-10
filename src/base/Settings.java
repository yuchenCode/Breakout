package base;

import java.awt.*;

public class Settings {

    public static final int SCREEN_WIDTH = 614;
    public static final int SCREEN_HEIGHT = 500;
    public static final int GAME_WIDTH = 600;
    public static final int GAME_HEIGHT = 450;
    public static final Rectangle GAME_BOUND = new Rectangle(0, 0, GAME_WIDTH, GAME_HEIGHT);
    public static final int SCORE = 2;
    public static final int FINAL_LEVEL = 3;
    public static final int TARGET_FPS = 1000;
    public static final double GAME_HERTZ = 30.0;
    public static final double TARGET_TIME_BETWEEN_RENDERS = 1000000000 / TARGET_FPS;
    public static final double TIME_BETWEEN_UPDATES = 1000000000 / GAME_HERTZ;
    public static final int MAX_UPDATES_BEFORE_RENDER = 5;
    public static final int MAX_BONUS_EXIST_TIME = 500;
    public static final int BRICK_WIDTH = 40;
    public static final int BRICK_HEIGHT = 20;
    public static final int BALL_ORIGIN_X = 295;
    public static final int BALL_ORIGIN_Y = 390;
    public static final int BALL_DIAMETER = 10;
    public static final double BALL_LAUNCH_SPEED = 3.0;
    public static final double BALL_LAUNCH_ANGLE = 270.0;
    public static final double BALL_SPEED_INCREASE = 0.001;
    public static final int PADDLE_ORIGIN_X = 240;
    public static final int PADDLE_Y = 400;
    public static final int PADDLE_NORMAL_WIDTH = 120;
    public static final int PADDLE_SUPER_WIDTH = 240;
    public static final int PADDLE_HEIGHT = 20;
    public static final int PADDLE_SPEED = 5;
    public static final double BONUS_SPEED = 3.0;
    public static final double BONUS_RELEASE_ANGLE = 90.0;
    public static final int LASER_WIDTH = 20;
    public static final int LASER_HEIGHT = 40;

    public static final int SCREEN_GAME_DISTANCE = SCREEN_HEIGHT - GAME_HEIGHT;
    public static final int BALL_RADIUS = BALL_DIAMETER / 2;
    public static final int BALL_BRICK_X = BRICK_WIDTH / 2 + BALL_RADIUS;
    public static final int BALL_BRICK_Y = BRICK_HEIGHT / 2 + BALL_RADIUS;
    public static final int DEADLINE_Y = PADDLE_Y + PADDLE_HEIGHT;




}

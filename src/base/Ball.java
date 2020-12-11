package base;

import java.awt.*;

public class Ball implements Movable{

    private int x;
    private int y;
    private int width;
    private int height;
    private Rectangle hitBox;
    private double speed;
    private double angle;
    private boolean out;

    public Ball(int x, int y, int width, int height){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        hitBox = new Rectangle(x, y, width, height);
        speed = 0.0;
        angle = 0.0;
        out = false;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public void setX(int x){
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getCenterX(){
        return x + Settings.BALL_DIAMETER/2;
    }

    public int getCenterY(){
        return y + Settings.BALL_DIAMETER/2;
    }

    public Rectangle getHitBox(){
        return hitBox;
    }

    public void setHitBox(){
        hitBox = new Rectangle(x, y, width, height);
    }

    public double getAngle(){
        return angle;
    }

    public double getSpeed(){
        return speed;
    }

    public int getIncreaseX(){
        return Math.abs((int) speed * (int) Math.cos(Math.toRadians(angle)));
    }

    public int getIncreaseY(){
        return Math.abs((int) speed * (int) Math.sin(Math.toRadians(angle)));
    }

    public void move(){
        x += (int) (speed * Math.cos(Math.toRadians(angle)));
        if ((int) (speed * Math.sin(Math.toRadians(angle))) == 0){
            y -= 1;
        } else {
            y += (int) (speed * Math.sin(Math.toRadians(angle)));
        }
        hitBox.setLocation(x, y);
        speed += Settings.BALL_SPEED_INCREASE;
        if (y > Settings.DEADLINE_Y){
            out = true;
        }
    }

    public void setAngle(double a){
        angle = a;
    }

    public void setSpeed(double s){
        speed = s;
    }

    public void bounceH(){
        if (angle < 180) {
            angle = 180 - angle;
        } else {
            angle = 540 - angle;
        }

    }

    public void bounceV(){
        angle = 360 - angle;
    }

    public void launch(){
        speed = Settings.BALL_LAUNCH_SPEED;
        angle = Settings.BALL_LAUNCH_ANGLE;
    }

    public boolean isOut(){
        return out;
    }

}

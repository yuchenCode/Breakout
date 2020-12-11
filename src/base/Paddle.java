package base;

import ui.Listener;

import java.awt.*;

public class Paddle implements Hittable, Movable {

    private int x;
    private int y;
    private int width;
    private int height;
    private Rectangle hitBox;
    private int speed;
    private Listener pListener;
    private boolean canHit;
    private boolean normalState;
    private boolean sticky;

    public Paddle(int x, Listener pl) {
        this.x = x;
        this.y = Settings.PADDLE_Y;
        this.width = Settings.PADDLE_NORMAL_WIDTH;
        this.height = Settings.PADDLE_HEIGHT;
        hitBox = new Rectangle(x, y, width, Settings.PADDLE_HEIGHT);
        speed = Settings.PADDLE_SPEED;
        pListener = pl;
        canHit = true;
        normalState = true;
        sticky = false;
    }

    public int getX() {
        return x;
    }

    public int getY(){
        return y;
    }

    public int getWidth(){
        return width;
    }

    public int getSideX() {
        return width / 3;
    }

    public Rectangle getHitBox() {
        return hitBox;
    }

    public void setSpeed(int s){
        speed = s;
    }

    public boolean getCanHit() {
        return canHit;
    }

    public boolean isHit(Ball b) {
        Rectangle r = b.getHitBox();
        return hitBox.intersects(r);
    }

    public void move() {
        if (pListener.isLeft() && x > 0) {
            x -= speed;
            hitBox.setLocation(x, y);
        }
        if (pListener.isRight() && (x + width) < Settings.GAME_WIDTH) {
            x += speed;
            hitBox.setLocation(x, y);
        }
    }

    public void setCanHit(boolean c) {
        canHit = c;
    }

    public void setNormalState(){
        if (!normalState) {
            normalState = true;
            x = x + (Settings.PADDLE_SUPER_WIDTH - Settings.PADDLE_NORMAL_WIDTH) / 2;
            width = Settings.PADDLE_NORMAL_WIDTH;
            hitBox = new Rectangle(x, y, width, height);
        }
    }

    public void setSuperState(){
        if (normalState) {
            normalState = false;
            if (x >= (Settings.PADDLE_SUPER_WIDTH - Settings.PADDLE_NORMAL_WIDTH) / 2 &&
                    x <= Settings.GAME_WIDTH - (Settings.PADDLE_NORMAL_WIDTH + Settings.PADDLE_SUPER_WIDTH) / 2) {
                x = x - (Settings.PADDLE_SUPER_WIDTH - Settings.PADDLE_NORMAL_WIDTH) / 2;
            } else if (x < (Settings.PADDLE_SUPER_WIDTH - Settings.PADDLE_NORMAL_WIDTH) / 2) {
                x = 0;
            } else if (x > Settings.GAME_WIDTH - (Settings.PADDLE_NORMAL_WIDTH + Settings.PADDLE_SUPER_WIDTH) / 2) {
                x = Settings.GAME_WIDTH -Settings.PADDLE_SUPER_WIDTH;
            }
            width = Settings.PADDLE_SUPER_WIDTH;
            hitBox = new Rectangle(x, y, width, height);
        }
    }

    public boolean isSticky(){
        return sticky;
    }

    public void setSticky(){
        sticky = true;
    }

    public void setClear(){
        sticky = false;
    }
}

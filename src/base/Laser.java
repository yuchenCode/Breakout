package base;

import java.awt.*;

public class Laser extends Ball{

    private int x;
    private int y;
    private int laserWidth;
    private int laserHeight;
    private Rectangle hitBox;
    private boolean exist;

    public Laser(int x, int y, int width, int height) {
        super(x, y, width, height);
//        this.x = x;
//        this.y = y;
//        laserWidth = Settings.LASER_WIDTH;
//        laserHeight = Settings.LASER_HEIGHT;
//        hitBox = new Rectangle(x, y, laserWidth, laserHeight);
        exist = true;
    }

//    public int getX(){
//        return x;
//    }
//
//    public int getY(){
//        return y;
//    }
//
//    public void setX(int x){
//        this.x = x;
//    }
//
//    public void setY(int y){
//        this.y = y;
//    }
//
//    public Rectangle getHitBox(){
//        return hitBox;
//    }
//
//    public void setHitBox(){
//        hitBox = new Rectangle(x, y, laserWidth, laserHeight);
//    }

    public boolean isExist(){
        return exist;
    }

    public void destroy(){
        exist = false;
    }

}

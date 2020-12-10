package base;

import java.awt.*;
import java.util.*;
import java.util.List;

public class Brick implements Hittable{

    private int x;
    private int y;
    private Rectangle hitBox;
    private Color color;
    private boolean exist;
    private String bonusKind;

    public Brick(int x, int y, Color color){
        this.x = x;
        this.y = y;
        hitBox = new Rectangle(x, y, Settings.BRICK_WIDTH, Settings.BRICK_HEIGHT);
        this.color = color;
        exist = true;
//        int bonusNumber = new Random().nextInt(4);
//        if (bonusNumber == 0) {
//            bonusKind = "Multi-ball";
//        } else if (bonusNumber == 1) {
//            bonusKind = "Wide-Paddle";
//        } else if (bonusNumber == 2) {
//            bonusKind = "Sticky-Paddle";
//        } else if (bonusNumber == 3) {
//            bonusKind = "Laser";
//        } else {
//            bonusKind = "null";
//        }
        bonusKind = "Laser";
    }

    public int getCenterX(){
        return x + Settings.BRICK_WIDTH/2;
    }

    public int getCenterY(){
        return y + Settings.BRICK_HEIGHT/2;
    }

    public Rectangle getHitBox(){
        return hitBox;
    }

    public Color getBrickColor(){
        return color;
    }

    public boolean isHit(Ball b){
        Rectangle r = b.getHitBox();
        return hitBox.intersects(r);
    }

    public void destroy(){
        exist = false;
    }

    public boolean isExist(){
        return exist;
    }

    public String getBonus(){
        return bonusKind;
    }
}

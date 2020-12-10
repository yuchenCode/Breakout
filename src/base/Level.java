package base;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Level {

    private int totalBrick;
    private List<Brick> brickList;
    private List<Color> brickColorList;

    public Level(int shape){
        if (shape == 1) {
            totalBrick = 11;
            brickList = setLevel1();
        } else if (shape == 2) {
            totalBrick = 36;
            brickList = setLevel2();
        } else if (shape == 3) {
            totalBrick = 41;
            brickList = setLevel3();
        } else if (shape == 4) {
            totalBrick = 64;
            brickList = setLevel4();
        } else if (shape == 5) {
            totalBrick = 98;
            brickList = setLevel5();
        }
        brickColorList = setBrickColor(brickList);
    }

    private List<Brick> setLevel1(){
        List<Brick> bl = new ArrayList<Brick>();
        for (int i=0; i<6; i++) {
            Color color = new Color(new Random().nextInt(255), new Random().nextInt(255), new Random().nextInt(255));
            if (i != 6 - 1) {
                bl.add(new Brick((i + 2) * Settings.BRICK_WIDTH,(i + 2) * Settings.BRICK_HEIGHT + Settings.SCREEN_GAME_DISTANCE , color));
                bl.add(new Brick((12 - i) * Settings.BRICK_WIDTH, (i + 2) * Settings.BRICK_HEIGHT + Settings.SCREEN_GAME_DISTANCE , color));
            } else {
                bl.add(new Brick((i + 2) * Settings.BRICK_WIDTH,(i + 2) * Settings.BRICK_HEIGHT + Settings.SCREEN_GAME_DISTANCE , color));
            }
        }
        return bl;
    }

    private List<Brick> setLevel2(){
        List<Brick> bl = new ArrayList<Brick>();
        for (int i=0; i<6; i++) {
            Color color = new Color(new Random().nextInt(255), new Random().nextInt(255), new Random().nextInt(255));
                for (int j=0; j<2*i+1; j++) {
                    bl.add(new Brick(j * Settings.BRICK_WIDTH,(i + 2) * Settings.BRICK_HEIGHT + Settings.SCREEN_GAME_DISTANCE , color));
                }
        }
        return bl;
    }

    private List<Brick> setLevel3(){
        List<Brick> bl = new ArrayList<Brick>();
        for (int i=0; i<9; i++){
            Color color = new Color(new Random().nextInt(255), new Random().nextInt(255), new Random().nextInt(255));
            if (i < 5) {
                for (int j=0; j<2*i+1; j++) {
                    bl.add(new Brick((j - i + 7) * Settings.BRICK_WIDTH, (i + 2) * Settings.BRICK_HEIGHT + Settings.SCREEN_GAME_DISTANCE, color));
                }
            } else {
                for (int j=0; j<17-2*i; j++) {
                    bl.add(new Brick((i + j - 1) * Settings.BRICK_WIDTH, (i + 2) * Settings.BRICK_HEIGHT + Settings.SCREEN_GAME_DISTANCE, color));
                }
            }
        }
        return bl;
    }

    private List<Brick> setLevel4(){
        List<Brick> bl = new ArrayList<Brick>();
        for (int i=0; i<9; i++){
            Color color = new Color(new Random().nextInt(255), new Random().nextInt(255), new Random().nextInt(255));
            if (i < 5) {
                for (int j=0; j<7-2*i; j++) {
                    bl.add(new Brick(j * Settings.BRICK_WIDTH, i * Settings.BRICK_HEIGHT + Settings.SCREEN_GAME_DISTANCE, color));
                    bl.add(new Brick((2 * i + j + 8) * Settings.BRICK_WIDTH, i * Settings.BRICK_HEIGHT + Settings.SCREEN_GAME_DISTANCE, color));
                }
            } else {
                for (int j=0; j<2*i-9; j++) {
                    bl.add(new Brick(j * Settings.BRICK_WIDTH, i * Settings.BRICK_HEIGHT + Settings.SCREEN_GAME_DISTANCE, color));
                    bl.add(new Brick((j - 2 * i + 24) * Settings.BRICK_WIDTH, i * Settings.BRICK_HEIGHT + Settings.SCREEN_GAME_DISTANCE, color));
                }
            }
        }
        return bl;
    }

    private List<Brick> setLevel5(){
        List<Brick> bl = new ArrayList<Brick>();
        for (int i=0; i<11; i++) {
            Color color = new Color(new Random().nextInt(255), new Random().nextInt(255), new Random().nextInt(255));
            if (i == 0 || i == 10) {
                for (int j=0; j<15; j++) {
                    bl.add(new Brick(j * Settings.BRICK_WIDTH, i * Settings.BRICK_HEIGHT + Settings.SCREEN_GAME_DISTANCE, color));
                }
            } else if (i % 2 == 1) {
                for (int j=0; j<8; j++) {
                    bl.add(new Brick(2 * j * Settings.BRICK_WIDTH, i * Settings.BRICK_HEIGHT + Settings.SCREEN_GAME_DISTANCE, color));
                }
            } else if (i % 2 == 0) {
                for (int j=0; j<7; j++) {
                    bl.add(new Brick((2 * j + 1) * Settings.BRICK_WIDTH, i * Settings.BRICK_HEIGHT + Settings.SCREEN_GAME_DISTANCE, color));
                }
            }
        }
        return bl;
    }

    public List<Brick> getBrick(){
        return brickList;
    }

    private List<Color> setBrickColor(List<Brick> bl){
        List<Color> cl = new ArrayList<Color>();
        for (int i=0; i<bl.size(); i++){
            cl.add(bl.get(i).getBrickColor());
        }
        return cl;
    }

    public List<Color> getBrickColor(){
        return brickColorList;
    }

    public int getTotalBrick(){
        return totalBrick;
    }
}

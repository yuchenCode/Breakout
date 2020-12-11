package base;

import java.awt.*;

public class Laser extends Ball{

    private boolean exist;

    public Laser(int x, int y, int width, int height) {
        super(x, y, width, height);
        exist = true;
    }

    public boolean isExist(){
        return exist;
    }

    public void destroy(){
        exist = false;
    }

}

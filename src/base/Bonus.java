package base;

public class Bonus extends Ball{

    private boolean exist;
    private boolean begin;
    private String bonusKind;

    public Bonus(int x, int y, String b) {
        super(x, y, Settings.BALL_DIAMETER, Settings.BALL_DIAMETER);
        exist = true;
        begin = false;
        bonusKind = b;
        release();
    }

    public void release(){
        setSpeed(Settings.BONUS_SPEED);
        setAngle(Settings.BONUS_RELEASE_ANGLE);
    }

    public String getBonusKind(){
        return bonusKind;
    }

    public boolean isExist(){
        return exist;
    }

    public void destroy(){
        exist = false;
    }

    public boolean isBegin(){
        return begin;
    }

    public void setBegin(){
        begin = true;
    }

    public void setClose(){
        begin = false;
    }
}

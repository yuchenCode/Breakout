package ui;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Listener implements KeyListener {

    private boolean left;
    private boolean right;
    private boolean about;
    private boolean start;
    private boolean launch;
    private boolean pause;
    private boolean high;
    private boolean menu;
    private boolean exit;

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            left = true;
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            right = true;
        } else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            launch = true;
        }
    }

    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            left = false;
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            right = false;
        } else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            launch = false;
        }
    }

    public void keyTyped(KeyEvent e) {
        if (e.getExtendedKeyCode() == KeyEvent.VK_A || e.getKeyChar() == 'A'  || e.getKeyChar() == 'a') {
            about = true;
        } else if (e.getExtendedKeyCode() == KeyEvent.VK_S || e.getKeyChar() == 'S'  || e.getKeyChar() == 's') {
            start = true;
//        } else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
//            launch = true;
//            System.out.print("z");
        } else if (e.getExtendedKeyCode() == KeyEvent.VK_P || e.getKeyChar() == 'P'  || e.getKeyChar() == 'p') {
            pause = true;
        } else if (e.getExtendedKeyCode() == KeyEvent.VK_H || e.getKeyChar() == 'H'  || e.getKeyChar() == 'h') {
            high = true;
        } else if (e.getExtendedKeyCode() == KeyEvent.VK_M || e.getKeyChar() == 'M'  || e.getKeyChar() == 'm') {
            menu = true;
        } else if (e.getExtendedKeyCode() == KeyEvent.VK_X || e.getKeyChar() == 'X'  || e.getKeyChar() == 'x') {
            exit = true;
        }
    }

    public boolean isLeft() {
        return left;
    }

    public boolean isRight() {
        return right;
    }

    public boolean isAbout() {
        if (about) {
            about = false;
            return true;
        }
        return false;
    }

    public boolean isStart() {
        if (start) {
            start = false;
            return true;
        }
        return false;
    }

    public boolean isLaunch() {
        return launch;
    }

    public boolean isPause() {
        if (pause) {
            pause = false;
            return true;
        }
        return false;
    }

    public boolean isHigh() {
        if (high) {
            high = false;
            return true;
        }
        return false;
    }

    public boolean isMenu() {
        if (menu) {
            menu = false;
            return true;
        }
        return false;
    }

    public boolean isExit() {
        if (exit) {
            exit = false;
            return true;
        }
        return false;
    }

    public void reset() {
        left = false;
        right = false;
        launch = false;
        start = false;
        high = false;
        exit = false;
        menu = false;
    }
}


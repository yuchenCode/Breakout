package ui;

import javax.swing.*;

import base.Settings;

public class GUI{
    public static void main(String[] args) {

        // set window
        JFrame window = new JFrame();
        window.setSize(Settings.SCREEN_WIDTH,Settings.SCREEN_HEIGHT);
        window.setResizable(false);
        window.setLocationRelativeTo(null);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setTitle("Breakout");
        Listener keyListener = new Listener();
        window.addKeyListener(keyListener);
        Menu menu = new Menu(window, keyListener);
        window.setVisible(true);
        window.requestFocusInWindow();
        // entrance
        menu.run();
    }
}

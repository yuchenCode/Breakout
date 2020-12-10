package ui;

import javax.swing.*;
import java.awt.*;

import base.Game;
import base.ScoreRecorder;
import utils.PersistentScoreRecorder;

public class Menu {

    private JFrame window;
    private Listener keyListener;
    private CardLayout cardLayout;
    private JPanel menuWindow;
    private MainScreen mainScreen;
    private AboutScreen aboutScreen;
    private Game game;
    private GameScreen gameScreen;
    private ScoreScreen scoreScreen;
    private ScoreRecorder scoreRecorder;
    private boolean exit;

    public Menu(JFrame win, Listener lis) {
        window = win;
        keyListener = lis;
        cardLayout = new CardLayout();
        menuWindow = new JPanel(cardLayout);
        mainScreen = new MainScreen();
        aboutScreen = new AboutScreen(keyListener);
        gameScreen = new GameScreen();
        scoreRecorder = new PersistentScoreRecorder();
        scoreScreen = new ScoreScreen(scoreRecorder, keyListener);
        menuWindow.add(mainScreen, "Main Screen");
        menuWindow.add(aboutScreen, "About Screen");
        menuWindow.add(gameScreen, "Game Screen");
        menuWindow.add(scoreScreen, "Score Screen");
        window.getContentPane().add(menuWindow);
    }

    public void run() {
        new Thread() {
            public void run() {
                try {
                    Thread.sleep(Long.MAX_VALUE);
                } catch(InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
        while (!exit) {
            if (keyListener.isStart()) {
                game = new Game(gameScreen, keyListener);
                cardLayout.show(menuWindow, "Game Screen");
                game.run();
                int s = game.getScores();
                if (s > scoreRecorder.getLowestScore()) {
                    String name = JOptionPane.showInputDialog("New High Score, Please enter your name:");
                    scoreRecorder.addScore(name, s);
                    cardLayout.show(menuWindow, "Score Screen");
                    scoreScreen.run();
                    cardLayout.show(menuWindow, "Main Screen");
                    keyListener.reset();
                }
                // start the game engine
            } else if (keyListener.isHigh()) {
                cardLayout.show(menuWindow, "Score Screen");
                scoreScreen.run();
                cardLayout.show(menuWindow, "Main Screen");
            } else if (keyListener.isExit()) {
                exit = true;
                scoreRecorder.saveScores();
                System.exit(0);
            } else if (keyListener.isAbout()) {
                cardLayout.show(menuWindow, "About Screen");
                aboutScreen.run();
                cardLayout.show(menuWindow, "Main Screen");
            }
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        exit = false;
    }
}

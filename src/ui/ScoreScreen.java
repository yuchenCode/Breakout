package ui;

import base.ScoreRecorder;
import base.ScoreRecorder.Score;
import base.Settings;

import javax.swing.*;
import java.awt.*;

public class ScoreScreen extends JPanel {

    private ScoreRecorder scoreRecorder;
    private boolean menu = false;
    private Listener keyListener;

    public ScoreScreen(ScoreRecorder sr, Listener lis) {
        this.scoreRecorder = sr;
        this.keyListener = lis;
    }

    public void paintComponent(Graphics g) {

        // paint high scores
        g.setColor(Color.GRAY);
        g.fillRect(0, 0, Settings.SCREEN_WIDTH, Settings.SCREEN_HEIGHT);
        drawString(g, "Breakout Hall of Fame", new Rectangle(0, 0,
                        Settings.GAME_WIDTH, Settings.SCREEN_HEIGHT / 8), 36);
        g.setColor(Color.GREEN);
        Score[] scores = scoreRecorder.getScores();
        g.setFont(new Font("Consolas", Font.BOLD, 24));
        for (int i = 0; i < scores.length; i++) {
            Score score = scores[i];
            g.drawString(score.getName(), 2 * Settings.GAME_WIDTH / 6, 96 + i * 32);
            g.drawString("" + score.getScore(), 4 * Settings.GAME_WIDTH / 6, 96 + i * 32);
        }
        drawString(g, "Press 'M' to return to the Main Menu", new Rectangle(0, 380, Settings.GAME_WIDTH, 96), 24);
    }

    private void drawString(Graphics g, String text, Rectangle rect, int size) {
        Graphics2D g2d = (Graphics2D) g.create();

        Font font = new Font("Consolas", Font.BOLD, size);
        g2d.setFont(font);
        FontMetrics metrics = g2d.getFontMetrics();
        int x = rect.x + (rect.width - metrics.stringWidth(text)) / 2;
        int y = rect.y + ((rect.height - metrics.getHeight()) / 2) + metrics.getAscent();

        g2d.setColor(Color.GREEN);
        g2d.drawString(text, x, y);
    }

    public void run() {
        while (!menu) {
            if (keyListener.isMenu()) {
                menu = true;
            }
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
            }
        }
        keyListener.reset();
        menu = false;
    }
}

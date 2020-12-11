package ui;

import base.Settings;

import javax.swing.*;
import java.awt.*;

public class AboutScreen extends JPanel {

    private boolean menu = false;
    private Listener keyListener;

    public AboutScreen(Listener lis) {
        keyListener = lis;
    }

    public void paintComponent(Graphics g) {

        // paint control information
        g.setColor(Color.GRAY);
        g.fillRect(0, 0, Settings.SCREEN_WIDTH, Settings.SCREEN_HEIGHT);
        g.setColor(Color.GREEN);
        drawString(g, "Breakout Controls", new Rectangle(0, 0, Settings.GAME_WIDTH, 64), 36);
        drawString(g, "Move Left:", new Rectangle(Settings.GAME_WIDTH / 20, 80, Settings.GAME_WIDTH / 4, 32), 20);
        drawString(g, "left arrow", new Rectangle(Settings.GAME_WIDTH * 3 / 10, 80, Settings.GAME_WIDTH / 4, 32), 20);
        drawString(g, "Move Right:", new Rectangle(Settings.GAME_WIDTH / 20, 130, Settings.GAME_WIDTH / 4, 32), 20);
        drawString(g, "right arrow", new Rectangle(Settings.GAME_WIDTH * 3 / 10, 130, Settings.GAME_WIDTH / 4, 32), 20);
        drawString(g, "Launch:", new Rectangle(Settings.GAME_WIDTH / 20, 180, Settings.GAME_WIDTH / 4, 32), 20);
        drawString(g, "space bar", new Rectangle(Settings.GAME_WIDTH * 3 / 10, 180, Settings.GAME_WIDTH / 4, 32), 20);
        drawString(g, "Play/Pause:", new Rectangle(Settings.GAME_WIDTH / 20, 230, Settings.GAME_WIDTH / 4, 32), 20);
        drawString(g, "p button", new Rectangle(Settings.GAME_WIDTH * 3 / 10, 230, Settings.GAME_WIDTH / 4, 32), 20);
        drawString(g, "Bonus View:", new Rectangle(Settings.GAME_WIDTH * 3 / 5, 80, Settings.GAME_WIDTH / 4, 32), 20);
        drawString(g, "Multi-ball:", new Rectangle(Settings.GAME_WIDTH * 3 / 5, 120, Settings.GAME_WIDTH / 4, 32), 20);
        g.setColor(Color.RED);
        g.fillRect(Settings.GAME_WIDTH * 7 / 8, 130, Settings.BALL_DIAMETER, Settings.BALL_DIAMETER);
        drawString(g, "Wide-Paddle:", new Rectangle(Settings.GAME_WIDTH * 3 / 5, 160, Settings.GAME_WIDTH / 4, 32), 20);
        g.setColor(Color.BLUE);
        g.fillRect(Settings.GAME_WIDTH * 7 / 8, 170, Settings.BALL_DIAMETER, Settings.BALL_DIAMETER);
        drawString(g, "Sticky-Paddle:", new Rectangle(Settings.GAME_WIDTH * 3 / 5, 200, Settings.GAME_WIDTH / 4, 32), 20);
        g.setColor(Color.PINK);
        g.fillRect(Settings.GAME_WIDTH * 7 / 8, 210, Settings.BALL_DIAMETER, Settings.BALL_DIAMETER);
        drawString(g, "Laser:", new Rectangle(Settings.GAME_WIDTH * 3 / 5, 240, Settings.GAME_WIDTH / 4, 32), 20);
        g.setColor(Color.YELLOW);
        g.fillRect(Settings.GAME_WIDTH * 7 / 8, 250, Settings.BALL_DIAMETER, Settings.BALL_DIAMETER);
        drawString(g, "Press 'M' to return to the Main Menu", new Rectangle(0, 300, Settings.SCREEN_WIDTH, 96), 24);
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

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
        g.setColor(Color.GRAY);
        g.fillRect(0, 0, Settings.SCREEN_WIDTH, Settings.SCREEN_HEIGHT);
        g.setColor(Color.GREEN);
        drawString(g, "Breakout Controls", new Rectangle(0, 0, Settings.SCREEN_WIDTH, 64), 36);
        g.drawString("Move Left", 1 * Settings.SCREEN_WIDTH / 6, 96 + 0 * 32);
        g.drawString("left arrow", 4 * Settings.SCREEN_WIDTH / 6, 96 + 0 * 32);
        g.drawString("Move Right", 1 * Settings.SCREEN_WIDTH / 6, 96 + 1 * 32);
        g.drawString("right arrow", 4 * Settings.SCREEN_WIDTH / 6, 96 + 1 * 32);
        g.drawString("Launch", 1 * Settings.SCREEN_WIDTH / 6, 96 + 2 * 32);
        g.drawString("space bar", 4 * Settings.SCREEN_WIDTH / 6, 96 + 2 * 32);
        g.drawString("Play/Pause", 1 * Settings.SCREEN_WIDTH / 6, 96 + 3 * 32);
        g.drawString("p button", 4 * Settings.SCREEN_WIDTH / 6, 96 + 3 * 32);
        drawString(g, "Press 'M' to return to the Main Menu", new Rectangle(0, 416, Settings.SCREEN_WIDTH, 96), 24);
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

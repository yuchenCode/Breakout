package ui;

import base.Settings;

import javax.swing.*;
import java.awt.*;

public class MainScreen extends JPanel {

    public void paintComponent(Graphics g) {
        g.setColor(Color.GRAY);
        g.fillRect(0, 0, Settings.SCREEN_WIDTH, Settings.SCREEN_HEIGHT);
        drawString(g, "Welcome to Breakout!!!!", new Rectangle(Settings.SCREEN_WIDTH / 3, Settings.SCREEN_HEIGHT / 10, Settings.SCREEN_WIDTH / 3, Settings.SCREEN_HEIGHT / 3),
                24);
        drawString(g, "To play a game press S", new Rectangle(Settings.SCREEN_WIDTH / 3, 2 * Settings.SCREEN_HEIGHT / 8, Settings.SCREEN_WIDTH / 3, Settings.SCREEN_HEIGHT / 3),
                18);
        drawString(g, "To see the High scores press H",
                new Rectangle(Settings.SCREEN_WIDTH / 3, 3 * Settings.SCREEN_HEIGHT / 8, Settings.SCREEN_WIDTH / 3, Settings.SCREEN_HEIGHT / 3), 18);
        drawString(g, "For help press A", new Rectangle(Settings.SCREEN_WIDTH / 3, 4 * Settings.SCREEN_HEIGHT / 8, Settings.SCREEN_WIDTH / 3, Settings.SCREEN_HEIGHT / 3), 18);
        drawString(g, "To exit press X", new Rectangle(Settings.SCREEN_WIDTH / 3, 5 * Settings.SCREEN_HEIGHT / 8, Settings.SCREEN_WIDTH / 3, Settings.SCREEN_HEIGHT / 3), 18);

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
}

package ui;

import base.*;

import javax.swing.*;
import java.awt.*;

public class GameScreen extends JComponent {

    private Game game;
    private Bonus bonus;

    protected void paintComponent(Graphics g) {
        if (game != null) {
            Graphics2D g2 = (Graphics2D) g;

            // paint field of game
            g.setColor(Color.GRAY);
            g.fillRect(0, Settings.SCREEN_GAME_DISTANCE, Settings.GAME_WIDTH, Settings.GAME_HEIGHT);

            // paint information background
            g.setColor(Color.DARK_GRAY);
            g.fillRect(0, 0, Settings.SCREEN_WIDTH, Settings.SCREEN_GAME_DISTANCE);

            // paint lives, scores and bricks information
            drawString(g,"Lives: " + game.getLives(), new Rectangle(0, 0,
                    Settings.GAME_WIDTH / 3, Settings.SCREEN_GAME_DISTANCE), 20);
            drawString(g,"Scores: " + game.getScores(), new Rectangle(Settings.GAME_WIDTH / 3, 0,
                    Settings.GAME_WIDTH / 3, Settings.SCREEN_GAME_DISTANCE), 20);
            drawString(g,"Bricks: " + game.getExistBricks(), new Rectangle(Settings.GAME_WIDTH * 2 / 3, 0,
                    Settings.GAME_WIDTH / 3, Settings.SCREEN_GAME_DISTANCE), 20);

            // paint bricks
            for (Brick b : game.getBrickList()){
                if (b.isExist()){
                    g2.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
                    g2.setColor(Color.WHITE);
                    g2.draw(b.getHitBox());
                    g2.setColor(b.getBrickColor());
                    g2.fill(b.getHitBox());
                }
            }

            // paint bonuses
            if (game.getBonus() != null && game.getBonus().isExist()) {
                if (game.getBonus().getBonusKind() == "Multi-ball") {
                    g2.setColor(Color.RED);
                } else if (game.getBonus().getBonusKind() == "Wide-Paddle") {
                    g2.setColor(Color.BLUE);
                } else if (game.getBonus().getBonusKind() == "Sticky-Paddle") {
                    g2.setColor(Color.PINK);
                } else if (game.getBonus().getBonusKind() == "Laser") {
                    g2.setColor(Color.YELLOW);
                }
                g2.fill(game.getBonus().getHitBox());
            }

            // paint laser
            if (game.getLaser() != null && game.getLaser().isExist()) {
                g2.setColor(Color.YELLOW);
                g2.fill(game.getLaser().getHitBox());
            }

            // paint balls
            g2.setColor(Color.WHITE);
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            for (Ball b : game.getBallList()) {
                g2.fillOval(game.getBallX(b), game.getBallY(b), Settings.BALL_DIAMETER, Settings.BALL_DIAMETER);
            }

            // paint paddle
            g2.setColor(Color.GREEN);
            g2.fill(game.getPaddle());

            // paint tips
            if (game.isPaused() && game.getLives() > 0) {
                drawString(g, "PAUSE", new Rectangle(290, 300, 20, 10), 24);
            } else if (game.getLives() == 0) {
                drawString(g, "GAME OVER", new Rectangle(280, 300, 40, 20), 48);
            }

        }
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

    public void addGame(Game g){
        System.out.println("here");
        game = g;
    }
}

package ui;

import base.Bonus;
import base.Brick;
import base.Game;
import base.Settings;

import javax.swing.*;
import java.awt.*;

public class GameScreen extends JComponent {

    private Game game;
    private Bonus bonus;

    protected void paintComponent(Graphics g) {
        if (game != null) {
            Graphics2D g2 = (Graphics2D) g;
            g.setColor(Color.GRAY);
            g.fillRect(0, Settings.SCREEN_GAME_DISTANCE, Settings.GAME_WIDTH, Settings.GAME_HEIGHT);
            g.setColor(Color.WHITE);
            g.fillRect(0, 0, Settings.SCREEN_WIDTH, Settings.SCREEN_GAME_DISTANCE);
            //g.setColor(Color.GREEN);
            drawString(g,"Lives: " + game.getLives(),
                    new Rectangle(0, 0, Settings.SCREEN_WIDTH / 3, Settings.SCREEN_GAME_DISTANCE), 20);
            drawString(g,"Scores: " + game.getScores(),
                    new Rectangle(Settings.SCREEN_WIDTH / 3, 0, Settings.SCREEN_WIDTH / 3, Settings.SCREEN_GAME_DISTANCE), 20);
            drawString(g,"Bricks: " + game.getExistBricks(),
                    new Rectangle(Settings.SCREEN_WIDTH * 2 / 3, 0, Settings.SCREEN_WIDTH / 3, Settings.SCREEN_GAME_DISTANCE), 20);

            for (Brick b : game.getBrickList()){
                if (b.isExist()){
                    g2.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);

                    g2.setColor(Color.WHITE);
                    g2.draw(b.getHitBox());
                    g2.setColor(b.getBrickColor());
                    g2.fill(b.getHitBox());
                }
            }
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
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
            if (game.getLaser() != null && game.getLaser().isExist()) {
                g2.setColor(Color.YELLOW);
                g2.fill(game.getLaser().getHitBox());
            }
            g2.setColor(Color.WHITE);
            g2.fillOval(game.getBallX(), game.getBallY(), Settings.BALL_DIAMETER, Settings.BALL_DIAMETER);
            g2.setColor(Color.GREEN);
            g2.fill(game.getPaddle());

            if (game.isPaused() && game.getLives() > 0) {
                g.drawString("Press 'p' to continue ", 256, 256);
            } else if (game.getLives() == 0) {
                g.drawString("Game over ", 480, 256);
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

    public void addBonus(Bonus b){
        bonus = b;
    }

}

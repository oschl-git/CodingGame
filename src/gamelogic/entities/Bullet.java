package gamelogic.entities;

import gui.GamePanel;

import java.awt.*;

/**
 * This class is the bullet shot by the player.
 */
public class Bullet {
    int x;
    int y;
    Player.directions direction;
    GamePanel gamePanel;


    //region Constructors, getters, setters

    public Bullet(int x, int y, Player.directions direction, GamePanel gamePanel) {
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.gamePanel = gamePanel;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    //endregion

    public void move() {
        switch (direction) {
            case UP -> y--;
            case DOWN -> y++;
            case LEFT -> x--;
            case RIGHT -> x++;
        }
    }

    /**
     * Draws the bullet on the screen.
     */
    public void draw(Graphics g) {
        if (checkCollision()) return;

        int xPx = x * GamePanel.UNIT_SIZE;
        int yPx = y * GamePanel.UNIT_SIZE;

        Graphics2D g2D = (Graphics2D) g;
        g2D.setColor(Color.red);
        g2D.setStroke(new BasicStroke(4));

        switch (direction) {
            case UP, DOWN -> g2D.drawLine(xPx + 15, yPx + 4, xPx + 15, yPx + GamePanel.UNIT_SIZE - 4);
            default -> g2D.drawLine(xPx + 4, yPx + 15, xPx + GamePanel.UNIT_SIZE - 4, yPx + 15);
        }
    }

    /**
     * Checks if bullet collides with anything, if so, it deletes the bullet.
     *
     * @return true if colliding, false if not
     */
    public boolean checkCollision() {
        if (gamePanel.objects[y][x] == 2 || gamePanel.objects[y][x] == 1) {
            if (gamePanel.objects[y][x] == 2) gamePanel.objects[y][x] = 0;
            gamePanel.removeBullet();
            return true;
        }
        return false;
    }

    public void printCoordinate() {
        System.out.println("Bullet: " + x + " " + y);
    }
}

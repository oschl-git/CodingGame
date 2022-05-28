package gamelogic.entities;

import gui.GamePanel;

import java.awt.*;

public class Bullet {
    int x;
    int y;
    Player.directions direction;
    GamePanel gamePanel;


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

    public void move() {
        switch (direction) {
            case UP -> y--;
            case DOWN -> y++;
            case LEFT -> x--;
            case RIGHT -> x++;
        }
    }

    public void draw(Graphics g) {
        if (checkCollision()) return;

        int xPx = x * GamePanel.UNIT_SIZE;
        int yPx = y * GamePanel.UNIT_SIZE;

        Graphics2D g2D = (Graphics2D) g;
        g2D.setColor(Color.red);
        g2D.setStroke(new BasicStroke(4));

        switch (direction) {
            case UP, DOWN -> g2D.drawLine(xPx + 15, yPx + 4, xPx + 15, yPx + GamePanel.UNIT_SIZE -4);
            default -> g2D.drawLine(xPx + 4, yPx + 15, xPx + GamePanel.UNIT_SIZE - 4, yPx + 15);
        }
    }

    public boolean checkCollision() {
        if (gamePanel.objects[y][x] == 2 || gamePanel.objects[y][x] == 1) {
            if (gamePanel.objects[y][x] == 2) gamePanel.objects[y][x] = 0;
            gamePanel.removeBullet();
            return true;
        }
        return false;
    }
}

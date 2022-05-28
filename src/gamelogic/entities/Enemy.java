package gamelogic.entities;

import gui.GamePanel;

import java.awt.*;

public class Enemy {
    int x = 0;
    int y = 0;
    Player.directions direction = Player.directions.RIGHT;
    GamePanel gamePanel;
    int steppedOn = 0;
    boolean dead = false;

    public Enemy(int x, int y, GamePanel gamePanel) {
        this.x = x;
        this.y = y;
        this.gamePanel = gamePanel;
    }

    public void draw(Graphics g) {
        if (dead) return;

        int xPx = x * GamePanel.UNIT_SIZE;
        int yPx = y * GamePanel.UNIT_SIZE;

        g.setColor(Color.green);
        g.fillRect(xPx, yPx, GamePanel.getUnitSize(), GamePanel.getUnitSize());
        Graphics2D g2D = (Graphics2D) g;
        g2D.setColor(Color.red);
        g2D.setStroke(new BasicStroke(3));

        switch (direction) {
            case RIGHT -> {
                g2D.drawLine(xPx + 15, yPx + 3, xPx + 25, yPx + 6);
                g2D.drawLine(xPx + 15, yPx + 9, xPx + 25, yPx + 6);
                g2D.drawLine(xPx + 15, yPx + 27, xPx + 25, yPx + 24);
                g2D.drawLine(xPx + 15, yPx + 21, xPx + 25, yPx + 24);
            }
            case LEFT -> {
                g2D.drawLine(xPx + 15, yPx + 3, xPx + 5, yPx + 6);
                g2D.drawLine(xPx + 15, yPx + 9, xPx + 5, yPx + 6);
                g2D.drawLine(xPx + 15, yPx + 27, xPx + 5, yPx + 24);
                g2D.drawLine(xPx + 15, yPx + 21, xPx + 5, yPx + 24);
            }
            case UP -> {
                g2D.drawLine(xPx + 3, yPx + 15, xPx + 6, yPx + 6);
                g2D.drawLine(xPx + 9, yPx + 15, xPx + 6, yPx + 6);
                g2D.drawLine(xPx + 27, yPx + 15, xPx + 24, yPx + 6);
                g2D.drawLine(xPx + 21, yPx + 15, xPx + 24, yPx + 6);
            }
            case DOWN -> {
                g2D.drawLine(xPx + 3, yPx + 15, xPx + 6, yPx + 24);
                g2D.drawLine(xPx + 9, yPx + 15, xPx + 6, yPx + 24);
                g2D.drawLine(xPx + 27, yPx + 15, xPx + 24, yPx + 24);
                g2D.drawLine(xPx + 21, yPx + 15, xPx + 24, yPx + 24);
            }
        }
    }

    public void move() {
        if (dead) return;

        if (isColliding()) {
            turnRight();
            checkForBullets();
            return;
        }

        gamePanel.objects[y][x] = steppedOn;
        switch (direction) {
            case UP -> y--;
            case DOWN -> y++;
            case LEFT -> x--;
            case RIGHT -> x++;
        }
        steppedOn = gamePanel.objects[y][x];
        gamePanel.objects[y][x] = 5;
        checkForBullets();
    }

    public boolean isColliding() {
        switch (direction) {
            case UP -> {
                if (gamePanel.objects[y - 1][x] >= 1 && gamePanel.objects[y - 1][x] <= 2) return true;
            }
            case DOWN -> {
                if (gamePanel.objects[y + 1][x] >= 1 && gamePanel.objects[y + 1][x] <= 2) return true;
            }
            case LEFT -> {
                if (gamePanel.objects[y][x - 1] >= 1 && gamePanel.objects[y][x - 1] <= 2) return true;
            }
            case RIGHT -> {
                if (gamePanel.objects[y][x + 1] >= 1 && gamePanel.objects[y][x + 1] <= 2) return true;
            }
        }
        return false;
    }

    public void turnRight() {
        switch (direction) {
            case UP -> direction = Player.directions.RIGHT;
            case DOWN -> direction = Player.directions.LEFT;
            case LEFT -> direction = Player.directions.UP;
            case RIGHT -> direction = Player.directions.DOWN;
        }
    }

    public void checkForBullets() {
        if (gamePanel.getBullet() == null) return;
        if (gamePanel.getBullet().getX() == this.x && gamePanel.getBullet().getY() == this.y) {
            gamePanel.objects[y][x] = steppedOn;
            dead = true;
            gamePanel.removeBullet();
        }
    }
}



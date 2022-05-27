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

    public void moveAndDraw(Graphics g) {
        //Move
        switch (direction) {
            case UP -> y--;
            case DOWN -> y++;
            case LEFT -> x--;
            case RIGHT -> x++;
        }
        int xPx = x * GamePanel.UNIT_SIZE;
        int yPx = y * GamePanel.UNIT_SIZE;

        if (checkCollision()) return;

        //Draw
        Graphics2D g2D = (Graphics2D) g;
        g2D.setColor(Color.white);
        g2D.setStroke(new BasicStroke(4));

        switch (direction) {
            case UP, DOWN -> g2D.drawLine(xPx + 15, yPx, xPx + 15, yPx + GamePanel.UNIT_SIZE);
            default -> g2D.drawLine(xPx, yPx + 15, xPx + GamePanel.UNIT_SIZE, yPx + 15);
        }
    }

    public boolean checkCollision() {
        if (gamePanel.objects[y][x] == 2 || gamePanel.objects[y][x] == 1) {
            if (gamePanel.objects[y][x] == 2) gamePanel.objects[y][x] = 0;
            gamePanel.setBullet(null);
            return true;
        }
        return false;
    }
}

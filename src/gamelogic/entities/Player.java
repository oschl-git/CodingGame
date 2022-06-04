package gamelogic.entities;

import gui.GamePanel;

import java.awt.*;

/**
 * This is the player class. It handles movement, rendering, etc.
 */
public class Player {
    int x = 0;
    int y = 0;
    directions direction = directions.RIGHT;
    boolean dead = false;

    public enum directions {UP, DOWN, LEFT, RIGHT}

    GamePanel gamePanel;
    ObjectManager objectManager;

    //region Constructors, getters, setters
    public Player(GamePanel gamePanel, ObjectManager objectManager) {
        this.gamePanel = gamePanel;
        this.objectManager = objectManager;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setDirection(directions direction) {
        this.direction = direction;
    }

    public void repositionPlayer(int x, int y) {
        dead = false;
        this.x = x;
        this.y = y;
    }
    //endregion

    /**
     * Draws the player on the screen, including an arrow that makes the player's direction apparent.
     */
    public void drawPlayer(Graphics g) {
        if (dead) return;

        int xPx = x * GamePanel.UNIT_SIZE;
        int yPx = y * GamePanel.UNIT_SIZE;

        g.setColor(Color.red);
        g.fillRect(xPx, yPx, GamePanel.getUnitSize(), GamePanel.getUnitSize());
        Graphics2D g2D = (Graphics2D) g;
        g2D.setColor(Color.white);
        g2D.setStroke(new BasicStroke(3));

        switch (direction) {
            case RIGHT -> {
                g2D.drawLine(xPx + 15, yPx + 5, xPx + 25, yPx + 15);
                g2D.drawLine(xPx + 15, yPx + 25, xPx + 25, yPx + 15);
            }
            case LEFT -> {
                g2D.drawLine(xPx + 15, yPx + 5, xPx + 5, yPx + 15);
                g2D.drawLine(xPx + 15, yPx + 25, xPx + 5, yPx + 15);
            }
            case UP -> {
                g2D.drawLine(xPx + 5, yPx + 15, xPx + 15, yPx + 5);
                g2D.drawLine(xPx + 25, yPx + 15, xPx + 15, yPx + 5);
            }
            case DOWN -> {
                g2D.drawLine(xPx + 5, yPx + 15, xPx + 15, yPx + 25);
                g2D.drawLine(xPx + 25, yPx + 15, xPx + 15, yPx + 25);
            }
        }
    }

    /**
     * Moves the player in the direction its facing.
     */
    public void movePlayer() {
        if (!isColliding()) {
            gamePanel.objects[y][x] = 0;
            switch (direction) {
                case UP -> y--;
                case DOWN -> y++;
                case LEFT -> x--;
                case RIGHT -> x++;
            }
        }
        checkVictory();
        checkDeath();
        gamePanel.objects[y][x] = 3;
    }

    /**
     * Turns the player right.
     */
    public void turnRight() {
        switch (direction) {
            case UP -> direction = directions.RIGHT;
            case DOWN -> direction = directions.LEFT;
            case LEFT -> direction = directions.UP;
            case RIGHT -> direction = directions.DOWN;
        }
    }

    /**
     * Turns the player left.
     */
    public void turnLeft() {
        switch (direction) {
            case UP -> direction = directions.LEFT;
            case DOWN -> direction = directions.RIGHT;
            case LEFT -> direction = directions.DOWN;
            case RIGHT -> direction = directions.UP;
        }
    }

    /**
     * Creates a new bullet if gamePanel.bullet == null.
     */
    public void shoot() {
        if (gamePanel.getBullet() == null) {
            gamePanel.setBullet(new Bullet(x, y, direction, gamePanel));
            gamePanel.getBullet().move();
        }

    }

    /**
     * Gets correct player position from the objects 2D array.
     */
    public void getPositionFromArray() {
        dead = false;
        direction = directions.RIGHT;
        for (int i = 0; i < gamePanel.objects.length; i++) {
            for (int j = 0; j < gamePanel.objects[i].length; j++) {
                if (gamePanel.objects[i][j] == 3) {
                    this.x = j;
                    this.y = i;
                    gamePanel.getObjectManager().startX = j;
                    gamePanel.getObjectManager().startY = i;
                    return;
                }
            }
        }
    }

    /**
     * Checks collision with walls
     *
     * @return true if no wall in front of player, else false
     */
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

    /**
     * Checks if player position is the same as the level goal, if so, shows the level completed dialog and then
     * reacts accordingly to what user chooses.
     */
    public void checkVictory() {
        if (gamePanel.objects[y][x] == 4) {
            int playerInput = gamePanel.getGameWindow().showLevelCompletedDialog();
            if (playerInput == 1) {
                gamePanel.increaseLevel();
                gamePanel.getGameWindow().resetCommandField();
            }
            gamePanel.stop();
            gamePanel.loadLevel();
        }
    }

    /**
     * Checks if player touched an enemy, if so, stops the game.
     */
    public void checkDeath() {
        if (gamePanel.objects[y][x] == 5) {
            gamePanel.stop();
            dead = true;
        }
    }
}
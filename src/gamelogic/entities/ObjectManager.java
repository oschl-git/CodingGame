package gamelogic.entities;

import gui.GamePanel;

import java.awt.*;

/**
 * This class handles drawing objects on the screen, such as walls.
 */
public class ObjectManager {
    GamePanel gamePanel;
    int startX = 0;
    int startY = 0;

    //region Constructors, getters, setters
    public ObjectManager(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }
    //endregion

    /**
     * Draws objects like walls, enemies, etc. on the screen
     */
    public void drawObjects(Graphics g) {
        for (int i = 0; i < gamePanel.objects.length; i++) {
            for (int j = 0; j < gamePanel.objects[i].length; j++) {
                switch (gamePanel.objects[i][j]) {
                    case 1 -> drawWall(g, j, i);
                    case 2 -> drawBreakableWall(g, j, i);
                    case 4 -> drawGoal(g, j, i);
                }
            }
        }
        drawStart(g, startX, startY);
    }

    /**
     * Draws a single wall on the game panel.
     *
     * @param x desired x coordinate
     * @param y desired y coordinate
     */
    public void drawWall(Graphics g, int x, int y) {
        Graphics2D g2D = (Graphics2D) g;

        g2D.setColor(new Color(54, 54, 54));
        g2D.fillRect(x * GamePanel.getUnitSize(), y * GamePanel.getUnitSize(),
                GamePanel.getUnitSize(), GamePanel.getUnitSize());

        g2D.setColor(new Color(215, 0, 15));
        g2D.setStroke(new BasicStroke(1));

        //Horizontal lines
        g2D.drawLine(x * GamePanel.getUnitSize(),
                y * GamePanel.getUnitSize() + 14,
                (x + 1) * GamePanel.getUnitSize(),
                y * GamePanel.getUnitSize() + 14);
        g2D.drawLine(x * GamePanel.getUnitSize(),
                y * GamePanel.getUnitSize() + 29,
                (x + 1) * GamePanel.getUnitSize(),
                y * GamePanel.getUnitSize() + 29);

        //Vertical lines
        g2D.drawLine(x * GamePanel.getUnitSize() + 14,
                y * GamePanel.getUnitSize(),
                x * GamePanel.getUnitSize() + 14,
                y * GamePanel.getUnitSize() + 14);
        g2D.drawLine(x * GamePanel.getUnitSize() + 29,
                y * GamePanel.getUnitSize(),
                x * GamePanel.getUnitSize() + 29,
                y * GamePanel.getUnitSize() + 14);
        g2D.drawLine(x * GamePanel.getUnitSize() + 7,
                y * GamePanel.getUnitSize() + 15,
                x * GamePanel.getUnitSize() + 7,
                y * GamePanel.getUnitSize() + 29);
        g2D.drawLine(x * GamePanel.getUnitSize() + 22,
                y * GamePanel.getUnitSize() + 15,
                x * GamePanel.getUnitSize() + 22,
                y * GamePanel.getUnitSize() + 29);
    }

    public void drawBreakableWall(Graphics g, int x, int y) {
        drawWall(g, x, y);
        Graphics2D g2D = (Graphics2D) g;
        g2D.setStroke(new BasicStroke(2));
        g2D.setColor(Color.black);
        g2D.drawLine(x * GamePanel.getUnitSize(),
                y * GamePanel.getUnitSize() + 14,
                x * GamePanel.getUnitSize() + 14,
                y * GamePanel.getUnitSize());
        g2D.drawLine((x + 1) * GamePanel.getUnitSize(),
                y * GamePanel.getUnitSize(),
                x * GamePanel.getUnitSize(),
                (y + 1) * GamePanel.getUnitSize());
        g2D.drawLine(x * GamePanel.getUnitSize() + 14,
                (y + 1) * GamePanel.getUnitSize(),
                (x + 1) * GamePanel.getUnitSize(),
                y * GamePanel.getUnitSize() + 14);
        g2D.drawLine(x * GamePanel.getUnitSize(),
                y * GamePanel.getUnitSize() + 14,
                x * GamePanel.getUnitSize() + 14,
                (y + 1) * GamePanel.getUnitSize());
        g2D.drawLine(x * GamePanel.getUnitSize(),
                y * GamePanel.getUnitSize(),
                (x + 1) * GamePanel.getUnitSize(),
                (y + 1) * GamePanel.getUnitSize());
        g2D.drawLine(x * GamePanel.getUnitSize() + 14,
                y * GamePanel.getUnitSize(),
                (x + 1) * GamePanel.getUnitSize(),
                y * GamePanel.getUnitSize() + 14);
    }

    public void drawStart(Graphics g, int x, int y) {
        g.setColor(Color.orange);
        g.drawRect(x * GamePanel.getUnitSize(), y * GamePanel.getUnitSize(),
                GamePanel.getUnitSize(), GamePanel.getUnitSize());
    }

    public void drawGoal(Graphics g, int x, int y) {
        g.setColor(Color.green);
        g.drawRect(x * GamePanel.getUnitSize(), y * GamePanel.getUnitSize(),
                GamePanel.getUnitSize(), GamePanel.getUnitSize());
    }
    public void getEnemiesFromArray() {
        for (int i = 0; i < gamePanel.objects.length; i++) {
            for (int j = 0; j < gamePanel.objects[i].length; j++) {
                if (gamePanel.objects[i][j] == 5) {
                    gamePanel.getEnemies().add(new Enemy(j, i, gamePanel));
                }
            }
        }
    }

}

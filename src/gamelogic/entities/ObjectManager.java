package gamelogic.entities;

import gui.GamePanel;

import java.awt.*;

/**
 * This class handles drawing objects on the screen, such as walls.
 */
public class ObjectManager {
    GamePanel gamePanel;

    public ObjectManager(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    /**
     * Draws objects like walls, enemies, etc. on the screen
     */
    public void drawObjects(Graphics g) {
        for (int i = 0; i < gamePanel.objects.length; i++) {
            for (int j = 0; j < gamePanel.objects[i].length; j++) {
                switch (gamePanel.objects[i][j]) {
                    case 1 -> drawWall(g, j, i);
                }
            }
        }
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

}

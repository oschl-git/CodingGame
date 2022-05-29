package gui;

import gamelogic.LevelLoader;
import gamelogic.entities.Bullet;
import gamelogic.entities.Enemy;
import gamelogic.entities.ObjectManager;
import gamelogic.entities.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * This class is the root of all game elements and displays the game.
 */
public class GamePanel extends JPanel implements ActionListener {
    public static final int UNIT_SIZE = 30;
    static final int WIDTH = 15;
    static final int HEIGHT = 15;
    static final int WIDTH_PX = WIDTH * UNIT_SIZE;
    static final int HEIGHT_PX = HEIGHT * UNIT_SIZE;


    int moveDelay;
    GameWindow gameWindow;
    ObjectManager objectManager = new ObjectManager(this);
    Player player = new Player(this, objectManager);
    Bullet bullet = null;
    ArrayList<Enemy> enemies = new ArrayList<Enemy>();
    LevelLoader levelLoader = new LevelLoader();
    public int[][] objects;
    Timer timer = new Timer(moveDelay, this);

    int level = 1;
    int tick = 0;
    boolean gameRunning = false;

    //region Constructors, getters, setters
    public GamePanel() {
        this.setBackground(Color.BLACK);
    }

    public static int getUnitSize() {
        return UNIT_SIZE;
    }

    public static int getW() {
        return WIDTH;
    }

    public static int getH() {
        return HEIGHT;
    }

    public Player getPlayer() {
        return player;
    }

    public ObjectManager getObjectManager() {
        return objectManager;
    }

    public void setGameWindow(GameWindow gameWindow) {
        this.gameWindow = gameWindow;
    }

    public Bullet getBullet() {
        return bullet;
    }

    public void setBullet(Bullet bullet) {
        if (this.bullet == null) this.bullet = bullet;
    }

    public void removeBullet() {
        this.bullet = null;
    }

    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }

    public void increaseLevel() {
        level++;
    }

    //endregion

    public void loadLevel() {
        enemies.clear();
        objects = levelLoader.readLevelFile(level);
        player.getPositionFromArray();
        objectManager.getEnemiesFromArray();
        repaint();
    }

    public void startRound() {
        loadLevel();
        tick = 0;
        gameWindow.codeManager.getCommands();
        gameRunning = true;
        this.moveDelay = gameWindow.getMoveDelay();
        timer.restart();
    }

    public void stop() {
        gameRunning = false;
        timer.stop();
        tick = 0;
    }

    /**
     * This method gets called every time the timer reaches 0. It calls methods for moving the player, moving enemies,
     * shooting, destroying walls, etc. It also repaints the game panel and changes the timer delay.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        this.moveDelay = gameWindow.getMoveDelay();
        timer.setDelay(moveDelay);

        if (gameWindow.codeManager.getCommandArray().size() <= tick && bullet == null) stop();

        if (bullet != null) bullet.move();

        for (Enemy enemy: enemies) {
            enemy.checkForBullets();
            enemy.move();
        }

        player.checkDeath();

        if (gameRunning && gameWindow.codeManager.getCommandArray().size() > tick) {
            gameWindow.codeManager.executeCommand(tick);
        }

        repaint();
        tick++;
    }

    /**
     * This method is used to paint objects on the game panel. It gets called with the repaint() method or when the
     * window becomes visible.
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawGrid(g);
        objectManager.drawObjects(g);
        player.drawPlayer(g);
        if (bullet != null) bullet.draw(g);
        for (Enemy enemy: enemies) {
            enemy.draw(g);
        }
    }

    /**
     * This method draws a grid on the game panel.
     */
    public void drawGrid(Graphics g) {
        g.setColor(Color.gray);
        for (int i = 0; i < WIDTH; i++) g.drawLine(i * UNIT_SIZE, 0, i * UNIT_SIZE, HEIGHT_PX);
        for (int i = 0; i < HEIGHT; i++) g.drawLine(0, i * UNIT_SIZE, WIDTH_PX, i * UNIT_SIZE);
    }

}

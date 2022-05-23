package gui;

import gamelogic.LevelLoader;
import gamelogic.entities.Player;
import gamelogic.entities.ObjectManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
    LevelLoader levelLoader = new LevelLoader();
    public int[][] objects;

    Timer timer;

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

    public void setGameWindow(GameWindow gameWindow) {
        this.gameWindow = gameWindow;
    }

    //endregion

    public void startGame() {
        objects = levelLoader.readLevelFile(1);
        player.getPosition();
        this.moveDelay = gameWindow.getMoveDelay();
        timer = new Timer(moveDelay, this);
        timer.start();
    }

    /**
     * This method gets called every time the timer reaches 0. It calls methods for moving the player, moving enemies,
     * shooting, destroying walls, etc. It also repaints the game panel and changes the timer delay.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        this.moveDelay = gameWindow.getMoveDelay();
        timer.setDelay(moveDelay);

        player.movePlayer();
        player.turnRight();

        repaint();
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
    }

    /**
     * This method draws a grid on the game panel.
     */
    public void drawGrid(Graphics g) {
        g.setColor(Color.gray);
        for (int i = 0; i < WIDTH; i++) g.drawLine(i*UNIT_SIZE, 0, i*UNIT_SIZE, HEIGHT_PX);
        for (int i = 0; i < HEIGHT; i++ ) g.drawLine(0, i*UNIT_SIZE, WIDTH_PX, i*UNIT_SIZE);
    }

}

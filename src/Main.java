import gui.GamePanel;
import gui.GameWindow;

/**
 * The Main class only creates the gameWindow and then loads the first level.
 */
public class Main {
    public static void main(String[] args) {
        GameWindow gameWindow = new GameWindow();
        GamePanel gamePanel = gameWindow.getGamePanel();
        gamePanel.loadLevel();
    }
}
import gamelogic.LevelLoader;
import gui.GamePanel;
import gui.GameWindow;

public class Main {
    public static void main(String[] args) {
        GameWindow gameWindow = new GameWindow();
        GamePanel gamePanel = gameWindow.getGamePanel();
        gamePanel.loadLevel();
    }
}

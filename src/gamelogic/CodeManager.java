package gamelogic;

import gui.GamePanel;
import javax.swing.*;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class handles commands from the player.
 */
public class CodeManager {
    GamePanel gamePanel;
    JTextPane commandField;

    //region Constructors, getters, setters
    public CodeManager(GamePanel gamePanel, JTextPane commandField) {
        this.gamePanel = gamePanel;
        this.commandField = commandField;
    }
    //endregion

    /**
     * This method converts text input into an ArrayList of commands.
     * @return ArrayList of commands
     */
    public ArrayList<String> getCommands() {
        String[] input = commandField.getText().split("\\r?\\n");
        ArrayList<String> output = new ArrayList<String>();
        for (String command : input) {
            String singleCommand = null;
            if (command.matches("walk(\\s)*(\\(\\d+\\))?")) singleCommand = "walk";
            else if (command.matches("turn_right(\\s)*(\\(\\d+\\))?")) singleCommand = "turn_right";
            else if (command.matches("turn_left(\\s)*(\\(\\d+\\))?")) singleCommand = "turn_left";
            else if (command.matches("shoot(\\s)*(\\(\\d+\\))?")) singleCommand = "shoot";

            int iterations = 1;
            Pattern p = Pattern.compile("\\d+");
            Matcher m = p.matcher(command);
            if (m.find()) iterations = Integer.parseInt(m.group());

            if (singleCommand != null) for (int i = 0; i < iterations; i++) {
                output.add(singleCommand);
            }
        }
        return output;
    }
}

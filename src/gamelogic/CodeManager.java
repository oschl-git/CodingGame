package gamelogic;

import gamelogic.entities.Player;
import gui.GamePanel;

import javax.swing.*;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class handles commands from the player.
 */
public class CodeManager {
    Player player;
    JTextPane commandField;
    enum commandTypes {WALK, TURN_RIGHT, TURN_LEFT, SHOOT, NEW_LINE};
    ArrayList<commandTypes> commands = new ArrayList<commandTypes>();

    //region Constructors, getters, setters
    public CodeManager(Player player, JTextPane commandField) {
        this.player = player;
        this.commandField = commandField;
    }

    public ArrayList<commandTypes> getCommandArray() {
        return commands;
    }
    //endregion

    public void executeCommand(int i) {
        switch (commands.get(i)) {
            case WALK -> player.movePlayer();
            case TURN_RIGHT -> player.turnRight();
            case TURN_LEFT -> player.turnLeft();
        }
    }

    /**
     * This method converts text input into an ArrayList of commands.
     */
    public void getCommands() {
        String[] input = commandField.getText().split("\\r?\\n");
        ArrayList<commandTypes> output = new ArrayList<commandTypes>();

        for (String command : input) {
            commandTypes singleCommand = null;
            if (command.matches("walk(\\s)*(\\(\\d+\\))?")) singleCommand = commandTypes.WALK;
            else if (command.matches("turn_right(\\s)*(\\(\\d+\\))?")) singleCommand = commandTypes.TURN_RIGHT;
            else if (command.matches("turn_left(\\s)*(\\(\\d+\\))?")) singleCommand = commandTypes.TURN_LEFT;
            else if (command.matches("shoot(\\s)*(\\(\\d+\\))?")) singleCommand = commandTypes.SHOOT;

            int iterations = 1;
            Pattern p = Pattern.compile("\\d+");
            Matcher m = p.matcher(command);
            if (m.find()) iterations = Integer.parseInt(m.group());

            if (singleCommand != null) for (int i = 0; i < iterations; i++) output.add(singleCommand);
            output.add(commandTypes.NEW_LINE);
        }

        commands = output;
    }
}

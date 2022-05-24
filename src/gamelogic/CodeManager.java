package gamelogic;

import gui.GamePanel;
import gui.GameWindow;

import javax.swing.*;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CodeManager {
    GamePanel gamePanel;
    JTextPane commandField;

    public CodeManager(GamePanel gamePanel, JTextPane commandField) {
        this.gamePanel = gamePanel;
        this.commandField = commandField;
    }

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
        System.out.println(output); //debug
        return output;
    }
}

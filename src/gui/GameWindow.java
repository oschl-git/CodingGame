package gui;

import gamelogic.CodeManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This is the window class. Everything is displayed inside.
 */
public class GameWindow extends JFrame {

    private GamePanel gamePanel;
    private JPanel window;
    private JTextPane commandField;
    private JButton commandHelpButton;
    private JButton executeButton;
    private JSlider moveDelaySlider;
    CodeManager codeManager;

    //region Constructors, getters, setters
    public GameWindow() {
        setTitle("CodingGame");
        setSize(1280, 720);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        setLocationRelativeTo(null);
        setContentPane(window);
        gamePanel.setGameWindow(this);
        this.codeManager = new CodeManager(gamePanel.getPlayer(), commandField);

        executeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gamePanel.startRound();
            }
        });
    }

    public GamePanel getGamePanel() {
        return gamePanel;
    }

    /**
     * Getter that returns a correct value based on a slider that's controlled by the user.
     *
     * @return move delay for the timer
     */
    public int getMoveDelay() {
        return 500 - (moveDelaySlider.getValue() * 4);
    }

    //endregion

    //region IDEA GUI Designer code
    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        window = new JPanel();
        window.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(2, 4, new Insets(0, 0, 0, 0), -1, -1));
        commandField = new JTextPane();
        window.add(commandField, new com.intellij.uiDesigner.core.GridConstraints(0, 2, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(640, 50), null, 0, false));
        gamePanel = new GamePanel();
        window.add(gamePanel, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(450, 450), new Dimension(450, 450), 0, false));
        commandHelpButton = new JButton();
        commandHelpButton.setText("Command help");
        window.add(commandHelpButton, new com.intellij.uiDesigner.core.GridConstraints(1, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(320, -1), null, 0, false));
        executeButton = new JButton();
        executeButton.setText("Execute");
        window.add(executeButton, new com.intellij.uiDesigner.core.GridConstraints(1, 3, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(320, -1), null, 0, false));
        moveDelaySlider = new JSlider();
        moveDelaySlider.setValueIsAdjusting(false);
        window.add(moveDelaySlider, new com.intellij.uiDesigner.core.GridConstraints(1, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label1 = new JLabel();
        label1.setText("Speed:");
        window.add(label1, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return window;
    }
    /*
      Note: The GUI designer seems to be reporting a version error, but from what I've been able to gather, this is
      most likely an Idea bug. I spent a lot of time trying to fix it, but since the game works normally, I decided
      that I don't care.
     */

}

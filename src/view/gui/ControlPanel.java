package view.gui;

import controller.GameController;
import model.GameModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class ControlPanel extends JPanel {
    private final int panelWidth;
    private final int panelHeight;
    private final GameController gameController;
    private final GameModel gameModel;

    public ControlPanel(GameModel gameModel, GameController gameController, int gamePanelWidth, int frameHeight, int panelHeight) {
        this.gameModel = gameModel;
        this.gameController = gameController;
        this.panelWidth = gamePanelWidth;
        this.panelHeight = panelHeight;

        this.setBounds(0, frameHeight - this.panelHeight, this.panelWidth, this.panelHeight);
        this.setLayout(null);

        JLabel diceNumber = new JLabel("Dice: " + Integer.toString(this.gameModel.getDiceNumber()));
        diceNumber.setBounds(0, 0, this.panelWidth, this.panelHeight / 9);

        JLabel turnNumber = new JLabel("Player: " + Integer.toString(this.gameModel.getTurnNumber()));
        turnNumber.setBounds(0, this.panelHeight / 9, this.panelWidth, this.panelHeight / 9);

        JLabel movableNumber = new JLabel("Move Count: " + Integer.toString(this.gameModel.getMovableNumber()));
        movableNumber.setBounds(0, 2 * this.panelHeight / 9, this.panelWidth, this.panelHeight / 9);

        JButton rollButton = new JButton("ROLL");
        rollButton.setBounds(0, this.panelHeight / 3, this.panelWidth / 2, this.panelHeight / 3);
        rollButton.addActionListener(e -> {
            try {
                this.gameController.rollDice();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        JButton restButton = new JButton("REST");
        restButton.setBounds(this.panelWidth / 2, this.panelHeight / 3, this.panelWidth / 2, this.panelHeight / 3);
        restButton.addActionListener(e -> {
            try {
                this.gameController.restTurn();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });


        JTextField moveInput = new JTextField();
        moveInput.setBounds(0, 2 *this.panelHeight / 3, this.panelWidth / 2, this.panelHeight / 3);


        JButton moveButton = new JButton("MOVE");
        moveButton.setBounds(this.panelWidth / 2, 2 * this.panelHeight / 3, this.panelWidth / 2, this.panelHeight / 3);
        moveButton.addActionListener(e -> {
            String moveString = moveInput.getText();
            try {
                this.gameController.movePlayer(moveString);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
            moveInput.setText(null);
        });


        this.add(diceNumber);
        this.add(turnNumber);
        this.add(movableNumber);
        this.add(rollButton);
        this.add(restButton);
        this.add(moveInput);
        this.add(moveButton);
    }
}

package view;

import controller.GameController;
import model.GameModel;
import model.Player;
import view.gui.ControlPanel;
import view.gui.GameFrame;
import view.gui.GamePanel;
import view.gui.StatusPanel;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class GameView {
    private GameModel gameModel;
    private GameController gameController;
    private int gameMode;
    private GameFrame gameFrame;

    public GameView(GameModel gameModel, GameController gameController) throws IOException {
        this.gameModel = gameModel;
        this.gameController = gameController;
        System.out.println("1) GUI");
        System.out.print("Select Mode (press enter for GUI): ");

        Scanner scanner = new Scanner(System.in);
        String modeInput = scanner.nextLine();

        if (modeInput.length() == 0 || modeInput.equals("1")) {
            this.gameMode = 1;
            GameFrame gameFrame = new GameFrame(this.gameModel, this.gameController);
            this.gameFrame = gameFrame;
            this.gameFrame.setVisible(true);
        }
    }

    public void alert(String alertMessage) {
        switch (gameMode) {
            case 1 -> {
                JOptionPane.showMessageDialog(this.gameFrame, alertMessage);
            }
            default -> {
                System.out.println(alertMessage);
            }
        }
    }
}

package view;

import model.GameModel;
import model.Player;
import view.gui.ControlPanel;
import view.gui.GameFrame;
import view.gui.GamePanel;
import view.gui.StatusPanel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class GameView {
    private GameModel gameModel;

    public GameView(GameModel gameModel) throws IOException {
        this.gameModel = gameModel;
        System.out.println("1) GUI");
        System.out.print("Select Mode (press enter for GUI): ");
        Scanner scanner = new Scanner(System.in);
        String modeInput = scanner.nextLine();
        if (modeInput.length() == 0 || modeInput.equals("1")) {
            int gamePanelWidth = 300;
            int statusPanelHeight = 100;
            int controlPanelHeight = 300;

            GameFrame gameFrame = new GameFrame(this.gameModel.getGameMap(), gamePanelWidth);
            GamePanel gamePanel = new GamePanel(gamePanelWidth, gameFrame.getWidth(), gameFrame.getHeight());

            ArrayList<Player> playerList = this.gameModel.getPlayerList();
            for (int i = 0; i < playerList.size(); i++) {
                StatusPanel statusPanel = new StatusPanel(playerList.get(i), gamePanelWidth, statusPanelHeight);
                gamePanel.add(statusPanel);
            }

            ControlPanel controlPanel = new ControlPanel(gamePanelWidth, gameFrame.getHeight(), controlPanelHeight);
            gamePanel.add(controlPanel);

            gameFrame.add(gamePanel);
            gameFrame.setVisible(true);
        } else {

        }
    }
}

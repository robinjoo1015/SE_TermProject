package view;

import model.GameModel;
import view.gui.GameFrame;

import java.util.Scanner;

public class GameView {
    private GameModel gameModel;

    public GameView(GameModel gameModel) {
        this.gameModel = gameModel;
        System.out.println("1) GUI");
        System.out.print("Select Mode (press enter for GUI): ");
        Scanner scanner = new Scanner(System.in);
        String modeInput = scanner.nextLine();
        if (modeInput.length() == 0 || modeInput.equals("1")) {
            GameFrame gameFrame = new GameFrame(this.gameModel.getGameMap());
        }
    }
}

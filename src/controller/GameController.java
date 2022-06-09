package controller;

import model.GameModel;
import view.GameView;

import java.io.IOException;
import java.util.ArrayList;

public class GameController {
    private GameModel gameModel;
    private GameView gameView;

    public GameController(GameModel gameModel) throws IOException {
        this.gameModel = gameModel;
        this.gameView = new GameView(this.gameModel, this);
    }

    public void rollDice() throws IOException {
        if (this.gameModel.getDiceNumber() != 0) {
            this.gameView.alert("Dice already rolled!");
        } else {
            this.gameModel.rollDice();
//            this.gameView.update();
        }
    }

    public void restTurn() throws IOException {
        this.gameModel.restTurn();
//        this.gameView.update();
    }

    public void movePlayer(String moveString) throws Exception {
        if (this.gameModel.isMovableString(moveString)) {
            this.gameModel.movePlayer(moveString);
//            this.gameView.update();
        } else {
            this.gameView.alert("Unaccepted Move!");
        }

        if (gameModel.isGameEnd()) {
            int maxScore = -1, maxIndex = -1;
            for (int i = 0; i < this.gameModel.playerNumber; i++) {
                if (this.gameModel.getPlayerList().get(i).getPlayerScore() > maxScore) {
                    maxScore = this.gameModel.getPlayerList().get(i).getPlayerScore();
                    maxIndex = i;
                }
            }
            this.gameView.alert("Player " + maxIndex + " Wins!");
        }
    }
}

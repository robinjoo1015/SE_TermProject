package controller;

import model.GameModel;
import view.GameView;

import java.io.IOException;
import java.util.ArrayList;

public class GameController {
    private GameModel gameModel;
    private GameView gameView;
//    private GameView testView;

    public GameController(GameModel gameModel) throws IOException {
        this.gameModel = gameModel;
        this.gameView = new GameView(this.gameModel, this);
//        this.testView = new GameView(this.gameModel, this);
    }

    public void rollDice() {
        if (this.gameModel.getDiceNumber() != 0) {
            this.gameView.alert("Dice already rolled!");
        } else {
            this.gameModel.rollDice();
        }
    }

    public void restTurn() {
        if (this.gameModel.getDiceNumber() != 0) {
            this.gameView.alert("Dice already rolled!");
        } else {
            this.gameModel.restTurn();
        }
    }

    public void movePlayer(String moveString) throws Exception {
        if (this.gameModel.getDiceNumber() == 0) {
            this.gameView.alert("Dice not rolled!");
            return;
        } else if (this.gameModel.isMovableString(moveString)) {
            this.gameModel.movePlayer(moveString);
        } else {
            this.gameView.alert("Unaccepted Move!");
            return;
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


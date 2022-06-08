package model;

import model.cell.BridgeEndCell;
import model.cell.BridgeStartCell;
import model.cell.Cell;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Random;
import java.util.Scanner;

public class GameModel {
    private GameMap gameMap;
    private ArrayList<Cell> gameMapArrayList;
    private ArrayList<Player> playerList;
    private ArrayList<Cell> playerCellList;
    public final int playerNumber;

    private int turnNumber;
    private int diceNumber;
    private int movableNumber;
    private Random randomGenerator;

    public GameModel() throws IOException {

        this.gameMap = new GameMap();
        this.gameMapArrayList = gameMap.getGameMapArrayList();

        playerCellList = new ArrayList<Cell>();
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter number of player (2~4) : ");
        this.playerNumber = scanner.nextInt();
        this.playerList = new ArrayList<Player>();
        for (int i = 0; i < this.playerNumber; i++) {
            Player newPlayer = new Player(i);
            this.playerList.add(newPlayer);
            this.gameMapArrayList.get(0).addCellPlayer(newPlayer);
            this.playerCellList.add(this.gameMapArrayList.get(0));
        }

        this.turnNumber = 0;
        this.diceNumber = 0;
        randomGenerator = new Random();
    }

    public GameMap getGameMap() {
        return this.gameMap;
    }

    public ArrayList<Player> getPlayerList() {
        return this.playerList;
    }

    private void nextTurn() {
        this.turnNumber = (this.turnNumber + 1) % playerNumber;
    }

    public void restTurn() {
        this.playerList.get(this.turnNumber).removeBridgeCard();
        this.nextTurn();
    }

    public void rollDice() {
        this.diceNumber = randomGenerator.nextInt(1, 7);
        this.movableNumber = this.diceNumber - this.playerList.get(this.turnNumber).getBridgeCardCount();
    }

    public boolean movePlayer(String moveString) {
        Player currentPlayer = this.playerList.get(this.turnNumber);
        Cell currentCell = this.playerCellList.get(this.turnNumber);
        Cell tempCell = currentCell;
        int currentCellIndex = this.gameMapArrayList.indexOf(currentCell);

        if (moveString.length() != this.movableNumber) {
            return false;
        }

        for (int i = 0; i < moveString.length(); i++) {
            Direction tempDirection = Direction.valueOf(moveString.substring(i, i + 1).toUpperCase());
            if (tempCell.getDirectionPrev() == tempDirection) {
                tempCell = this.gameMapArrayList.get(--currentCellIndex);
            } else if (tempCell.getDirectionNext() == tempDirection) {
                tempCell = this.gameMapArrayList.get(++currentCellIndex);
            } else if (tempCell.getClass() == BridgeStartCell.class && tempDirection == Direction.R) {
                //
            } else if (tempCell.getClass() == BridgeEndCell.class && tempDirection == Direction.L) {
                //
            } else {
                return false;
            }
        }

        return true;
    }
}

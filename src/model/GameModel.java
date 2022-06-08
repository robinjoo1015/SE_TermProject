package model;

import model.cell.Cell;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class GameModel {
    public GameMap gameMap;
    private ArrayList<Player> playerList;
    private ArrayList<Cell> playerCellList;
    public final int playerNumber;

    private int turnNumber;
    private int diceNumber;
    private int moveableCount;
    private Random randomGenerator;
    private ArrayList<Cell> gameMapArrayList;
    public GameModel() throws IOException {

        this.gameMap = new GameMap();
        this.gameMapArrayList = gameMap.getGameMapArrayList();

        playerCellList = new ArrayList<Cell>();
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter number of player (2~4) : ");
        this.playerNumber = scanner.nextInt();
        this.playerList = new ArrayList<Player>();
        for(int i=0;i<this.playerNumber;i++) {
            Player newPlayer = new Player(i);
            this.playerList.add(newPlayer);
            this.gameMapArrayList.get(0).addCellPlayer(newPlayer);
            this.playerCellList.add(this.gameMapArrayList.get(0));
        }

        this.turnNumber = 0;
        randomGenerator = new Random();
    }

    public void nextTurn() {
        this.turnNumber = (this.turnNumber + 1) % playerNumber;
    }

    public void rollDice() {
        this.diceNumber = randomGenerator.nextInt(1, 7);
//        return this.diceNumber;
    }



    public boolean movePlayer(String moveString) {
        Player currentPlayer = this.playerList.get(this.turnNumber);
        Cell currentCell = playerCellList.get(this.turnNumber);
        Cell tempCell = currentCell;


        return true;
    }
}

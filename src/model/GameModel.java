package model;

import model.cell.*;
import view.MapObserver;
import view.GameObserver;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class GameModel implements MapObservable, GameObservable {
    private GameMap gameMap;
    private ArrayList<Cell> gameMapArrayList;
    private ArrayList<BridgeInfo> bridgeInfoArrayList;
    private ArrayList<Player> playerList;
    private ArrayList<Cell> playerCellList;
    public int playerNumber;
    private int turnNumber;
    private int diceNumber;
    private int movableNumber;
    private Random randomGenerator;
    private int endPlayerCount;
    private ArrayList<Boolean> isPlayerEndList;

    private ArrayList<MapObserver> mapObserverArrayList;
    private ArrayList<GameObserver> gameObserverArrayList;

    public GameModel() throws IOException {

        this.gameMap = new GameMap();
        this.gameMapArrayList = gameMap.getGameMapArrayList();
        this.bridgeInfoArrayList = gameMap.getBridgeInfoArrayList();

        playerCellList = new ArrayList<Cell>();
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.print("Enter number of player (2~4) : ");
            this.playerNumber = scanner.nextInt();
        } while (this.playerNumber < 2 || this.playerNumber > 4);
        this.playerList = new ArrayList<Player>();
        for (int i = 0; i < this.playerNumber; i++) {
            Player newPlayer = new Player(i);
            this.playerList.add(newPlayer);
            this.gameMapArrayList.get(0).addCellPlayer(newPlayer);
            this.playerCellList.add(this.gameMapArrayList.get(0));
        }

        this.turnNumber = 0;
        this.diceNumber = 0;
        this.movableNumber = 0;
        this.endPlayerCount = 0;
        this.isPlayerEndList = new ArrayList<>();
        for (int i = 0; i < this.playerNumber; i++) {
            this.isPlayerEndList.add(false);
        }
        randomGenerator = new Random();
        this.mapObserverArrayList = new ArrayList<>();
        this.gameObserverArrayList = new ArrayList<>();
    }

    public GameMap getGameMap() {
        return this.gameMap;
    }

    public ArrayList<Player> getPlayerList() {
        return this.playerList;
    }

    public int getDiceNumber() {
        return this.diceNumber;
    }

    public int getTurnNumber() {
        return this.turnNumber;
    }

    public int getMovableNumber() {
        return this.movableNumber;
    }

    public boolean isGameEnd() {
        if (this.endPlayerCount == this.playerNumber - 1) {
            return true;
        } else {
            return false;
        }
    }

    private void nextTurn() {
        do {
            this.turnNumber = (this.turnNumber + 1) % playerNumber;
        } while (this.isPlayerEndList.get(this.turnNumber) == true && this.isGameEnd() == false);
        this.diceNumber = 0;
        this.movableNumber = 0;
        this.notifyGameUpdate();
    }

    public void restTurn() {
        this.playerList.get(this.turnNumber).removeBridgeCard();
        this.nextTurn();
    }

    public void rollDice() {
        this.diceNumber = randomGenerator.nextInt(1, 7);
        this.movableNumber = this.diceNumber - this.playerList.get(this.turnNumber).getBridgeCardCount();
        this.notifyGameUpdate();
    }

    public boolean isMovableString(String moveString) {
        Player currentPlayer = this.playerList.get(this.turnNumber);
        Cell currentCell = this.playerCellList.get(this.turnNumber);
        int currentCellIndex = this.gameMapArrayList.indexOf(currentCell);

        for (int i = 0; i < moveString.length(); i++) {
            Direction currentDirection = Direction.valueOf(moveString.substring(i, i + 1).toUpperCase());
            if (currentCell.getDirectionPrev() == currentDirection && this.endPlayerCount == 0) {
                currentCell = this.gameMapArrayList.get(--currentCellIndex);
            } else if (currentCell.getDirectionNext() == currentDirection) {
                currentCell = this.gameMapArrayList.get(++currentCellIndex);
            } else if (currentCell.getClass() == BridgeStartCell.class && currentDirection == Direction.R) {
                for (int j = 0; j < this.bridgeInfoArrayList.size(); j++) {
                    if (this.bridgeInfoArrayList.get(j).getBridgeStartCell() == currentCell) {
                        currentCell = this.bridgeInfoArrayList.get(j).getBridgeEndCell();
                        currentCellIndex = this.gameMapArrayList.indexOf(currentCell);
                        break;
                    }
                }
            } else if (currentCell.getClass() == BridgeEndCell.class && currentDirection == Direction.L && this.endPlayerCount == 0) {
                for (int j = 0; j < this.bridgeInfoArrayList.size(); j++) {
                    if (this.bridgeInfoArrayList.get(j).getBridgeEndCell() == currentCell) {
                        currentCell = this.bridgeInfoArrayList.get(j).getBridgeStartCell();
                        currentCellIndex = this.gameMapArrayList.indexOf(currentCell);
                        break;
                    }
                }
            } else {
                return false;
            }
            if (currentCell.getClass() == EndCell.class) {
                return true;
            }
        }
        if (moveString.length() != this.movableNumber) {
            return false;
        }
        return true;
    }

    public void movePlayer(String moveString) throws Exception {
        Player currentPlayer = this.playerList.get(this.turnNumber);
        Cell currentCell = this.playerCellList.get(this.turnNumber);
        int currentCellIndex = this.gameMapArrayList.indexOf(currentCell);


        currentCell.removeCellPlayer(currentPlayer);

        for (int i = 0; i < moveString.length(); i++) {
            Direction currentDirection = Direction.valueOf(moveString.substring(i, i + 1).toUpperCase());
            if (currentCell.getDirectionPrev() == currentDirection) {
                currentCell = this.gameMapArrayList.get(--currentCellIndex);
            } else if (currentCell.getDirectionNext() == currentDirection) {
                currentCell = this.gameMapArrayList.get(++currentCellIndex);
            } else if (currentCell.getClass() == BridgeStartCell.class && currentDirection == Direction.R) {
                for (int j = 0; j < this.bridgeInfoArrayList.size(); j++) {
                    if (this.bridgeInfoArrayList.get(j).getBridgeStartCell() == currentCell) {
                        currentCell = this.bridgeInfoArrayList.get(j).getBridgeEndCell();
                        currentCellIndex = this.gameMapArrayList.indexOf(currentCell);
                        currentPlayer.addCard(Card.B);
                        break;
                    }
                }
            } else if (currentCell.getClass() == BridgeEndCell.class && currentDirection == Direction.L) {
                for (int j = 0; j < this.bridgeInfoArrayList.size(); j++) {
                    if (this.bridgeInfoArrayList.get(j).getBridgeEndCell() == currentCell) {
                        currentCell = this.bridgeInfoArrayList.get(j).getBridgeStartCell();
                        currentCellIndex = this.gameMapArrayList.indexOf(currentCell);
                        currentPlayer.addCard(Card.B);
                        break;
                    }
                }
            }

            if (currentCell.getClass() == CardCell.class && ((CardCell) currentCell).isCardAvailable()) {
                currentPlayer.addCard(((CardCell) currentCell).getCard());
            }
            if (currentCell.getClass() == EndCell.class) {
                this.isPlayerEndList.set(this.turnNumber, true);
                switch (this.endPlayerCount) {
                    case 0 -> {
                        currentPlayer.addScore(7);
                    }
                    case 1 -> {
                        currentPlayer.addScore(3);
                    }
                    case 2 -> {
                        currentPlayer.addScore(1);
                    }
                }
                this.endPlayerCount += 1;
                break;
            }
        }

        currentCell.addCellPlayer(currentPlayer);
        this.playerCellList.set(this.turnNumber, currentCell);

        this.nextTurn();
        this.notifyMapUpdate();
    }

    @Override
    public void mapSubscribe(MapObserver o) {
        this.mapObserverArrayList.add(o);
    }

    @Override
    public void mapUnsubscribe(MapObserver o) {
        this.mapObserverArrayList.remove(o);
    }

    @Override
    public void notifyMapUpdate() {
        for(int i=0;i<this.mapObserverArrayList.size();i++) {
            this.mapObserverArrayList.get(i).update();
        }
    }

    @Override
    public void gameSubscribe(GameObserver o) {
        this.gameObserverArrayList.add(o);
    }

    @Override
    public void gameUnsubscribe(GameObserver o) {
        this.gameObserverArrayList.remove(o);
    }

    @Override
    public void notifyGameUpdate() {
        for(int i=0;i<this.gameObserverArrayList.size();i++) {
            this.gameObserverArrayList.get(i).update();
        }
    }
}

package model;

import model.cell.Cell;

import java.util.ArrayList;

public class Player {
    public ArrayList<Card> cardList;
    public int playerId;

    public Player(int id) {
        this.cardList = new ArrayList<Card>();
//        this.currentCell = startCell;
        this.playerId = id;
    }

    public int getPlayerId() {
        return playerId;
    }
}

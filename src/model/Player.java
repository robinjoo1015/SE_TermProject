package model;

import model.cell.Cell;

import java.util.ArrayList;

public class Player {
    private ArrayList<Card> cardList;
    private int playerId;
    private int playerScore;

    public Player(int id) {
        this.cardList = new ArrayList<Card>();
//        this.currentCell = startCell;
        this.playerId = id;
    }

    public int getPlayerId() {
        return playerId;
    }

    public int getBridgeCardCount() {
        int count = 0;
        for(int i=0;i<this.cardList.size();i++) {
            if(this.cardList.get(i)==Card.B) {
                count += 1;
            }
        }
        return count;
    }
}

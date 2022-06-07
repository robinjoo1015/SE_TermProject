package model;

import java.util.ArrayList;

public class Player {
    public ArrayList<Card> cardList;
    public Cell currentCell;

    public Player(Cell startCell) {
        this.cardList = new ArrayList<Card>();
        this.currentCell = startCell;
    }
}

package model.cell;

import model.Card;
import model.Direction;
import model.Player;

public class CardCell extends Cell {
    private Card card;
    private boolean cardAvailable;

    public CardCell(Direction directionPrev, Direction directionNext, Card card) {
        super();
        this.card = card;
        this.directionPrev = directionPrev;
        this.directionNext = directionNext;
        this.cardAvailable = true;
    }

    public boolean isCardAvailable() {
        return this.cardAvailable;
    }

    //    public int getCardScore() {
//        int cellCardScore = 0;
//        if(isCardAvailable()) {
//            cellCardScore = this.card.getCardScore();
//        }
//        return cellCardScore;
//    }

    public Card getCardType() {
        return this.card;
    }

    public Card getCard() {
        this.cardAvailable = false;
        return this.card;
    }
}

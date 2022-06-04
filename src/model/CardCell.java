package model;

public class CardCell extends Cell {
    public static Card card;
    public static boolean cardAvailable;

    public CardCell(Direction directionPrev, Direction directionNext, Card card) {
        this.card = card;
        this.directionPrev = directionPrev;
        this.directionNext = directionNext;
        this.cardAvailable = true;
    }

    public boolean isCardAvailable() {
        return this.cardAvailable;
    }

    public int getCardScore() {
        int cellCardScore = 0;
        if(isCardAvailable()) {
            cellCardScore = this.card.getCardScore();
        }
        return cellCardScore;
    }
}

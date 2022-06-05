package model;

public class BridgeCell extends Cell {
    public static Card bridgeCard;
    public BridgeCell() {
        this.directionPrev = Direction.L;
        this.directionNext = Direction.R;
        this.bridgeCard = Card.B;
    }

    public Card getCard() {
        return this.bridgeCard;
    }
}

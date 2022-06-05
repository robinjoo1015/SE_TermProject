package model;

public class BridgeStartCell extends Cell {
    public static Direction directionBridgeNext;
//    public static Card bridgeCard;
    public BridgeStartCell(Direction directionPrev, Direction directionNext) {
        this.directionPrev = directionPrev;
        this.directionNext = directionNext;
//        this.directionBridgeNext = Direction.RIGHT;
        this.directionBridgeNext = Direction.R;
//        this.bridgeCard = Card.B;
    }

    public Direction getDirectionBridgeNext() {
        return this.directionBridgeNext;
    }
}

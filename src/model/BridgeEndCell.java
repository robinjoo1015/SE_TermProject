package model;

public class BridgeEndCell extends Cell {
    public static Direction directionBridgePrev;
    public static Card bridgeCard;
    public BridgeEndCell(Direction directionPrev, Direction directionNext) {
        this.directionPrev = directionPrev;
        this.directionNext = directionNext;
//        this.directionBridgePrev = Direction.LEFT;
        this.directionBridgePrev = Direction.L;
        this.bridgeCard = Card.B;
    }

    public Direction getDirectionBridgePrev() {
        return this.directionBridgePrev;
    }
}

package model;

public class BridgeEndCell extends Cell {
    public static Direction directionBridgePrev;
    public static Card bridgeCard;
    public BridgeEndCell(Direction directionPrev, Direction DirectionNext) {
        this.directionPrev = directionPrev;
        this.directionNext = directionNext;
    }

    public Direction getDirectionBridgePrev() {
        return this.directionBridgePrev;
    }
}

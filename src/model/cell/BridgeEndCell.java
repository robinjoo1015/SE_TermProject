package model.cell;

import model.Direction;

public class BridgeEndCell extends Cell {
    public final Direction directionBridgePrev;
    public Cell cellBridgePrev;

    //    public static Card bridgeCard;
    public BridgeEndCell(Direction directionPrev, Direction directionNext) {
        super();
        this.directionPrev = directionPrev;
        this.directionNext = directionNext;
//        this.directionBridgePrev = Direction.LEFT;
        this.directionBridgePrev = Direction.L;
//        this.bridgeCard = Card.B;
    }

    public Direction getDirectionBridgePrev() {
        return this.directionBridgePrev;
    }

//    public void setCellBridgePrev(Cell cellBridgePrev) {
//        this.cellBridgePrev = cellBridgePrev;
//    }
//
//    public Cell getCellBridgePrev() {
//        return this.cellBridgePrev;
//    }
}

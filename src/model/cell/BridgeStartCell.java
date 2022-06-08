package model.cell;

import model.Direction;
import model.Player;

public class BridgeStartCell extends Cell {
    public final Direction directionBridgeNext;
//    public Cell cellBridgeNext;
//    public static Card bridgeCard;
    public BridgeStartCell(Direction directionPrev, Direction directionNext) {
        super();
        this.directionPrev = directionPrev;
        this.directionNext = directionNext;
//        this.directionBridgeNext = Direction.RIGHT;
        this.directionBridgeNext = Direction.R;
//        this.bridgeCard = Card.B;
    }

    public Direction getDirectionBridgeNext() {
        return this.directionBridgeNext;
    }

//    public void setCellBridgeNext(Cell cellBridgeNext) {
//        this.cellBridgeNext = cellBridgeNext;
//    }
//
//    public Cell getCellBridgeNext() {
//        return this.cellBridgeNext;
//    }
}

package model.cell;

import model.Direction;
import model.Player;

public class BridgeStartCell extends Cell {
    private final Direction directionBridgeNext;

    public BridgeStartCell(Direction directionPrev, Direction directionNext) {
        super();
        this.directionPrev = directionPrev;
        this.directionNext = directionNext;
        this.directionBridgeNext = Direction.R;
    }

    public Direction getDirectionBridgeNext() {
        return this.directionBridgeNext;
    }
}
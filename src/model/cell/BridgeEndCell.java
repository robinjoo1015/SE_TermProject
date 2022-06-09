package model.cell;

import model.Direction;

public class BridgeEndCell extends Cell {
    private final Direction directionBridgePrev;

    public BridgeEndCell(Direction directionPrev, Direction directionNext) {
        super();
        this.directionPrev = directionPrev;
        this.directionNext = directionNext;
        this.directionBridgePrev = Direction.L;
    }

    public Direction getDirectionBridgePrev() {
        return this.directionBridgePrev;
    }
}

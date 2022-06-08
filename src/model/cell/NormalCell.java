package model.cell;

import model.Direction;
import model.cell.Cell;

public class NormalCell extends Cell {
    public NormalCell(Direction directionPrev, Direction directionNext) {
        super();
        this.directionPrev = directionPrev;
        this.directionNext = directionNext;
    }
}

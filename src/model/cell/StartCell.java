package model.cell;

import model.Direction;
import model.cell.Cell;

public class StartCell extends Cell {
    public StartCell(Direction directionNext) {
        super();
        this.directionNext = directionNext;
        this.directionPrev = null;
    }
}

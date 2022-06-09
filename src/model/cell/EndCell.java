package model.cell;

import model.Direction;
import model.cell.Cell;

public class EndCell extends Cell {
    public EndCell(Direction directionPrev) {
        super();
        this.directionPrev = directionPrev;
        this.directionNext = null;
    }
}

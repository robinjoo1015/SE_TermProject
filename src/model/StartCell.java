package model;

public class StartCell extends Cell {
    public StartCell(Direction directionNext) {
        this.directionNext = directionNext;
        this.directionPrev = null;
        this.cellPrev = null;
    }


}

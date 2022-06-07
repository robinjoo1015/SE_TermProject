package model;

public class EndCell extends Cell {
    public EndCell(Direction directionPrev) {
        this.directionPrev = directionPrev;
        this.directionNext = null;
        this.cellNext = null;
    }
}

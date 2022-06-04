package model;

public class NormalCell extends Cell {
    public NormalCell(Direction directionPrev, Direction directionNext) {
        this.directionPrev = directionPrev;
        this.directionNext = directionNext;
    }
}

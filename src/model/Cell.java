package model;

public abstract class Cell {
    static Direction directionPrev;
    static Direction directionNext;
    static Cell cellPrev;
    static Cell cellNext;

    public Direction getDirectionPrev() {
        return this.directionPrev;
    }
    public Direction getDirectionNext() {
        return this.directionNext;
    }

    public Cell getCellPrev() {
        return this.cellPrev;
    }
    public Cell getCellNext() {
        return this.cellNext;
    }

    public void setCellPrev(Cell cellPrev) {
        this.cellPrev = cellPrev;
    }
    public void setCellNext(Cell cellNext) {
        this.cellNext = cellNext;
    }
}
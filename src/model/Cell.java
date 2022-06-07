package model;

public abstract class Cell {
    public Direction directionPrev;
    public Direction directionNext;
    public Cell cellPrev;
    public Cell cellNext;

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
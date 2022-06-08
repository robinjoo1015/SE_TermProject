package model.cell;

import model.Direction;
import model.Player;

import java.util.ArrayList;

public class Cell {
    public Direction directionPrev;
    public Direction directionNext;
    //    public Cell cellPrev;
//    public Cell cellNext;
    public ArrayList<Player> cellPlayerList;

    public Cell() {
        cellPlayerList = new ArrayList<Player>();
    }

    public Direction getDirectionPrev() {
        return this.directionPrev;
    }

    public Direction getDirectionNext() {
        return this.directionNext;
    }

//    public Cell getCellPrev() {
//        return this.cellPrev;
//    }
//
//    public Cell getCellNext() {
//        return this.cellNext;
//    }
//
//    public void setCellPrev(Cell cellPrev) {
//        this.cellPrev = cellPrev;
//    }
//
//    public void setCellNext(Cell cellNext) {
//        this.cellNext = cellNext;
//    }

    public void addCellPlayer(Player player) {
        this.cellPlayerList.add(player);
    }

    public void removeCellPlayer(Player player) throws Exception {
        try {
            this.cellPlayerList.remove(player);
        } catch (Exception e) {
            throw e;
        }
    }
}
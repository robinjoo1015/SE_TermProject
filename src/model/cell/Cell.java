package model.cell;

import model.Direction;
import model.Player;

import java.util.ArrayList;

public class Cell {
    public Direction directionPrev;
    public Direction directionNext;
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

    public void addCellPlayer(Player player) {
        this.cellPlayerList.add(player);
    }

    public ArrayList<Player> getCellPlayerList() {
        return this.cellPlayerList;
    }

    public void removeCellPlayer(Player player) throws Exception {
        try {
            this.cellPlayerList.remove(player);
        } catch (Exception e) {
            throw e;
        }
    }
}
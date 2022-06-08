package model;

import model.cell.BridgeEndCell;
import model.cell.BridgeStartCell;

public class BridgeInfo {
    private BridgeStartCell bridgeStartCell;
    private BridgeEndCell bridgeEndCell;
    public int x;
    public int y;
    private int bridgeLength;

    public BridgeInfo(BridgeStartCell bridgeStartCell, int x, int y) {
        this.bridgeStartCell = bridgeStartCell;
        this.bridgeEndCell = null;
        this.x = x;
        this.y = y;
    }

    public void setBridgeEndCell(BridgeEndCell bridgeEndCell) {
        this.bridgeEndCell = bridgeEndCell;
    }

    public BridgeEndCell getBridgeEndCell() {
        return this.bridgeEndCell;
    }

    public BridgeStartCell getBridgeStartCell() {
        return this.bridgeStartCell;
    }

    public void setBridgeLength(int bridgeLength) {
        this.bridgeLength = bridgeLength;
    }

    public int getBridgeLength() {
        return this.bridgeLength;
    }
}

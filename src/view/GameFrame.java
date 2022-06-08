package view;

import model.BridgeInfo;
import model.cell.BridgeStartCell;
import model.cell.Cell;
import model.Direction;
import model.GameMap;
import model.cell.EndCell;
import model.cell.StartCell;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GameFrame extends JFrame {
    int cellSize;

    public GameFrame(GameMap gameMap) {
        this.setTitle("Bridge Game GUI");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setBackground(Color.white);

        int minx = 0, miny = 0, maxx = 0, maxy = 0, curx = 0, cury = 0;
        Cell currentCell = null;

        ArrayList<Cell> gameMapArrayList = gameMap.getGameMapArrayList();
        ArrayList<BridgeInfo> bridgeInfoArrayList = gameMap.getBridgeInfoArrayList();

        for (int i = 0; i < gameMapArrayList.size(); i++) {
            currentCell = gameMapArrayList.get(i);
            try {
                switch (currentCell.getDirectionNext()) {
                    case U: {
                        cury--;
                        miny = Math.min(cury, miny);
                        break;
                    }
                    case D: {
                        cury++;
                        maxy = Math.max(cury, maxy);
                        break;
                    }
                    case L: {
                        curx--;
                        minx = Math.min(curx, minx);
                        break;
                    }
                    case R: {
                        curx++;
                        maxx = Math.max(curx, maxx);
                        break;
                    }
                }
            } catch (Exception e) {
                break;
            }
        }
//        System.out.printf("%d %d %d %d\n", minx, miny, maxx, maxy);

        cellSize = 60;
        curx = cellSize * (2 - minx);
        cury = cellSize * (2 - miny);
        for (int i = 0; i < gameMapArrayList.size(); i++) {
            currentCell = gameMapArrayList.get(i);
            if (currentCell.getClass() == StartCell.class) {
                CellPanel cellPanel = new CellPanel(curx, cury, cellSize * 2, cellSize * 2);
                cellPanel.setBackground(Color.yellow);
                this.add(cellPanel);
                switch (currentCell.getDirectionNext()) {
                    case L:
                    case U:
                        break;
                    case D:
                    case R:
                        cury += cellSize;
                        curx += cellSize;
                }
            } else if (currentCell.getClass() == EndCell.class) {
                switch (currentCell.getDirectionPrev()) {
                    case R: {
                        curx -= cellSize;
                        break;
                    }
                    case D: {
                        cury -= cellSize;
                        break;
                    }
                    default: {
                        break;
                    }
                }
                CellPanel cellPanel = new CellPanel(curx, cury, cellSize * 2, cellSize * 2);
                cellPanel.setBackground(Color.yellow);
                this.add(cellPanel);
            } else {
                CellPanel cellPanel = new CellPanel(curx, cury, cellSize, cellSize);
                cellPanel.setBackground(Color.orange);
                this.add(cellPanel);
                if (currentCell.getClass() == BridgeStartCell.class) {
                    for (int j = 0; j < bridgeInfoArrayList.size(); j++) {
                        if (bridgeInfoArrayList.get(j).getBridgeStartCell() == currentCell) {
                            CellPanel bridgePanel = new CellPanel(curx + cellSize, cury, cellSize * bridgeInfoArrayList.get(j).getBridgeLength(), cellSize);
                            bridgePanel.setBackground(new Color(0xCC5533));
                            this.add(bridgePanel);
                        }
                    }
                }
            }
            try {
                switch (currentCell.getDirectionNext()) {
                    case U: {
                        cury -= cellSize;
                        break;
                    }
                    case D: {
                        cury += cellSize;
                        break;
                    }
                    case L: {
                        curx -= cellSize;
                        break;
                    }
                    case R: {
                        curx += cellSize;
                        break;
                    }
                }
            } catch (Exception e) {
                break;
            }
        }

        this.setSize((maxx - minx + 8) * cellSize, (maxy - miny + 8) * cellSize);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

}
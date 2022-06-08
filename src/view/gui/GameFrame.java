package view.gui;

import model.BridgeInfo;
import model.Player;
import model.cell.*;
import model.GameMap;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class GameFrame extends JFrame {
    private final int cellSize;
    private int gameFrameWidth;
    private int gameFrameHeight;
    private final int gamePanelWidth;

    public GameFrame(GameMap gameMap, int gamePanelWidth) throws IOException {
        this.setTitle("Bridge Game GUI");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        this.setResizable(false);
        this.setResizable(true);
        this.setBackground(Color.white);
        this.setLayout(null);

        this.gamePanelWidth = gamePanelWidth;
        int minx = 0, miny = 0, maxx = 0, maxy = 0, curx = 0, cury = 0;
        Cell currentCell = null;

        ArrayList<Cell> gameMapArrayList = gameMap.getGameMapArrayList();
        ArrayList<BridgeInfo> bridgeInfoArrayList = gameMap.getBridgeInfoArrayList();

        for (int i = 0; i < gameMapArrayList.size(); i++) {
            currentCell = gameMapArrayList.get(i);
            try {
                switch (currentCell.getDirectionNext()) {
                    case U -> {
                        cury--;
                        miny = Math.min(cury, miny);
                    }
                    case D -> {
                        cury++;
                        maxy = Math.max(cury, maxy);
                    }
                    case L -> {
                        curx--;
                        minx = Math.min(curx, minx);
                    }
                    case R -> {
                        curx++;
                        maxx = Math.max(curx, maxx);
                    }
                }
            } catch (Exception e) {
                break;
            }
        }

        this.cellSize = 60;
        curx = this.cellSize * (1 - minx);
        cury = this.cellSize * (1 - miny);

        for (int i = 0; i < gameMapArrayList.size(); i++) {
            currentCell = gameMapArrayList.get(i);
            CellPanel cellPanel;
            if (currentCell.getClass() == StartCell.class) {
                cellPanel = new CellPanel(curx, cury, this.cellSize * 2, this.cellSize * 2);
                cellPanel.setBackground(new Color(0xf8d55c));
                cellPanel.add(new JLabel("START"));
                switch (currentCell.getDirectionNext()) {
                    case L, U -> {
                        break;
                    }
                    case D, R -> {
                        cury += this.cellSize;
                        curx += this.cellSize;
                    }
                }
            } else if (currentCell.getClass() == EndCell.class) {
                switch (currentCell.getDirectionPrev()) {
                    case R -> {
                        curx -= this.cellSize;
                    }
                    case D -> {
                        cury -= this.cellSize;
                    }
                    default -> {
                        break;
                    }
                }
                cellPanel = new CellPanel(curx, cury, this.cellSize * 2, this.cellSize * 2);
                cellPanel.setBackground(new Color(0xf8d55c));
                cellPanel.add(new JLabel("END"));
            } else {
                cellPanel = new CellPanel(curx, cury, this.cellSize, this.cellSize);
                cellPanel.setBackground(new Color(0xfae39a));
                if (currentCell.getClass() == CardCell.class) {
                    if (((CardCell) currentCell).isCardAvailable()) {
                        BufferedImage image = null;
                        switch (((CardCell) currentCell).getCardType()) {
                            case P -> {
                                image = ImageIO.read(new File("./res/phillipsDriver.png"));
                            }
                            case H -> {
                                image = ImageIO.read(new File("./res/hammer.png"));
                            }
                            case S -> {
                                image = ImageIO.read(new File("./res/saw.png"));
                            }
                        }
                        JLabel picLabel = new JLabel(new ImageIcon(image.getScaledInstance(this.cellSize - 10, this.cellSize - 10, Image.SCALE_DEFAULT)));
                        cellPanel.add(picLabel);
                    }
                }
                if (currentCell.getClass() == BridgeStartCell.class) {
                    for (int j = 0; j < bridgeInfoArrayList.size(); j++) {
                        if (bridgeInfoArrayList.get(j).getBridgeStartCell() == currentCell) {
                            CellPanel bridgePanel = new CellPanel(curx + this.cellSize, cury, this.cellSize * bridgeInfoArrayList.get(j).getBridgeLength(), this.cellSize);
                            bridgePanel.setBackground(new Color(0xbc9b71));
                            this.add(bridgePanel);
                        }
                    }
                }
            }
            ArrayList<Player> currentCellPlayerList = currentCell.getCellPlayerList();
            for (int j = 0; j < currentCellPlayerList.size(); j++) {
                cellPanel.add(new JLabel(Integer.toString(currentCellPlayerList.get(j).getPlayerId())));
            }
            this.add(cellPanel);

            try {
                switch (currentCell.getDirectionNext()) {
                    case U -> {
                        cury -= this.cellSize;
                    }
                    case D -> {
                        cury += this.cellSize;
                    }
                    case L -> {
                        curx -= this.cellSize;
                    }
                    case R -> {
                        curx += this.cellSize;
                    }
                }
            } catch (Exception e) {
                break;
            }
        }

        this.gameFrameWidth = (maxx - minx + 6) * this.cellSize + this.gamePanelWidth;
        this.gameFrameHeight = (maxy - miny + 6) * this.cellSize;

        this.setSize(this.gameFrameWidth, this.gameFrameHeight);

        this.setLocationRelativeTo(null);
    }

}
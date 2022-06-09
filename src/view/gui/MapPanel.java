package view.gui;

import model.BridgeInfo;
import model.GameMap;
import model.GameModel;
import model.Player;
import model.cell.*;
import view.MapObserver;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class MapPanel extends JPanel implements MapObserver {
    private GameModel gameModel;
    private GameMap gameMap;
    private int cellSize;
    private int initx;
    private int inity;
    public MapPanel(GameModel gameModel, int curx, int cury, int cellSize, int mapPanelWidth, int mapPanelHeight) throws IOException {
        this.gameModel = gameModel;
        this.gameMap = this.gameModel.getGameMap();
        this.cellSize = cellSize;
        this.setBackground(Color.white);
        this.initx = curx;
        this.inity = cury;

        Cell currentCell = null;
        ArrayList<Cell> gameMapArrayList = this.gameMap.getGameMapArrayList();
        ArrayList<BridgeInfo> bridgeInfoArrayList = this.gameMap.getBridgeInfoArrayList();
        for (int i = 0; i < gameMapArrayList.size(); i++) {
            currentCell = gameMapArrayList.get(i);
            CellPanel cellPanel;
            if (currentCell.getClass() == StartCell.class) {
                cellPanel = new CellPanel(curx, cury, this.cellSize * 2, this.cellSize * 2);
                cellPanel.setBackground(new Color(0xf8d55c));
                JLabel startLabel = new JLabel("START", SwingConstants.CENTER);
                startLabel.setBounds(0, 0, this.cellSize * 2, this.cellSize * 2);
                cellPanel.add(startLabel);
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
                JLabel endLabel = new JLabel("END", SwingConstants.CENTER);
                endLabel.setBounds(0, 0, this.cellSize * 2, this.cellSize * 2);
                cellPanel.add(endLabel);
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
            cellPanel.setLayout(null);
            for (int j = 0; j < currentCellPlayerList.size(); j++) {
                JLabel playerLabel = new JLabel(Integer.toString(currentCellPlayerList.get(j).getPlayerId(), SwingConstants.CENTER));
                int playerId = currentCellPlayerList.get(j).getPlayerId();
                playerLabel.setBounds((playerId % 2) * (this.cellSize/2), (playerId / 2) * (this.cellSize/2), this.cellSize / 2, this.cellSize / 2);
                playerLabel.setBackground(null);
                cellPanel.add(playerLabel);
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
        this.setBounds(0, 0, mapPanelWidth, mapPanelHeight);
        this.setLayout(null);

        this.gameModel.mapSubscribe(this);
    }

    private void reRender() throws IOException {
        int curx = this.initx;
        int cury = this.inity;
        Cell currentCell = null;
        ArrayList<Cell> gameMapArrayList = this.gameMap.getGameMapArrayList();
        ArrayList<BridgeInfo> bridgeInfoArrayList = this.gameMap.getBridgeInfoArrayList();
        for (int i = 0; i < gameMapArrayList.size(); i++) {
            currentCell = gameMapArrayList.get(i);
            CellPanel cellPanel;
            if (currentCell.getClass() == StartCell.class) {
                cellPanel = new CellPanel(curx, cury, this.cellSize * 2, this.cellSize * 2);
                cellPanel.setBackground(new Color(0xf8d55c));
                JLabel startLabel = new JLabel("START", SwingConstants.CENTER);
                startLabel.setBounds(0, 0, this.cellSize * 2, this.cellSize * 2);
                cellPanel.add(startLabel);
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
                JLabel endLabel = new JLabel("END", SwingConstants.CENTER);
                endLabel.setBounds(0, 0, this.cellSize * 2, this.cellSize * 2);
                cellPanel.add(endLabel);
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
            cellPanel.setLayout(null);
            for (int j = 0; j < currentCellPlayerList.size(); j++) {
                JLabel playerLabel = new JLabel(Integer.toString(currentCellPlayerList.get(j).getPlayerId(), SwingConstants.CENTER));
                int playerId = currentCellPlayerList.get(j).getPlayerId();
                playerLabel.setBounds((playerId % 2) * (this.cellSize/2), (playerId / 2) * (this.cellSize/2), this.cellSize / 2, this.cellSize / 2);
                playerLabel.setBackground(null);
                cellPanel.add(playerLabel);
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
    }

    @Override
    public void update() {
        this.setVisible(false);
        this.removeAll();
        try {
            this.reRender();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        this.setVisible(true);
    }
}

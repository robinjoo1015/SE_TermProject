package view.gui;

import controller.GameController;
import model.BridgeInfo;
import model.GameModel;
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
    private GameModel gameModel;
    private GameMap gameMap;
    private GameController gameController;

    public GameFrame(GameModel gameModel, GameController gameController) throws IOException {
        this.setTitle("Bridge Game GUI");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        this.setResizable(false);
        this.setResizable(true);
        this.setLayout(null);

        this.gameModel = gameModel;
        this.gameMap = gameModel.getGameMap();
        this.gameController = gameController;

        this.gamePanelWidth = 300;
        int minx = 0, miny = 0, maxx = 0, maxy = 0, curx = 0, cury = 0;
        Cell currentCell = null;

        ArrayList<Cell> gameMapArrayList = this.gameMap.getGameMapArrayList();
        ArrayList<BridgeInfo> bridgeInfoArrayList = this.gameMap.getBridgeInfoArrayList();

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
        int mapPanelWidth = (maxx - minx + 6) * this.cellSize;
        int mapPanelHeight = (maxx - minx + 6) * this.cellSize;

        MapPanel mapPanel = new MapPanel(this.gameModel, curx, cury, this.cellSize, mapPanelWidth, mapPanelHeight);

        this.add(mapPanel);

        this.gameFrameWidth = mapPanelWidth + this.gamePanelWidth;
        this.gameFrameHeight = mapPanelHeight;

        this.setSize(this.gameFrameWidth, this.gameFrameHeight);


        GamePanel gamePanel = new GamePanel(gameModel, gameController, gamePanelWidth, this.getWidth(), this.getHeight());

//        this.gameFrameWidth = mapPanelWidth + this.gamePanelWidth;
//        this.gameFrameHeight = Math.max(mapPanelHeight, gamePanel.getHeight());
//
//        this.setSize(this.gameFrameWidth, this.gameFrameHeight);

        this.add(gamePanel);

        this.setLocationRelativeTo(null);
    }

}
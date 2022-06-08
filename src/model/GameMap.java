package model;

import model.cell.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class GameMap {
    private ArrayList<Cell> gameMapArrayList;
    private ArrayList<BridgeInfo> bridgeInfoArrayList;

    public GameMap() throws IOException {

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter map file name (press enter for default) : ");
        String mapFileName = scanner.nextLine();
        if (mapFileName.length() == 0) {
            mapFileName = "default";
        }
        mapFileName = "./map/" + mapFileName + ".map";

        gameMapArrayList = new ArrayList<>();
        bridgeInfoArrayList = new ArrayList<>();
        BufferedReader mapFileReader = new BufferedReader(
                new FileReader(mapFileName)
        );
        String mapReadBuffer;
        Direction directionPrev, directionNext = null;
        int x = 0, y = 0;
        while ((mapReadBuffer = mapFileReader.readLine()) != null) {
            try {
                directionPrev = Direction.valueOf(mapReadBuffer.substring(2, 3));
                try {
                    directionNext = Direction.valueOf(mapReadBuffer.substring(4, 5));
                } catch (Exception e) {
                    directionNext = directionPrev;
                    directionPrev = null;
                }
            } catch (Exception e) {
                directionPrev = directionNext.getOpposite();
                directionNext = null;
            }

            Cell newCell;
            switch (mapReadBuffer.charAt(0)) {
                case 'S' -> {
                    if (directionPrev == null) {
                        newCell = new StartCell(directionNext);
                    } else {
                        newCell = new CardCell(directionPrev, directionNext, Card.S);
                    }
                }
                case 'E' -> {
                    newCell = new EndCell(directionPrev);
                }
                case 'C' -> {
                    newCell = new NormalCell(directionPrev, directionNext);
                }
                case 'B' -> {
                    newCell = new BridgeStartCell(directionPrev, directionNext);
                    bridgeInfoArrayList.add(new BridgeInfo((BridgeStartCell) newCell, x, y));
                }
                case 'b' -> {
                    newCell = new BridgeEndCell(directionPrev, directionNext);
                    int l = 1;
                    for (int i = 0; i < bridgeInfoArrayList.size(); i++) {
                        BridgeInfo tempBridgeInfo = bridgeInfoArrayList.get(i);
                        if (tempBridgeInfo.getBridgeEndCell() == null && tempBridgeInfo.y == y) {
                            tempBridgeInfo.setBridgeEndCell((BridgeEndCell) newCell);
                            tempBridgeInfo.setBridgeLength(x - tempBridgeInfo.x - 1);
                        }
                    }
                }
                default -> {
                    newCell = new CardCell(directionPrev, directionNext, Card.valueOf(mapReadBuffer.substring(0, 1)));
                }
            }
            gameMapArrayList.add(newCell);
            try {
                switch (directionNext) {
                    case U -> {
                        y += 1;
                    }
                    case D -> {
                        y -= 1;
                    }
                    case L -> {
                        x -= 1;
                    }
                    case R -> {
                        x += 1;
                    }
                }
            } catch (Exception e) {
                break;
            }
        }
        mapFileReader.close();
    }

    public ArrayList<Cell> getGameMapArrayList() {
        return this.gameMapArrayList;
    }

    public ArrayList<BridgeInfo> getBridgeInfoArrayList() {
        return this.bridgeInfoArrayList;
    }
}

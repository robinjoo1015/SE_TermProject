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
    public GameMap() throws IOException {

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter map file name (press enter for default) : ");
        String mapFileName = scanner.nextLine();
        if(mapFileName.length() == 0) {
            mapFileName = "default";
        }
        mapFileName = "./map/"+mapFileName+".map";
        File mapFile = new File(mapFileName);

        gameMapArrayList = new ArrayList<Cell>();
//        System.out.println(filePath);
        BufferedReader mapFileReader = new BufferedReader(
                new FileReader(mapFileName)
        );
        String mapReadBuffer;
        Direction directionPrev = null, directionNext = null;
        while((mapReadBuffer = mapFileReader.readLine()) != null) {
//            System.out.println(mapReadBuffer);
            try {
//                switch(mapReadBuffer.charAt(2)) {
//                    case 'L':
//                        directionPrev = Direction.LEFT;
//                        break;
//                    case 'R':
//                        directionPrev = Direction.RIGHT;
//                        break;
//                    case 'U':
//                        directionPrev = Direction.UP;
//                        break;
//                    case 'D':
//                        directionPrev = Direction.DOWN;
//                        break;
//                }
                directionPrev = Direction.valueOf(mapReadBuffer.substring(2, 3));
                try {
//                switch(mapReadBuffer.charAt(4)) {
//                    case 'L':
//                        directionNext = Direction.LEFT;
//                        break;
//                    case 'R':
//                        directionNext = Direction.RIGHT;
//                        break;
//                    case 'U':
//                        directionNext = Direction.UP;
//                        break;
//                    case 'D':
//                        directionNext = Direction.DOWN;
//                        break;
//                }
                    directionNext = Direction.valueOf(mapReadBuffer.substring(4));
                } catch(Exception e) {
                    directionNext = directionPrev;
                    directionPrev = null;
                }
            } catch(Exception e) {
                directionPrev = directionNext;
            }
//            try {
////                switch(mapReadBuffer.charAt(4)) {
////                    case 'L':
////                        directionNext = Direction.LEFT;
////                        break;
////                    case 'R':
////                        directionNext = Direction.RIGHT;
////                        break;
////                    case 'U':
////                        directionNext = Direction.UP;
////                        break;
////                    case 'D':
////                        directionNext = Direction.DOWN;
////                        break;
////                }
//                directionNext = Direction.valueOf(mapReadBuffer.substring(4));
//            } catch(Exception e) {
//                directionNext = directionPrev;
//            }

            Cell newCell;
            switch (mapReadBuffer.charAt(0)) {
                case 'S':
                    if(directionPrev==null) {
                        newCell = new StartCell(directionNext);
                    } else {
                        newCell = new CardCell(directionPrev, directionNext, Card.S);
                    }
                    break;
                case 'E':
                    newCell = new EndCell(directionPrev);
                    break;
                case 'C':
                    newCell = new NormalCell(directionPrev, directionNext);
                    break;
                case 'B':
                    newCell = new BridgeStartCell(directionPrev, directionNext);
                    break;
                case 'b':
                    newCell = new BridgeEndCell(directionPrev, directionNext);
                    break;
                default:
                    newCell = new CardCell(directionPrev, directionNext, Card.valueOf(mapReadBuffer.substring(0, 1)));
            }
            gameMapArrayList.add(newCell);
        }
        mapFileReader.close();

//        for(int i=0;i<gameMapArrayList.size();i++) {
//            Cell currentCell = gameMapArrayList.get(i);
//            if(i==0) {
//                currentCell.setCellPrev(null);
//                currentCell.setCellNext(gameMapArrayList.get(i+1));
//            } else if(i==gameMapArrayList.size()-1) {
//                currentCell.setCellPrev(gameMapArrayList.get(i-1));
//                currentCell.setCellNext(null);
//            } else {
//                currentCell.setCellPrev(gameMapArrayList.get(i-1));
//                currentCell.setCellNext(gameMapArrayList.get(i+1));
//            }
//        }

    }

    public ArrayList<Cell> getGameMapArrayList() {
        return this.gameMapArrayList;
    }

//    public Cell getStartCell() {
//        return gameMapArrayList.get(0);
//    }
}

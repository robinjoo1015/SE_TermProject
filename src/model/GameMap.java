package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class GameMap  {
    public static ArrayList<Cell> gameMapArrayList;
    public GameMap(String filePath) throws IOException {
        System.out.println(filePath);
        BufferedReader mapFileReader = new BufferedReader(
                new FileReader(filePath)
        );
        String mapReadBuffer;
        Cell prevCell = null, currentCell = null;
        while((mapReadBuffer = mapFileReader.readLine()) != null) {
            System.out.println(mapReadBuffer);
            Direction directionPrev = null, directionNext = null;
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
            } catch(Exception e) {}
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
            }
            switch (mapReadBuffer.charAt(0)) {
                case 'S':
                    currentCell = new StartCell(directionNext);
                    break;
                case 'E':
                    currentCell = new EndCell(prevCell.getDirectionNext().getOpposite());
                    break;
                case 'C':
                    currentCell = new NormalCell(directionPrev, directionNext);
                    break;
                case 'B':
                    currentCell = new BridgeStartCell(directionPrev, directionNext);
                    break;
                case 'b':
                    currentCell = new BridgeEndCell(directionPrev, directionNext);
                    break;
                default:
                    currentCell = new CardCell(directionPrev, directionNext, Card.valueOf(mapReadBuffer.substring(0, 1)));
            }
            if(prevCell != null) {
                prevCell.setCellNext(currentCell);
            }
            currentCell.setCellPrev(prevCell);
            prevCell = currentCell;
        }
        mapFileReader.close();
    }
}

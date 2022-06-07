package view;

import model.Cell;
import model.Direction;
import model.GameMap;
import model.Player;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GUI  {
    ArrayList<Player> playerList;
    int cellSize;
    public GUI(GameMap gameMap, ArrayList<Player> playerList) {
        JFrame frame = new JFrame();
        frame.setTitle("Bridge Game GUI");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        frame.setLayout(null);

        int minx=0, miny=0, maxx=0, maxy=0, curx=0, cury=0;
//        Cell currentCell = gameMap.getStartCell();
        Cell currentCell = gameMap.gameMapArrayList.get(10);
        while(currentCell.getCellNext() != null) {
            switch(currentCell.getDirectionNext()) {
                case U: {
                    System.out.print("u");
                    cury--;
                    miny = cury<miny?cury:miny;
                    break;
                }
                case D: {
                    System.out.print("d");
                    cury++;
                    maxy = cury<maxy?maxy:cury;
                    break;
                }
                case L: {
                    System.out.print("l");
                    curx--;
                    minx = curx<minx?curx:minx;
                    break;
                }
                case R: {
                    System.out.print("r");
                    curx++;
                    maxx = curx<maxx?maxx:curx;
                    break;
                }
            }
            currentCell = currentCell.getCellNext();
        }
        System.out.printf("%d %d %d %d\n", minx, miny, maxx, maxy);

        cellSize = 50;
        frame.setSize((maxx-minx)*cellSize+400, (maxy-miny)*cellSize+400);
        frame.setBackground(Color.white);



        JPanel panel = new JPanel();
        panel.setBounds(10, 10, cellSize, cellSize);
        panel.setBackground(Color.blue);
        frame.add(panel);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        

        this.playerList = playerList;
    }




}

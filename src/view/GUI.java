package view;

import model.Cell;
import model.Direction;
import model.GameMap;
import model.Player;

import javax.sound.sampled.Line;
import javax.swing.*;
import javax.swing.border.LineBorder;
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
        frame.setBackground(Color.white);

        int minx=0, miny=0, maxx=0, maxy=0, curx=0, cury=0;
//        Cell currentCell = gameMap.getStartCell();
        Cell currentCell = gameMap.getStartCell();
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

        cellSize = 60;
        curx = cellSize*(1-minx);
        cury = cellSize*(1-miny);
        currentCell = gameMap.getStartCell();
        while(currentCell != null) {
            if(currentCell.getCellPrev()==null) {
                JPanel panel = new JPanel();
                panel.setBounds(curx, cury, cellSize * 2, cellSize * 2);
                panel.setBackground(Color.yellow);
                panel.setBorder(new LineBorder(Color.black));
                frame.add(panel);
                if(currentCell.getDirectionNext()==Direction.U) {
                    cury += cellSize;
                } else if(currentCell.getDirectionNext()==Direction.D) {
                    cury -= cellSize;
                } else {
                    curx += cellSize;
                }
            } else if(currentCell.getCellNext()==null){
                if(currentCell.getDirectionPrev()==Direction.U) {
                    cury -= cellSize;
                } else if(currentCell.getDirectionPrev()==Direction.D) {
                    cury += cellSize;
                } else {
                    curx += cellSize;
                }
                JPanel panel = new JPanel();
                panel.setBounds(curx, cury, cellSize * 2, cellSize * 2);
                panel.setBackground(Color.yellow);
                panel.setBorder(new LineBorder(Color.black));
                frame.add(panel);
            } else {
                JPanel panel = new JPanel();
                panel.setBounds(curx, cury, cellSize, cellSize);
                panel.setBackground(Color.orange);
                panel.setBorder(new LineBorder(Color.black));
                frame.add(panel);
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
                currentCell = currentCell.getCellNext();
            } catch(Exception e) {
                break;
            }
        }

        frame.setSize((maxx-minx+6)*cellSize, (maxy-miny+6)*cellSize);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);



        this.playerList = playerList;
    }




}

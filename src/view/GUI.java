package view;

import model.GameMap;
import model.Player;

import javax.swing.*;
import java.util.ArrayList;

public class GUI extends JFrame {
    ArrayList<Player> playerList;
    public GUI(GameMap gameMap, ArrayList<Player> playerList) {
        setTitle("Bridge Game GUI");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        this.playerList = playerList;
    }
}

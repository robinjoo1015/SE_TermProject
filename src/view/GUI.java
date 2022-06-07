package view;

import model.GameMap;
import model.Player;

import javax.swing.*;
import java.util.ArrayList;

public class GUI extends JFrame {
    ArrayList<Player> playerList;
    public GUI(GameMap gameMap, ArrayList<Player> playerList) {
        setTitle("Bridge Game GUI");
        setSize(1000, 1000);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);



        this.playerList = playerList;
    }


}

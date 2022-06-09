package view.gui;

import controller.GameController;
import model.GameModel;
import model.Player;
import view.GameObserver;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.ArrayList;

public class GamePanel extends JPanel implements GameObserver {
    private final int panelWidth;
    private GameModel gameModel;
    private GameController gameController;
    private int statusPanelHeight;
    private int controlPanelHeight;

    public GamePanel(GameModel gameModel, GameController gameController, int panelWidth, int mapPanelWidth) {
        this.gameModel = gameModel;
        this.gameController = gameController;
        this.panelWidth = panelWidth;
        this.statusPanelHeight = 100;
        this.controlPanelHeight = 300;


        this.setBounds(mapPanelWidth, 0, this.panelWidth, this.statusPanelHeight * this.gameModel.getPlayerList().size() + this.controlPanelHeight);
        this.setBorder(new LineBorder(Color.black));
        this.setLayout(null);

        ArrayList<Player> playerList = this.gameModel.getPlayerList();
        for (int i = 0; i < playerList.size(); i++) {
            StatusPanel statusPanel = new StatusPanel(playerList.get(i), this.getWidth(), this.statusPanelHeight);
            this.add(statusPanel);
        }

        ControlPanel controlPanel = new ControlPanel(this.gameModel, this.gameController, this.panelWidth, this.getHeight(), this.controlPanelHeight);
        this.add(controlPanel);

        this.gameModel.gameSubscribe(this);

    }

    private void reRender() {
        this.statusPanelHeight = 100;
        ArrayList<Player> playerList = this.gameModel.getPlayerList();
        for (int i = 0; i < playerList.size(); i++) {
            StatusPanel statusPanel = new StatusPanel(playerList.get(i), this.getWidth(), this.statusPanelHeight);
            this.add(statusPanel);
        }

        int controlPanelHeight = 300;
        ControlPanel controlPanel = new ControlPanel(this.gameModel, this.gameController, this.getWidth(), this.getHeight(), controlPanelHeight);
        this.add(controlPanel);
    }

    @Override
    public void update() {
        this.setVisible(false);
        this.removeAll();
        this.reRender();
        this.setVisible(true);
    }
}

package view.gui;

import model.Card;
import model.Player;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class StatusPanel extends JPanel {
    private Player player;
    private ArrayList<Card> cardList;
    private final int panelWidth;
    private final int panelHeight;

    public StatusPanel(Player player, int gamePanelWidth, int panelHeight) {

        this.player = player;
        this.cardList = player.getCardList();
        this.panelWidth = gamePanelWidth;
        this.panelHeight = panelHeight;

        this.setBounds(0, this.panelHeight * player.getPlayerId(), this.panelWidth, this.panelHeight);
        this.setBackground(new Color(0xc9f9b9));

        this.add(new JLabel("Player " + Integer.toString(player.getPlayerId())));
        for (int i = 0; i < this.cardList.size(); i++) {
            JPanel cardPanel = new JPanel();
            cardPanel.add(new JLabel(this.cardList.get(i).toString()));
            this.add(cardPanel);
        }

    }
}

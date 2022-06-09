package view.gui;

import model.Card;
import model.Player;

import javax.swing.*;
import javax.swing.border.LineBorder;
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
        this.setLayout(null);

        JLabel playerLabel = new JLabel("Player " + Integer.toString(player.getPlayerId()));
        playerLabel.setBounds(0, 0, this.panelWidth/2, this.panelHeight/2);
        JLabel scoreLabel = new JLabel("Score = " + Integer.toString(player.getPlayerScore()));
        scoreLabel.setBounds(this.panelWidth/2, 0, this.panelWidth/2, this.panelHeight/2);

        this.add(playerLabel);
        this.add(scoreLabel);

        int cardPanelWidth = 50;
        for (int i = 0; i < this.cardList.size(); i++) {
            JPanel cardPanel = new JPanel();
            cardPanel.add(new JLabel(this.cardList.get(i).toString()));
            cardPanel.setBounds(i * cardPanelWidth, this.panelHeight / 2, cardPanelWidth, this.panelHeight / 2);
            cardPanel.setBorder(new LineBorder(Color.black));
            cardPanel.setBackground(Color.pink);
            this.add(cardPanel);
        }

        this.setBorder(new LineBorder(Color.black));

    }
}

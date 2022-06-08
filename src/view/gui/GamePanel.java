package view.gui;

import model.Player;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.ArrayList;

public class GamePanel extends JPanel {
    private final int panelWidth;

    public GamePanel(int panelWidth, int frameWidth, int frameHeight) {
        this.panelWidth = panelWidth;
        this.setBounds(frameWidth - this.panelWidth, 0, panelWidth, frameHeight);
        this.setBorder(new LineBorder(Color.black));
        this.setBackground(Color.gray);
        this.setLayout(null);
    }
}

package view.gui;

import model.Player;
import model.cell.Cell;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.ArrayList;

public class CellPanel extends JPanel {
    public CellPanel(int x, int y, int width, int height) {
        this.setBounds(x, y, width, height);
        this.setBorder(new LineBorder(Color.black));
        this.setLayout(null);
    }
}

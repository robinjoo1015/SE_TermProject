package view.gui;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class CellPanel extends JPanel {
    public CellPanel(int x, int y, int width, int height) {
        this.setBounds(x, y, width, height);
        this.setBorder(new LineBorder(Color.black));
        this.setLayout(null);
    }
}

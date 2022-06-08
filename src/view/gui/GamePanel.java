package view.gui;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class GamePanel extends JPanel {
    public GamePanel() {
        this.setBorder(new LineBorder(Color.black));
        this.setBackground(Color.gray);
    }
}

package view.gui;

import javax.swing.*;

public class ControlPanel extends JPanel {
    private final int panelWidth;
    private final int panelHeight;

    public ControlPanel(int gamePanelWidth, int gamePanelHeight, int panelHeight) {

        this.panelWidth = gamePanelWidth;
        this.panelHeight = panelHeight;

        this.setBounds(0, gamePanelHeight - this.panelHeight, this.panelWidth, this.panelHeight);
        this.setLayout(null);

        JButton rollButton = new JButton("ROLL");
        rollButton.setBounds(0, 0, this.panelWidth / 2, this.panelWidth / 2);
        JButton restButton = new JButton("REST");
        restButton.setBounds(this.panelHeight / 2, 0, this.panelWidth / 2, this.panelWidth / 2);

        JTextField moveInput = new JTextField();
        moveInput.setBounds(0, this.panelHeight / 2, this.panelWidth / 2, this.panelHeight / 2);
        JButton moveButton = new JButton("MOVE");
        moveButton.setBounds(this.panelHeight / 2, this.panelWidth / 2, this.panelWidth / 2, this.panelWidth / 2);

        this.add(rollButton);
        this.add(restButton);
        this.add(moveInput);
        this.add(moveButton);
    }
}

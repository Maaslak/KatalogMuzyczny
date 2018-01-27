package Gui.Scrollable;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public abstract class Scrollable extends JFrame {
    private JPanel mainPanel;
    private JTable table1;
    private JButton selectButton;
    protected JPanel filterPanel;
    private JButton filterButton;

    public Scrollable() {
        this.setContentPane(mainPanel);
        this.pack();

    }


    public JButton getFilterButton() {
        return filterButton;
    }

    public void setFilterButton(JButton filterButton) {
        this.filterButton = filterButton;
    }
}

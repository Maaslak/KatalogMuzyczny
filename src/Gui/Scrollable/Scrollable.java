package Gui.Scrollable;

import DataBase.DataBaseConnector;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public abstract class Scrollable extends JFrame {
    private JPanel mainPanel;
    private JTable table1;
    private JButton selectButton;
    protected JPanel filterPanel;
    private JButton filterButton;
    private JButton addButton;
    private JButton deleteButton;
    private DataBaseConnector dataBaseConnector;

    public Scrollable(DataBaseConnector dataBaseConnector) {
        this.dataBaseConnector = dataBaseConnector;
        this.setContentPane(mainPanel);
        this.pack();
    }


    public JButton getFilterButton() {
        return filterButton;
    }

    public void setFilterButton(JButton filterButton) {
        this.filterButton = filterButton;
    }

    public JButton getSelectButton() {
        return selectButton;
    }

    public void setSelectButton(JButton selectButton) {
        this.selectButton = selectButton;
    }

    public JTable getTable1() {
        return table1;
    }

    public void setTable1(JTable table1) {
        this.table1 = table1;
    }
}

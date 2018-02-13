package Gui.Scrollable;

import DataBase.DataBaseConnector;

import javax.swing.*;
import javax.swing.event.RowSorterEvent;
import javax.swing.event.RowSorterListener;
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
    private JButton exitButton;
    private JButton editButton;
    private DataBaseConnector dataBaseConnector;
    private JFrame father;

    public Scrollable(DataBaseConnector dataBaseConnector, JFrame father) {
        this.setDataBaseConnector(dataBaseConnector);
        this.father = father;
        this.setContentPane(mainPanel);
        this.pack();
        getExitButton().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                super.mouseClicked(mouseEvent);
                setVisible(false);
                father.setVisible(true);
            }
        });
        this.table1.setAutoCreateRowSorter(true);

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

    public DataBaseConnector getDataBaseConnector() {
        return dataBaseConnector;
    }

    public void setDataBaseConnector(DataBaseConnector dataBaseConnector) {
        this.dataBaseConnector = dataBaseConnector;
    }

    public JButton getExitButton() {
        return exitButton;
    }

    public void setExitButton(JButton exitButton) {
        this.exitButton = exitButton;
    }

    public JButton getAddButton() {
        return addButton;
    }

    public void setAddButton(JButton addButton) {
        this.addButton = addButton;
    }

    public JButton getDeleteButton() {
        return deleteButton;
    }

    public void setDeleteButton(JButton deleteButton) {
        this.deleteButton = deleteButton;
    }

    public JButton getEditButton() {
        return editButton;
    }

    public void setEditButton(JButton editButton) {
        this.editButton = editButton;
    }
}

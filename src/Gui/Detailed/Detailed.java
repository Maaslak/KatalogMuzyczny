package Gui.Detailed;

//import Gui.Scrollable.Scrollable;
import DataBase.DataBaseConnector;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public abstract class Detailed extends JFrame{

    private JPanel mainPanel;
    private JTable table1;
    protected JPanel informationPanel;
    private JButton selectButton;
    private JButton addButton;
    private JButton editButton;
    private JButton deleteButton;
    private JButton exitButton;
    protected JPanel additionalInformationPanel;
    private DataBaseConnector dataBaseConnector;
    private JFrame father;

    public Detailed(DataBaseConnector dataBaseConnector, JFrame father){
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
        TableModel model = new DefaultTableModel() {
            public boolean isCellEditable(int rowIndex, int mColIndex) {
                return false;
            }
        };
        this.table1.setModel(model);
        this.table1.setAutoCreateRowSorter(true);
    }

    public JButton getSelectButton() {
        return selectButton;
    }

    public void setSelectButton(JButton selectButton) {
        this.selectButton = selectButton;
    }

    public JButton getAddButton() {
        return addButton;
    }

    public void setAddButton(JButton addButton) {
        this.addButton = addButton;
    }

    public JButton getEditButton() {
        return editButton;
    }

    public void setEditButton(JButton editButton) {
        this.editButton = editButton;
    }

    public JButton getDeleteButton() {
        return deleteButton;
    }

    public void setDeleteButton(JButton deleteButton) {
        this.deleteButton = deleteButton;
    }

    public JButton getExitButton() {
        return exitButton;
    }

    public void setExitButton(JButton exitButton) {
        this.exitButton = exitButton;
    }

    public DataBaseConnector getDataBaseConnector() {
        return dataBaseConnector;
    }

    public void setDataBaseConnector(DataBaseConnector dataBaseConnector) {
        this.dataBaseConnector = dataBaseConnector;
    }

    public JTable getTable1() {
        return table1;
    }

    public void setTable1(JTable table1) {
        this.table1 = table1;
    }
}

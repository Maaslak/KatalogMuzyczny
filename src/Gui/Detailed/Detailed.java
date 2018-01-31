package Gui.Detailed;

//import Gui.Scrollable.Scrollable;
import DataBase.DataBaseConnector;
import Gui.Detailed.Detailed;

import javax.swing.*;
import java.awt.*;
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
    private DataBaseConnector dataBaseConnector;
    private JFrame father;

    public Detailed(DataBaseConnector dataBaseConnector, JFrame father){
        this.dataBaseConnector = dataBaseConnector;
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
}

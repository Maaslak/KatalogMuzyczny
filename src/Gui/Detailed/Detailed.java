package Gui.Detailed;

import Gui.Scrollable.Scrollable;

import javax.swing.*;
import java.awt.*;

public abstract class Detailed extends JPanel {

    private JPanel mainPanel;
    private JTable table1;
    protected JPanel informationPanel;
    private JButton selectButton;
    private JButton addButton;
    private JButton editButton;
    private JButton deleteButton;

    public Detailed(){

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
}

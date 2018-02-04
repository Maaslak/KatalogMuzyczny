package Gui.Change;

import DataBase.DataBaseConnector;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public abstract class Change extends JFrame{
    private JPanel mainPanel;
    private JButton okButton;
    private JButton cancelButton;
    protected JPanel addPanel;
    private DataBaseConnector dataBaseConnector;
    private JFrame father;

    public Change(DataBaseConnector dataBaseConnector, JFrame father) {
        this.setDataBaseConnector(dataBaseConnector);
        this.setFather(father);
        this.setContentPane(mainPanel);
        this.pack();
        getCancelButton().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                super.mouseClicked(mouseEvent);
                setVisible(false);
                getFather().setVisible(true);
            }
        });
    }



    public JButton getOkButton() {
        return okButton;
    }

    public void setOkButton(JButton okButton) {
        this.okButton = okButton;
    }

    public JButton getCancelButton() {
        return cancelButton;
    }

    public void setCancelButton(JButton cancelButton) {
        this.cancelButton = cancelButton;
    }

    public JFrame getFather() {
        return father;
    }

    public void setFather(JFrame father) {
        this.father = father;
    }

    public DataBaseConnector getDataBaseConnector() {
        return dataBaseConnector;
    }

    public void setDataBaseConnector(DataBaseConnector dataBaseConnector) {
        this.dataBaseConnector = dataBaseConnector;
    }
}

package Gui.Change;

import DataBase.DataBaseConnector;
import JavaObjects.Gatunek;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AddOrEditGenereWindow extends Change {
    private Gatunek gatunek;
    private JTextField name;
    private JTextArea description;
    private JLabel nameLabel;
    private JLabel descriptionLabel;
    private GridBagConstraints c;

    public AddOrEditGenereWindow(DataBaseConnector dataBaseConnector, JFrame father) {
        super(dataBaseConnector, father);
        setComponents();
        setLisnersForAdding();
    }

    public AddOrEditGenereWindow(DataBaseConnector dataBaseConnector, JFrame father, Gatunek gatunek) {
        super(dataBaseConnector, father);
        this.gatunek = gatunek;
        setComponents();
        setComponentsContentForEditing();
        setLisnersForEdit();
    }

    private void setComponents(){
        nameLabel = new JLabel("Name: ");
        descriptionLabel = new JLabel("Description");
        name = new JTextField("", 10);
        description = new JTextArea(10, 10);
        description.setLineWrap(true);
        description.setWrapStyleWord(true);
        c = new GridBagConstraints();
        c.gridy = 1;
        c.gridx = 1;
        this.addPanel.add(nameLabel, c);
        c.gridy = 1;
        c.gridx = 2;
        this.addPanel.add(name, c);
        c.gridy = 2;
        c.gridx = 1;
        this.addPanel.add(descriptionLabel, c);
        c.gridy = 2;
        c.gridx = 2;
        this.addPanel.add(description, c);
        this.pack();

    }

    private void setComponentsContentForEditing(){
        name.setText(gatunek.getNazwa());
        description.setText(gatunek.getOpis());
        name.setEnabled(false);
    }

    public void setLisnersForAdding(){
        getOkButton().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                super.mouseClicked(mouseEvent);
                try{
                    String nameText = name.getText();
                    String descriptionText = description.getText();
                    getDataBaseConnector().insertGatunek(nameText, descriptionText);
                    ((AddOrEditArtistWindow)getFather()).addGatunek(nameText);
                } catch(Exception e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null, e.getMessage(), "alert", JOptionPane.ERROR_MESSAGE);
                }
                setVisible(false);
                getFather().setVisible(true);
            }
        });
    }

    public void setLisnersForEdit(){
        getOkButton().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                super.mouseClicked(mouseEvent);
                try{
                    String nameText = name.getText();
                    String descriptionText = description.getText();
                    getDataBaseConnector().updateGatunek(nameText, descriptionText);
                } catch(Exception e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null, e.getMessage(), "alert", JOptionPane.ERROR_MESSAGE);
                }
                setVisible(false);
                getFather().setVisible(true);
            }
        });
    }
}

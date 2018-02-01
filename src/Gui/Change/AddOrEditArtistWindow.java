package Gui.Change;

import DataBase.DataBaseConnector;
import JavaObjects.Album;
import JavaObjects.Koncert;
import JavaObjects.Zespol;
import com.github.lgooddatepicker.components.DatePicker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class AddOrEditArtistWindow extends Change {

    private JLabel zespolName, name, date, city, country;
    private JTextField koncertJTextField, nameJTextField, cityJTextField, countryJTextField;
    private JComboBox zespolyJComboBox;
    private DatePicker dateDatePicker;
    private Koncert koncert;
    private Zespol zespolEdit;
    private GridBagConstraints c;

    /*public AddOrEditArtistWindow(DataBaseConnector dataBaseConnector, Koncert koncert, JFrame father) {
        super(dataBaseConnector, father);
        this.koncert = koncert;
        setAddArtistInConcert();
        mouse();
    }*/

    public AddOrEditArtistWindow(DataBaseConnector dataBaseConnector, JFrame father) {
        super(dataBaseConnector, father);
        setAddArtist();
        mouse(); //TODO przeciazyc
    }

    /*public AddOrEditArtistWindow(DataBaseConnector dataBaseConnector, Koncert koncert, JFrame father, Zespol zespol) {
        super(dataBaseConnector, father);
        this.zespolEdit = zespol;
        setEditArtistInConcert();
        mouse(); //TODO przeciazyc
    }*/

    public AddOrEditArtistWindow(DataBaseConnector dataBaseConnector, JFrame father, Zespol zespol) {
        super(dataBaseConnector, father);
        this.zespolEdit = zespol;
        setEditArtist();
        mouse(); //TODO przeciazyc
    }




    public void setAddArtistInConcert(){
        this.zespolName = new JLabel("Name");
        try {
            ArrayList<Zespol> zespoly = getDataBaseConnector().getZespoly();
            this.zespolyJComboBox = new JComboBox();
            for(int i =0; i<zespoly.size();i++)
                this.zespolyJComboBox.addItem(zespoly.get(i).getNazwa());
        } catch (Exception e) {
            e.printStackTrace();
        }

        c = new GridBagConstraints();
        c.gridy = 1;
        c.gridx = 1;
        addPanel.add(zespolName, c);
        c.gridy = 1;
        c.gridx = 2;
        addPanel.add(zespolyJComboBox, c);

        this.pack();
    }

    public void setAddArtist(){
        this.name = new JLabel("Name");
        this.date = new JLabel("Date");
        this.city = new JLabel("City");
        this.country = new JLabel("Country");

        this.nameJTextField = new JTextField(5);
        this.dateDatePicker = new DatePicker();
        this.cityJTextField = new JTextField(5);
        this.countryJTextField = new JTextField(5);

        c = new GridBagConstraints();
        c.gridy = 2;
        c.gridx = 1;
        addPanel.add(name, c);
        c.gridy = 3;
        c.gridx = 1;
        addPanel.add(date, c);
        c.gridy = 4;
        c.gridx = 1;
        addPanel.add(city, c);
        c.gridy = 5;
        c.gridx = 1;
        addPanel.add(country, c);
        c.gridy = 2;
        c.gridx = 2;
        addPanel.add(nameJTextField, c);
        c.gridy = 3;
        c.gridx = 2;
        addPanel.add(dateDatePicker, c);
        c.gridy = 4;
        c.gridx = 2;
        addPanel.add(cityJTextField, c);
        c.gridy = 5;
        c.gridx = 2;
        addPanel.add(countryJTextField, c);
        this.pack();
    }

    public void setEditArtistInConcert(){
        this.zespolName = new JLabel("Name");
        try {
            ArrayList<Zespol> zespoly = getDataBaseConnector().getZespoly();
            this.zespolyJComboBox = new JComboBox();
            for(int i =0; i<zespoly.size();i++)
                this.zespolyJComboBox.addItem(zespoly.get(i).getNazwa());
        } catch (Exception e) {
            e.printStackTrace();
        }

        c = new GridBagConstraints();
        c.gridy = 1;
        c.gridx = 1;
        addPanel.add(zespolName, c);
        c.gridy = 1;
        c.gridx = 2;
        addPanel.add(zespolyJComboBox, c);

        this.pack();
    }

    public void setEditArtist(){
        this.name = new JLabel("Name");
        this.date = new JLabel("Date");
        this.city = new JLabel("City");
        this.country = new JLabel("Country");

        this.nameJTextField = new JTextField(zespolEdit.getNazwa(),5);
        this.dateDatePicker = new DatePicker();
        this.cityJTextField = new JTextField(zespolEdit.getMiasto_zalozenia(),5);
        this.countryJTextField = new JTextField(zespolEdit.getKraj_zalozenia(),5);

        c = new GridBagConstraints();
        c.gridy = 1;
        c.gridx = 1;
        addPanel.add(this.name, c);
        c.gridy = 2;
        c.gridx = 1;
        addPanel.add(date, c);
        c.gridy = 3;
        c.gridx = 1;
        addPanel.add(city, c);
        c.gridy = 4;
        c.gridx = 1;
        addPanel.add(country, c);
        c.gridy = 1;
        c.gridx = 2;
        addPanel.add(nameJTextField, c);
        c.gridy = 2;
        c.gridx = 2;
        addPanel.add(dateDatePicker, c);
        c.gridy = 3;
        c.gridx = 2;
        addPanel.add(cityJTextField, c);
        c.gridy = 4;
        c.gridx = 2;
        addPanel.add(countryJTextField, c);
        this.pack();
    }

    public void mouse(){
        getOkButton().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                super.mouseClicked(mouseEvent);
                setVisible(false);
                getFather().setVisible(true);
            }
        });
    }
}

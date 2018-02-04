package Gui.Change;

import DataBase.DataBaseConnector;
import JavaObjects.Album;
import JavaObjects.Koncert;
import JavaObjects.Utwor;
import JavaObjects.Zespol;
import com.github.lgooddatepicker.components.DatePicker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.sql.Timestamp;

public class AddOrEditSongWindow extends Change{

    private JLabel title, time;
    private JTextField titleJTextField, timeJTextField;
    private Album album;
    private Utwor utworEdit;
    private GridBagConstraints c;

    public AddOrEditSongWindow(DataBaseConnector dataBaseConnector, Album album, JFrame father) {
        super(dataBaseConnector, father);
        this.album = album;
        setAddSong();
    }

    public AddOrEditSongWindow(DataBaseConnector dataBaseConnector, Album album, JFrame father, Utwor utworEdit) {
        super(dataBaseConnector, father);
        this.utworEdit = utworEdit;
        this.album = album;
        //setEditSong();
    }

    public void setAddSong(){
        this.title = new JLabel("Title");
        this.time = new JLabel("Time[in sec]");

        this.titleJTextField = new JTextField(5);
        this.timeJTextField = new JTextField(5);

        c = new GridBagConstraints();
        c.gridy = 1;
        c.gridx = 1;
        addPanel.add(title, c);
        c.gridy = 2;
        c.gridx = 1;
        addPanel.add(time, c);
        c.gridy = 1;
        c.gridx = 2;
        addPanel.add(titleJTextField, c);
        c.gridy = 2;
        c.gridx = 2;
        addPanel.add(timeJTextField, c);
        this.pack();

        getOkButton().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                super.mouseClicked(mouseEvent);
                int time_sec = 0;
                if(!timeJTextField.getText().isEmpty())
                    time_sec = Integer.valueOf(timeJTextField.getText());
                try {
                    int time_min = time_sec/60;
                    //time_sec = time_sec%60;
                    Timestamp timestamp = new Timestamp(time_sec*1000);
                    Utwor utwor = new Utwor(titleJTextField.getText(), timestamp);
                    getDataBaseConnector().insertUtwor(utwor,album.getId());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                setVisible(false);
                getFather().setVisible(true);
            }
        });
    }

    /*public void setEditSong(){
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
    }*/

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

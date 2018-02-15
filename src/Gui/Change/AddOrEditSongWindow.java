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
import java.text.NumberFormat;

public class AddOrEditSongWindow extends Change{

    private JLabel title, time;
    private JTextField titleJTextField;
    private JFormattedTextField timeJTextField;
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
        setEditSong();
    }

    public void setAddSong(){
        this.title = new JLabel("Title");
        this.time = new JLabel("Time[in sec]");

        this.titleJTextField = new JTextField(5);
        NumberFormat amountFormat = NumberFormat.getNumberInstance();
        timeJTextField = new JFormattedTextField(amountFormat);
        timeJTextField.setColumns(5);

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
                    JOptionPane.showMessageDialog(null, e.getMessage(), "alert", JOptionPane.ERROR_MESSAGE);
                }
                setVisible(false);
                getFather().setVisible(true);
            }
        });
    }

    public void setEditSong(){
        this.title = new JLabel("Title");
        this.time = new JLabel("Time[in sec]");

        this.titleJTextField = new JTextField(utworEdit.getTytul(),5);
        this.timeJTextField = (JFormattedTextField) new JTextField(5);

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
                    //Utwor utwor = new Utwor(titleJTextField.getText(), timestamp);
                    getDataBaseConnector().updateUtwor(timestamp,titleJTextField.getText());
                } catch (Exception e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null, e.getMessage(), "alert", JOptionPane.ERROR_MESSAGE);
                }
                setVisible(false);
                getFather().setVisible(true);
            }
        });
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

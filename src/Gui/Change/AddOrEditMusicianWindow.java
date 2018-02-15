package Gui.Change;

import DataBase.DataBaseConnector;
import JavaObjects.Album;
import JavaObjects.Muzyk;
import JavaObjects.Utwor;
//import javafx.scene.control.DatePicker;
import com.github.lgooddatepicker.components.DatePicker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.sql.Timestamp;

public class AddOrEditMusicianWindow extends Change{

    private JLabel imie, nazwisko, pochodzenie, data_ur;
    private JTextField imieJTextField, nazwiskoJTextField, pochodzenieJTextField;
    private DatePicker dateDatePicker;
    private Muzyk muzykEdit;
    private GridBagConstraints c;

    public AddOrEditMusicianWindow(DataBaseConnector dataBaseConnector, JFrame father) {
        super(dataBaseConnector, father);
        setAddMusician();
    }

    public AddOrEditMusicianWindow(DataBaseConnector dataBaseConnector, JFrame father, Muzyk muzykEdit) {
        super(dataBaseConnector, father);
        this.muzykEdit = muzykEdit;
        setEditMusician();
    }

    public void setAddMusician(){
        this.imie = new JLabel("Imie");
        this.nazwisko = new JLabel("Nazwisko");
        this.pochodzenie = new JLabel("Pochodzenie");
        this.data_ur = new JLabel("Data Urodzenia");

        this.imieJTextField = new JTextField(5);
        this.nazwiskoJTextField = new JTextField(5);
        this.pochodzenieJTextField = new JTextField(5);
        this.dateDatePicker = new DatePicker();

        c = new GridBagConstraints();
        c.gridy = 1;
        c.gridx = 1;
        addPanel.add(this.imie, c);
        c.gridy = 2;
        c.gridx = 1;
        addPanel.add(this.nazwisko, c);
        c.gridy = 3;
        c.gridx = 1;
        addPanel.add(this.pochodzenie, c);
        c.gridy = 4;
        c.gridx = 1;
        addPanel.add(this.data_ur, c);

        c.gridy = 1;
        c.gridx = 2;
        addPanel.add(this.imieJTextField, c);
        c.gridy = 2;
        c.gridx = 2;
        addPanel.add(this.nazwiskoJTextField, c);
        c.gridy = 3;
        c.gridx = 2;
        addPanel.add(this.pochodzenieJTextField, c);
        c.gridy = 4;
        c.gridx = 2;
        addPanel.add(this.dateDatePicker, c);
        this.pack();

        getOkButton().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                super.mouseClicked(mouseEvent);
                Date dateFrom = null;
                if(dateDatePicker.getDate() != null)
                    dateFrom = Date.valueOf(dateDatePicker.getDate());
                try {
                    getDataBaseConnector().insertMuzyk(imieJTextField.getText(), nazwiskoJTextField.getText(), dateFrom, pochodzenieJTextField.getText());
                } catch (Exception e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null, e.getMessage(), "alert", JOptionPane.ERROR_MESSAGE);
                }
                setVisible(false);
                getFather().setVisible(true);
            }
        });
    }

    public void setEditMusician(){
        this.imie = new JLabel("Imie");
        this.nazwisko = new JLabel("Nazwisko");
        this.pochodzenie = new JLabel("Pochodzenie");
        this.data_ur = new JLabel("Data Urodzenia");

        this.imieJTextField = new JTextField(muzykEdit.getImie(),5);
        this.nazwiskoJTextField = new JTextField(muzykEdit.getNazwisko(),5);
        this.pochodzenieJTextField = new JTextField(muzykEdit.getPochodzenie(),5);
        this.dateDatePicker = new DatePicker();

        c = new GridBagConstraints();
        c.gridy = 1;
        c.gridx = 1;
        addPanel.add(this.imie, c);
        c.gridy = 2;
        c.gridx = 1;
        addPanel.add(this.nazwisko, c);
        c.gridy = 3;
        c.gridx = 1;
        addPanel.add(this.pochodzenie, c);
        c.gridy = 4;
        c.gridx = 1;
        addPanel.add(this.data_ur, c);

        c.gridy = 1;
        c.gridx = 2;
        addPanel.add(this.imieJTextField, c);
        c.gridy = 2;
        c.gridx = 2;
        addPanel.add(this.nazwiskoJTextField, c);
        c.gridy = 3;
        c.gridx = 2;
        addPanel.add(this.pochodzenieJTextField, c);
        c.gridy = 4;
        c.gridx = 2;
        addPanel.add(this.dateDatePicker, c);
        this.pack();


        getOkButton().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                super.mouseClicked(mouseEvent);
                Date dateFrom = null;
                if(dateDatePicker.getDate() != null)
                    dateFrom = Date.valueOf(dateDatePicker.getDate());
                try {
                    getDataBaseConnector().updateMuzyk(imieJTextField.getText(), nazwiskoJTextField.getText(), dateFrom, pochodzenieJTextField.getText());
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

package Gui.Change;

import DataBase.DataBaseConnector;
import JavaObjects.Album;
import JavaObjects.Zespol;
import com.github.lgooddatepicker.components.DatePicker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AddAlbumWindow extends Change {

    private JLabel zespolname, name, date, rating, language;
    private JTextField zespolJTextField, nameJTextField, ratingJTextField, languageJTextField;
    private DatePicker dateDatePicker;
    private Zespol zespol;
    private Album album;
    private GridBagConstraints c;

    public AddAlbumWindow(DataBaseConnector dataBaseConnector, Zespol zespol, JFrame father) {
        super(dataBaseConnector, father);
        this.zespol = zespol;
        setAddPanel();
        mouse();
    }

    public AddAlbumWindow(DataBaseConnector dataBaseConnector, JFrame father) {
        super(dataBaseConnector, father);
        setAddPanelwithoutzespol();
        mouse(); //TODO przeciazyc
    }

    public void setAddPanel(){
        this.name = new JLabel("Name");
        this.date = new JLabel("Date");
        this.rating = new JLabel("Rate");
        this.language = new JLabel("Language");

        this.nameJTextField = new JTextField();
        this.dateDatePicker = new DatePicker();
        this.ratingJTextField = new JTextField();
        this.languageJTextField = new JTextField();

        c = new GridBagConstraints();
        c.gridy = 1;
        c.gridx = 1;
        addPanel.add(this.name, c);
        c.gridy = 2;
        c.gridx = 1;
        addPanel.add(date, c);
        c.gridy = 3;
        c.gridx = 1;
        addPanel.add(rating, c);
        c.gridy = 4;
        c.gridx = 1;
        addPanel.add(language, c);
        c.gridy = 1;
        c.gridx = 2;
        addPanel.add(nameJTextField, c);
        c.gridy = 2;
        c.gridx = 2;
        addPanel.add(dateDatePicker, c);
        c.gridy = 3;
        c.gridx = 2;
        addPanel.add(ratingJTextField, c);
        c.gridy = 4;
        c.gridx = 2;
        addPanel.add(languageJTextField, c);
        this.pack();
    }

    public void setAddPanelwithoutzespol(){
        this.zespolname = new JLabel("Artist");
        this.name = new JLabel("Name");
        this.date = new JLabel("Date");
        this.rating = new JLabel("Rate");
        this.language = new JLabel("Language");

        this.zespolJTextField = new JTextField();
        nameJTextField = new JTextField(5);
        dateDatePicker = new DatePicker();
        ratingJTextField = new JTextField(5);
        languageJTextField = new JTextField(5);

        c = new GridBagConstraints();
        c.gridy = 1;
        c.gridx = 1;
        addPanel.add(this.zespolname, c);
        c.gridy = 2;
        c.gridx = 1;
        addPanel.add(name, c);
        c.gridy = 3;
        c.gridx = 1;
        addPanel.add(date, c);
        c.gridy = 4;
        c.gridx = 1;
        addPanel.add(rating, c);
        c.gridy = 5;
        c.gridx = 1;
        addPanel.add(language, c);
        c.gridy = 1;
        c.gridx = 2;
        addPanel.add(zespolJTextField, c);
        c.gridy = 2;
        c.gridx = 2;
        addPanel.add(nameJTextField, c);
        c.gridy = 3;
        c.gridx = 2;
        addPanel.add(dateDatePicker, c);
        c.gridy = 4;
        c.gridx = 2;
        addPanel.add(ratingJTextField, c);
        c.gridy = 5;
        c.gridx = 2;
        addPanel.add(languageJTextField, c);
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

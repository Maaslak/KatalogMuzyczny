package Gui.Change;

import DataBase.DataBaseConnector;
import JavaObjects.Album;
import JavaObjects.Zespol;
import com.github.lgooddatepicker.components.DatePicker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.util.ArrayList;

public class AddOrEditAlbumWindow extends Change {

    private JLabel zespolname, name, date, rating, language;
    private JTextField zespolJTextField, nameJTextField, ratingJTextField, languageJTextField;
    private JComboBox zespolyJComboBox;
    private DatePicker dateDatePicker;
    private Zespol zespol;
    private Album albumEdit;
    private GridBagConstraints c;

    public AddOrEditAlbumWindow(DataBaseConnector dataBaseConnector, JFrame father) {
        super(dataBaseConnector, father);
        setAddPanelwithoutzespol();
    }

    public AddOrEditAlbumWindow(DataBaseConnector dataBaseConnector, JFrame father, Album albumEdit) {
        super(dataBaseConnector, father);
        this.albumEdit = albumEdit;
        setEditPanelwithoutzespol();
    }

    public AddOrEditAlbumWindow(DataBaseConnector dataBaseConnector, Zespol zespol, JFrame father) {
        super(dataBaseConnector, father);
        this.zespol = zespol;
        setAdd();
    }

    public AddOrEditAlbumWindow(DataBaseConnector dataBaseConnector, Zespol zespol, JFrame father, Album albumEdit) {
        super(dataBaseConnector, father);
        this.zespol = zespol;
        this.albumEdit = albumEdit;
        setEdit();
    }

    public void setAddPanelwithoutzespol(){
        this.zespolname = new JLabel("Artist");
        this.name = new JLabel("Name");
        this.date = new JLabel("Date");
        this.rating = new JLabel("Rate");
        this.language = new JLabel("Language");
        try {
            ArrayList<Zespol> zespoly = getDataBaseConnector().getZespoly();
            this.zespolyJComboBox = new JComboBox();
            for(int i =0; i<zespoly.size();i++)
                this.zespolyJComboBox.addItem(zespoly.get(i).getNazwa());

            //this.zespolJTextField = new JTextField(5);
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
            addPanel.add(zespolyJComboBox, c);
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
            getOkButton().addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent mouseEvent) {
                    super.mouseClicked(mouseEvent);
                    Date dateFrom = null;
                    Float ratingFloat = null;
                    Integer row = zespolyJComboBox.getSelectedIndex();
                    if(dateDatePicker.getDate() != null)
                        dateFrom = Date.valueOf(dateDatePicker.getDate());
                    if(!ratingJTextField.getText().isEmpty())
                        ratingFloat = Float.valueOf(ratingJTextField.getText());
                    try {
                        Album album = new Album(nameJTextField.getText(), dateFrom,ratingFloat,languageJTextField.getText());
                        getDataBaseConnector().insertAlbum(album, zespoly.get(row).getId());
                    } catch (Exception e) {
                        e.printStackTrace();
                        JOptionPane.showMessageDialog(null, e.getMessage(), "alert", JOptionPane.ERROR_MESSAGE);
                    }
                    setVisible(false);
                    getFather().setVisible(true);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e.getMessage(), "alert", JOptionPane.ERROR_MESSAGE);
        }
    }

    void setEditPanelwithoutzespol(){
        this.zespolname = new JLabel("Artist");
        this.name = new JLabel("Name");
        this.date = new JLabel("Date");
        this.rating = new JLabel("Rate");
        this.language = new JLabel("Language");
        try {
            ArrayList<Zespol> zespoly = getDataBaseConnector().getZespoly();
            this.zespolyJComboBox = new JComboBox();
            for(int i =0; i<zespoly.size();i++)
                this.zespolyJComboBox.addItem(zespoly.get(i).getNazwa());

            //this.zespolJTextField = new JTextField(5);
            nameJTextField = new JTextField(albumEdit.getNazwa(),5);
            dateDatePicker = new DatePicker();
            dateDatePicker.setDate(albumEdit.getDate().toLocalDate());
            ratingJTextField = new JTextField(5);
            languageJTextField = new JTextField(albumEdit.getJezyk(),5);

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
            addPanel.add(zespolyJComboBox, c);
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
            getOkButton().addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent mouseEvent) {
                    super.mouseClicked(mouseEvent);
                    Date dateFrom = null;
                    Float ratingFloat = null;
                    Integer row = zespolyJComboBox.getSelectedIndex();
                    if(dateDatePicker.getDate() != null)
                        dateFrom = Date.valueOf(dateDatePicker.getDate());
                    if(!ratingJTextField.getText().isEmpty())
                        ratingFloat = Float.valueOf(ratingJTextField.getText());
                    try {
                        //Album album = new Album(nameJTextField.getText(), dateFrom,ratingFloat,languageJTextField.getText());
                        //getDataBaseConnector().insertAlbum(album, zespoly.get(row).getId());
                        getDataBaseConnector().updateAlbum(nameJTextField.getText(), dateFrom,ratingFloat,languageJTextField.getText(),albumEdit.getId());
                    } catch (Exception e) {
                        e.printStackTrace();
                        JOptionPane.showMessageDialog(null, e.getMessage(), "alert", JOptionPane.ERROR_MESSAGE);
                    }
                    setVisible(false);
                    getFather().setVisible(true);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e.getMessage(), "alert", JOptionPane.ERROR_MESSAGE);
        }
    }

    /*public void setEditPanel(){
        this.name = new JLabel("Name");
        this.date = new JLabel("Date");
        this.rating = new JLabel("Rate");
        this.language = new JLabel("Language");

        this.nameJTextField = new JTextField(albumEdit.getNazwa(),5);
        this.dateDatePicker = new DatePicker();
        this.ratingJTextField = new JTextField(Float.toString(albumEdit.getOcena()),5);
        this.languageJTextField = new JTextField(albumEdit.getJezyk(),5);

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
    }*/

    public void setAdd(){
        //z id zespolu
        this.name = new JLabel("Name");
        this.date = new JLabel("Date");
        this.rating = new JLabel("Rate");
        this.language = new JLabel("Language");

        this.nameJTextField = new JTextField(5);
        this.dateDatePicker = new DatePicker();
        this.ratingJTextField = new JTextField(5);
        this.languageJTextField = new JTextField(5);

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
        getOkButton().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                super.mouseClicked(mouseEvent);
                Date dateFrom = null;
                Float ratingFloat = null;
                if(dateDatePicker.getDate() != null)
                    dateFrom = Date.valueOf(dateDatePicker.getDate());
                if(!ratingJTextField.getText().isEmpty())
                    ratingFloat = Float.valueOf(ratingJTextField.getText());
                try {
                    Album album = new Album(nameJTextField.getText(), dateFrom,ratingFloat,languageJTextField.getText());
                    getDataBaseConnector().insertAlbum(album, zespol.getId());
                } catch (Exception e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null, e.getMessage(), "alert", JOptionPane.ERROR_MESSAGE);
                }
                setVisible(false);
                getFather().setVisible(true);
            }
        });
    }

    public void setEdit(){
        //z id zespolu
        this.name = new JLabel("Name");
        this.date = new JLabel("Date");
        this.rating = new JLabel("Rate");
        this.language = new JLabel("Language");

        this.nameJTextField = new JTextField(albumEdit.getNazwa(),5);
        this.dateDatePicker = new DatePicker();
        dateDatePicker.setDate(albumEdit.getDate().toLocalDate());
        this.ratingJTextField = new JTextField(Float.toString(albumEdit.getOcena()),5);
        this.languageJTextField = new JTextField(albumEdit.getJezyk(),5);

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

package Gui.Change;

import DataBase.DataBaseConnector;
import JavaObjects.Festiwal;
import JavaObjects.Koncert;
import JavaObjects.Zespol;
import com.github.lgooddatepicker.components.DatePicker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.util.ArrayList;

public class AddOrEditConcertWindow extends Change{
    private JLabel koncert_name, name, zespol, date, city, festival;
    private JTextField koncertJTextField, nameJTextField, cityJTextField;
    private JComboBox zespolyJComboBox, concertJComboBox, festivalJComboBox;
    private DatePicker dateDatePicker;
    private Festiwal festiwal;
    private Koncert koncertEdit;
    private GridBagConstraints c;

    public AddOrEditConcertWindow(DataBaseConnector dataBaseConnector, JFrame father) {
        super(dataBaseConnector, father);
        setAddConcert();
        mouse(); //TODO przeciazyc
    }

    public AddOrEditConcertWindow(DataBaseConnector dataBaseConnector, JFrame father, Koncert koncertEdit) {
        super(dataBaseConnector, father);
        this.koncertEdit = koncertEdit;
        setEditConcert();
        mouse(); //TODO przeciazyc
    }

    public AddOrEditConcertWindow(DataBaseConnector dataBaseConnector, Festiwal festival, JFrame father) {
        super(dataBaseConnector, father);
        this.koncertEdit = koncertEdit;
        setAddConcertInFestival();
        mouse(); //TODO przeciazyc
    }

    public AddOrEditConcertWindow(DataBaseConnector dataBaseConnector, Festiwal festival, JFrame father, Koncert koncertEdit) {
        super(dataBaseConnector, father);
        this.koncertEdit = koncertEdit;
        setEditConcertInFestival();
        mouse(); //TODO przeciazyc
    }

    public void setAddConcert(){
        this.name = new JLabel("Name");
        this.zespol = new JLabel("Artist");
        this.date = new JLabel("Date");
        this.city = new JLabel("City");
        this.festival = new JLabel("Festival");

        this.nameJTextField = new JTextField(5);
        try {
            ArrayList<Zespol> zespoly = getDataBaseConnector().getZespoly();
            this.zespolyJComboBox = new JComboBox();
            for(int i =0; i<zespoly.size();i++)
                this.zespolyJComboBox.addItem(zespoly.get(i).getNazwa());

            this.dateDatePicker = new DatePicker();
            this.cityJTextField = new JTextField(5);

            ArrayList<Festiwal> festiwale = getDataBaseConnector().getFestiwale();
            this.festivalJComboBox = new JComboBox();
            for(int i =0; i<festiwale.size();i++)
                this.zespolyJComboBox.addItem(festiwale.get(i).getNazwa());

            c = new GridBagConstraints();
            c.gridy = 1;
            c.gridx = 1;
            addPanel.add(name, c);
            c.gridy = 2;
            c.gridx = 1;
            addPanel.add(zespol, c);
            c.gridy = 3;
            c.gridx = 1;
            addPanel.add(date, c);
            c.gridy = 4;
            c.gridx = 1;
            addPanel.add(city, c);
            c.gridy = 1;
            c.gridx = 2;
            addPanel.add(nameJTextField, c);
            c.gridy = 2;
            c.gridx = 2;
            addPanel.add(zespolyJComboBox, c);
            c.gridy = 3;
            c.gridx = 2;
            addPanel.add(dateDatePicker, c);
            c.gridy = 4;
            c.gridx = 2;
            addPanel.add(cityJTextField, c);
            this.pack();
            getOkButton().addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent mouseEvent) {
                    super.mouseClicked(mouseEvent);
                    Integer row_zespol = zespolyJComboBox.getSelectedIndex();
                    Integer row_festival;
                    if(festivalJComboBox.getSelectedIndex()>-1)
                        row_festival = festivalJComboBox.getSelectedIndex();
                    else
                        row_festival = null;

                    Date dateFrom = null;
                    if(dateDatePicker.getDate() != null)
                        dateFrom = Date.valueOf(dateDatePicker.getDate());
                    try {
                        getDataBaseConnector().insertKoncert(nameJTextField.getText(),dateFrom,cityJTextField.getText(),zespoly.get(row_zespol).getId(),festiwale.get(row_festival).getId());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    setVisible(false);
                    getFather().setVisible(true);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setEditConcert(){
        this.name = new JLabel("Name");
        this.zespol = new JLabel("Artist");
        this.date = new JLabel("Date");
        this.city = new JLabel("City");

        this.nameJTextField = new JTextField(koncertEdit.getNazwa(),5);
        try {
            ArrayList<Zespol> zespoly = getDataBaseConnector().getZespoly();
            this.zespolyJComboBox = new JComboBox();
            for(int i =0; i<zespoly.size();i++)
                this.zespolyJComboBox.addItem(zespoly.get(i).getNazwa());
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.dateDatePicker = new DatePicker();
        this.cityJTextField = new JTextField(koncertEdit.getMiasto_nazwa(),5);

        c = new GridBagConstraints();
        c.gridy = 1;
        c.gridx = 1;
        addPanel.add(name, c);
        c.gridy = 2;
        c.gridx = 1;
        addPanel.add(zespol, c);
        c.gridy = 3;
        c.gridx = 1;
        addPanel.add(date, c);
        c.gridy = 4;
        c.gridx = 1;
        addPanel.add(city, c);
        c.gridy = 1;
        c.gridx = 2;
        addPanel.add(nameJTextField, c);
        c.gridy = 2;
        c.gridx = 2;
        addPanel.add(zespolyJComboBox, c);
        c.gridy = 3;
        c.gridx = 2;
        addPanel.add(dateDatePicker, c);
        c.gridy = 4;
        c.gridx = 2;
        addPanel.add(cityJTextField, c);
        this.pack();
    }

    public void setAddConcertInFestival(){
        this.koncert_name = new JLabel("Concert");
        try {
            ArrayList<Koncert> koncerty = getDataBaseConnector().getKoncert();
            this.concertJComboBox = new JComboBox();
            for(int i =0; i<koncerty.size();i++)
                this.concertJComboBox.addItem(koncerty.get(i).getNazwa());
        } catch (Exception e) {
            e.printStackTrace();
        }

        c = new GridBagConstraints();
        c.gridy = 1;
        c.gridx = 1;
        addPanel.add(koncert_name, c);
        c.gridy = 1;
        c.gridx = 2;
        addPanel.add(concertJComboBox, c);
        //update
        this.pack();
    }

    public void setEditConcertInFestival(){
        this.koncert_name = new JLabel("Concert");
        try {
            ArrayList<Koncert> koncerty = getDataBaseConnector().getKoncert();
            this.concertJComboBox = new JComboBox();
            for(int i =0; i<koncerty.size();i++)
                this.concertJComboBox.addItem(koncerty.get(i).getNazwa());
        } catch (Exception e) {
            e.printStackTrace();
        }

        c = new GridBagConstraints();
        c.gridy = 1;
        c.gridx = 1;
        addPanel.add(koncert_name, c);
        c.gridy = 1;
        c.gridx = 2;
        addPanel.add(concertJComboBox, c);

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

package Gui.Change;

import DataBase.DataBaseConnector;
import JavaObjects.Festiwal;
import JavaObjects.Koncert;
import JavaObjects.Miasto;
import JavaObjects.Zespol;
import com.github.lgooddatepicker.components.DatePicker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.util.ArrayList;

public class AddOrEditConcertWindow extends Change{
    private JLabel koncert_name, name, zespol, date, city, festival;
    private JTextField koncertJTextField, nameJTextField, cityJTextField;
    private JComboBox zespolyJComboBox, concertJComboBox, festivalJComboBox, cityJComboBox;
    private DatePicker dateDatePicker;
    private Festiwal festiwal;
    private Koncert koncertEdit;
    private GridBagConstraints c;
    private boolean isNewCity;

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
        this.cityJComboBox = new JComboBox();

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
            ArrayList<Miasto> miasta = getDataBaseConnector().getMiasta();
            for (Miasto miasto :
                    miasta) {
                this.cityJComboBox.addItem(miasto.getNazwa());
            }
            AddOrEditConcertWindow temp = this;
            this.cityJComboBox.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    temp.cityJTextField.setText((String)temp.cityJComboBox.getSelectedItem());
                }
            });
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
            c.gridy = 4;
            c.gridx = 3;
            addPanel.add(cityJComboBox, c);
            this.pack();
            getOkButton().addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent mouseEvent) {
                    super.mouseClicked(mouseEvent);
                    try {
                        if(cityJTextField.getText().isEmpty())
                            throw new Exception("Please add city information");
                        System.out.println(cityJTextField.getText());
                    if(((DefaultComboBoxModel)cityJComboBox.getModel()).getIndexOf(cityJTextField.getText()) == -1) {
                        DialagForm dialog = new DialagForm("Do you want to add a new city?");
                        dialog.pack();
                        boolean accepted = dialog.customSetVisible(true);
                        if(!accepted)
                            throw new Exception("If you don't want to add a new city, please chose one from the drop downlist");
                    }
                    Integer row_zespol = zespolyJComboBox.getSelectedIndex();
                    Integer festiwalId;
                    if(festivalJComboBox.getSelectedIndex()>-1)
                        festiwalId = festiwale.get(festivalJComboBox.getSelectedIndex()).getId();
                    else
                        festiwalId = null;

                    Date dateFrom = null;
                    if(dateDatePicker.getDate() != null)
                        dateFrom = Date.valueOf(dateDatePicker.getDate());
                        Koncert koncert = new Koncert(nameJTextField.getText(), dateFrom,cityJTextField.getText(),zespoly.get(row_zespol).getId());
                        getDataBaseConnector().insertKoncert(koncert,festiwalId);
                    setVisible(false);
                    getFather().setVisible(true);
                    } catch (Exception e) {
                        e.printStackTrace();
                        JOptionPane.showMessageDialog(null, e.getMessage(), "alert", JOptionPane.ERROR_MESSAGE);
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e.getMessage(), "alert", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void setEditConcert(){
        this.name = new JLabel("Name");
        this.zespol = new JLabel("Artist");
        this.date = new JLabel("Date");
        this.city = new JLabel("City");
        this.cityJComboBox = new JComboBox();
        this.nameJTextField = new JTextField(koncertEdit.getNazwa(),5);
        try {
            ArrayList<Zespol> zespoly = getDataBaseConnector().getZespoly();
            this.zespolyJComboBox = new JComboBox();
            for(int i =0; i<zespoly.size();i++)
                this.zespolyJComboBox.addItem(zespoly.get(i).getNazwa());
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e.getMessage(), "alert", JOptionPane.ERROR_MESSAGE);
        }
        this.dateDatePicker = new DatePicker();
        this.cityJTextField = new JTextField(koncertEdit.getMiasto_nazwa(),5);
        ArrayList<Miasto> miasta = null;
        try {
            miasta = getDataBaseConnector().getMiasta();
            for (Miasto miasto :
                    miasta) {
                this.cityJComboBox.addItem(miasto);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "alert", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
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
            JOptionPane.showMessageDialog(null, e.getMessage(), "alert", JOptionPane.ERROR_MESSAGE);
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
            JOptionPane.showMessageDialog(null, e.getMessage(), "alert", JOptionPane.ERROR_MESSAGE);
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

    public void setNewCity(boolean newCity) {
        isNewCity = newCity;
    }
}

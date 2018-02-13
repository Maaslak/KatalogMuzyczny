package Gui.Change;

import DataBase.DataBaseConnector;
import JavaObjects.Gatunek;
import JavaObjects.Koncert;
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

public class AddOrEditArtistWindow extends Change {

    private JLabel zespolName, name, date, city, country, gatunekAdd, gatunekDel;
    private JTextField koncertJTextField, nameJTextField, cityJTextField, countryJTextField, newGatunek;
    private JButton addGatunekJButton = new JButton("Add new genere");
    private JButton editGatunekJButton = new JButton("Edit Genere");
    private JButton deleteGatunekJButton = new JButton("Delete Genere");
    private JComboBox zespolyJComboBox;
    private JComboBox gatunkiAddJComboBox;
    private JComboBox gatunkiDeleteJComboBox;
    private DatePicker dateDatePicker;
    private Koncert koncert;
    private Zespol zespolEdit;
    private GridBagConstraints c;
    private boolean externalGanunkiChange = false;
    private ArrayList<String> addedGeneres;
    private ArrayList<String> deletedGeneres;

    /*public AddOrEditArtistWindow(DataBaseConnector dataBaseConnector, Koncert koncert, JFrame father) {
        super(dataBaseConnector, father);
        this.koncert = koncert;
        setAddArtistInConcert();
        mouse();
    }*/

    public AddOrEditArtistWindow(DataBaseConnector dataBaseConnector, JFrame father) {
        super(dataBaseConnector, father);
        addedGeneres = new ArrayList<>();
        deletedGeneres = new ArrayList<>();
        setAddArtist();
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
        addedGeneres = new ArrayList<>();
        deletedGeneres = new ArrayList<>();
        setEditArtist();
    }




    /*public void setAddArtistInConcert(){
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
    }*/

    public void setAddArtist(){
        this.name = new JLabel("Name");
        this.date = new JLabel("Date");
        this.city = new JLabel("City");
        this.country = new JLabel("Country");
        this.gatunekAdd = new JLabel("Add genere");
        this.gatunekDel = new JLabel("Selected generes (delete)");
        this.gatunkiAddJComboBox = new JComboBox();
        this.gatunkiDeleteJComboBox = new JComboBox();
        try {
            ArrayList<Gatunek> gatunki = getDataBaseConnector().getGatunki(null, "");
            for (Gatunek gatunek :
                    gatunki) {
                this.gatunkiAddJComboBox.addItem(gatunek.getNazwa());
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e.getMessage(), "alert", JOptionPane.ERROR_MESSAGE);
        }
        this.gatunkiAddJComboBox.addActionListener (new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(((DefaultComboBoxModel)gatunkiDeleteJComboBox.getModel()).getIndexOf(gatunkiAddJComboBox.getSelectedItem()) == -1) {
                    externalGanunkiChange = true;
                    gatunkiDeleteJComboBox.addItem(gatunkiAddJComboBox.getSelectedItem());
                    if(addedGeneres.indexOf((String)gatunkiAddJComboBox.getSelectedItem()) == -1)
                        addedGeneres.add((String) gatunkiAddJComboBox.getSelectedItem());
                }
            }
        });
        this.gatunkiDeleteJComboBox.addActionListener (new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(!externalGanunkiChange) {
                    gatunkiDeleteJComboBox.removeItem(gatunkiDeleteJComboBox.getSelectedItem());
                    int index = addedGeneres.indexOf((String)gatunkiDeleteJComboBox.getSelectedItem());
                    if(index != -1)
                        addedGeneres.remove(index);
                    else
                        deletedGeneres.add((String) gatunkiDeleteJComboBox.getSelectedItem());
                }
                externalGanunkiChange = false;
            }
        });
        AddOrEditArtistWindow temp = this;
        this.addGatunekJButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddOrEditGenereWindow genereWindow = new AddOrEditGenereWindow(getDataBaseConnector(),temp);
                genereWindow.setVisible(true);
                temp.setVisible(false);
            }
        });
        this.editGatunekJButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Gatunek gatunek = getDataBaseConnector().getGatunki(null, (String) gatunkiAddJComboBox.getSelectedItem()).get(0);
                    AddOrEditGenereWindow genereWindow = new AddOrEditGenereWindow(getDataBaseConnector(), temp, gatunek);
                    genereWindow.setVisible(true);
                    temp.setVisible(false);
                }
                catch (Exception ex){
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "alert", JOptionPane.ERROR_MESSAGE);
                }


            }
        });

        this.deleteGatunekJButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String selected = (String)gatunkiAddJComboBox.getSelectedItem();
                    getDataBaseConnector().deleteGatunek(selected);
                    temp.setVisible(true);
                    gatunkiAddJComboBox.removeItem(selected);
                    gatunkiDeleteJComboBox.removeItem(selected);
                    addedGeneres.remove(selected);
                    deletedGeneres.remove(selected);
                }
                catch (Exception ex){
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "alert", JOptionPane.ERROR_MESSAGE);
                }


            }
        });

        this.gatunkiDeleteJComboBox.removeAllItems();
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
        c.gridy = 6;
        c.gridx = 1;
        addPanel.add(gatunekAdd, c);
        c.gridy = 6;
        c.gridx = 2;
        addPanel.add(gatunkiAddJComboBox, c);
        c.gridx = 3;
        addPanel.add(addGatunekJButton, c);
        c.gridx = 4;
        addPanel.add(editGatunekJButton, c);
        c.gridx = 5;
        addPanel.add(deleteGatunekJButton, c);
        c.gridy = 7;
        c.gridx = 1;
        addPanel.add(gatunekDel, c);
        c.gridy = 7;
        c.gridx = 2;
        addPanel.add(gatunkiDeleteJComboBox, c);
        this.pack();

        getOkButton().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                super.mouseClicked(mouseEvent);
                Date dateFrom = null;
                if(dateDatePicker.getDate() != null)
                    dateFrom = Date.valueOf(dateDatePicker.getDate());
                try {
                    Zespol zespol = new Zespol(nameJTextField.getText(),dateFrom,cityJTextField.getText(),countryJTextField.getText(), getDataBaseConnector());
                    getDataBaseConnector().insertZespol(zespol);
                    zespol = getDataBaseConnector().getZespoly(zespol.getNazwa(), zespol.getDate(), null, "", "").get(0);
                    for (String genere :
                            addedGeneres) {
                        getDataBaseConnector().insertPrzynaleznosc(genere, zespol.getId(), null);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null, e.getMessage(), "alert", JOptionPane.ERROR_MESSAGE);
                }
                setVisible(false);
                getFather().setVisible(true);
            }
        });
    }

    /*public void setEditArtistInConcert(){
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
    }*/

    public void setEditArtist(){
        this.name = new JLabel("Name");
        this.date = new JLabel("Date");
        this.city = new JLabel("City");
        this.country = new JLabel("Country");
        this.nameJTextField = new JTextField(zespolEdit.getNazwa(),5);
        this.dateDatePicker = new DatePicker();
        this.cityJTextField = new JTextField(zespolEdit.getMiasto_zalozenia(),5);
        this.countryJTextField = new JTextField(zespolEdit.getKraj_zalozenia(),5);
        this.gatunekAdd = new JLabel("Add genere");
        this.gatunekDel = new JLabel("Selected generes (delete)");
        this.addGatunekJButton = new JButton("Add genere");
        this.gatunkiAddJComboBox = new JComboBox();
        this.gatunkiDeleteJComboBox = new JComboBox();
        this.addGatunekJButton = new JButton("Add genere");
        try {
            ArrayList<Gatunek> gatunki = getDataBaseConnector().getGatunki(null, "");
            for (Gatunek gatunek :
                    gatunki) {
                this.gatunkiAddJComboBox.addItem(gatunek.getNazwa());
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e.getMessage(), "alert", JOptionPane.ERROR_MESSAGE);
        }
        try {
            ArrayList<Gatunek> gatunki = getDataBaseConnector().getGatunki(zespolEdit.getId(), "");
            for (Gatunek gatunek :
                    gatunki) {
                this.gatunkiDeleteJComboBox.addItem(gatunek.getNazwa());
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e.getMessage(), "alert", JOptionPane.ERROR_MESSAGE);
        }
        this.gatunkiAddJComboBox.addActionListener (new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(((DefaultComboBoxModel)gatunkiDeleteJComboBox.getModel()).getIndexOf(gatunkiAddJComboBox.getSelectedItem()) == -1) {
                    externalGanunkiChange = true;
                    gatunkiDeleteJComboBox.addItem(gatunkiAddJComboBox.getSelectedItem());
                    if(addedGeneres.indexOf((String)gatunkiAddJComboBox.getSelectedItem()) == -1)
                        addedGeneres.add((String) gatunkiAddJComboBox.getSelectedItem());
                }
            }
        });
        this.gatunkiDeleteJComboBox.addActionListener (new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(!externalGanunkiChange) {
                    gatunkiDeleteJComboBox.removeItem(gatunkiDeleteJComboBox.getSelectedItem());
                    int index = addedGeneres.indexOf((String)gatunkiDeleteJComboBox.getSelectedItem());
                    if(index != -1)
                        addedGeneres.remove(index);
                    else
                        deletedGeneres.add((String) gatunkiDeleteJComboBox.getSelectedItem());
                }
                externalGanunkiChange = false;
            }
        });
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
        c.gridy = 6;
        c.gridx = 1;
        addPanel.add(gatunekAdd, c);
        c.gridy = 6;
        c.gridx = 2;
        addPanel.add(gatunkiAddJComboBox, c);
        c.gridx = 3;
        addPanel.add(addGatunekJButton, c);
        c.gridx = 4;
        addPanel.add(editGatunekJButton, c);
        c.gridy = 7;
        c.gridx = 1;
        addPanel.add(gatunekDel, c);
        c.gridy = 7;
        c.gridx = 2;
        addPanel.add(gatunkiDeleteJComboBox, c);
        this.pack();
        AddOrEditArtistWindow temp = this;
        this.addGatunekJButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddOrEditGenereWindow genereWindow = new AddOrEditGenereWindow(getDataBaseConnector(),temp);
                genereWindow.setVisible(true);
                temp.setVisible(false);
            }
        });
        getOkButton().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                super.mouseClicked(mouseEvent);
                Date dateFrom = null;
                if(dateDatePicker.getDate() != null)
                    dateFrom = Date.valueOf(dateDatePicker.getDate());
                try {
                    String new_name = nameJTextField.getText();
                    String new_city = cityJTextField.getText();
                    String new_country = countryJTextField.getText();
                    getDataBaseConnector().updateZespol(new_name,dateFrom,new_city,new_country,zespolEdit.getId());
                } catch (Exception e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null, e.getMessage(), "alert", JOptionPane.ERROR_MESSAGE);
                }
                setVisible(false);
                getFather().setVisible(true);
            }
        });
    }

    public void addGatunek(String nazwa) {
        gatunkiAddJComboBox.addItem(nazwa);
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

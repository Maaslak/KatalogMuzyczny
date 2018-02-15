package Gui.Change;

import DataBase.DataBaseConnector;
import JavaObjects.Muzyk;
import JavaObjects.Zespol;
import com.github.lgooddatepicker.components.DatePicker;
import javafx.scene.control.ComboBox;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.util.ArrayList;

public class AddOrEditMembershipWindow extends Change{
    private JLabel muzyk_label, zespol_label, funkcja, from, to;
    private JTextField funkcjaJTextField;
    private DatePicker fromDatePicker, toDatePicker;
    private Muzyk muzyk;
    private Zespol zespol;
    private GridBagConstraints c;
    private JComboBox zespolJComboBox, muzykJComboBox;

    public AddOrEditMembershipWindow(DataBaseConnector dataBaseConnector, JFrame father, Muzyk muzyk) {
        super(dataBaseConnector, father);
        this.muzyk = muzyk;
        setAddZespol();
    }

    public AddOrEditMembershipWindow(DataBaseConnector dataBaseConnector, JFrame father, Zespol zespol) {
        super(dataBaseConnector, father);
        this.zespol = zespol;
        setAddMusician();
    }

    public void setAddZespol(){
        this.zespol_label = new JLabel("zespol");
        this.funkcja = new JLabel("funkcja");
        this.from = new JLabel("Od kiedy");
        this.to = new JLabel("Do kiedy");

        this.funkcjaJTextField = new JTextField(5);
        this.fromDatePicker = new DatePicker();
        this.toDatePicker = new DatePicker();

        ArrayList<Zespol> zespoly = null;
        try {
            zespoly = getDataBaseConnector().getZespoly();
            this.zespolJComboBox = new JComboBox();
            for(int i =0; i<zespoly.size();i++)
                this.zespolJComboBox.addItem(zespoly.get(i));
        } catch (Exception e) {
            e.printStackTrace();
        }

        c = new GridBagConstraints();
        c.gridy = 1;
        c.gridx = 1;
        addPanel.add(this.zespol_label, c);
        c.gridy = 2;
        c.gridx = 1;
        addPanel.add(this.funkcja, c);
        c.gridy = 3;
        c.gridx = 1;
        addPanel.add(this.from, c);
        c.gridy = 4;
        c.gridx = 1;
        addPanel.add(this.to, c);

        c.gridy = 1;
        c.gridx = 2;
        addPanel.add(this.zespolJComboBox, c);
        c.gridy = 2;
        c.gridx = 2;
        addPanel.add(this.funkcjaJTextField, c);
        c.gridy = 3;
        c.gridx = 2;
        addPanel.add(this.fromDatePicker, c);
        c.gridy = 4;
        c.gridx = 2;
        addPanel.add(this.toDatePicker, c);
        this.pack();

        getOkButton().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                super.mouseClicked(mouseEvent);
                Date dateFrom = null;
                if(fromDatePicker.getDate() != null)
                    dateFrom = Date.valueOf(fromDatePicker.getDate());
                Date dateTo = null;
                if(toDatePicker.getDate() != null)
                    dateTo = Date.valueOf(toDatePicker.getDate());
                try {

                    /*TODO muzykid czlonkostwo musi miec podane muzykid
                    getDataBaseConnector().insertCzlonkostwo(dateFrom, zespolJComboBox.getSelectedIndex(), muzyk.getMuzykId(), dateTo, funkcjaJTextField.getText());
                    */
                } catch (Exception e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null, e.getMessage(), "alert", JOptionPane.ERROR_MESSAGE);
                }
                setVisible(false);
                getFather().setVisible(true);
            }
        });
    }

    public void setAddMusician(){
        this.muzyk_label = new JLabel("muzyk");
        this.funkcja = new JLabel("funkcja");
        this.from = new JLabel("Od kiedy");
        this.to = new JLabel("Do kiedy");

        this.funkcjaJTextField = new JTextField(5);
        this.fromDatePicker = new DatePicker();
        this.toDatePicker = new DatePicker();

        ArrayList<Zespol> muzycy = null;
        try {
            muzycy = getDataBaseConnector().getZespoly();
            this.zespolJComboBox = new JComboBox();
            for(int i =0; i<muzycy.size();i++)
                this.zespolJComboBox.addItem(muzycy.get(i));
        } catch (Exception e) {
            e.printStackTrace();
        }

        c = new GridBagConstraints();
        c.gridy = 1;
        c.gridx = 1;
        addPanel.add(this.muzyk_label, c);
        c.gridy = 2;
        c.gridx = 1;
        addPanel.add(this.funkcja, c);
        c.gridy = 3;
        c.gridx = 1;
        addPanel.add(this.from, c);
        c.gridy = 4;
        c.gridx = 1;
        addPanel.add(this.to, c);

        c.gridy = 1;
        c.gridx = 2;
        addPanel.add(this.muzyk_label, c);
        c.gridy = 2;
        c.gridx = 2;
        addPanel.add(this.funkcjaJTextField, c);
        c.gridy = 3;
        c.gridx = 2;
        addPanel.add(this.fromDatePicker, c);
        c.gridy = 4;
        c.gridx = 2;
        addPanel.add(this.toDatePicker, c);
        this.pack();


        getOkButton().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                super.mouseClicked(mouseEvent);
                Date dateFrom = null;
                if(fromDatePicker.getDate() != null)
                    dateFrom = Date.valueOf(fromDatePicker.getDate());
                Date dateTo = null;
                if(toDatePicker.getDate() != null)
                    dateTo = Date.valueOf(toDatePicker.getDate());
                try {

                    /*TODO muzykid czlonkostwo musi miec podane muzykid
                    getDataBaseConnector().insertCzlonkostwo(dateFrom, zespol.getId(),muzykJComboBox.getSelectedIndex().   "getMuzykId", dateTo, funkcjaJTextField.getText());
                    */
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

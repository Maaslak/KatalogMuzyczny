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

public class AddOrEditFestivalWindow extends Change{
    private JLabel name, date_start, date_end;
    private JTextField nameJTextField;
    private DatePicker startDatePicker, endDatePicker;
    private Festiwal festiwalEdit;
    private GridBagConstraints c;

    public AddOrEditFestivalWindow(DataBaseConnector dataBaseConnector, JFrame father) {
        super(dataBaseConnector, father);
        setAddFestival();
        mouse(); //TODO przeciazyc
    }

    public AddOrEditFestivalWindow(DataBaseConnector dataBaseConnector, JFrame father, Festiwal festiwalEdit) {
        super(dataBaseConnector, father);
        this.festiwalEdit = festiwalEdit;
        setEditFestival();
        mouse(); //TODO przeciazyc
    }

    public void setAddFestival(){
        this.name = new JLabel("Name");
        this.date_start = new JLabel("Date start");
        this.date_end = new JLabel("Date end");

        this.nameJTextField = new JTextField(5);
        this.startDatePicker = new DatePicker();
        this.endDatePicker = new DatePicker();

        c = new GridBagConstraints();
        c.gridy = 1;
        c.gridx = 1;
        addPanel.add(name, c);
        c.gridy = 2;
        c.gridx = 1;
        addPanel.add(date_start, c);
        c.gridy = 3;
        c.gridx = 1;
        addPanel.add(date_end, c);
        c.gridy = 1;
        c.gridx = 2;
        addPanel.add(nameJTextField, c);
        c.gridy = 2;
        c.gridx = 2;
        addPanel.add(startDatePicker, c);
        c.gridy = 3;
        c.gridx = 2;
        addPanel.add(endDatePicker, c);
        this.pack();

        getOkButton().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                super.mouseClicked(mouseEvent);
                Date dateStart = null;
                if(startDatePicker.getDate() != null)
                    dateStart = Date.valueOf(startDatePicker.getDate());
                Date dateEnd = null;
                if(endDatePicker.getDate() != null)
                    dateEnd = Date.valueOf(endDatePicker.getDate());
                try {
                    Festiwal festiwal = new Festiwal(nameJTextField.getText(),dateStart,dateEnd);
                    getDataBaseConnector().insertFestiwal(festiwal);
                } catch (Exception e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null, e.getMessage(), "alert", JOptionPane.ERROR_MESSAGE);
                }
                setVisible(false);
                getFather().setVisible(true);
            }
        });
    }

    public void setEditFestival(){
        //id festiwalu
        this.name = new JLabel("Name");
        this.date_start = new JLabel("Date start");
        this.date_end = new JLabel("Date end");

        this.nameJTextField = new JTextField(festiwalEdit.getNazwa(),5);
        this.startDatePicker = new DatePicker();
        this.endDatePicker = new DatePicker();
        startDatePicker.setDate(this.festiwalEdit.getDataRozpoczecia().toLocalDate());
        endDatePicker.setDate(this.festiwalEdit.getDataZakonczenia().toLocalDate());

        c = new GridBagConstraints();
        c.gridy = 1;
        c.gridx = 1;
        addPanel.add(name, c);
        c.gridy = 2;
        c.gridx = 1;
        addPanel.add(date_start, c);
        c.gridy = 3;
        c.gridx = 1;
        addPanel.add(date_end, c);
        c.gridy = 1;
        c.gridx = 2;
        addPanel.add(nameJTextField, c);
        c.gridy = 2;
        c.gridx = 2;
        addPanel.add(startDatePicker, c);
        c.gridy = 3;
        c.gridx = 2;
        addPanel.add(endDatePicker, c);
        this.pack();
        getOkButton().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                super.mouseClicked(mouseEvent);
                try {
                    Date dateStart = null, dateEnd = null;
                    if(startDatePicker.getDate() != null)
                        dateStart = Date.valueOf(startDatePicker.getDate());
                    if(endDatePicker.getDate() != null)
                        dateEnd= Date.valueOf(endDatePicker.getDate());
                    Festiwal festiwal = new Festiwal(nameJTextField.getText(),dateStart,dateEnd);
                    getDataBaseConnector().updateFestiwale(festiwalEdit.getId(), festiwal);
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

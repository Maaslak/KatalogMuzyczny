package Gui.Scrollable;

import DataBase.DataBaseConnector;
import Gui.Detailed.ConcertWindow;
import JavaObjects.Koncert;
import com.github.lgooddatepicker.components.DatePicker;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class ConcertsWindow extends Scrollable {
    private JLabel date, name, festival;
    private JTextField nameJTextField;
    private JCheckBox festivalJCheckBox;
    private DatePicker from, to;
    private GridBagConstraints c;
    //private ConcertWindow concertWindow;
    private ArrayList<Koncert> koncerty;

    public ConcertsWindow(DataBaseConnector dataBaseConnector, JFrame father) {
        super(dataBaseConnector,father);
        try {
            this.koncerty = dataBaseConnector.getKoncert();
            DefaultTableModel model = (DefaultTableModel) getTable1().getModel();
            String header[] = new String[] { "Nazwa", "Data", "Miasto" };
            model.setColumnIdentifiers(header);
            for(int i=0; i<koncerty.size();i++){
                model.addRow(new Object[] {koncerty.get(i).getNazwa(), koncerty.get(i).getData(), koncerty.get(i).getMiasto_nazwa()});
            }
            getTable1().setModel(model);
        } catch (Exception e) {
            e.printStackTrace();
        }

        c = new GridBagConstraints();
        setFilterPanel();

        mouse();
    }

    public void setFilterPanel() {
        date = new JLabel("Date: ");
        name = new JLabel("Name");
        festival = new JLabel("Rating");
        from = new DatePicker();
        to = new DatePicker();
        nameJTextField = new JTextField(5);
        festivalJCheckBox = new JCheckBox("festivals");
        c.gridy = 0;
        c.gridx = 0;
        filterPanel.add(date, c);
        c.gridy = 0;
        c.gridx = 1;
        filterPanel.add(from, c);
        c.gridy = 0;
        c.gridx = 3;
        filterPanel.add(to, c);
        c.gridy = 1;
        c.gridx = 0;
        filterPanel.add(name, c);
        c.gridy = 1;
        c.gridx = 1;
        filterPanel.add(nameJTextField, c);
        c.gridy = 3;
        c.gridx = 0;
        filterPanel.add(festival, c);
        c.gridy = 3;
        c.gridx = 1;
        filterPanel.add(festivalJCheckBox, c);
        this.pack();
    }

    public void mouse(){
        super.getFilterButton().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                super.mouseClicked(mouseEvent);
                try {
                    koncerty.clear();
                    koncerty = getDataBaseConnector().getKoncert();
                    DefaultTableModel model = (DefaultTableModel) getTable1().getModel();
                    model.getDataVector().removeAllElements();
                    model.fireTableDataChanged();
                    for(int i=0; i<koncerty.size();i++){
                        model.addRow(new Object[] {koncerty.get(i).getNazwa(), koncerty.get(i).getData(), koncerty.get(i).getMiasto_nazwa()});
                    }
                    getTable1().setModel(model);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        ConcertsWindow temp = this;
        super.getSelectButton().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                super.mouseClicked(mouseEvent);
                if(!getTable1().getSelectionModel().isSelectionEmpty()) {
                    ConcertWindow concertWindow= new ConcertWindow(getDataBaseConnector(),temp,koncerty.get(getTable1().getSelectedRow()));
                    concertWindow.setVisible(true);
                    setVisible(false);
                }
            }
        });
    }
}
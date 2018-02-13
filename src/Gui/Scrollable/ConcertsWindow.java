package Gui.Scrollable;

import DataBase.DataBaseConnector;
import Gui.Change.AddOrEditArtistWindow;
import Gui.Change.AddOrEditConcertWindow;
import Gui.Detailed.ConcertWindow;
import JavaObjects.Koncert;
import com.github.lgooddatepicker.components.DatePicker;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.util.ArrayList;

public class ConcertsWindow extends Scrollable {
    private JLabel date, name, city;
    private JTextField nameJTextField, cityTextField;
    private DatePicker from, to;
    private GridBagConstraints c;
    //private ConcertWindow concertWindow;
    private ArrayList<Koncert> koncerty;

    public ConcertsWindow(DataBaseConnector dataBaseConnector, JFrame father) {
        super(dataBaseConnector,father);
        try {
            this.koncerty = dataBaseConnector.getKoncert();
            DefaultTableModel model = (DefaultTableModel) getTable1().getModel();
            String header[] = new String[] { "Name", "Date", "City" };
            model.setColumnIdentifiers(header);
            for(int i=0; i<koncerty.size();i++){
                model.addRow(new Object[] {koncerty.get(i).getNazwa(), koncerty.get(i).getData(), koncerty.get(i).getMiasto_nazwa()});
            }
            getTable1().setModel(model);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e.getMessage(), "alert", JOptionPane.ERROR_MESSAGE);
        }

        c = new GridBagConstraints();
        setFilterPanel();

        mouse();
    }

    public void setFilterPanel() {
        date = new JLabel("Date: ");
        name = new JLabel("Name");
        city = new JLabel("City");
        //festival = new JLabel("Rating");
        from = new DatePicker();
        to = new DatePicker();
        nameJTextField = new JTextField(5);
        cityTextField = new JTextField(5);
        //festivalJCheckBox = new JCheckBox("festivals");
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
        c.gridy = 2;
        c.gridx = 0;
        filterPanel.add(city, c);
        c.gridy = 2;
        c.gridx = 1;
        filterPanel.add(cityTextField, c);
        this.pack();
    }

    @Override
    public void setVisible(boolean b) {
        super.setVisible(b);
        this.koncerty.clear();
        try {
            this.koncerty = getDataBaseConnector().getKoncert();
            DefaultTableModel model = (DefaultTableModel) getTable1().getModel();
            model.getDataVector().removeAllElements();
            model.fireTableDataChanged();
            for(int i=0; i<koncerty.size();i++){
                model.addRow(new Object[] {koncerty.get(i).getNazwa(), koncerty.get(i).getData(), koncerty.get(i).getMiasto_nazwa()});
            }
            getTable1().setModel(model);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e.getMessage(), "alert", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void mouse(){
        super.getFilterButton().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                super.mouseClicked(mouseEvent);
                try {
                    koncerty.clear();
                    Date dateFrom = null, dateTo = null;
                    if(from.getDate() != null)
                        dateFrom = Date.valueOf(from.getDate());
                    if(to.getDate() != null)
                        dateTo = Date.valueOf(to.getDate());
                    koncerty = getDataBaseConnector().getKoncert(nameJTextField.getText(),dateFrom,dateTo,cityTextField.getText(),null);
                    DefaultTableModel model = (DefaultTableModel) getTable1().getModel();
                    model.getDataVector().removeAllElements();
                    model.fireTableDataChanged();
                    for(int i=0; i<koncerty.size();i++){
                        model.addRow(new Object[] {koncerty.get(i).getNazwa(), koncerty.get(i).getData(), koncerty.get(i).getMiasto_nazwa()});
                    }
                    getTable1().setModel(model);
                } catch (Exception e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null, e.getMessage(), "alert", JOptionPane.ERROR_MESSAGE);

                }
            }
        });

        ConcertsWindow temp = this;
        super.getAddButton().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                super.mouseClicked(mouseEvent);
                AddOrEditConcertWindow addOrEditConcertWindow = new AddOrEditConcertWindow(getDataBaseConnector(),temp);
                addOrEditConcertWindow.setVisible(true);
                setVisible(false);
            }
        });

        super.getEditButton().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                super.mouseClicked(mouseEvent);
                if(!getTable1().getSelectionModel().isSelectionEmpty()) {
                    AddOrEditConcertWindow addOrEditConcertWindow = new AddOrEditConcertWindow(getDataBaseConnector(),temp,koncerty.get(getTable1().getSelectedRow()));
                    addOrEditConcertWindow.setVisible(true);
                    setVisible(false);
                }
            }
        });

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

        super.getDeleteButton().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                super.mouseClicked(mouseEvent);
                if(!getTable1().getSelectionModel().isSelectionEmpty()) {
                    Koncert koncert = koncerty.get(getTable1().getSelectedRow());
                    try {
                        getDataBaseConnector().deleteKoncert(koncert.getNazwa(), koncert.getData());
                    } catch (Exception e) {
                        e.printStackTrace();
                        JOptionPane.showMessageDialog(null, e.getMessage(), "alert", JOptionPane.ERROR_MESSAGE);
                    }
                    setVisible(true);
                }
            }
        });
    }
}

package Gui.Scrollable;

import DataBase.DataBaseConnector;
import Gui.Change.AddOrEditAlbumWindow;
import Gui.Change.AddOrEditArtistWindow;
import Gui.Detailed.ArtistWindow;
import JavaObjects.Zespol;
import com.github.lgooddatepicker.components.DatePicker;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.lang.reflect.Array;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;


public class ArtistsWindow extends Scrollable {
    private JLabel date, name, city, nationality;
    private JTextField nameJTextField;
    private JTextField cityJTextField, nationalityJTextField;
    private DatePicker from, to;
    private GridBagConstraints c;
    private ArrayList<Zespol> zespoly;


    public ArtistsWindow(DataBaseConnector dataBaseConnector, JFrame father) {
        super(dataBaseConnector,father);

        try {
            this.zespoly = dataBaseConnector.getZespoly();
            DefaultTableModel model = (DefaultTableModel) getTable1().getModel();
            model.addColumn("Name");
            model.addColumn("Formed Date");
            model.addColumn("City");
            model.addColumn("Country");

            for(int i=0; i<zespoly.size();i++){
                model.addRow(new Object[] {zespoly.get(i).getNazwa(), zespoly.get(i).getDate(), zespoly.get(i).getMiasto_zalozenia(), zespoly.get(i).getKraj_zalozenia()});
            }

        } catch (Exception e) {
            e.printStackTrace();
            //TODO wyskakujace okienko z bledem
        }

        c = new GridBagConstraints();
        setFilterPanel();



        mouse();
    }

    public void setFilterPanel() {
        date = new JLabel("Date: ");
        name = new JLabel("Name (regexp)");
        city = new JLabel("City");
        nationality = new JLabel("Nationality");
        from = new DatePicker();
        to = new DatePicker();
        nameJTextField = new JTextField(5);
        cityJTextField = new JTextField(5);
        nationalityJTextField = new JTextField(5);
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
        filterPanel.add(city, c);
        c.gridy = 3;
        c.gridx = 1;
        filterPanel.add(cityJTextField, c);
        c.gridy = 4;
        c.gridx = 0;
        filterPanel.add(nationality, c);
        c.gridy = 4;
        c.gridx = 1;
        filterPanel.add(nationalityJTextField, c);
        this.pack();
    }

    @Override
    public void setVisible(boolean b) {
        super.setVisible(b);
        this.zespoly.clear();
        try {
            this.zespoly = getDataBaseConnector().getZespoly();
            DefaultTableModel model = (DefaultTableModel) getTable1().getModel();
            model.getDataVector().removeAllElements();
            model.fireTableDataChanged();
            for(int i=0; i<zespoly.size();i++){
                model.addRow(new Object[] {zespoly.get(i).getNazwa(), zespoly.get(i).getDate(), zespoly.get(i).getMiasto_zalozenia(), zespoly.get(i).getKraj_zalozenia()});
            }
            getTable1().setModel(model);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void mouse(){
        super.getFilterButton().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                super.mouseClicked(mouseEvent);
                try {
                    zespoly.clear();
                    Date dateFrom = null, dateTo = null;
                    if(from.getDate() != null)
                        dateFrom = Date.valueOf(from.getDate());
                    if(to.getDate() != null)
                        dateTo = Date.valueOf(to.getDate());
                    zespoly = getDataBaseConnector().getZespoly(nameJTextField.getText(),dateFrom,dateTo,nationalityJTextField.getText(),cityJTextField.getText());
                    DefaultTableModel model = (DefaultTableModel) getTable1().getModel();
                    model.getDataVector().removeAllElements();
                    model.fireTableDataChanged();
                    for(int i=0; i<zespoly.size();i++){
                        model.addRow(new Object[] {zespoly.get(i).getNazwa(), zespoly.get(i).getDate(), zespoly.get(i).getMiasto_zalozenia(), zespoly.get(i).getKraj_zalozenia()});
                    }
                    getTable1().setModel(model);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });


        ArtistsWindow temp = this;
        super.getSelectButton().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                super.mouseClicked(mouseEvent);
                try{
                if(getTable1().getSelectedRow() == -1)
                    throw new Exception("Please select an album");
                ArtistWindow artistWindow = new ArtistWindow(getDataBaseConnector(),temp,zespoly.get(getTable1().convertRowIndexToModel(getTable1().getSelectedRow())));
                artistWindow.setVisible(true);
                setVisible(false);
                } catch (Exception e) {
                        e.printStackTrace();
                        JOptionPane.showMessageDialog(null, e.getMessage(), "alert", JOptionPane.ERROR_MESSAGE);
                    }
            }
        });

        super.getAddButton().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                super.mouseClicked(mouseEvent);
                AddOrEditArtistWindow addOrEditArtistWindow = new AddOrEditArtistWindow(getDataBaseConnector(),temp);
                addOrEditArtistWindow.setVisible(true);
                setVisible(false);
            }
        });

        super.getEditButton().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                super.mouseClicked(mouseEvent);
                try {
                if(getTable1().getSelectedRow() == -1)
                    throw new Exception("Please select an album");
                AddOrEditArtistWindow addOrEditArtistWindow = new AddOrEditArtistWindow(getDataBaseConnector(),temp,zespoly.get(getTable1().convertRowIndexToModel(getTable1().getSelectedRow())));
                addOrEditArtistWindow.setVisible(true);
                setVisible(false);
                } catch (Exception e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null, e.getMessage(), "alert", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        super.getDeleteButton().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                super.mouseClicked(mouseEvent);
                try {
                    if(getTable1().getSelectedRow() == -1)
                        throw new Exception("Please select an album");
                    getDataBaseConnector().deleteZespol(zespoly.get(getTable1().convertRowIndexToModel(getTable1().getSelectedRow())).getId());
                    setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null, e.getMessage(), "alert", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}

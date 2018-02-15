package Gui.Scrollable;

import DataBase.DataBaseConnector;
import Gui.Change.AddOrEditAlbumWindow;
import Gui.Change.AddOrEditMusicianWindow;
import Gui.Detailed.AlbumWindow;
import Gui.Detailed.MusicianWindow;
import JavaObjects.Album;
import JavaObjects.Muzyk;
import com.github.lgooddatepicker.components.DatePicker;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.util.ArrayList;

public class MusiciansWindow extends Scrollable {
    private JLabel imie, nazwisko, pochodzenie, data_ur;
    private JTextField imieJTextField, nazwiskoJTextField, pochodzenieJTextField;
    private DatePicker from,to;
    private GridBagConstraints c;
    private ArrayList<Muzyk> musicians;


    public MusiciansWindow(DataBaseConnector dataBaseConnector, JFrame father) {
        super(dataBaseConnector,father);
        c = new GridBagConstraints();
        setFilterPanel();

        try {
            this.musicians = dataBaseConnector.getMuzycy();
            DefaultTableModel model = (DefaultTableModel) getTable1().getModel();
            String header[] = new String[] { "Imię", "Nazwisko", "Pochodzenie",
                    "Data_ur" };

            model.setColumnIdentifiers(header);
            for(int i=0; i<musicians.size();i++){
                model.addRow(new Object[] {musicians.get(i).getImie(), musicians.get(i).getNazwisko(), musicians.get(i).getPochodzenie(), musicians.get(i).getDataUrodzenia()});
            }
            getTable1().setModel(model);

        } catch (Exception e) {
            e.printStackTrace();
            //TODO wyskakujace okienko z bledem
        }

        mouse();
    }

    public void setFilterPanel() {
        imie = new JLabel("Imię");
        nazwisko = new JLabel("Naziwsko");
        pochodzenie = new JLabel("Pochodzenie");
        data_ur = new JLabel("Data urodzenia: ");
        from = new DatePicker();
        to = new DatePicker();
        imieJTextField = new JTextField(5);
        nazwiskoJTextField = new JTextField(5);
        pochodzenieJTextField = new JTextField(5);
        c.gridy = 0;
        c.gridx = 0;
        filterPanel.add(data_ur, c);
        c.gridy = 0;
        c.gridx = 1;
        filterPanel.add(from, c);
        c.gridy = 0;
        c.gridx = 3;
        filterPanel.add(to, c);
        c.gridy = 1;
        c.gridx = 0;
        filterPanel.add(imie, c);
        c.gridy = 1;
        c.gridx = 1;
        filterPanel.add(imieJTextField, c);
        c.gridy = 3;
        c.gridx = 0;
        filterPanel.add(nazwisko, c);
        c.gridy = 3;
        c.gridx = 1;
        filterPanel.add(nazwiskoJTextField, c);
        c.gridy = 4;
        c.gridx = 0;
        filterPanel.add(pochodzenie, c);
        c.gridy = 4;
        c.gridx = 1;
        filterPanel.add(pochodzenieJTextField, c);
        this.pack();
    }

    @Override
    public void setVisible(boolean b) {
        super.setVisible(b);
        this.musicians.clear();
        try {
            this.musicians = getDataBaseConnector().getMuzycy();
            DefaultTableModel model = (DefaultTableModel) getTable1().getModel();
            model.getDataVector().removeAllElements();
            model.fireTableDataChanged();
            for(int i=0; i<musicians.size();i++){
                model.addRow(new Object[] {musicians.get(i).getImie(), musicians.get(i).getNazwisko(), musicians.get(i).getPochodzenie(), musicians.get(i).getDataUrodzenia()});
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
                    musicians.clear();
                    Date dateFrom = null, dateTo = null;
                    if(from.getDate() != null)
                        dateFrom = Date.valueOf(from.getDate());
                    if(to.getDate() != null)
                        dateTo = Date.valueOf(to.getDate());
                    musicians = getDataBaseConnector().getMuzycy();
                    DefaultTableModel model = (DefaultTableModel) getTable1().getModel();
                    model.getDataVector().removeAllElements();
                    model.fireTableDataChanged();
                    for(int i=0; i<musicians.size();i++){
                        model.addRow(new Object[] {musicians.get(i).getImie(), musicians.get(i).getNazwisko(), musicians.get(i).getPochodzenie(), musicians.get(i).getDataUrodzenia()});
                    }
                    getTable1().setModel(model);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        MusiciansWindow temp = this;
        super.getSelectButton().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                super.mouseClicked(mouseEvent);
                if(!getTable1().getSelectionModel().isSelectionEmpty()) {
                    MusicianWindow musicianWindow = new MusicianWindow(getDataBaseConnector(),temp,musicians.get(getTable1().getSelectedRow()));
                    musicianWindow.setVisible(true);
                    setVisible(false);
                }
            }
        });

        super.getAddButton().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                super.mouseClicked(mouseEvent);
                AddOrEditMusicianWindow addOrEditMusicianWindow = new AddOrEditMusicianWindow(getDataBaseConnector(),temp);
                addOrEditMusicianWindow.setVisible(true);
                setVisible(false);
            }
        });

        super.getEditButton().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                super.mouseClicked(mouseEvent);
                if(!getTable1().getSelectionModel().isSelectionEmpty()) {
                    AddOrEditMusicianWindow addOrEditMusicianWindow = new AddOrEditMusicianWindow(getDataBaseConnector(),temp,musicians.get(getTable1().convertRowIndexToModel(getTable1().getSelectedRow())));
                    addOrEditMusicianWindow.setVisible(true);
                    setVisible(false);
                }
            }
        });

        super.getDeleteButton().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                super.mouseClicked(mouseEvent);
                try {
                    getDataBaseConnector().deleteMuzyk((musicians.get(getTable1().convertRowIndexToModel(getTable1().getSelectedRow()))).getNazwisko());
                    setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null, e.getMessage(), "alert", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}

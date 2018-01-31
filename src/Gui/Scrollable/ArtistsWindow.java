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
import java.util.ArrayList;
import java.util.Date;


public class ArtistsWindow extends Scrollable {
    private JLabel date, name, nationality;
    private JTextField nameJTextField;
    private JTextField nationalityJTextField;
    private DatePicker from, to;
    private GridBagConstraints c;
    private ArrayList<Zespol> zespoly;


    public ArtistsWindow(DataBaseConnector dataBaseConnector, JFrame father) {
        super(dataBaseConnector,father);

        try {
            this.zespoly = dataBaseConnector.getZespoly();
            DefaultTableModel model = (DefaultTableModel) getTable1().getModel();
            model.addColumn("nazwa");
            model.addColumn("data_zalozenia");
            model.addColumn("miast_zalozenia");
            model.addColumn("kraj_zalozenia");

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
        nationality = new JLabel("Nationality");
        from = new DatePicker();
        to = new DatePicker();
        nameJTextField = new JTextField(5);
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
        filterPanel.add(nationality, c);
        c.gridy = 3;
        c.gridx = 1;
        filterPanel.add(nationalityJTextField, c);
        this.pack();
    }

    public void mouse(){
        super.getFilterButton().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                super.mouseClicked(mouseEvent);
                try {
                    zespoly.clear();
                    //boolean temp[] = {};
                    zespoly = getDataBaseConnector().getZespoly(nameJTextField.getText(),null,null,null,nationality.getText());
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
                if(!getTable1().getSelectionModel().isSelectionEmpty()) {
                    ArtistWindow artistWindow = new ArtistWindow(getDataBaseConnector(),temp,zespoly.get(getTable1().getSelectedRow()));
                    artistWindow.setVisible(true);
                    setVisible(false);
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

        /*super.getEditButton().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                super.mouseClicked(mouseEvent);
                if(!getTable1().getSelectionModel().isSelectionEmpty()) {
                    AddOrEditArtistWindow addOrEditArtistWindow = new AddOrEditArtistWindow(getDataBaseConnector(),temp,zespoly.get(getTable1().getSelectedRow()));
                    addOrEditArtistWindow.setVisible(true);
                    setVisible(false);
                }
            }
        });

        super.getDeleteButton().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                super.mouseClicked(mouseEvent);
                if(!getTable1().getSelectionModel().isSelectionEmpty()) {
                    // Drop
                };
            }
        });*/
    }
}

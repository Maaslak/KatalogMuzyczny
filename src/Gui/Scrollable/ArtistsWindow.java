package Gui.Scrollable;

import DataBase.DataBaseConnector;
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


public class ArtistsWindow extends Scrollable {
    private JLabel date, name, nationality;
    private JTextField nameJTextField;
    private JTextField nationalityJTextField;
    private DatePicker from, to;
    private GridBagConstraints c;

    private ArrayList<Zespol> zespoly;


    public ArtistsWindow(DataBaseConnector dataBaseConnector) {
        super(dataBaseConnector);

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
            }
        });



        super.getSelectButton().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                super.mouseClicked(mouseEvent);
                if(!getTable1().getSelectionModel().isSelectionEmpty()) {
                    ArtistWindow artistWindow = new ArtistWindow(zespoly.get(getTable1().getSelectedRow()));
                    artistWindow.setVisible(true);
                }
            }
        });
    }
}

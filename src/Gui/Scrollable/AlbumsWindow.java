package Gui.Scrollable;

import DataBase.DataBaseConnector;
import JavaObjects.Album;
import com.github.lgooddatepicker.components.DatePicker;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class AlbumsWindow extends Scrollable{
    private JLabel date, name, rating;
    private JTextField nameJTextField;
    private JTextField ratingJTextField;
    private DatePicker from, to;
    private GridBagConstraints c;
    private ArrayList<Album> albums;


    public AlbumsWindow(DataBaseConnector dataBaseConnector) {
        super(dataBaseConnector);
        c = new GridBagConstraints();
        setFilterPanel();

        try {
            this.albums = dataBaseConnector.getAlbumy();
            DefaultTableModel model = (DefaultTableModel) getTable1().getModel();
            String header[] = new String[] { "Nazwa", "Data_wydania", "Ocena",
                    "Język" };

            model.setColumnIdentifiers(header);
            for(int i=0; i<albums.size();i++){
                model.addRow(new Object[] {albums.get(i).getNazwa(), albums.get(i).getDate(), albums.get(i).getOcena(), albums.get(i).getJezyk()});
            }
            getTable1().setModel(model);

        } catch (Exception e) {
            e.printStackTrace();
            //TODO wyskakujace okienko z bledem
        }
        
        mouse();
    }

    public void setFilterPanel() {
        date = new JLabel("Date: ");
        name = new JLabel("Name (regexp)");
        rating = new JLabel("Rating");
        from = new DatePicker();
        to = new DatePicker();
        nameJTextField = new JTextField(5);
        ratingJTextField = new JTextField(5);
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
        filterPanel.add(rating, c);
        c.gridy = 3;
        c.gridx = 1;
        filterPanel.add(ratingJTextField, c);
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
            }
        });
    }
}

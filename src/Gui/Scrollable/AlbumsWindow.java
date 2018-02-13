package Gui.Scrollable;

import DataBase.DataBaseConnector;
import Gui.Change.AddOrEditAlbumWindow;
import Gui.Detailed.AlbumWindow;
import JavaObjects.Album;
import com.github.lgooddatepicker.components.DatePicker;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.util.ArrayList;

public class AlbumsWindow extends Scrollable{
    private JLabel date, name, rating, language;
    private JTextField nameJTextField;
    private JTextField ratingJTextField, languageJTextField;
    private DatePicker from, to;
    private GridBagConstraints c;
    private ArrayList<Album> albums;


    public AlbumsWindow(DataBaseConnector dataBaseConnector, JFrame father) {
        super(dataBaseConnector,father);
        c = new GridBagConstraints();
        setFilterPanel();

        try {
            this.albums = dataBaseConnector.getAlbumy();
            DefaultTableModel model = (DefaultTableModel) getTable1().getModel();
            String header[] = new String[] { "Name", "Recorded", "Rating",
                    "Language" };

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
        language = new JLabel("Language");
        from = new DatePicker();
        to = new DatePicker();
        nameJTextField = new JTextField(5);
        ratingJTextField = new JTextField(5);
        languageJTextField = new JTextField(5);
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
        c.gridy = 4;
        c.gridx = 0;
        filterPanel.add(language, c);
        c.gridy = 4;
        c.gridx = 1;
        filterPanel.add(languageJTextField, c);
        this.pack();
    }

    @Override
    public void setVisible(boolean b) {
        super.setVisible(b);
        this.albums.clear();
        try {
            this.albums = getDataBaseConnector().getAlbumy();
            DefaultTableModel model = (DefaultTableModel) getTable1().getModel();
            model.getDataVector().removeAllElements();
            model.fireTableDataChanged();
            for(int i=0; i<albums.size();i++){
                model.addRow(new Object[] {albums.get(i).getNazwa(), albums.get(i).getDate(), albums.get(i).getOcena(), albums.get(i).getJezyk()});
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
                    albums.clear();
                    Date dateFrom = null, dateTo = null;
                    Float ratingFloat = null;
                    if(from.getDate() != null)
                        dateFrom = Date.valueOf(from.getDate());
                    if(to.getDate() != null)
                        dateTo = Date.valueOf(to.getDate());
                    if(!ratingJTextField.getText().isEmpty())
                        ratingFloat = Float.valueOf(ratingJTextField.getText());
                    albums = getDataBaseConnector().getAlbumy(nameJTextField.getText(),dateFrom,dateTo,ratingFloat,languageJTextField.getText(),null);
                    DefaultTableModel model = (DefaultTableModel) getTable1().getModel();
                    model.getDataVector().removeAllElements();
                    model.fireTableDataChanged();
                    for(int i=0; i<albums.size();i++){
                        model.addRow(new Object[] {albums.get(i).getNazwa(), albums.get(i).getDate(), albums.get(i).getOcena(), albums.get(i).getJezyk()});
                    }
                    getTable1().setModel(model);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        AlbumsWindow temp = this;
        super.getSelectButton().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                super.mouseClicked(mouseEvent);
                if(!getTable1().getSelectionModel().isSelectionEmpty()) {
                    AlbumWindow albumWindow = new AlbumWindow(getDataBaseConnector(),temp,albums.get(getTable1().getSelectedRow()));
                    albumWindow.setVisible(true);
                    setVisible(false);
                }
            }
        });

        super.getAddButton().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                super.mouseClicked(mouseEvent);
                AddOrEditAlbumWindow addOrEditAlbumWindow = new AddOrEditAlbumWindow(getDataBaseConnector(),temp);
                addOrEditAlbumWindow.setVisible(true);
                setVisible(false);
            }
        });

        super.getEditButton().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                super.mouseClicked(mouseEvent);
                if(!getTable1().getSelectionModel().isSelectionEmpty()) {
                    AddOrEditAlbumWindow addOrEditAlbumWindow = new AddOrEditAlbumWindow(getDataBaseConnector(),temp,albums.get(getTable1().convertRowIndexToModel(getTable1().getSelectedRow())));
                    addOrEditAlbumWindow.setVisible(true);
                    setVisible(false);
                }
            }
        });

        super.getDeleteButton().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                super.mouseClicked(mouseEvent);
                try {
                    getDataBaseConnector().deleteAlbum(albums.get(getTable1().convertRowIndexToModel(getTable1().getSelectedRow())).getId());
                    setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null, e.getMessage(), "alert", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}

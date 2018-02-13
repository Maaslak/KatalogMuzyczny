package Gui.Detailed;

import DataBase.DataBaseConnector;
import Gui.Change.AddOrEditAlbumWindow;
import JavaObjects.Album;
import JavaObjects.Gatunek;
import JavaObjects.Zespol;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class ArtistWindow extends Detailed{

    private JLabel info;
    private JLabel head;
    private Zespol zespol;
    private ArrayList<Album> albums;
    private ArrayList<Gatunek> generes;
    private GridBagConstraints c;

    public ArtistWindow(DataBaseConnector dataBaseConnector, JFrame father, Zespol zespol){
        super(dataBaseConnector,father);
        this.zespol = zespol;
        setInformationPanel();

        try {
            this.albums = dataBaseConnector.getAlbumy("",null,null,null, "", new Integer(zespol.getId()));
            DefaultTableModel model = (DefaultTableModel) getTable1().getModel();
            String header[] = new String[] { "Name", "Recorded", "Rating",
                    "Language" };

            model.setColumnIdentifiers(header);
            model.getDataVector().removeAllElements();
            model.fireTableDataChanged();
            for(int i=0; i<albums.size();i++){
                model.addRow(new Object[] {albums.get(i).getNazwa(), albums.get(i).getDate(), albums.get(i).getOcena(), albums.get(i).getJezyk()});
            }
            getTable1().setModel(model);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e.getMessage(), "alert", JOptionPane.ERROR_MESSAGE);
        }

        mouse();
    }

    public void setInformationPanel(){
        this.info = new JLabel(zespol.toString());
        this.head = new JLabel("Albumy");
        c = new GridBagConstraints();
        c.gridy = 1;
        c.gridx = 1;
        informationPanel.add(info, c);
        c.gridy = 2;
        c.gridx = 1;
        informationPanel.add(head, c);
        this.pack();
    }

    @Override
    public void setVisible(boolean b) {
        super.setVisible(b);
        this.albums.clear();
        try {
            this.albums = getDataBaseConnector().getAlbumy("",null,null,null, "", new Integer(zespol.getId()));
            DefaultTableModel model = (DefaultTableModel) getTable1().getModel();
            model.getDataVector().removeAllElements();
            model.fireTableDataChanged();
            for(int i=0; i<albums.size();i++){
                model.addRow(new Object[] {albums.get(i).getNazwa(), albums.get(i).getDate(), albums.get(i).getOcena(), albums.get(i).getJezyk()});
            }
            getTable1().setModel(model);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e.getMessage(), "alert", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void mouse(){
        ArtistWindow temp = this;
        super.getAddButton().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                super.mouseClicked(mouseEvent);
                AddOrEditAlbumWindow addOrEditAlbumWindow = new AddOrEditAlbumWindow(getDataBaseConnector(),zespol,temp);
                addOrEditAlbumWindow.setVisible(true);
                setVisible(false);
            }
        });

        super.getDeleteButton().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                super.mouseClicked(mouseEvent);
                try {
                    getDataBaseConnector().deleteAlbum(albums.get(getTable1().getSelectedRow()).getId());
                    setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null, e.getMessage(), "alert", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        super.getEditButton().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                super.mouseClicked(mouseEvent);
                if(!getTable1().getSelectionModel().isSelectionEmpty()) {
                    AddOrEditAlbumWindow addOrEditAlbumWindow = new AddOrEditAlbumWindow(getDataBaseConnector(),zespol,temp,albums.get(getTable1().getSelectedRow()));
                    addOrEditAlbumWindow.setVisible(true);
                    setVisible(false);
                }
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

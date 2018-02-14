package Gui.Detailed;

import DataBase.DataBaseConnector;
import Gui.Change.AddOrEditAlbumWindow;
import JavaObjects.Album;
import JavaObjects.Gatunek;
import JavaObjects.Zespol;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class ArtistWindow extends Detailed{

    private JLabel info;
    private JLabel head;
    private JComboBox generesJComboBox;
    private JButton genereButton;
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
        this.generesJComboBox = new JComboBox();
        this.genereButton = new JButton("Read about selected genere");
        try {
            generes = getDataBaseConnector().getGatunki(zespol.getId(), "");
            for (Gatunek genere :
                    generes) {
                    this.generesJComboBox.addItem(genere);
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e.getMessage(), "alert", JOptionPane.ERROR_MESSAGE);
        }
        c = new GridBagConstraints();
        c.gridy = 1;
        c.gridx = 1;
        informationPanel.add(info, c);
        c.gridy = 3;
        c.gridx = 1;
        informationPanel.add(head, c);
        c.gridx = 1;
        c.gridy = 2;
        informationPanel.add(generesJComboBox, c);
        c.gridx = 2;
        c.gridy = 2;
        informationPanel.add(genereButton, c);
        this.pack();
        this.genereButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Gatunek genere = (Gatunek)generesJComboBox.getSelectedItem();
                if(genere != null)
                    JOptionPane.showMessageDialog(null, genere.getOpis(), genere.getNazwa(), JOptionPane.INFORMATION_MESSAGE);
            }
        });
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
                    if(getTable1().getSelectedRow() == -1)
                        throw new Exception("Please select a row");
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

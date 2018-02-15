package Gui.Detailed;

import DataBase.DataBaseConnector;
import Gui.Change.AddOrEditArtistWindow;
import Gui.Change.AddOrEditMembershipWindow;
import Gui.Change.AddOrEditSongWindow;
import Gui.Scrollable.ArtistsWindow;
import JavaObjects.Album;
import JavaObjects.Muzyk;
import JavaObjects.Utwor;
import JavaObjects.Zespol;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class MusicianWindow extends Detailed{
    private JLabel info;
    private JLabel head;
    private Muzyk muzyk;
    //private JComboBox zespolyJComboBox;
    private ArrayList<Zespol> zespoly;
    private GridBagConstraints c;

    public MusicianWindow(DataBaseConnector dataBaseConnector, JFrame father, Muzyk muzyk){
        super(dataBaseConnector,father);
        this.muzyk = muzyk;
        setInformationPanel();
        super.getEditButton().setVisible(false);
        super.getSelectButton().setVisible(false);
        try {

            /* TODO
            Napisac w dataBaseConnector getZespoly(muzyk.getImie(),muzyk.getNazwisko())

            this.zespoly = dataBaseConnector.getZespoly(muzyk.getId());
            */

            DefaultTableModel model = (DefaultTableModel) getTable1().getModel();
            String header[] = new String[] { "Name"};

            model.setColumnIdentifiers(header);
            for(int i=0; i<zespoly.size();i++){
                model.addRow(new Object[] {zespoly.get(i).getNazwa()});
            }
            getTable1().setModel(model);

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e.getMessage(), "alert", JOptionPane.ERROR_MESSAGE);
        }
        mouse();
    }

    public void setInformationPanel(){
        this.info = new JLabel(muzyk.toString());
        this.head = new JLabel("Zespoly");

        /*ArrayList<Zespol> zespoly = null;
        try {
            zespoly = getDataBaseConnector().getZespoly();
            this.zespolyJComboBox = new JComboBox();
            for(int i =0; i<zespoly.size();i++)
                this.zespolyJComboBox.addItem(zespoly.get(i));
            Zespol selected = getDataBaseConnector().getZespol(albumEdit.getZespolId());
            this.zespolyJComboBox.setSelectedItem(selected);
        } catch (Exception e) {
            e.printStackTrace();
        }*/

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
        this.zespoly.clear();
        try {
            //todo getZespoly(imie,nazwisko)
            //this.zespoly = getDataBaseConnector().getZespoly(muzyk.getImie(),muzyk.getNazwisko());

            DefaultTableModel model = (DefaultTableModel) getTable1().getModel();
            model.getDataVector().removeAllElements();
            model.fireTableDataChanged();
            for(int i=0; i<zespoly.size();i++){
                model.addRow(new Object[] {zespoly.get(i).getNazwa()});
            }
            getTable1().setModel(model);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e.getMessage(), "alert", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void mouse(){
        MusicianWindow temp = this;
        super.getAddButton().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                super.mouseClicked(mouseEvent);
                AddOrEditMembershipWindow addOrEditMembershipWindow = new AddOrEditMembershipWindow(getDataBaseConnector(),temp,muzyk);
                addOrEditMembershipWindow.setVisible(true);
                setVisible(false);
            }
        });

        /* TODO muzykId muzyk.getId()
        super.getDeleteButton().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                super.mouseClicked(mouseEvent);
                try {
                    getDataBaseConnector().deleteCzlonkostwo(zespoly.get(getTable1().getSelectedRow()).getId(),muzyk.getId());
                    setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null, e.getMessage(), "alert", JOptionPane.ERROR_MESSAGE);
                }
            }
        });*/

        super.getSelectButton().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                super.mouseClicked(mouseEvent);
                if(!getTable1().getSelectionModel().isSelectionEmpty()) {
                    ArtistWindow artistWindow = new ArtistWindow(getDataBaseConnector(),temp,zespoly.get(getTable1().convertRowIndexToModel(getTable1().getSelectedRow())));
                    artistWindow.setVisible(true);
                    setVisible(false);
                }
            }
        });
    }
}

package Gui.Detailed;

import DataBase.DataBaseConnector;
import Gui.Change.AddOrEditAlbumWindow;
import Gui.Change.AddOrEditSongWindow;
import JavaObjects.Album;
import JavaObjects.Utwor;
import com.github.lgooddatepicker.components.DatePicker;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class AlbumWindow extends Detailed{

    private JLabel info;
    private JLabel head;
    private Album album;
    private ArrayList<Utwor> utwory;
    private GridBagConstraints c;

    public AlbumWindow(DataBaseConnector dataBaseConnector, JFrame father, Album album){
        super(dataBaseConnector,father);
        this.album = album;
        setInformationPanel();
        try {
            this.utwory = dataBaseConnector.getUtwory(album.getId());
            DefaultTableModel model = (DefaultTableModel) getTable1().getModel();
            String header[] = new String[] { "Title", "Duration" };

            model.setColumnIdentifiers(header);
            for(int i=0; i<utwory.size();i++){
                model.addRow(new Object[] {utwory.get(i).getTytul(), utwory.get(i).getFormatedTimestamp()});
            }
            getTable1().setModel(model);

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e.getMessage(), "alert", JOptionPane.ERROR_MESSAGE);
        }
        mouse();
    }

    public void setInformationPanel(){
        this.info = new JLabel(album.toString());
        this.head = new JLabel("Utwory");
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
        this.utwory.clear();
        try {
            this.utwory = getDataBaseConnector().getUtwory(album.getId());
            DefaultTableModel model = (DefaultTableModel) getTable1().getModel();
            model.getDataVector().removeAllElements();
            model.fireTableDataChanged();
            for(int i=0; i<utwory.size();i++){
                model.addRow(new Object[] {utwory.get(i).getTytul(), utwory.get(i).getFormatedTimestamp()});
            }
            getTable1().setModel(model);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e.getMessage(), "alert", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void mouse(){
        AlbumWindow temp = this;
        super.getAddButton().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                super.mouseClicked(mouseEvent);
                AddOrEditSongWindow addOrEditSongWindow = new AddOrEditSongWindow(getDataBaseConnector(),album,temp);
                addOrEditSongWindow.setVisible(true);
                setVisible(false);
            }
        });

        super.getDeleteButton().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                super.mouseClicked(mouseEvent);
                try {
                    getDataBaseConnector().deleteUtwor(utwory.get(getTable1().getSelectedRow()).getTytul(), album.getId());
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

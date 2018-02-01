package Gui.Detailed;

import DataBase.DataBaseConnector;
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
    private DataBaseConnector dataBaseConnector;
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
            String header[] = new String[] { "Tytuł", "Czas" };

            model.setColumnIdentifiers(header);
            for(int i=0; i<utwory.size();i++){
                model.addRow(new Object[] {utwory.get(i).getTytul(), utwory.get(i).getCzas()});
            }
            getTable1().setModel(model);

        } catch (Exception e) {
            e.printStackTrace();
            //TODO wyskakujace okienko z bledem
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

    public void mouse(){
        super.getAddButton().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                super.mouseClicked(mouseEvent);
            }
        });

        super.getDeleteButton().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                super.mouseClicked(mouseEvent);
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

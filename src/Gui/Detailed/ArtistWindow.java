package Gui.Detailed;

import DataBase.DataBaseConnector;
import Gui.Change.AddOrEditAlbumWindow;
import JavaObjects.Album;
import JavaObjects.Zespol;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class ArtistWindow extends Detailed{

    private JLabel info;
    private JLabel head;
    private Zespol zespol;
    private ArrayList<Album> albumy;
    private GridBagConstraints c;

    public ArtistWindow(DataBaseConnector dataBaseConnector, JFrame father, Zespol zespol){
        super(dataBaseConnector,father);
        this.zespol = zespol;
        setInformationPanel();
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
            }
        });

        super.getEditButton().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                super.mouseClicked(mouseEvent);
                if(!getTable1().getSelectionModel().isSelectionEmpty()) {
                    AddOrEditAlbumWindow addOrEditAlbumWindow = new AddOrEditAlbumWindow(getDataBaseConnector(),zespol,temp,albumy.get(getTable1().getSelectedRow()));
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

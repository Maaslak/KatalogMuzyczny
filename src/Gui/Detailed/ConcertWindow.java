package Gui.Detailed;

import DataBase.DataBaseConnector;
import Gui.Change.AddOrEditAlbumWindow;
import Gui.Change.AddOrEditArtistWindow;
import Gui.Change.AddOrEditConcertWindow;
import JavaObjects.Koncert;
import JavaObjects.Zespol;
import com.github.lgooddatepicker.components.DatePicker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class ConcertWindow extends Detailed{

    private JLabel info;
    private JLabel head;
    private Koncert koncert;
    private Zespol zespol;
    private GridBagConstraints c;

    public ConcertWindow(DataBaseConnector dataBaseConnector, JFrame father, Koncert koncert){
        super(dataBaseConnector,father);
        this.koncert = koncert;
        getAddButton().setVisible(false);
        getEditButton().setVisible(false);
        getSelectButton().setVisible(false);
        getDeleteButton().setVisible(false);
        setInformationPanel();
        mouse();
    }

    public void setInformationPanel(){
        this.info = new JLabel(koncert.toString());
        this.head = new JLabel("Zespoly");
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
        ConcertWindow temp = this;

        /*super.getEditButton().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                super.mouseClicked(mouseEvent);
                AddOrEditConcertWindow addOrEditConcertWindow = new AddOrEditConcertWindow(getDataBaseConnector(),temp,koncert);
            }
        });*/

        /*super.getSelectButton().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                super.mouseClicked(mouseEvent);
            }
        });*/
    }
}

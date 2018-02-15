package Gui.Detailed;

import DataBase.DataBaseConnector;
import Gui.Change.AddOrEditAlbumWindow;
import Gui.Change.AddOrEditArtistWindow;
import Gui.Change.AddOrEditConcertWindow;
import JavaObjects.Koncert;
import JavaObjects.Zespol;
import com.github.lgooddatepicker.components.DatePicker;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
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
        this.info = new JLabel(koncert.getInfo());
        this.head = new JLabel("Zespoly");
        try {
            this.zespol = getDataBaseConnector().getZespol(koncert.getZespolId());
            DefaultTableModel model = (DefaultTableModel) getTable1().getModel();
            model.addColumn("Name");
            model.addColumn("Formed Date");
            model.addColumn("City");
            model.addColumn("Country");
            model.addRow(new Object[] {zespol.getNazwa(), zespol.getDate(), zespol.getMiasto_zalozenia(), zespol.getKraj_zalozenia()});
            getTable1().setModel(model);
        } catch (Exception e) {
            e.printStackTrace();
        }
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

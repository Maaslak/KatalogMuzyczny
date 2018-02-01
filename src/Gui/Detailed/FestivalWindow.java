package Gui.Detailed;
import DataBase.DataBaseConnector;
import Gui.Change.AddOrEditConcertWindow;
import Gui.Change.AddOrEditFestivalWindow;
import JavaObjects.Festiwal;
import JavaObjects.Koncert;
import JavaObjects.Zespol;
import com.github.lgooddatepicker.components.DatePicker;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class FestivalWindow extends Detailed{

    private JLabel info;
    private JLabel head;
    private Festiwal festiwal;
    private ArrayList<Koncert> koncerty;
    private GridBagConstraints c;

    public FestivalWindow(DataBaseConnector dataBaseConnector, JFrame father, Festiwal festiwal){
        super(dataBaseConnector,father);
        this.festiwal = festiwal;
        setInformationPanel();
        try {
            //this.koncerty = dataBaseConnector.getKoncert("",null,null,"", "", new Integer(festiwal.getId()));
            DefaultTableModel model = (DefaultTableModel) getTable1().getModel();
            String header[] = new String[] { "Nazwa", "Data", "Miasto" };

            model.setColumnIdentifiers(header);
            for(int i=0; i<koncerty.size();i++){
                model.addRow(new Object[] {koncerty.get(i).getNazwa(), koncerty.get(i).getData(), koncerty.get(i).getMiasto_nazwa()});
            }
            getTable1().setModel(model);

        } catch (Exception e) {
            e.printStackTrace();
            //TODO wyskakujace okienko z bledem
        }
        mouse();
    }

    public void setInformationPanel(){
        this.info = new JLabel(festiwal.toString());
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
        this.koncerty.clear();
        try {
            this.koncerty = getDataBaseConnector().getKoncert();
            DefaultTableModel model = (DefaultTableModel) getTable1().getModel();
            model.getDataVector().removeAllElements();
            model.fireTableDataChanged();
            for(int i=0; i<koncerty.size();i++){
                model.addRow(new Object[] {koncerty.get(i).getNazwa(), koncerty.get(i).getData(), koncerty.get(i).getMiasto_nazwa()});
            }
            getTable1().setModel(model);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    FestivalWindow temp = this;
    public void mouse(){
        super.getAddButton().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                super.mouseClicked(mouseEvent);
                AddOrEditConcertWindow addOrEditConcertWindow = new AddOrEditConcertWindow(getDataBaseConnector(),festiwal,temp);
                addOrEditConcertWindow.setVisible(true);
                setVisible(false);
            }
        });

        super.getEditButton().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                super.mouseClicked(mouseEvent);
                if(!getTable1().getSelectionModel().isSelectionEmpty()) {
                    AddOrEditConcertWindow addOrEditConcertWindow = new AddOrEditConcertWindow(getDataBaseConnector(),festiwal,temp,koncerty.get(getTable1().getSelectedRow()));
                    addOrEditConcertWindow.setVisible(true);
                    setVisible(false);
                }
            }
        });

        super.getDeleteButton().addMouseListener(new MouseAdapter() {
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

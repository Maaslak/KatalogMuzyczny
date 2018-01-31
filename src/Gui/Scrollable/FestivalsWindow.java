package Gui.Scrollable;

import DataBase.DataBaseConnector;
import Gui.Detailed.ConcertWindow;
import Gui.Detailed.FestivalWindow;
import JavaObjects.Festiwal;
import com.github.lgooddatepicker.components.DatePicker;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class FestivalsWindow extends Scrollable{
    private JLabel date, name, festival;
    private JTextField nameJTextField;
    private JCheckBox festivalJCheckBox;
    private DatePicker from, to;
    private GridBagConstraints c;
    //private FestivalWindow festivalWindow;
    private ArrayList<Festiwal> festiwale;

    public FestivalsWindow(DataBaseConnector dataBaseConnector, JFrame father) {
        super(dataBaseConnector,father);

        try {
            this.festiwale = dataBaseConnector.getFestiwale();
            DefaultTableModel model = (DefaultTableModel) getTable1().getModel();
            String header[] = new String[] { "Nazwa", "Data rozpoczecia", "Data zakoczenia" };
            model.setColumnIdentifiers(header);
            for(int i=0; i<festiwale.size();i++){
                model.addRow(new Object[] {festiwale.get(i).getNazwa(), festiwale.get(i).getDataRozpoczecia(), festiwale.get(i).getDataZakonczenia()});
            }
            getTable1().setModel(model);
        } catch (Exception e) {
            e.printStackTrace();
        }

        c = new GridBagConstraints();
        setFilterPanel();

        mouse();
    }

    public void setFilterPanel() {
        date = new JLabel("Date: ");
        name = new JLabel("Name");
        festival = new JLabel("Rating");
        from = new DatePicker();
        to = new DatePicker();
        nameJTextField = new JTextField(5);
        festivalJCheckBox = new JCheckBox("festivals");
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
        filterPanel.add(festival, c);
        c.gridy = 3;
        c.gridx = 1;
        filterPanel.add(festivalJCheckBox, c);
        this.pack();
    }

    public void mouse(){
        super.getFilterButton().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                super.mouseClicked(mouseEvent);
                try {
                    festiwale.clear();
                    festiwale = getDataBaseConnector().getFestiwale();
                    DefaultTableModel model = (DefaultTableModel) getTable1().getModel();
                    model.getDataVector().removeAllElements();
                    model.fireTableDataChanged();
                    for(int i=0; i<festiwale.size();i++){
                        model.addRow(new Object[] {festiwale.get(i).getNazwa(), festiwale.get(i).getDataRozpoczecia(), festiwale.get(i).getDataZakonczenia()});
                    }
                    getTable1().setModel(model);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });

        FestivalsWindow temp = this;
        super.getSelectButton().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                super.mouseClicked(mouseEvent);
                if(!getTable1().getSelectionModel().isSelectionEmpty()) {
                    FestivalWindow festivalWindow= new FestivalWindow(getDataBaseConnector(),temp,festiwale.get(getTable1().getSelectedRow()));
                    festivalWindow.setVisible(true);
                    setVisible(false);
                }
            }
        });
    }
}

package Gui.Scrollable;

import DataBase.DataBaseConnector;
import Gui.Detailed.ConcertWindow;
import Gui.Detailed.FestivalWindow;
import com.github.lgooddatepicker.components.DatePicker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ConcertsAndFestivalsWindow extends Scrollable {
    private JLabel date, name, festival;
    private JTextField nameJTextField;
    private JCheckBox festivalJCheckBox;
    private DatePicker from, to;
    private GridBagConstraints c;
    private FestivalWindow festivalWindow;
    private ConcertWindow concertWindow;
    private Integer row;
    /* TODO
    private ArrayList<Concert> concerts;
    private ArrayList<Festival> festivals;
    private Concert concert;
    private Festival festival;
    */

    public ConcertsAndFestivalsWindow(DataBaseConnector dataBaseConnector) {
        super(dataBaseConnector);

        c = new GridBagConstraints();
        setFilterPanel();

        super.getFilterButton().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                super.mouseClicked(mouseEvent);
            }
        });

        /*super.getSelectButton().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent, FestivalWindow festivalWindow, ConcertWindow concertWindow) {
                super.mouseClicked(mouseEvent);

                if(!getTable1().getSelectionModel().isSelectionEmpty()){

                    getTable1().getSelectionModel();
                }
            }
        });*/
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
}

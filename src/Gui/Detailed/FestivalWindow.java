package Gui.Detailed;
import DataBase.DataBaseConnector;
import JavaObjects.Festiwal;
import JavaObjects.Koncert;
import JavaObjects.Zespol;
import com.github.lgooddatepicker.components.DatePicker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class FestivalWindow extends Detailed{

    private JLabel info;
    private JLabel head;
    private Festiwal festiwal;
    private ArrayList<Zespol> zespoly;
    private GridBagConstraints c;

    public FestivalWindow(DataBaseConnector dataBaseConnector, JFrame father, Festiwal festiwal){
        super(dataBaseConnector,father);
        this.festiwal = festiwal;
        setInformationPanel();
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

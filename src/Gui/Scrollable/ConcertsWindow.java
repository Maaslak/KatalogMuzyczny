package Gui.Scrollable;

import com.github.lgooddatepicker.components.DatePicker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ConcertsWindow extends Scrollable {
    private JLabel date, name, festival;
    private JTextField nameJTextField;
    private JCheckBox festivalJCheckBox;
    private DatePicker from, to;
    private GridBagConstraints c;


    public ConcertsWindow() {
        super();
        c = new GridBagConstraints();
        setFilterPanel();

        super.getFilterButton().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                super.mouseClicked(mouseEvent);
            }
        });
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

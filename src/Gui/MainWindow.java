package Gui;

import DataBase.DataBaseConnector;
import Gui.Scrollable.AlbumsWindow;
import Gui.Scrollable.ArtistsWindow;
import Gui.Scrollable.ConcertsAndFestivalsWindow;
import Gui.Scrollable.ConcertsAndFestivalsWindow;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

public class MainWindow {
    private JPanel mainPanel;
    private JButton artistsButton;
    private JButton albumsButton;
    private JButton concertsButton;
    private ArtistsWindow artistsWindow;
    private AlbumsWindow albumsWindow;
    private ConcertsAndFestivalsWindow concertsWindow;
    private DataBaseConnector dataBaseConnector;

    public MainWindow() {
        JFrame frame = new JFrame("Main Window");
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        try {
            this.dataBaseConnector = new DataBaseConnector();

            artistsWindow = new ArtistsWindow(dataBaseConnector);
            //artistsWindow.setFilterPanel();
            albumsWindow = new AlbumsWindow(dataBaseConnector);
            //albumsWindow.setFilterPanel();
            concertsWindow = new ConcertsAndFestivalsWindow(dataBaseConnector);
            //concertsWindow.setFilterPanel();

            artistsButton.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent mouseEvent) {
                    super.mouseClicked(mouseEvent);
                    artistsWindow.setVisible(true);
                }
            });
            albumsButton.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent mouseEvent) {
                    super.mouseClicked(mouseEvent);
                    albumsWindow.setVisible(true);
                }
            });
            concertsButton.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent mouseEvent) {
                    super.mouseClicked(mouseEvent);
                    concertsWindow.setVisible(true);
                }
            });
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

}

package Gui;

import DataBase.DataBaseConnector;
import Gui.Scrollable.AlbumsWindow;
import Gui.Scrollable.ArtistsWindow;
import Gui.Scrollable.ConcertsWindow;
import Gui.Scrollable.FestivalsWindow;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

public class MainWindow{
    private JPanel mainPanel;
    private JButton artistsButton;
    private JButton albumsButton;
    private JButton concertsButton;
    private JButton festivalsButton;
    private ArtistsWindow artistsWindow;
    private AlbumsWindow albumsWindow;
    private ConcertsWindow concertsWindow;
    private FestivalsWindow festivalsWindow;
    private DataBaseConnector dataBaseConnector;

    public MainWindow() {
        JFrame frame = new JFrame("Main Window");
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        try {
            this.dataBaseConnector = new DataBaseConnector();

            artistsWindow = new ArtistsWindow(dataBaseConnector,frame);
            albumsWindow = new AlbumsWindow(dataBaseConnector,frame);
            concertsWindow = new ConcertsWindow(dataBaseConnector,frame);
            festivalsWindow = new FestivalsWindow(dataBaseConnector,frame);

            artistsButton.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent mouseEvent) {
                    super.mouseClicked(mouseEvent);
                    artistsWindow.setVisible(true);
                    frame.setVisible(false);
                }
            });
            albumsButton.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent mouseEvent) {
                    super.mouseClicked(mouseEvent);
                    albumsWindow.setVisible(true);
                    frame.setVisible(false);
                }
            });
            concertsButton.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent mouseEvent) {
                    super.mouseClicked(mouseEvent);
                    concertsWindow.setVisible(true);
                    frame.setVisible(false);
                }
            });
            festivalsButton.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent mouseEvent) {
                    super.mouseClicked(mouseEvent);
                    festivalsWindow.setVisible(true);
                    frame.setVisible(false);
                }
            });
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

}

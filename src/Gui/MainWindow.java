package Gui;

import Gui.Scrollable.AlbumsWindow;
import Gui.Scrollable.ArtistsWindow;
import Gui.Scrollable.ConcertsWindow;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainWindow {
    private JPanel mainPanel;
    private JButton artistsButton;
    private JButton albumsButton;
    private JButton concertsButton;
    private ArtistsWindow artistsWindow;
    private AlbumsWindow albumsWindow;
    private ConcertsWindow concertsWindow;

    public MainWindow() {
        JFrame frame = new JFrame("Main Window");
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        artistsWindow = new ArtistsWindow();
        artistsWindow.setFilterPanel();
        albumsWindow = new AlbumsWindow();
        albumsWindow.setFilterPanel();
        concertsWindow = new ConcertsWindow();
        concertsWindow.setFilterPanel();

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
    }

}

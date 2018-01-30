package Main;

import DataBase.DataBaseConnector;
import Gui.MainWindow;

import java.sql.SQLException;

public class Main {
    private static DataBaseConnector dataBaseConnector;


    public static void main(String [] args) {
        DataBaseConnector.setUser("maaslak");
        DataBaseConnector.setPassword("maaslak");
        try {
            dataBaseConnector = new DataBaseConnector();
            MainWindow mainWindow = new MainWindow();
        } catch (SQLException e) {
            System.out.println("Nie udalo sie polaczyc z baza danych");
            e.printStackTrace();
        }

        try {
            dataBaseConnector.disconnect();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}

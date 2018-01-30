package Main;

import DataBase.DataBaseConnector;
import Gui.MainWindow;
import JavaObjects.Zespol;

import java.sql.SQLException;
import java.util.ArrayList;

public class Main {
    private static DataBaseConnector dataBaseConnector;

    public static void testZespoly(){
        ArrayList<Zespol> zespoly = null;
        try {
            zespoly = dataBaseConnector.getZespoly();
            for (Zespol zespol:
                    zespoly) {
                System.out.println(zespol);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public static void main(String [] args) {
        DataBaseConnector.setUser("MAASLAK");
        DataBaseConnector.setPassword("MAASLAK");
        try {
            dataBaseConnector = new DataBaseConnector();
            MainWindow mainWindow = new MainWindow();
            //testZespoly();
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

package Singleton;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Created by Valodia Tsacanias <https://github.com/valoTs> on 22/05/17.
 */
public class SingletonConnection {


        //url de connexion
        private static final String HOST = "localhost";
        private static final String PORT = "3306";
        private static final String DATABASE = "JEETP5";
        private static final String URL = "jdbc:mysql://" + HOST + ":" + PORT + "/" + DATABASE;
        //nom du user
        private static final String USER = "ldnr";
        //mot de passe du user
        private static final String PASSWORD = "ldnr";
        //Objet Connection
        private static Connection connection;
        //
        public static Connection getInstance() {
            if (connection == null) {
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    connection = DriverManager.getConnection(URL, USER, PASSWORD);
                    if (connection.equals(true)) {
                        System.out.println("connection etablie");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        return connection;
        }
    }




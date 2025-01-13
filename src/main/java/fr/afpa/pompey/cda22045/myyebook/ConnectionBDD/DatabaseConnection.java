package fr.afpa.pompey.cda22045.myyebook.ConnectionBDD;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnection {

    // public static final Logger LOGGER = Logger.getLogger(DatabaseConnection.class.getName);
    private static final Properties props = new Properties();
    private  static Connection connection;
    private static final String PATHCONF = "conf.properties";

    private DatabaseConnection() {
        // chargement du fichier de propriétés
        try (InputStream is = getClass().getClassLoader().getResourceAsStream(PATHCONF)) {

            if (is == null) {
                throw new RuntimeException("Fichier de configuration non trouvé : " + PATHCONF);
            }

            props.load(is);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // traitement du fichier de propriétés et de la connexion à la BDD
        try {
            // chargement du driver
            Class.forName(props.getProperty("jdbc.driver.class"));

            // récupération des paramètres pour la connexion
            String url = props.getProperty("jdbc.url");
            String user = props.getProperty("jdbc.user");
            String password = props.getProperty("jdbc.password");

            connection = DriverManager.getConnection(url, user, password);


        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

//    public static Connection getInstanceDB() {
//        if (connection == null) {
//            new DatabaseConnection();
//            //LOGGER.info("RelationWithDB infos : Connection established");
//            System.out.println("RelationWithDB infos : Connection established");
//        }
//        else {
//            //LOGGER.info("RelationWithDB infos : Connection already existing");
//            System.out.println("RelationWithDB infos : Connection already existing");
//        }
//        return getConnection();
//    }

    public static Connection getInstanceDB() {
        try {
            if (connection == null || connection.isClosed()) {
                new DatabaseConnection(); // Recharge la connexion
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static void closeInstanceDB() {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Connection closed");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static Connection getConnection() {
        return connection;
    }
}

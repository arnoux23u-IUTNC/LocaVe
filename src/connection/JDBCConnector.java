package connection;

import java.io.*;
import java.sql.*;

/**
 * Classe d'instanciation d'un connecteur
 *
 * @author arnoux23u
 */
public class JDBCConnector {

    private final static String url = "jdbc:oracle:thin:@charlemagne.iutnc.univ-lorraine.fr:1521:infodb";
    private static Connection connection;
    private static Class<JDBCConnector> instance;
    private static String user, password;

    /**
     * Methode de connection statique
     *
     * @return connection
     * @throws JDBCException exception
     */
    public static synchronized Connection connect() throws JDBCException {
        if (instance != null)
            return connection;
        try {
            instance = JDBCConnector.class;
            connection = DriverManager.getConnection(url, user, password);
            return connection;
        } catch (SQLException exception) {
            reset();
            String msg = exception.getMessage();
            if (msg.contains("denied"))
                throw new JDBCException("Identifiants incorrects");
            if (msg.contains("suitable"))
                throw new JDBCException("Driver introuvable");
        }
        return null;
    }

    public static void setUser(String user) {
        JDBCConnector.user = user;
    }

    public static void setPassword(char[] password) {
        JDBCConnector.password = String.valueOf(password);
    }

    private static void reset(){
        instance = null;
        connection = null;
    }

}
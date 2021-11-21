package connection;

import java.sql.*;

import static gui.MenuGUI.tableExists;

/**
 * Classe d'instanciation d'un connecteur
 *
 * @author arnoux23u
 */
public class JDBCConnector {

    private final static String url = "jdbc:oracle:thin:@charlemagne.iutnc.univ-lorraine.fr:1521:infodb";
    private static Connection connection;
    /**
     * Booleen, a false si les tables ont ete crees au demmarage
     */
    public static boolean created = true;
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
            DriverManager.setLoginTimeout(4);
            connection = DriverManager.getConnection(url, user, password);
            if (!tableExists("agence") || !tableExists("calendrier") || !tableExists("categorie") || !tableExists("client") || !tableExists("dossier") || !tableExists("tarif") || !tableExists("vehicule") || !tableExists("audit")) {
                JDBCInit.create();
                created = false;
            }
            return connection;
        } catch (SQLException exception) {
            reset();
            String msg = exception.getMessage().toLowerCase();
            if (msg.contains("denied"))
                throw new JDBCException("Identifiants incorrects");
            if (msg.contains("suitable"))
                throw new JDBCException("Driver introuvable");
            if (msg.contains("adapter"))
                throw new JDBCException("Aucun VPN detecté");
            else
                throw new JDBCException("Erreur de connexion");
        }
    }

    /**
     * Methode setuser
     *
     * @param user utilisateur
     */
    public static void setUser(String user) {
        JDBCConnector.user = user;
    }

    /**
     * Methode setpassword
     *
     * @param password mot de passe
     */
    public static void setPassword(char[] password) {
        JDBCConnector.password = String.valueOf(password);
    }

    /**
     * Methode de deconnection
     */
    private static void reset() {
        instance = null;
        connection = null;
    }

    /**
     * Getter user
     *
     * @return utilisateur
     */
    public static String getUser() {
        return user;
    }
}
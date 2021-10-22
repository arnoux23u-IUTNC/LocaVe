package connection;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Classe d'instanciation d'un connecteur
 *
 * @author arnoux23u
 * @author adnet6u
 */
public class JDBCConnector {

    private final static String url = "jdbc:oracle:thin:@charlemagne.iutnc.univ-lorraine.fr:1521:infodb";
    private static final String[] params = new String[2];

    /**
     * Constructeur vide
     */
    public JDBCConnector() {
    }

    /**
     * Methode de connection
     *
     * @return connection
     * @throws JDBCException exception
     */
    public static Connection connect() throws JDBCException {
        try {
            BufferedReader br = new BufferedReader(new FileReader("src/connection/session.txt"));
            String s;
            int i = 0;
            while ((s = br.readLine()) != null) {
                params[i] = s;
                i++;
            }
            br.close();
            return DriverManager.getConnection(url, params[0], params[1]);
        } catch (FileNotFoundException exception) {
            System.out.println("Merci de creer votre session.txt");
        } catch (IOException exception) {
            System.out.println("Erreur de lecture/écriture");
        } catch (Exception e) {
            throw new JDBCException("Unable to connect, please verify session.txt !");
        }
        return null;
    }
}
package connection;

/**
 * classe de gestion des exceptions JDBC
 *
 * @author arnoux23u
 */
public class JDBCException extends Exception {
    /**
     * Constructeur exception message parametre
     *
     * @param msg message
     */
    public JDBCException(String msg) {
        super(msg);
    }
}
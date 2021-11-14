package gui.panels;

import connection.JDBCConnector;
import connection.JDBCException;

import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

/**
 * Panel permettant d'afficher toutes les reservations
 * Utilisation du patron singleton
 *
 * @author arnoux23u
 */
public class DisplayResPanel extends JPanel {

    /**
     * Instance singleton
     */
    private static DisplayResPanel instance;

    /**
     * Container panel central
     */
    private Container displayed;

    /**
     * Panel result
     */
    private final JPanel panel1;

    /**
     * Constructeur prive
     */
    private DisplayResPanel() {
        setPreferredSize(new Dimension(889, 730));
        setLayout(new BorderLayout());

        //Labels
        JLabel label1 = new JLabel("Liste des r\u00e9servations") {{
            setHorizontalAlignment(SwingConstants.CENTER);
            setFont(getFont().deriveFont(getFont().getStyle() & ~Font.BOLD, getFont().getSize() + 10f));
        }};

        //Panels
        panel1 = new JPanel() {{
            setLayout(new BorderLayout());
            setPreferredSize(new Dimension(200, 200));
            setBorder(new EmptyBorder(100, 20, 20, 20));
        }};

        //Containers
        displayed = new JLabel("Recherche en cours ...") {{
            setFont(new Font("Tahoma", Font.BOLD, 18));
            setHorizontalAlignment(CENTER);
        }};

        add(label1, BorderLayout.PAGE_START);
        panel1.add(displayed, BorderLayout.CENTER);
        add(panel1, BorderLayout.CENTER);
    }

    /**
     * Getter instance panel
     *
     * @return panel
     */
    public static synchronized DisplayResPanel getInstance() {
        if (instance == null) {
            instance = new DisplayResPanel();
        }
        return instance;
    }

    /**
     * Methode actualisation resultat
     */
    public void actualise() {
        panel1.removeAll();
        String[][] data;
        String[] headers;
        /*
        TODO NOE
            Recuperer toutes les reservations et afficher toutes les colonnes
            Exemple :
            String[] headers = {"Nom","Prenom"};
            String[][] data = {{"BIGRON","Steven"},{"ST1NERE","Noere"},{"MISKINE","Anto"},{"V","Thomthom"}};
         */
        try {
            Connection connection = JDBCConnector.connect();
            String sql = "";
            if (connection != null) {
                PreparedStatement statement = connection.prepareStatement(sql);
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {

                }
            }
        } catch (JDBCException | SQLException e1) {
            e1.printStackTrace();
        }
        headers = new String[]{"Nom", "Prenom", "Adresse", "Note"};
        data = new String[][]{{"BIGRON", "Steven", "Rue du reveil", "ABSENT"}, {"ST1NERE", "Noere", "Rue du malade", "MORT"}, {"MISKINE", "Anto", "Rue du $magik", "FAUX ACCENT"}, {"V", "Thomthom", "Rue du Java", "THREAD"}};

        displayed = new JTable(data, headers) {{
            setPreferredSize(new Dimension(100, 100));
            setFont(new Font("Tahoma", Font.PLAIN, 20));
            setRowHeight(40);
            setEnabled(false);
        }};
        panel1.add(((JTable) displayed).getTableHeader(), BorderLayout.NORTH);
        panel1.add(displayed, BorderLayout.CENTER);
        revalidate();
        repaint();
    }
}

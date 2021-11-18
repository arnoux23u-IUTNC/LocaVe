package gui.panels;

import connection.*;

import java.awt.*;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;

/**
 * Panel permettant d'afficher toutes les reservations
 * Ce panel est la a titre INDICATIF, il n'est pas nécessaire pour le programme
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
     * Static center alignement
     */
    private static final DefaultTableCellRenderer center = new DefaultTableCellRenderer() {{
        setHorizontalAlignment(JLabel.CENTER);
    }};

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
        ArrayList<ArrayList<String>> data = new ArrayList<>();
        String[] headers = new String[]{"N°", "Date Retrait", "Agence Retrait", "Date Retour", "Agence Retour", "Retour effectif", "Kil Retrait", "Kil Retour", "Tarif", "Assurance", "Jours", "Semaines", "Remise", "Client", "Immat", "Agence"};
        try {
            //On se connecte a la BDD
            Connection connection = JDBCConnector.connect();
            if (connection != null) {
                //On recupere toutes les reservations
                PreparedStatement statement = connection.prepareStatement("SELECT * FROM DOSSIER");
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    //Pour chaque reservation, on ajoute les informations utiles a la liste
                    data.add(new ArrayList<String>() {{
                        add(resultSet.getString("NO_DOSSIER"));
                        add(resultSet.getString("DATE_RETRAIT").substring(0, 10));
                        add(resultSet.getString("AG_RETRAIT"));
                        add(resultSet.getString("DATE_RETOUR").substring(0, 10));
                        add(resultSet.getString("AG_RETOUR"));
                        add(resultSet.getString("DATE_EFFECT"));
                        add(resultSet.getString("KIL_RETRAIT"));
                        add(resultSet.getString("KIL_RETOUR"));
                        add(resultSet.getString("TYPE_TARIF"));
                        add(resultSet.getString("ASSUR"));
                        add(resultSet.getString("NBJOUR_FACT"));
                        add(resultSet.getString("NBSEM_FACT"));
                        add(resultSet.getString("REMISE"));
                        add(resultSet.getString("CODE_CLI"));
                        add(resultSet.getString("NO_IMM"));
                        add(resultSet.getString("AG_RESERVE"));
                    }});
                }
                String[][] dataArray = new String[data.size()][headers.length];
                for (int i = 0; i < data.size(); i++) {
                    for (int j = 0; j < headers.length; j++) {
                        dataArray[i][j] = data.get(i).get(j) != null ? data.get(i).get(j) : "";
                    }
                }
                //Affichage sous forme de tableau
                displayed = new JTable(dataArray, headers) {{
                    setPreferredSize(new Dimension(100, 100));
                    setFont(new Font("Tahoma", Font.PLAIN, 14));
                    setRowHeight(40);
                    setEnabled(false);
                    getColumn("Date Retrait").setPreferredWidth(150);
                    getColumn("Client").setPreferredWidth(150);
                    getColumn("Agence Retour").setPreferredWidth(150);
                    getColumn("Immat").setPreferredWidth(150);
                    getColumn("Assurance").setCellRenderer(center);
                    getColumn("Tarif").setCellRenderer(center);
                    getColumn("N°").setCellRenderer(center);
                    getColumn("Date Retour").setPreferredWidth(150);
                    setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
                }};
                panel1.add(((JTable) displayed).getTableHeader(), BorderLayout.NORTH);
                panel1.add(displayed, BorderLayout.CENTER);
                revalidate();
                repaint();
            }
        } catch (JDBCException | SQLException e1) {
            e1.printStackTrace();
        }
    }
}

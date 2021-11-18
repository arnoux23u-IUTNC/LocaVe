package gui.panels;

import connection.*;

import java.awt.*;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

/**
 * Panel permettant de trouver les agences ayant toutes les categories de vehicule
 * Utilisation du patron singleton
 *
 * @author arnoux23u
 * @author steiner58u
 */
public class CategAgencesPanel extends JPanel {

    /**
     * Instance singleton
     */
    private static CategAgencesPanel instance;

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
    private CategAgencesPanel() {
        setPreferredSize(new Dimension(889, 730));
        setLayout(new BorderLayout());

        //Labels
        JLabel label1 = new JLabel("Agences toutes cat\u00e9gories") {{
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
    public static synchronized CategAgencesPanel getInstance() {
        if (instance == null) {
            instance = new CategAgencesPanel();
        }
        return instance;
    }

    /**
     * Methode actualisation resultat
     * Recharge le panel
     */
    public void actualise() {
        panel1.removeAll();
        ArrayList<ArrayList<String>> data = new ArrayList<>();
        String[] headers = new String[]{"Code Agence", "Adresse"};
        try {
            //On se connecte a la BDD
            Connection connection = JDBCConnector.connect();
            if (connection != null) {
                //On recupere le code, la rue, la ville et le code postal de chaque agence
                PreparedStatement statement = connection.prepareStatement("SELECT A.CODE_AG, A.RUE, A.VILLE, A.CODPOSTAL FROM VEHICULE V INNER JOIN AGENCE A ON V.CODE_AG = A.CODE_AG HAVING COUNT(DISTINCT CODE_CATEG) = (SELECT COUNT(CODE_CATEG) FROM CATEGORIE) GROUP BY A.CODE_AG, A.RUE, A.VILLE, A.CODPOSTAL");
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    //Pour chaque agence, on l'ajoute a la liste
                    data.add(new ArrayList<String>() {{
                        add(resultSet.getString("CODE_AG"));
                        add(resultSet.getString("RUE") + " - " + resultSet.getString("CODPOSTAL") + " - " + resultSet.getString("VILLE"));
                    }});
                }
                String[][] dataArray = new String[data.size()][headers.length];
                for (int i = 0; i < data.size(); i++) {
                    for (int j = 0; j < headers.length; j++) {
                        dataArray[i][j] = data.get(i).get(j);
                    }
                }
                //Affichage sous forme de tableau
                displayed = new JTable(dataArray, headers) {{
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
        } catch (JDBCException | SQLException e) {
            e.printStackTrace();
        }
    }
}

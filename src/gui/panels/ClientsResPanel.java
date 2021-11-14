package gui.panels;

import connection.JDBCConnector;
import connection.JDBCException;
import gui.*;

import java.awt.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

/**
 * Panel permettant de trouver les clients ayant loues un certain nombres de vehicules
 * Utilisation du patron singleton
 *
 * @author arnoux23u
 * @author steiner58u
 */
public class ClientsResPanel extends JPanel {

    /**
     * Instance singleton
     */
    private static ClientsResPanel instance;

    /**
     * Container resultat
     */
    Container displayed;

    /**
     * Constructeur prive
     */
    private ClientsResPanel() {
        setPreferredSize(new Dimension(889, 730));
        setLayout(new BorderLayout());

        //Labels
        JLabel label1 = new JLabel("Clients fidèles") {{
            setHorizontalAlignment(SwingConstants.CENTER);
            setFont(getFont().deriveFont(getFont().getStyle() & ~Font.BOLD, getFont().getSize() + 10f));

        }};
        JLabel label2 = new JLabel("Nombre de modèles distincts loués");

        //JTextFields
        JTextField jTextField = new JTextField() {{
            setPreferredSize(new Dimension(60, 30));
        }};

        //Panels
        JPanel panel1 = new JPanel() {{
            setBorder(new EmptyBorder(20, 10, 20, 10));
            setLayout(new BorderLayout());
        }};
        JPanel panel2 = new JPanel() {{
            setLayout(new GridBagLayout());
        }};
        JPanel panel3 = new JPanel() {{
            setLayout(new BorderLayout());
        }};

        //Buttons
        StyledButton rechercher = new StyledButton("Rechercher") {{
            addActionListener(e -> {
                String[][] data;
                String[] headers = new String[2];
                try {
                    int nbModeles = Integer.parseInt(jTextField.getText());
                    if (nbModeles > 0) {
                        /*
                        TODO NOE
                            Recuperer toutes les colonnes et toutes les valeurs des clients ayant loué un certain nombre de modèles de vehicules
                            Exemple :
                            String[] headers = {"Nom","Prenom"};
                            String[][] data = {{"BIGRON","Steven"},{"ST1NERE","Noere"},{"MISKINE","Anto"},{"V","Thomthom"}};
                         */
                        try {
                            Connection connection = JDBCConnector.connect();
                            String sql = "select c.CODE_CLI, count( distinct v.MODELE) from CLIENT C inner join DOSSIER D on C.CODE_CLI = D.CODE_CLI inner join VEHICULE v on v.NO_IMM = d.NO_IMM having count(distinct v.MODELE) = 2 group by c.CODE_CLI order by c.CODE_CLI";
                            if (connection != null) {
                                PreparedStatement statement = connection.prepareStatement(sql);
                                ResultSet resultSet = statement.executeQuery();
                                ResultSetMetaData rsmd = resultSet.getMetaData();
                                String name = rsmd.getColumnName(1);
                                String name2 = rsmd.getColumnName(2);
                                headers[0] = name;
                                headers[1] = name2;
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
                        panel3.add(((JTable) displayed).getTableHeader(), BorderLayout.NORTH);
                        panel3.add(displayed, BorderLayout.CENTER);
                    } else
                        JOptionPane.showMessageDialog(null, "Le nombre de modèles doit être supérieur à 0");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Veuillez entrer un nombre valide", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
                revalidate();
                repaint();
            });
        }};

        //Containers
        displayed = new JLabel("Recherche en cours ...") {{
            setFont(new Font("Tahoma", Font.BOLD, 18));
            setHorizontalAlignment(CENTER);
        }};

        add(label1, BorderLayout.PAGE_START);
        panel2.add(label2, MenuGUI.createConstraint(0, 0, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, 0, 0, 0, 20, 0, 0));
        panel2.add(jTextField, MenuGUI.createConstraint(1, 0, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, 0, 0, 0, 20, 0, 0));
        panel2.add(rechercher, MenuGUI.createConstraint(2, 0, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, 0, 0, 0, 20, 0, 0));
        panel1.add(panel2, BorderLayout.NORTH);
        panel3.add(displayed, BorderLayout.CENTER);
        panel1.add(panel3, BorderLayout.CENTER);
        add(panel1, BorderLayout.CENTER);
    }

    /**
     * Getter instance panel
     *
     * @return panel
     */
    public static synchronized ClientsResPanel getInstance() {
        if (instance == null) {
            instance = new ClientsResPanel();
        }
        return instance;
    }
}

package gui.panels;

import connection.*;
import gui.*;

import java.awt.*;
import java.sql.*;
import java.util.ArrayList;
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
                panel3.removeAll();
                ArrayList<ArrayList<String>> data = new ArrayList<>();
                String[] headers = new String[]{"Code Client", "Nom", "Adresse"};
                try {
                    //On recupere le nombre de modeles voulus (pour ne pas chercher forcement qu'avec 2)
                    int nbModeles = Integer.parseInt(jTextField.getText());
                    if (nbModeles > 0) {
                        try {
                            //On se connecte a la BDD
                            Connection connection = JDBCConnector.connect();
                            //On recupere les clients ayant loue le nombre voulu de modeles
                            if (connection != null) {
                                PreparedStatement statement = connection.prepareStatement("SELECT C.* FROM CLIENT C INNER JOIN DOSSIER D ON C.CODE_CLI = D.CODE_CLI INNER JOIN VEHICULE V ON V.NO_IMM = D.NO_IMM HAVING COUNT(DISTINCT V.MODELE) = ? GROUP BY C.CODE_CLI, NOM, RUE, VILLE, CODPOSTAL ORDER BY C.CODE_CLI");
                                statement.setInt(1, nbModeles);
                                ResultSet resultSet = statement.executeQuery();
                                while (resultSet.next()) {
                                    //Pour chaque client, on recupere toute les informations et on les ajoute a la liste
                                    data.add(new ArrayList<String>() {{
                                        add(resultSet.getString("CODE_CLI"));
                                        add(resultSet.getString("NOM"));
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
                                panel3.add(((JTable) displayed).getTableHeader(), BorderLayout.NORTH);
                                panel3.add(displayed, BorderLayout.CENTER);
                                repaint();
                                revalidate();
                            }
                        } catch (JDBCException | SQLException e1) {
                            e1.printStackTrace();
                        }
                    } else
                        JOptionPane.showMessageDialog(null, "Le nombre de modèles doit être supérieur à 0");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Veuillez entrer un nombre valide", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
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

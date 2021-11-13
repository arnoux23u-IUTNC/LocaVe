package gui.panels;

import com.toedter.calendar.JCalendar;
import connection.JDBCConnector;
import connection.JDBCException;
import gui.*;

import java.awt.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;
import javax.swing.*;

/**
 * Panel permettant de voir les vehicules disponibles
 * Utilisation du patron singleton
 *
 * @author arnoux23u
 * @author steiner58u
 */
public class AvailablesCarsPanel extends JPanel {

    /**
     * Instance singleton
     */
    private static AvailablesCarsPanel instance;

    /**
     * Constructeur prive
     */
    private AvailablesCarsPanel() {
        setPreferredSize(new Dimension(889, 730));
        setLayout(new BorderLayout());

        //Labels
        JLabel label1 = new JLabel("Demande de disponibilit\u00e9s v\u00e9hicule") {{
            setHorizontalAlignment(SwingConstants.CENTER);
            setFont(getFont().deriveFont(getFont().getStyle() & ~Font.BOLD, getFont().getSize() + 10f));

        }};
        JLabel label3 = new JLabel("Cat\u00e9gorie");
        JLabel label4 = new JLabel("Date d\u00e9part");
        JLabel label5 = new JLabel("Date retour");
        JLabel label6 = new JLabel("Aucun véhicule disponible") {{
            setFont(new Font("Tahoma", Font.BOLD, 18));
            setHorizontalAlignment(CENTER);
        }};

        //Panels
        JPanel panel1 = new JPanel() {{
            setPreferredSize(new Dimension(300, 225));
            setLayout(new GridBagLayout());
            ((GridBagLayout) getLayout()).rowHeights = new int[]{0, 0, 0, 0, 0};
            ((GridBagLayout) getLayout()).rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0E-4};
        }};
        JPanel panel2 = new JPanel() {{
            setPreferredSize(new Dimension(280, 60));
            setLayout(new GridLayout(2, 0));
        }};
        JPanel panel3 = new JPanel() {{
            setPreferredSize(new Dimension(280, 280));
            setLayout(new GridBagLayout());
        }};
        JPanel panel4 = new JPanel() {{
            setPreferredSize(new Dimension(280, 280));
            setLayout(new GridBagLayout());
        }};

        //ComboBoxes
        JComboBox<String> comboBox1 = new JComboBox<String>() {{
            /*
            TODO NOE
             Recuprer liste des categories et ajouter chaque catégorie avec le methode addItem
             Ex : addItem("Categorie 1");
             Faire ça juste en dessous de ce commentaire
             --> Definir le texte par defaut sur la premiere categorie
             */
            Connection connection = null;
            try {
                connection = JDBCConnector.connect();
            } catch (JDBCException e) {
                e.printStackTrace();
            }
            String sql = "select LIBELLE from CATEGORIE";
            ResultSet resultSet = null;
            try {
                assert connection != null;
                resultSet = connection.createStatement().executeQuery(sql);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            while (true) {
                try {
                    assert resultSet != null;
                    if (!resultSet.next()) break;
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                try {
                    addItem(resultSet.getString("LIBELLE"));
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }};

        //Dates and Calendars
        Date date1 = new Date(1441065600000L);
        Date date2 = new Date(1448841600000L);
        JCalendar calendar1 = new JCalendar(date1) {{
            setPreferredSize(new Dimension(280, 180));
            setMinSelectableDate(date1);
            setMaxSelectableDate(date2);
            setLocale(java.util.Locale.FRANCE);
        }};
        JCalendar calendar2 = new JCalendar(date1) {{
            setPreferredSize(new Dimension(280, 180));
            setMinSelectableDate(date1);
            setMaxSelectableDate(date2);
            setLocale(java.util.Locale.FRANCE);
        }};

        //Buttons
        JButton button3 = new StyledButton("Soumettre") {{
            addActionListener(e -> {
                ArrayList<String> vehicules = new ArrayList<>();
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                Date date1 = calendar1.getDate();
                Date date2 = calendar2.getDate();
                if (date1.before(date2) || date1.equals(date2)) {
                    String dateDebut = format.format(calendar1.getDate());
                    String dateFin = format.format(calendar2.getDate());
                    String categorie = (String) comboBox1.getSelectedItem();
                    if (categorie != null) {
                        /*
                        TODO NOE
                         Pour chaque vehicule dispo, l'ajouter a la liste
                         La liste doit contenir les plaques d'immatriculation
                        */
                        StringBuilder sb = new StringBuilder("<html>");
                        for (String vehicule : vehicules) {
                            sb.append(vehicule).append("<br/><br/>");
                        }
                        sb.append("</html>");
                        label6.setText(sb.toString());
                    } else
                        JOptionPane.showMessageDialog(null, "La catégorie ne doit pas être nulle", "Erreur", JOptionPane.ERROR_MESSAGE);
                } else
                    JOptionPane.showMessageDialog(null, "La date de départ doit être inférieure à la date de retour", "Erreur", JOptionPane.ERROR_MESSAGE);
            });
        }};

        add(label1, BorderLayout.PAGE_START);
        add(label6, BorderLayout.CENTER);
        panel2.add(label3);
        panel2.add(comboBox1);
        panel1.add(panel2, MenuGUI.createConstraint(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, 0, 0, 0, 0, 0, 0));
        panel3.add(label4, MenuGUI.createConstraint(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, 0, 0, 0, 0, 0, 0));
        panel3.add(calendar1, MenuGUI.createConstraint(0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, 25, 0, 0, 0, 0, 0));
        panel1.add(panel3, MenuGUI.createConstraint(0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, 0, 0, 0, 0, 0, 0));
        panel4.add(label5, MenuGUI.createConstraint(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, 0, 0, 0, 0, 0, 0));
        panel4.add(calendar2, MenuGUI.createConstraint(0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, 25, 0, 0, 0, 0, 0));
        panel1.add(panel4, MenuGUI.createConstraint(0, 2, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, 0, 0, 0, 0, 0, 0));
        panel1.add(button3, MenuGUI.createConstraint(0, 3, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, 0, 0, 0, 0, 0, 0));
        add(panel1, BorderLayout.LINE_START);
    }

    /**
     * Getter instance panel
     *
     * @return panel
     */
    public static synchronized AvailablesCarsPanel getInstance() {
        if (instance == null) {
            instance = new AvailablesCarsPanel();
        }
        return instance;
    }
}

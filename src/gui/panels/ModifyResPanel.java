package gui.panels;

import com.toedter.calendar.JCalendar;
import connection.JDBCConnector;
import connection.JDBCException;
import gui.*;

import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.*;

/**
 * Panel permettant de modifier le calendrier des reservations
 * Utilisation du patron singleton
 *
 * @author arnoux23u
 * @author steiner58u
 */
public class ModifyResPanel extends JPanel {

    /**
     * Instance singleton
     */
    private static ModifyResPanel instance;

    /**
     * Constructeur prive
     */
    private ModifyResPanel() {
        setPreferredSize(new Dimension(889, 730));
        setLayout(new BorderLayout());

        //Labels
        JLabel label1 = new JLabel("Modification r\u00e9servations v\u00e9hicule") {{
            setHorizontalAlignment(SwingConstants.CENTER);
            setFont(getFont().deriveFont(getFont().getStyle() & ~Font.BOLD, getFont().getSize() + 10f));

        }};
        JLabel label3 = new JLabel("Immatriculation");
        JLabel label4 = new JLabel("Date d\u00e9part");
        JLabel label5 = new JLabel("Date retour");
        JLabel label6 = new JLabel() {{
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
             Recuprer liste des vehicules et ajouter chaque vehicule avec le methode addItem
             Ex : addItem("YZ-DER-DR");
             Faire ça juste en dessous de ce commentaire
             --> Definir le texte par defaut sur le premier vehicule
             */
            try {
                Connection connection = JDBCConnector.connect();
                String sql = "SELECT NO_IMM FROM VEHICULE";
                if (connection != null) {
                    PreparedStatement statement = connection.prepareStatement(sql);
                    ResultSet resultSet = statement.executeQuery();
                    while (resultSet.next()) {
                        addItem(resultSet.getString("NO_IMM"));
                    }
                }
            } catch (JDBCException | SQLException e1) {
                e1.printStackTrace();
            }
        }};

        //Dates and Calendars
        Date date1 = new Date(1443657600000L);
        Date date2 = new Date(1446249600000L);
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
                String immatriculation = (String) comboBox1.getSelectedItem();
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                Date date1 = calendar1.getDate();
                Date date2 = calendar2.getDate();
                if (date1.before(date2) || date1.equals(date2)) {
                    String dateDebut = format.format(date1);
                    String dateFin = format.format(date2);
                    if (immatriculation != null) {
                        /*
                        TODO NOE
                         Faire ta requete pour modifier
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
                        label6.setText("Calendrier de [" + immatriculation + "] modifié");
                    } else
                        JOptionPane.showMessageDialog(null, "Immatriculation Incorrecte", "Error", JOptionPane.ERROR_MESSAGE);
                } else
                    JOptionPane.showMessageDialog(null, "La date de retour doit être après la date de départ", "Error", JOptionPane.ERROR_MESSAGE);
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
    public static synchronized ModifyResPanel getInstance() {
        if (instance == null) {
            instance = new ModifyResPanel();
        }
        return instance;
    }
}

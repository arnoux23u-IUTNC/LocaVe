package gui.panels;

import com.toedter.calendar.JCalendar;
import connection.*;
import gui.*;

import java.awt.*;
import java.sql.*;
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
        JLabel label7 = new JLabel("Disponibilité") {{
            setHorizontalAlignment(CENTER);
        }};

        //Panels
        JPanel panel1 = new JPanel() {{
            setPreferredSize(new Dimension(300, 225));
            setLayout(new GridBagLayout());
            ((GridBagLayout) getLayout()).rowHeights = new int[]{0, 0, 0, 0, 0, 0};
            ((GridBagLayout) getLayout()).rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0E-4};
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
        JPanel panel5 = new JPanel() {{
            setPreferredSize(new Dimension(280, 280));
            setLayout(new BorderLayout());
        }};
        JPanel panel6 = new JPanel() {{
            setPreferredSize(new Dimension(280, 280));
            setLayout(new GridLayout(0, 2));
        }};

        //ComboBoxes
        JComboBox<String> comboBox1 = new JComboBox<String>() {{
            try {
                //On se connecte a la BDD
                Connection connection = JDBCConnector.connect();
                if (connection != null) {
                    //On recupere la liste des immatriculations
                    PreparedStatement statement = connection.prepareStatement("SELECT NO_IMM FROM VEHICULE");
                    ResultSet resultSet = statement.executeQuery();
                    while (resultSet.next()) {
                        //On ajoute les immatriculations dans la comboBox
                        addItem(resultSet.getString("NO_IMM"));
                    }
                }
            } catch (JDBCException | SQLException e1) {
                e1.printStackTrace();
            }
        }};

        /*
        Boutons Radio
        On peut choisir si on veut rendre le vehicule disponible ou indisponible
         */
        ButtonGroup group = new ButtonGroup();
        JRadioButton radio1 = new JRadioButton("Reservé") {{
            setSelected(true);
        }};
        JRadioButton radio2 = new JRadioButton("Disponible");
        group.add(radio1);
        group.add(radio2);

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
                //On recupere le vehicule choisi
                String immatriculation = (String) comboBox1.getSelectedItem();
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                Date date1 = calendar1.getDate();
                Date date2 = calendar2.getDate();
                if (date1.before(date2) || date1.equals(date2)) {
                    //On recupere le choix concernant la disponibilite du vehicule
                    String dateDebut = format.format(date1);
                    String dateFin = format.format(date2);
                    boolean disponible = !radio1.isSelected();
                    if (immatriculation != null) {
                        try {
                            //On se connecte a la BDD
                            Connection connection = JDBCConnector.connect();
                            if (connection != null) {
                                //On mets a jour le calendrier entre les dates selectionnes (incluses)
                                PreparedStatement statement = connection.prepareStatement("UPDATE CALENDRIER SET PASLIBRE = ? WHERE NO_IMM LIKE ? AND DATEJOUR BETWEEN TO_DATE(?, 'YYYY-MM-DD') AND TO_DATE(?, 'YYYY-MM-DD')");
                                statement.setString(1, (disponible ? null : "x"));
                                statement.setString(2, immatriculation);
                                statement.setString(3, dateDebut);
                                statement.setString(4, dateFin);
                                if (statement.executeUpdate() > 0)
                                    label6.setText("Calendrier de [" + immatriculation + "] modifié");
                                else
                                    label6.setText("[" + immatriculation + "] n'est pas dans le calendrier");
                            }
                        } catch (JDBCException | SQLException e1) {
                            e1.printStackTrace();
                        }
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
        panel5.add(label7, BorderLayout.NORTH);
        panel6.add(radio1);
        panel6.add(radio2);
        panel5.add(panel6, BorderLayout.CENTER);
        panel1.add(panel5, MenuGUI.createConstraint(0, 3, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, 0, 0, 0, 0, 0, 0));
        panel1.add(button3, MenuGUI.createConstraint(0, 4, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, 20, 0, 0, 0, 0, 0));
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

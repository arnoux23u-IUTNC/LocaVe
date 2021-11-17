package gui.panels;

import com.toedter.calendar.JCalendar;
import connection.*;
import gui.*;

import java.awt.*;
import java.sql.*;
import java.util.Date;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

/**
 * Panel permettant de calculer le cout d'une reservation
 * Utilisation du patron singleton
 *
 * @author arnoux23u
 * @author steiner58u
 */
public class CalculateCostPanel extends JPanel {

    /**
     * Instance singleton
     */
    private static CalculateCostPanel instance;

    /**
     * Constructeur prive
     */
    private CalculateCostPanel() {
        setPreferredSize(new Dimension(889, 730));
        setLayout(new BorderLayout());

        //Labels
        JLabel label1 = new JLabel("Calcul co\u00fbt r\u00e9servation") {{
            setHorizontalAlignment(SwingConstants.CENTER);
            setFont(getFont().deriveFont(getFont().getStyle() & ~Font.BOLD, getFont().getSize() + 10f));

        }};
        JLabel label3 = new JLabel("Mod\u00e8le");
        JLabel label4 = new JLabel("Date d\u00e9part");
        JLabel label5 = new JLabel("Date retour");
        JLabel label6 = new JLabel("Co\u00fbt de la location : -,-- €") {{
            setFont(new Font("Tahoma", Font.BOLD, 15));
            setHorizontalAlignment(CENTER);
        }};
        JLabel label11 = new JLabel("") {{
            setFont(new Font("Tahoma", Font.BOLD, 18));
            setHorizontalAlignment(CENTER);
            setBorder(new EmptyBorder(0, 0, 30, 0));
        }};
        JLabel label7 = new JLabel("Kilomètres envisagés");
        JLabel label8 = new JLabel("Tarif Weekend") {{
            setHorizontalAlignment(SwingConstants.CENTER);
        }};
        JLabel label9 = new JLabel("Assurance") {{
            setHorizontalAlignment(SwingConstants.CENTER);
        }};
        JLabel label10 = new JLabel(" kms");

        //Panels
        JPanel panel1 = new JPanel() {{
            setPreferredSize(new Dimension(300, 225));
            setLayout(new GridBagLayout());
            ((GridBagLayout) getLayout()).rowHeights = new int[]{0, 0, 0, 0, 0, 0};
            ((GridBagLayout) getLayout()).rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 1.0E-4};
        }};
        JPanel panel2 = new JPanel() {{
            setPreferredSize(new Dimension(300, 60));
            setLayout(new GridBagLayout());
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
            setLayout(new BorderLayout());
            setBorder(new EmptyBorder(10, 0, 0, 0));
        }};
        JPanel panel6 = new JPanel() {{
            setPreferredSize(new Dimension(280, 280));
            setLayout(new GridLayout(2, 1));
        }};
        JPanel panel7 = new JPanel() {{
            setLayout(new BorderLayout());
            setBorder(new EmptyBorder(15, 0, 0, 0));
        }};
        JPanel panel8 = new JPanel() {{
            setPreferredSize(new Dimension(280, 280));
            setLayout(new GridBagLayout());
        }};
        JPanel panel9 = new JPanel() {{
            setPreferredSize(new Dimension(280, 280));
            setLayout(new GridBagLayout());
        }};
        JPanel panel10 = new JPanel() {{
            setBorder(new EmptyBorder(0, 20, 0, 0));
            setLayout(new GridBagLayout());
        }};

        //ComboBoxes
        JComboBox<String> comboBox1 = new JComboBox<>() {{
            try {
                Connection connection = JDBCConnector.connect();
                if (connection != null) {
                    PreparedStatement statement = connection.prepareStatement("SELECT MODELE FROM VEHICULE");
                    ResultSet resultSet = statement.executeQuery();
                    while (resultSet.next())
                        addItem(resultSet.getString("MODELE"));
                }
            } catch (JDBCException | SQLException e) {
                e.printStackTrace();
            }
        }};

        //JTextFields
        JTextField jTextField = new JTextField() {{
            setPreferredSize(new Dimension(60, 30));
            setMaximumSize(new Dimension(60, 30));
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
        ButtonGroup weekend = new ButtonGroup();
        JRadioButton radioButton1 = new JRadioButton("Non") {{
            setSelected(true);
        }};
        JRadioButton radioButton2 = new JRadioButton("500KM") {{
            setEnabled(false);
        }};
        JRadioButton radioButton3 = new JRadioButton("800KM") {{
            setEnabled(false);
        }};
        weekend.add(radioButton1);
        weekend.add(radioButton2);
        weekend.add(radioButton3);

        //CheckBoxes
        JCheckBox checkBox1 = new JCheckBox();

        //Buttons
        JButton button3 = new StyledButton("Calculer") {
            {
                addActionListener(e -> {
                    String modele = (String) comboBox1.getSelectedItem();
                    Date d1 = calendar1.getDate();
                    Date d2 = calendar2.getDate();
                    if (d1.before(d2) || d1.equals(d2)) {
                        int nbJours = (int) ((d2.getTime() - d1.getTime()) / (1000 * 60 * 60 * 24)) + 1;
                        int nbSemaines = nbJours / 7;
                        int nbJoursRestants = nbJours % 7;
                        if (modele != null) {
                            int kms = 0;
                            if (!jTextField.getText().isEmpty() && !jTextField.getText().replaceAll(" ", "").equals("")) {
                                try {
                                    kms = Integer.parseInt(jTextField.getText());
                                } catch (NumberFormatException exception) {
                                    JOptionPane.showMessageDialog(null, "Veuillez entrer un nombre entier", "Erreur", JOptionPane.ERROR_MESSAGE);
                                }
                            }
                            try {
                                Connection connection = JDBCConnector.connect();
                                if (connection != null) {
                                    PreparedStatement statement = connection.prepareStatement("SELECT TARIF_JOUR * ? AS \"JOUR\", TARIF_HEBDO * ? AS \"SEMAINE\", TARIF_KIL * ? AS \"KIL\", TARIF_ASUR * ? AS \"ASSUR\" FROM CATEGORIE C INNER JOIN VEHICULE V ON C.CODE_CATEG = V.CODE_CATEG INNER JOIN TARIF T ON C.CODE_TARIF = T.CODE_TARIF WHERE MODELE LIKE ?");
                                    statement.setInt(1, nbJoursRestants);
                                    statement.setInt(2, nbSemaines);
                                    statement.setInt(3, Math.max(kms, 0));
                                    statement.setInt(4, checkBox1.isSelected() ? nbJours : 0);
                                    statement.setString(5, modele);
                                    ResultSet resultSet = statement.executeQuery();
                                    resultSet.next();
                                    double tarifUnitaire = resultSet.getDouble("JOUR") + resultSet.getDouble("SEMAINE");
                                    double tarifKil = resultSet.getDouble("KIL");
                                    double tarifAssur = resultSet.getDouble("ASSUR");
                                    double total = tarifAssur + tarifUnitaire + tarifKil;
                                    StringBuilder sb = new StringBuilder("<html>Nombre de jours loués : ");
                                    sb.append(nbJours);
                                    sb.append(" [ ");
                                    sb.append(nbSemaines);
                                    sb.append(" semaine(s) et ");
                                    sb.append(nbJoursRestants);
                                    sb.append(" jour(s) ]<br/><br/>");
                                    sb.append("Tarif unitaire : ");
                                    sb.append(tarifUnitaire);
                                    sb.append(" €<br/><br/>");
                                    if (kms > 0) {
                                        sb.append("Tarif kilométrique : ");
                                        sb.append(tarifKil);
                                        sb.append(" €<br/><br/>");
                                    }
                                    if (checkBox1.isSelected()) {
                                        sb.append("Tarif assurance : ");
                                        sb.append(tarifAssur);
                                        sb.append(" €<br/><br/>");
                                    }
                                    sb.append("</html>");
                                    String sb2 = "<html>Total : " + total + " €</html>";
                                    label6.setText(sb.toString());
                                    label11.setText(sb2);
                                }
                            } catch (JDBCException | SQLException ex) {
                                ex.printStackTrace();
                            }
                        } else
                            JOptionPane.showMessageDialog(null, "Le modèle ne doit pas être null", "Erreur", JOptionPane.ERROR_MESSAGE);
                    } else
                        JOptionPane.showMessageDialog(null, "La date de départ doit être inférieure à la date de retour", "Erreur", JOptionPane.ERROR_MESSAGE);
                });
            }
        };

        add(label1, BorderLayout.PAGE_START);
        panel2.add(label3, MenuGUI.createConstraint(0, 0, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, 0, 0, 0, 20, 0, 0));
        panel2.add(comboBox1, MenuGUI.createConstraint(1, 0, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, 0, 0, 0, 0, 0, 0));
        panel1.add(panel2, MenuGUI.createConstraint(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, 0, 15, 0, 30, 0, 0));
        panel3.add(label4, MenuGUI.createConstraint(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, 0, 0, 0, 20, 0, 0));
        panel3.add(calendar1, MenuGUI.createConstraint(1, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, 0, 0, 0, 0, 0, 0));
        panel1.add(panel3, MenuGUI.createConstraint(1, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, 0, 10, 0, 30, 0, 0));
        panel4.add(label5, MenuGUI.createConstraint(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, 0, 0, 0, 20, 0, 0));
        panel4.add(calendar2, MenuGUI.createConstraint(1, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, 0, 0, 0, 0, 0, 0));
        panel1.add(panel4, MenuGUI.createConstraint(2, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, 0, 10, 0, 30, 0, 0));
        panel7.add(jTextField, BorderLayout.CENTER);
        panel7.add(label10, BorderLayout.EAST);
        panel6.add(label7);
        panel6.add(panel7);
        panel1.add(button3, MenuGUI.createConstraint(4, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, 80, 20, 80, 10, 0, 0));
        panel5.add(panel1, BorderLayout.NORTH);
        panel8.add(label8, MenuGUI.createConstraint(1, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, 0, 0, 0, 20, 0, 0));
        panel8.add(radioButton1, MenuGUI.createConstraint(0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, 15, 0, 0, 20, 0, 0));
        panel8.add(radioButton2, MenuGUI.createConstraint(1, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, 15, 0, 0, 20, 0, 0));
        panel8.add(radioButton3, MenuGUI.createConstraint(2, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, 15, 0, 0, 20, 0, 0));
        panel9.add(label9, MenuGUI.createConstraint(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, 0, 0, 0, 20, 0, 0));
        panel9.add(checkBox1, MenuGUI.createConstraint(1, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, 0, 0, 0, 0, 0, 0));
        panel10.add(panel6, MenuGUI.createConstraint(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, 0, 0, 0, 20, 0, 0));
        panel10.add(panel8, MenuGUI.createConstraint(0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, 50, 0, 0, 20, 0, 0));
        panel10.add(panel9, MenuGUI.createConstraint(0, 2, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, 50, 0, 0, 20, 0, 0));
        panel5.add(panel10, BorderLayout.WEST);
        panel5.add(label6, BorderLayout.CENTER);
        panel5.add(label11, BorderLayout.SOUTH);
        add(panel5);
    }

    /**
     * Getter instance panel
     *
     * @return panel
     */
    public static synchronized CalculateCostPanel getInstance() {
        if (instance == null) {
            instance = new CalculateCostPanel();
        }
        return instance;
    }
}

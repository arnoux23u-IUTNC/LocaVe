package gui.panels;

import com.toedter.calendar.JCalendar;
import gui.*;

import java.awt.*;
import java.math.*;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.swing.*;

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
        JLabel label6 = new JLabel("<html>Nombre de jours lou\u00e9s : 0<br/><br/>Co\u00fbt de la location : -,-- €</html>") {{
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
        }};

        //ComboBoxes
        JComboBox<String> comboBox1 = new JComboBox<String>() {{
            /*
            TODO NOE
             Recuprer liste des modeles de vehicule et ajouter chaque modele avec le methode addItem
             Ex : addItem("Dacia");
             Faire ça juste en dessous de ce commentaire
             --> Definir le texte par defaut sur le premier modèle
             */
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
        JButton button3 = new StyledButton("Soumettre") {
            {
                addActionListener(e -> {
                    String modele = (String) comboBox1.getSelectedItem();
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                    Date d1 = calendar1.getDate();
                    Date d2 = calendar2.getDate();
                    if (d1.before(d2) || d1.equals(d2)) {
                        String dateDebut = format.format(d1);
                        String dateFin = format.format(d2);
                        int nbJours = (int) ((d2.getTime() - d1.getTime()) / (1000 * 60 * 60 * 24)) + 1;
                        if (modele != null) {
                            /*
                            TODO NOE
                             Recuprer le prix de la location dans la variable prixLoc
                             */
                            float prixLoc = 0;
                            label6.setText("<html>Nombre de jours loués : " + nbJours + "<br/><br/>Coût de la location : " + new BigDecimal(prixLoc).setScale(2, RoundingMode.HALF_UP) + " €</html>");
                        } else
                            JOptionPane.showMessageDialog(null, "Le modèle ne doit pas être nul", "Erreur", JOptionPane.ERROR_MESSAGE);
                    } else
                        JOptionPane.showMessageDialog(null, "La date de départ doit être inférieure à la date de retour", "Erreur", JOptionPane.ERROR_MESSAGE);
                });
            }
        };

        add(label1, BorderLayout.PAGE_START);
        panel2.add(label3, MenuGUI.createConstraint(0, 0, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, 0, 0, 0, 20, 0, 0));
        panel2.add(comboBox1, MenuGUI.createConstraint(1, 0, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, 0, 0, 0, 0, 0, 0));
        panel1.add(panel2, MenuGUI.createConstraint(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, 0, 30, 0, 30, 0, 0));
        panel3.add(label4, MenuGUI.createConstraint(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, 0, 0, 0, 20, 0, 0));
        panel3.add(calendar1, MenuGUI.createConstraint(1, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, 0, 0, 0, 0, 0, 0));
        panel1.add(panel3, MenuGUI.createConstraint(1, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, 0, 30, 0, 30, 0, 0));
        panel4.add(label5, MenuGUI.createConstraint(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, 0, 0, 0, 20, 0, 0));
        panel4.add(calendar2, MenuGUI.createConstraint(1, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, 0, 0, 0, 0, 0, 0));
        panel1.add(panel4, MenuGUI.createConstraint(2, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, 0, 30, 0, 30, 0, 0));
        panel1.add(button3, MenuGUI.createConstraint(3, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, 80, 0, 80, 0, 0, 0));
        panel5.add(panel1, BorderLayout.NORTH);
        panel5.add(label6, BorderLayout.CENTER);

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

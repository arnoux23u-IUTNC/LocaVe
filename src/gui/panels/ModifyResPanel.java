package gui.panels;

import com.toedter.calendar.JCalendar;
import gui.*;

import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.*;

/**
 * Panel permettant de modifier le calendrier des reservations
 * Utilisation du patron singleton
 *
 * @author arnoux23u
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
                String immatriculation = (String) comboBox1.getSelectedItem();
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                String dateDebut = format.format(calendar1.getDate());
                String dateFin = format.format(calendar2.getDate());
                /*
                TODO NOE
                 Faire ta requete pour modifier
                 */
                label6.setText("Calendrier de [" + immatriculation + "] modifié");
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

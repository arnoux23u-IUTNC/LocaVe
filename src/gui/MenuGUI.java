package gui;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

/**
 * Classe permettant de modeliser le menu de l'interface graphique
 *
 * @author arnoux23u
 */
public class MenuGUI extends JPanel {

    /**
     * Objet representant l'ancien JPanel affiche
     */
    private JPanel oldPane = null;

    /**
     * Constructeur public par defaut
     */
    public MenuGUI() {
        setLayout(new BorderLayout());

        //Panels
        JPanel panel1 = new JPanel() {{
            setLayout(new BorderLayout());
            setBorder(new LineBorder(Color.black, 3));
        }};
        JPanel panel2 = new JPanel() {{
            setLayout(new BorderLayout());
        }};
        JPanel panel3 = new JPanel() {{
            setLayout(new BorderLayout());
            setPreferredSize(new Dimension(350, 210));
            setBorder(new EmptyBorder(50, 5, 10, 5));
        }};
        JPanel panel4 = new JPanel() {{
            setLayout(new GridBagLayout());
            setBorder(new EmptyBorder(30, 20, 5, 20));
            ((GridBagLayout) getLayout()).columnWidths = new int[]{0, 0, 0};
            ((GridBagLayout) getLayout()).rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
            ((GridBagLayout) getLayout()).columnWeights = new double[]{1.0, 1.0, 1.0E-4};
            ((GridBagLayout) getLayout()).rowWeights = new double[]{1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 0.0, 1.0E-4};
        }};

        //Labels
        JLabel label1 = new JLabel() {{
            setIcon(new ImageIcon("assets/locave.png"));
            setHorizontalAlignment(SwingConstants.CENTER);
        }};
        JLabel label2 = new JLabel() {{
            setIcon(new ImageIcon("assets/calendar.png"));
        }};
        JLabel label3 = new JLabel() {{
            setIcon(new ImageIcon("assets/cash.png"));
        }};
        JLabel label4 = new JLabel() {{
            setIcon(new ImageIcon("assets/car.png"));
        }};
        JLabel label5 = new JLabel() {{
            setIcon(new ImageIcon("assets/client.png"));
        }};
        JLabel label6 = new JLabel() {{
            setIcon(new ImageIcon("assets/agence.png"));
        }};
        JLabel label7 = new JLabel() {{
            setIcon(new ImageIcon("assets/check.png"));
        }};
        JLabel label8 = new JLabel() {{
            setIcon(new ImageIcon("assets/list.png"));
        }};

        //Boutons
        JButton button1 = new StyledButton("V\u00e9hicules disponibles");
        JButton button2 = new StyledButton("Modifier dispo. v\u00e9hicule");
        JButton button3 = new StyledButton("Calcul montant r\u00e9serv.");
        JButton button4 = new StyledButton("Cat\u00e9gories v\u00e9hic. agence");
        JButton button5 = new StyledButton("Clients fid\u00e8les");
        JButton button6 = new StyledButton("Clore r\u00e9servation");
        JButton button7 = new StyledButton("Affichage r\u00e9servations");

        //TODO LISTENERS PANELS
        /*
        button1.addActionListener(e -> changer(panel2, p1));
        button2.addActionListener(e -> changer(panel2, p2));
        button3.addActionListener(e -> changer(panel2, p3));
        button4.addActionListener(e -> changer(panel2, p4));
        button5.addActionListener(e -> changer(panel2, p5));
        button6.addActionListener(e -> changer(panel2, p6));
        button7.addActionListener(e -> changer(panel2, p7));
         */

        panel3.add(label1, BorderLayout.NORTH);
        panel4.add(label2, createConstraint(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, 5, 5, 27, 5, 0, 0));
        panel4.add(button1, createConstraint(1, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, 0, 0, 22, 0, 0, 0));
        panel4.add(label4, createConstraint(0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, 5, 5, 27, 5, 0, 0));
        panel4.add(button2, createConstraint(1, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, 0, 0, 22, 0, 0, 0));
        panel4.add(label3, createConstraint(0, 2, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, 5, 5, 27, 5, 0, 0));
        panel4.add(button3, createConstraint(1, 2, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, 0, 0, 22, 0, 0, 0));
        panel4.add(label6, createConstraint(0, 3, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, 5, 5, 27, 5, 0, 0));
        panel4.add(button4, createConstraint(1, 3, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, 0, 0, 22, 0, 0, 0));
        panel4.add(label5, createConstraint(0, 4, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, 5, 5, 27, 5, 0, 0));
        panel4.add(button5, createConstraint(1, 4, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, 0, 0, 22, 0, 0, 0));
        panel4.add(label7, createConstraint(0, 5, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, 5, 5, 27, 5, 0, 8));
        panel4.add(button6, createConstraint(1, 5, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, 0, 0, 22, 0, 0, 0));
        panel4.add(label8, createConstraint(0, 6, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, 5, 5, 27, 5, 0, 0));
        panel4.add(button7, createConstraint(1, 6, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, 0, 0, 22, 0, 0, 0));
        panel3.add(panel4, BorderLayout.CENTER);
        panel1.add(panel3, BorderLayout.WEST);
        panel2.add(panel1, BorderLayout.WEST);
        panel2.add(new JPanel() {{
            add(new JLabel("COucou"));
        }}, BorderLayout.CENTER);
        add(panel2, BorderLayout.CENTER);
    }

    public GridBagConstraints createConstraint(int gridx, int gridy, int gw, int gh, double wx, double wy, int anchor, int fill, int top, int left, int bottom, int right, int ipdax, int ipday) {
        return new GridBagConstraints(gridx, gridy, gw, gh, wx, wy, anchor, fill, new Insets(top, left, bottom, right), ipdax, ipday);
    }

    public void changer(JPanel k, JPanel p) {
        if (oldPane != null) {
            k.remove(oldPane);
        }
        oldPane = p;
        k.add(p, BorderLayout.CENTER);
        k.repaint();
        k.revalidate();
    }

}

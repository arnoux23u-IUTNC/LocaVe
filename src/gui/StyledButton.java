package gui;

import javax.swing.*;

/**
 * Classe permettant l'ajout de boutons en evitant des demarches repetitives
 * Extend JButton
 *
 * @author arnou23u
 */
public class StyledButton extends JButton {
    /**
     * Constructeur public a un parametre
     *
     * @param st texte
     */
    public StyledButton(String st) {
        setText(st);
        setContentAreaFilled(false);
        setFocusPainted(false);
    }
}
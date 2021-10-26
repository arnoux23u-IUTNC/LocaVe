package mvc.model;


import mvc.Observateur;
import mvc.Sujet;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

public class Locave implements Sujet {

    private final List<Observateur> observateurs;

    public Locave() {
        observateurs = new ArrayList<>();

        /*
        TODO
        CREER VUES ET CONTROLLEURS
        AJOUTER CHAQUE VUE AU MODELE
         */

    }

    @Override
    public void enregistrerObservateur(Observateur o) {
        observateurs.add(o);
    }

    @Override
    public void supprimerObservateur(Observateur o) {
        observateurs.remove(o);
    }

    @Override
    public void notifierObservateurs() {
        for (Observateur o : observateurs)
            o.actualiser(this);
    }

    public static void main(String[] args) {
        //Construction objet
        Locave locave = new Locave();

        //Déclaration des vues
        /*VueArea va = new VueArea(modeleTexte);
        VueMin vmi = new VueMin(modeleTexte);
        VueMax vma = new VueMax(modeleTexte);
        VueCompteur vc = new VueCompteur(modeleTexte);*/

        //Ajout des observateurs
        /*modeleTexte.enregistrerObservateur(vma);
        modeleTexte.enregistrerObservateur(vmi);
        modeleTexte.enregistrerObservateur(va);
        modeleTexte.enregistrerObservateur(vc);*/

        //Construction de l'IG dans une JFrame
        JFrame frame = new JFrame();
        frame.setLayout(new BorderLayout());

        JTextField saisie = new JTextField(10);
        saisie.setPreferredSize(new Dimension(200, 30));
        saisie.addActionListener(e -> saisie.setText(""));
        JPanel panelSaisie = new JPanel();
        panelSaisie.setLayout(new GridLayout(1, 2));
        panelSaisie.add(new JLabel("Donner une chaine " + "    ", JLabel.CENTER));
        panelSaisie.add(saisie);

        JPanel labels = new JPanel();
        labels.setLayout(new GridLayout(2, 2));
        labels.add(new JLabel("Plus grand mot ", JLabel.CENTER));
        labels.add(vmi);
        labels.add(new JLabel("Plus petit mot ", JLabel.CENTER));
        labels.add(vma);

        JPanel zone = new JPanel();
        zone.add(new JScrollPane(va));
        zone.setLayout(new GridLayout(1, 1));
        zone.setPreferredSize(new Dimension(500, 500));
        JPanel compteur = new JPanel();
        JTextField text = new JTextField();
        text.setPreferredSize(new Dimension(200, 30));
        JButton boutton = new JButton("Clic");
        compteur.add(text);
        compteur.add(vc);
        compteur.add(boutton);


        new ControlleurEntree(modeleTexte, saisie);
        new ControlleurCompteur(modeleTexte, boutton, text);

        JPanel zonelabels = new JPanel();
        zonelabels.setLayout(new BorderLayout());
        zonelabels.add(zone, BorderLayout.CENTER);
        zonelabels.add(labels, BorderLayout.SOUTH);

        frame.add(panelSaisie, BorderLayout.NORTH);
        frame.add(zonelabels, BorderLayout.CENTER);
        frame.add(compteur, BorderLayout.SOUTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(new Dimension(400, 400));
        frame.setVisible(true);
    }
}

import gui.MenuGUI;

import javax.swing.*;

public class LancerLocve {

    public static void main(String[] args) {
        new JFrame("LocaVe - Bienvenue") {
            {
                setSize(1500, 800);
                setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                setIconImage(new ImageIcon("ressources/locave.png").getImage());
                setResizable(false);
                setLocationRelativeTo(null);
                setContentPane(new MenuGUI());
                setVisible(true);
                new Locave();
            }
        };
    }

}

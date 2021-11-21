import connection.*;
import gui.MenuGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Objects;

/**
 * Classe de lancement de l'application
 *
 * @author arnoux23u
 */
public class Locave extends JPanel {

    //Champ de texte pour le login
    private static final JTextField loginField = new JTextField(20);

    //Champ de texte pour le mot de passe
    private static final JPasswordField passwordField = new JPasswordField(20);

    //Etat de la connexion
    private static final JLabel state = new JLabel("", SwingConstants.CENTER) {{
        setBounds(60, 80, 180, 25);
    }};

    //Fenetre
    private static JFrame frame;

    /*
    Action executée lors du clic sur le bouton de connexion
    On tente de se connecter a la base de donnée avec les informations fournies
    Si echec, on affiche le message d'erreur correspondant
     */
    private static final ActionListener ac = e -> {
        try {
            state.setForeground(Color.blue);
            state.setText("Connexion en cours");
            JDBCConnector.setUser(loginField.getText());
            JDBCConnector.setPassword(passwordField.getPassword());
            JDBCConnector.connect();
            state.setForeground(new Color(23, 138, 16));
            state.setText("Connecté");
            new Timer(1000, c -> {
                frame.dispose();
                new JFrame("LocaVe - Bienvenue") {{
                    setSize(1500, 800);
                    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    setIconImage(new ImageIcon(Objects.requireNonNull(getClass().getResource("locave.png"))).getImage());
                    setResizable(false);
                    setLocationRelativeTo(null);
                    setContentPane(new MenuGUI());
                    setVisible(true);
                    new Locave();
                }};
            }) {{
                setRepeats(false);
            }}.start();
        } catch (JDBCException ex) {
            state.setForeground(Color.red);
            state.setText(String.valueOf(ex.getMessage()));
        }
    };

    /**
     * Methode de lancement
     *
     * @param args arguments shell
     */
    public static void main(String[] args) {
        //Création de la fenêtre
        frame = new JFrame("LocaVe - Connexion") {{
            setSize(300, 180);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setIconImage(new ImageIcon(Objects.requireNonNull(getClass().getResource("lock.png"))).getImage());
            setResizable(false);
            setLocationRelativeTo(null);
            requestFocusInWindow();
            setContentPane(new JPanel() {{
                setLayout(null);
                add(new JLabel("User") {{
                    setBounds(10, 10, 80, 25);
                }});
                loginField.setBounds(100, 10, 160, 25);
                loginField.requestFocus();
                loginField.setCaretPosition(loginField.getText().length());
                loginField.addActionListener(ac);
                add(loginField);
                add(new JLabel("Password") {{
                    setBounds(10, 40, 80, 25);
                }});
                passwordField.setBounds(100, 40, 160, 25);
                passwordField.addActionListener(ac);
                add(passwordField);
                add(new JButton("Login") {{
                        setBounds(110, 110, 80, 25);
                        addActionListener(ac);
                    }}
                );
                add(state);
            }});
            setVisible(true);
        }};
    }
}

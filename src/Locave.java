import connection.JDBCConnector;
import connection.JDBCException;
import gui.MenuGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.Arrays;

public class Locave extends JPanel {

    private static final JTextField loginField = new JTextField(20);
    private static final JPasswordField passwordField = new JPasswordField(20);
    private static final JLabel state = new JLabel("", SwingConstants.CENTER) {
        {
            setBounds(60, 80, 180, 25);
        }
    };
    private static JFrame frame;
    private static final ActionListener ac = e -> {
        try {
            state.setForeground(Color.blue);
            state.setText("Connexion en cours");
            JDBCConnector.setUser(loginField.getText());
            JDBCConnector.setPassword(passwordField.getPassword());
            Connection c = JDBCConnector.connect();
            state.setForeground(new Color(23, 138, 16));
            state.setText("Connecté");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
            frame.dispose();
            new JFrame("LocaVe - Bienvenue") {
                {
                    setSize(1500, 800);
                    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    setIconImage(new ImageIcon("assets/locave.png").getImage());
                    setResizable(false);
                    setLocationRelativeTo(null);
                    setContentPane(new MenuGUI());
                    setVisible(true);
                    new Locave();
                }
            };
        } catch (JDBCException ex) {
            state.setForeground(Color.red);
            state.setText(String.valueOf(ex.getMessage()));
        }
    };

    public static void main(String[] args) {
        frame = new JFrame("LocaVe - Connexion") {{
            setSize(300, 180);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setIconImage(new ImageIcon("assets/lock.png").getImage());
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
                add(new JButton("Login") {
                    {
                        setBounds(110, 110, 80, 25);
                        addActionListener(ac);
                    }
                });
                add(state);
            }});
            setVisible(true);
        }};
    }
}

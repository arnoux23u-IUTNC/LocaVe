import gui.MenuPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class Locave extends JPanel {

    private static final JTextField loginField = new JTextField(20) {{
        setText("admin");
    }};

    private static final JPasswordField passwordField = new JPasswordField(20) {{
        setText("admin");
    }};

    private static JFrame frame;

    private static final ActionListener ac = e -> {
        if (loginField.getText().equals("admin") && Arrays.equals(passwordField.getPassword(), new char[]{'a', 'd', 'm', 'i', 'n'})) {
            frame.dispose();
            new JFrame("LocaVe - Bienvenue") {
                {
                    setSize(1500, 800);
                    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    setIconImage(new ImageIcon("assets/locave.png").getImage());
                    setResizable(false);
                    setLocationRelativeTo(null);
                    setContentPane(new MenuPanel());
                    setVisible(true);
                    new Locave();
                }
            };
        }
    };

    public static void main(String[] args) {
        frame = new JFrame("LocaVe - Connexion") {{
            setSize(300, 150);
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
                        setBounds(110, 80, 80, 25);
                        addActionListener(ac);
                    }
                });
            }});
            setVisible(true);
        }};
    }
}

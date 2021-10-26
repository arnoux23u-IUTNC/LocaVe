import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        new JFrame("LocaVe - Connexion") {{
            setSize(300, 150);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setIconImage(new ImageIcon("assets/lock.png").getImage());
            setResizable(false);
            setLocationRelativeTo(null);
            setContentPane(new JPanel() {{
                setLayout(null);
                add(new JLabel("User") {{
                    setBounds(10, 10, 80, 25);
                }});
                JTextField loginField = new JTextField(20) {{
                    setText("admin");
                    setBounds(100, 10, 160, 25);
                }};
                add(loginField);
                add(new JLabel("Password") {{
                    setBounds(10, 40, 80, 25);
                }});
                JPasswordField passwordField = new JPasswordField(20) {{
                    setText("admin");
                    setBounds(100, 40, 160, 25);
                }};
                add(passwordField);
                add(new JButton("Login") {{
                    setBounds(110, 80, 80, 25);
                    addActionListener(e -> {
                        if (loginField.getText().equals("admin") && Arrays.equals(passwordField.getPassword(), new char[]{'a', 'd', 'm', 'i', 'n'})) {
                            dispose();
                            //TODO new Locave
                        }
                    });
                }});
            }});
            setVisible(true);
        }};
    }

}

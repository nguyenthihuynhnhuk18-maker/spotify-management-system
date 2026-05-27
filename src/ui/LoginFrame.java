package ui;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.UserDAO;
import model.User;
import util.SessionManager;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPasswordField;

public class LoginFrame extends JFrame {

    private static final long serialVersionUID = 1L;

    private JPanel contentPane;

    private JTextField txtUsername;

    private JPasswordField txtPassword;

    /**
     * Launch
     */
    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {

            public void run() {

                try {

                    LoginFrame frame =
                            new LoginFrame();

                    frame.setVisible(true);

                } catch (Exception e) {

                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create frame
     */
    public LoginFrame() {

        setTitle("Spotify Login");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setBounds(100, 100, 450, 300);

        setLocationRelativeTo(null);

        contentPane = new JPanel();

        contentPane.setBackground(
                new Color(25,20,20)
        );

        contentPane.setBorder(
                new EmptyBorder(5,5,5,5)
        );

        setContentPane(contentPane);

        contentPane.setLayout(null);

        // TITLE
        JLabel lblTitle =
                new JLabel("Spotify Login");

        lblTitle.setForeground(Color.WHITE);

        lblTitle.setBounds(170,20,200,30);

        contentPane.add(lblTitle);

        // USERNAME
        JLabel lblUser =
                new JLabel("Username");

        lblUser.setForeground(Color.WHITE);

        lblUser.setBounds(50,80,100,25);

        contentPane.add(lblUser);

        txtUsername =
                new JTextField();

        txtUsername.setBounds(150,80,200,30);

        contentPane.add(txtUsername);

        // PASSWORD
        JLabel lblPass =
                new JLabel("Password");

        lblPass.setForeground(Color.WHITE);

        lblPass.setBounds(50,130,100,25);

        contentPane.add(lblPass);

        txtPassword =
                new JPasswordField();

        txtPassword.setBounds(150,130,200,30);

        contentPane.add(txtPassword);

        // BUTTON LOGIN
        JButton btnLogin =
                new JButton("Login");

        btnLogin.setBackground(
                new Color(30,215,96)
        );

        btnLogin.setBounds(150,190,120,35);

        btnLogin.addActionListener(e -> {

            login();
        });

        contentPane.add(btnLogin);
    }

    private void login() {

        String username =
                txtUsername.getText();

        String password =
                new String(
                        txtPassword.getPassword()
                );

        UserDAO dao =
                new UserDAO();

        User user =
                dao.login(
                        username,
                        password
                );

        if(user != null) {

            String token =
                    SessionManager
                            .createSession(user);

            JOptionPane.showMessageDialog(
                    null,
                    "Login Success!\n"
                    + "Role: "
                    + user.getRole()
            );

            MainFrame main =
                    new MainFrame(
                            user,
                            token
                    );

            main.setVisible(true);

            dispose();

        } else {

            JOptionPane.showMessageDialog(
                    null,
                    "Wrong username or password!"
            );
        }
    }
}
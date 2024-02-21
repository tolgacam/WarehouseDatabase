package ca.ubc.cs304.UI;

import ca.ubc.cs304.database.DatabaseService;
import ca.ubc.cs304.delegates.Login;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WelcomeUI extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;

    public WelcomeUI(Login controller, String usernamet, String passwordt) {
        setTitle("Welcome");

        setSize(340, 200);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(mainPanel);

        // Create the username label and text field
        JLabel lblUsername = new JLabel("Username:");
        JTextField txtUsername = new JTextField(20);

        // Add username components to a sub-panel with FlowLayout (center)
        JPanel usernamePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        usernamePanel.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0)); // Add empty border insets
        usernamePanel.add(lblUsername);
        usernamePanel.add(txtUsername);

        // Add the username sub-panel to the main panel
        mainPanel.add(usernamePanel);

        // Create the password label and text field
        JLabel lblPassword = new JLabel("Password:");
        JPasswordField txtPassword = new JPasswordField(20);

        // Add password components to a sub-panel with FlowLayout (center)
        JPanel passwordPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        passwordPanel.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0)); // Add empty border insets
        passwordPanel.add(lblPassword);
        passwordPanel.add(txtPassword);

        // Add the password sub-panel to the main panel
        mainPanel.add(passwordPanel);

        // Create the login button
        JButton btnLogin = new JButton("Login");

        // Add an ActionListener to the login button
        btnLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Get the entered username and password
                String username = txtUsername.getText();
                char[] passwordChars = txtPassword.getPassword();
                //String username = "ora_tolgacam";

                //String password = "a30999213";

                String password = new String(passwordChars);
                // Perform login validation here (e.g., check against a database)
                // For this example, we'll just show a message dialog
                if (usernamet.equals("admin") && passwordt.equals("password")) {
                    JOptionPane.showMessageDialog(mainPanel,"Login successful!");
                } else {
                    JOptionPane.showMessageDialog(mainPanel, "Invalid username or password.");
                }
            }
        });

        // Add the login button to the main panel
        mainPanel.add(btnLogin);

        // Center the JFrame on the screen
        setLocationRelativeTo(null);

        // Show the JFrame
        setVisible(true);
    }
}

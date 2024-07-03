package task3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ATM_Interface {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(Window::new);
    }
}

class Window implements ActionListener {

    private JPanel loginPanel, dashboardPanel; // Separate panels for login and dashboard
    private JButton login;
    private CardLayout cardLayout;
    private JPanel contentPane;
    private JButton withdraw, deposit, checkBalance, exit; // Dashboard buttons
    private JTextField userNameTextField;
    private JPasswordField passwordField;
    private double accountBalance = 10000.0; // Example initial balance

    public Window() {
        JFrame frame = new JFrame("ATM Interface");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setLocationRelativeTo(null); // Center the frame on the screen

        // Using CardLayout for switching between panels
        cardLayout = new CardLayout();
        contentPane = new JPanel(cardLayout);

        // Initialize and configure login panel
        loginPanel = createLoginPanel();

        // Initialize and configure dashboard panel
        dashboardPanel = createDashboardPanel();

        // Add panels to the content pane
        contentPane.add(loginPanel, "Login");
        contentPane.add(dashboardPanel, "Dashboard");

        // Set the content pane of the frame
        frame.setContentPane(contentPane);
        frame.setVisible(true);
    }

    private JPanel createLoginPanel() {
        JPanel panel = new JPanel(null);

        JLabel heading = new JLabel("<html><u><b>Welcome to the Bank Management System</b></u></html>", SwingConstants.CENTER);
        heading.setBounds(20, 20, 360, 30);
        panel.add(heading);

        JLabel userNameLabel = new JLabel("User Name:");
        userNameLabel.setBounds(35, 100, 100, 30);
        panel.add(userNameLabel);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(35, 150, 100, 30);
        panel.add(passwordLabel);

        userNameTextField = new JTextField();
        userNameTextField.setBounds(130, 100, 200, 30);
        panel.add(userNameTextField);

        passwordField = new JPasswordField();
        passwordField.setBounds(130, 150, 200, 30);
        panel.add(passwordField);

        login = new JButton("LOGIN");
        login.setBounds(150, 200, 100, 30);
        panel.add(login);
        login.addActionListener(this); // Register the action listener

        return panel;
    }

    private JPanel createDashboardPanel() {
        JPanel panel = new JPanel(null);

        JLabel dashboardWelcome = new JLabel("Please select your transaction !");
        dashboardWelcome.setBounds(110, 30, 200, 30);
        panel.add(dashboardWelcome);

        // Initialize dashboard buttons and set their bounds
        withdraw = new JButton("Withdraw");
        withdraw.setBounds(120, 100, 150, 30);
        panel.add(withdraw);
        withdraw.addActionListener(this); // Register the action listener

        deposit = new JButton("Deposit");
        deposit.setBounds(120, 150, 150, 30);
        panel.add(deposit);
        deposit.addActionListener(this); // Register the action listener

        checkBalance = new JButton("Check Balance");
        checkBalance.setBounds(120, 200, 150, 30);
        panel.add(checkBalance);
        checkBalance.addActionListener(this); // Register the action listener

        exit = new JButton("Exit");
        exit.setBounds(120, 250, 150, 30);
        panel.add(exit);
        exit.addActionListener(this); // Register the action listener

        return panel;
    }


    private void handleWithdraw() {
        String input = JOptionPane.showInputDialog(null, "Enter withdrawal amount:");
        try {
            double withdrawalAmount = Double.parseDouble(input);
            if (withdrawalAmount > 0 && withdrawalAmount <= accountBalance) {
                accountBalance -= withdrawalAmount;
                JOptionPane.showMessageDialog(null, "Withdrawal successful. New balance: $" + accountBalance);
            } else {
                JOptionPane.showMessageDialog(null, "Invalid withdrawal amount.");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid amount.");
        }
    }

    private void handleDeposit() {
        String input = JOptionPane.showInputDialog(null, "Enter deposit amount:");
        try {
            double depositAmount = Double.parseDouble(input);
            if (depositAmount > 0) {
                accountBalance += depositAmount;
                JOptionPane.showMessageDialog(null, "Deposit successful. New balance: $" + accountBalance);
            } else {
                JOptionPane.showMessageDialog(null, "Invalid deposit amount.");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid amount.");
        }
    }

    private final String username = "username123";
    private final String password = "password123";

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == login) {
            // Authenticate the user credentials
            String enteredUsername = userNameTextField.getText();
            String enteredPassword = new String(passwordField.getPassword());

            if (enteredUsername.equals(username) && enteredPassword.equals(password)) {
                // Credentials are correct, switch to the dashboard panel
                cardLayout.show(contentPane, "Dashboard");
            } else {
                // Credentials are incorrect, show error message
                JOptionPane.showMessageDialog(null, "Invalid username or password. Please try again.", "Authentication Failed", JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource() == withdraw) {
            // Handle Withdraw button action
            handleWithdraw();
        } else if (e.getSource() == deposit) {
            // Handle Deposit button action
            handleDeposit();
        } else if (e.getSource() == checkBalance) {
            // Handle Check Balance button action
            JOptionPane.showMessageDialog(null, "Current balance: $" + accountBalance);
        } else if (e.getSource() == exit) {
            // Handle Exit button action
            int confirm = JOptionPane.showConfirmDialog(dashboardPanel, "Are you sure you want to exit?", "Confirm Exit", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                System.exit(0); // Exit the application
            }
        }
    }
}

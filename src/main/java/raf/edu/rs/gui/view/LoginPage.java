package raf.edu.rs.gui.view;

import lombok.Getter;
import raf.edu.rs.gui.MainFrame;
import raf.edu.rs.gui.font.MyFont;
import raf.edu.rs.gui.restReservation.TokenDecoder;

import javax.swing.*;
import java.awt.*;

@Getter
public class LoginPage extends JPanel{
    private JTextField emailField;
    private JPasswordField passwordField;
    private JPanel panel;
    private JButton loginButton;
    private JOptionPane  optionPane;
    private JButton backBtn;
    private JButton changePasswordBtn;

    public LoginPage () {
        optionPane = new JOptionPane();
        emailField = new JTextField("email", 14);
        emailField.setFont(MyFont.getMediumFont());
        passwordField = new JPasswordField("password", 14);
        passwordField.setFont(MyFont.getMediumFont());

        loginButton = new JButton("Login");
        backBtn = new JButton("Back");
        changePasswordBtn = new JButton("Change password");

        loginButton.addActionListener((event) -> {

            try {
                String token = MainFrame.getInstance().getUserServiceRestClient()
                        .login(emailField.getText().toString(), String.valueOf(passwordField.getPassword()));
                //this.setVisible(false);
                MainFrame.getInstance().setToken(token);
                System.out.println(token);

               MainFrame.getInstance().setCurrentUser(TokenDecoder.decodeToken(token));
               String role = MainFrame.getInstance().getCurrentUser().getRole();
               if (role.equals("ROLE_MANAGER"))
                   MainFrame.getInstance().showManagerView();
               else if (role.equals("ROLE_CLIENT"))
                   MainFrame.getInstance().showClientView();
               else
                   MainFrame.getInstance().showAdminView();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Incorrect email or password", "Error", JOptionPane.ERROR_MESSAGE);
                //e.printStackTrace();
            }
        });

        backBtn.addActionListener((event)->{
            try{
                MainFrame.getInstance().showMainView();
            }catch (Exception e){
                e.printStackTrace();
            }
        });

        changePasswordBtn.addActionListener((event)->{
            try{
                MainFrame.getInstance().showForgotPasswordView();
            }catch (Exception e){
                e.printStackTrace();
            }
        });



        panel = new JPanel();
        panel.add(emailField);
        panel.add(passwordField);
        this.add(panel, BorderLayout.CENTER);

        JPanel optionsPanel = new JPanel(new FlowLayout());
        optionsPanel.add(backBtn);
        optionsPanel.add(loginButton);
        optionsPanel.add(changePasswordBtn);
        this.add(optionsPanel, BorderLayout.SOUTH);
    }

}
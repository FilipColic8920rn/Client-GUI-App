package raf.edu.rs.gui.view;

import raf.edu.rs.gui.MainFrame;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class ForgotPasswordView extends JPanel {

    private JTextField emailField;
    private JTextField codeTextField;
    private JTextField newPasswordField;
    private JButton sendRequestBtn;
    private JButton changePasswordBtn;
    private JButton backBtn;
    private JPanel requestPanel;
    private JPanel newPasswordPanel;
    private String email;

    public ForgotPasswordView(){
        init();
    }
    private void init(){

        this.setLayout(new BorderLayout());

        emailField = new JTextField();
        codeTextField = new JTextField();
        newPasswordField = new JTextField();
        sendRequestBtn = new JButton("Confirm");
        backBtn = new JButton("Back");
        changePasswordBtn = new JButton("Change password");
        requestPanel = new JPanel(null);
        newPasswordPanel = new JPanel(null);

        requestPanel.setBorder(new EmptyBorder(5,5,5,5));
        newPasswordPanel.setBorder(new EmptyBorder(5,5,5,5));

        JLabel lbEmail = new JLabel("Email");
        lbEmail.setBounds(350,100,40,20);

        emailField.setBounds(400, 100, 200, 20);
        requestPanel.add(emailField);
        requestPanel.add(lbEmail);
        this.add(requestPanel, BorderLayout.CENTER);

        sendRequestBtn.addActionListener((event) -> {
            try {
                //System.out.println(emailField.getText());
                MainFrame.getInstance().getUserServiceRestClient()
                        .sendChangePasswordRequest(emailField.getText());
                this.email = emailField.getText();
                newPassword();
                //this.setVisible(false);
                //System.out.println(userDto.toString());

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "User with specified email not found!", "Error", JOptionPane.ERROR_MESSAGE);
                //e.printStackTrace();
            }
        });
        changePasswordBtn.addActionListener((event) -> {
            try {
                //System.out.println(emailField.getText());
                MainFrame.getInstance().getUserServiceRestClient()
                        .changePassword(this.email, codeTextField.getText(), newPasswordField.getText());
                JOptionPane.showMessageDialog(null, "Password changed!", "Success", JOptionPane.PLAIN_MESSAGE);
                MainFrame.getInstance().showMainView();
                //this.setVisible(false);
                //System.out.println(userDto.toString());

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Invalid token", "Error", JOptionPane.ERROR_MESSAGE);
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

        JPanel optionsPanel = new JPanel(new FlowLayout());
        optionsPanel.add(sendRequestBtn);
        optionsPanel.add(backBtn);
        this.add(optionsPanel, BorderLayout.SOUTH);

    }

    private void newPassword(){
        System.out.println("here");
        JLabel lbPassword = new JLabel("New password:");
        JLabel lbToken = new JLabel("Token:");


        lbToken.setBounds(300,100,100,20);
        codeTextField.setBounds(400, 100, 200, 20);

        newPasswordPanel.add(lbToken);
        newPasswordPanel.add(codeTextField);

        lbPassword.setBounds(300,150,100,20);
        newPasswordField.setBounds(400, 150, 200, 20);

        newPasswordPanel.add(newPasswordField);
        newPasswordPanel.add(lbPassword);
        this.removeAll();
        this.revalidate();
        this.repaint();
        this.add(newPasswordPanel, BorderLayout.CENTER);
        JPanel optionsPanel = new JPanel(new FlowLayout());
        optionsPanel.add(changePasswordBtn);
        this.add(optionsPanel, BorderLayout.SOUTH);

    }


}

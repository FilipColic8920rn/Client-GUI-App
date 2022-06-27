package raf.edu.rs.gui.view;

import raf.edu.rs.gui.MainFrame;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class RegisterManagerView extends JPanel {
    private JPanel panel;
    private JTextField nameField;
    private JTextField lastnameField;
    private JTextField usernameField;
    private JTextField passwordField;
    private JTextField emailField;
    private JTextField dateOfBirthField;
    private JTextField dateOfEmpField;
    private JTextField phoneNumberField;
    private JButton registerBtn;
    private JButton backBtn;

    public RegisterManagerView() {
        init();
    }

    private void init() {
        this.setLayout(new BorderLayout());

        nameField = new JTextField("");
        lastnameField= new JTextField();
        usernameField= new JTextField();
        passwordField= new JTextField();
        emailField= new JTextField();
        dateOfBirthField= new JTextField();
        dateOfEmpField= new JTextField();
        phoneNumberField= new JTextField();
        registerBtn = new JButton("Register");
        backBtn = new JButton("Back");

        List<JTextField> comp = new ArrayList<>();
        comp.add(nameField);
        comp.add(lastnameField);
        comp.add(usernameField);
        comp.add(passwordField);
        comp.add(emailField);
        comp.add(dateOfBirthField);
        comp.add(phoneNumberField);
        comp.add(dateOfEmpField);


        List<JLabel> lb = new ArrayList<>();
        lb.add(new JLabel("Name:"));
        lb.add(new JLabel("Last name:"));
        lb.add(new JLabel("Username:"));
        lb.add(new JLabel("Password"));
        lb.add(new JLabel("Email:"));
        lb.add(new JLabel("Date of birth:"));
        lb.add(new JLabel("Phone number:"));
        lb.add(new JLabel("Date of employment:"));

        panel = new JPanel(null);
        panel.setBorder(new EmptyBorder(5,5,5,5));

        for (int i = 0; i < comp.size(); i++){
            lb.get(i).setBounds(50,20+i*30,200,10);
            comp.get(i).setBounds(210,17+i*30,120,20);
            panel.add(comp.get(i));
            panel.add(lb.get(i));
        }

        registerBtn.addActionListener((event) -> {
            try {
                MainFrame.getInstance().getUserServiceRestClient()
                        .registerManager(nameField.getText(), lastnameField.getText(), usernameField.getText(),
                                passwordField.getText(), emailField.getText(), dateOfBirthField.getText(),
                                phoneNumberField.getText(), dateOfEmpField.getText());
            }
            catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error while registering manager!",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        backBtn.addActionListener((event)->{
            try{
                MainFrame.getInstance().showMainView();
            }catch (Exception e){
                e.printStackTrace();
            }
        });


        //registerBtn.setBounds(100,300,100,20);
        //panel.add(registerBtn);
        this.add(panel, BorderLayout.CENTER);

        JPanel optionsPanel = new JPanel(new FlowLayout());
        optionsPanel.add(backBtn);
        optionsPanel.add(registerBtn);
        this.add(optionsPanel, BorderLayout.SOUTH);
    }


    public JPanel getPanel() {
        return panel;
    }

    public JTextField getNameField() {
        return nameField;
    }

    public JTextField getLastnameField() {
        return lastnameField;
    }

    public JTextField getUsernameField() {
        return usernameField;
    }

    public JTextField getPasswordField() {
        return passwordField;
    }

    public JTextField getEmailField() {
        return emailField;
    }

    public JTextField getDateOfBirthField() {
        return dateOfBirthField;
    }


    public JTextField getPhoneNumberField() {
        return phoneNumberField;
    }

}
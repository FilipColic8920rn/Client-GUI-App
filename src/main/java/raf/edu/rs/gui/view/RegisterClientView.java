package raf.edu.rs.gui.view;

import raf.edu.rs.gui.MainFrame;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class RegisterClientView extends JPanel {

    private JPanel panel;
    private JTextField nameField;
    private JTextField lastnameField;
    private JTextField usernameField;
    private JTextField passwordField;
    private JTextField emailField;
    private JTextField dateOfBirthField;
    private JTextField passportNumberField;
    private JTextField phoneNumberField;
    private JButton registeBtn;
    private JButton backBtn;

    public RegisterClientView() {
        init();
    }

    private void init(){
        this.setLayout(new BorderLayout());

        nameField = new JTextField("");
        //nameField.setMaximumSize(new Dimension(10,20));
        lastnameField= new JTextField();
        usernameField= new JTextField();
        passwordField= new JTextField();
        emailField= new JTextField();
        dateOfBirthField= new JTextField();
        passportNumberField= new JTextField();
        phoneNumberField= new JTextField();
        registeBtn = new JButton("Register");
        backBtn = new JButton("Back");

        List<JTextField> comp = new ArrayList<>();
        comp.add(nameField);
        comp.add(lastnameField);
        comp.add(usernameField);
        comp.add(passwordField);
        comp.add(emailField);
        comp.add(dateOfBirthField);
        comp.add(phoneNumberField);
        comp.add(passportNumberField);


        List<JLabel> lb = new ArrayList<>();
        lb.add(new JLabel("Name:"));
        lb.add(new JLabel("Last name:"));
        lb.add(new JLabel("Username:"));
        lb.add(new JLabel("Password"));
        lb.add(new JLabel("Email:"));
        lb.add(new JLabel("Date of birth:"));
        lb.add(new JLabel("Phone number:"));
        lb.add(new JLabel("Passport number:"));

        panel = new JPanel(null);
        panel.setBorder(new EmptyBorder(5,5,5,5));

        for (int i = 0; i < comp.size(); i++){
            lb.get(i).setBounds(50,20+i*30,200,10);
            comp.get(i).setBounds(210,17+i*30,120,20);
            panel.add(comp.get(i));
            panel.add(lb.get(i));
        }


        registeBtn.addActionListener((event) -> {

            try {
                MainFrame.getInstance().getUserServiceRestClient()
                        .registerClient(nameField.getText(), lastnameField.getText(), usernameField.getText(), passwordField.getText(),
                                emailField.getText(), dateOfBirthField.getText(), phoneNumberField.getText(), passportNumberField.getText());
                //this.setVisible(false);
                //System.out.println(userDto.toString());

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error while registering client!", "Error", JOptionPane.ERROR_MESSAGE);
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


        //registeBtn.setBounds(100,300,100,20);
        //panel.add(registeBtn);
        this.add(panel, BorderLayout.CENTER);

        JPanel optionsPanel = new JPanel(new FlowLayout());
        optionsPanel.add(backBtn);
        optionsPanel.add(registeBtn);
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

    public JTextField getPassportNumberField() {
        return passportNumberField;
    }

    public JTextField getPhoneNumberField() {
        return phoneNumberField;
    }
}
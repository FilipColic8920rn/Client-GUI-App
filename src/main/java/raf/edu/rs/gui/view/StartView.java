package raf.edu.rs.gui.view;

import raf.edu.rs.gui.MainFrame;

import javax.swing.*;
import java.awt.*;

public class StartView extends JPanel {

    private JPanel panel;
    private JButton loginBtn;
    private JButton regClientBtn;
    private JButton regManagerBtn;

    public StartView(){
        init();
    }

    private void init(){
        loginBtn = new JButton("Login");
        regClientBtn = new JButton("Register Client");
        regManagerBtn = new JButton("Register Manager");

        panel = new JPanel();
        loginBtn.addActionListener((e) -> MainFrame.getInstance().showLogin());
        panel.add(loginBtn, BorderLayout.CENTER);
        regClientBtn.addActionListener((e) -> MainFrame.getInstance().showRegisterClient());
        panel.add(regClientBtn, BorderLayout.CENTER);
        regManagerBtn.addActionListener((e)->MainFrame.getInstance().showRegisterManager());
        panel.add(regManagerBtn, BorderLayout.CENTER);

        this.add(panel);
    }


}

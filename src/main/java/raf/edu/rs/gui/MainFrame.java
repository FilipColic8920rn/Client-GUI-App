package raf.edu.rs.gui;

import raf.edu.rs.gui.restReservation.ReservationService;
import raf.edu.rs.gui.restclient.NotificationServiceRestClient;
import raf.edu.rs.gui.restclient.dto.UserDto;
import raf.edu.rs.gui.view.*;
import raf.edu.rs.gui.restclient.UserServiceRestClient;
import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private static MainFrame instance = null;
    private String token;
    private UserDto currentUser;
    private UserServiceRestClient userServiceRestClient;
    private NotificationServiceRestClient notificationServiceRestClient;
    private ReservationService reservationService;
    private LoginPage loginPage;
    private RegisterClientView registerClientView;
    private RegisterManagerView registerManagerView;
    private AdminView adminView;
    private ManagerView managerView;
    private ClientView clientView;
    private StartView startView;
    private ForgotPasswordView forgotPasswordView;

    private MainFrame() {}

    public static MainFrame getInstance() {
        if (instance == null) {
            instance = new MainFrame();
            instance.initializeGui();
        }

        return instance;
    }

    private void initializeGui() {

        this.setResizable(false);
        this.setPreferredSize(new Dimension(900, 700));
        this.setLocationRelativeTo(null);

        userServiceRestClient = new UserServiceRestClient();
        notificationServiceRestClient = new NotificationServiceRestClient();
        reservationService = new ReservationService();

        loginPage = new LoginPage();
        registerClientView = new RegisterClientView();
        registerManagerView = new RegisterManagerView();
        forgotPasswordView = new ForgotPasswordView();
        adminView = new AdminView();
        managerView = new ManagerView();
        clientView = new ClientView();
        startView = new StartView();

        this.setTitle("Client GUI application");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        this.setSize(toolkit.getScreenSize().width / 2, toolkit.getScreenSize().height / 2);
        this.setLocationRelativeTo(null);

        showMainView();
    }

    public void showLogin() {
        this.getContentPane().setVisible(false);
        this.getContentPane().removeAll();
        this.getContentPane().add(loginPage);
        this.getContentPane().setVisible(true);
    }

    public void showRegisterClient() {
        this.getContentPane().setVisible(false);
        this.getContentPane().removeAll();
        this.getContentPane().add(registerClientView);
        this.getContentPane().setVisible(true);
    }

    public void showRegisterManager(){
        this.getContentPane().setVisible(false);
        this.getContentPane().removeAll();
        this.getContentPane().add(registerManagerView);
        this.getContentPane().setVisible(true);
    }

    public void showAdminView() {
        this.getContentPane().setVisible(false);
        this.getContentPane().removeAll();
        this.getContentPane().add(adminView);
        this.getContentPane().setVisible(true);
    }

    public void showManagerView() {
        this.getContentPane().setVisible(false);
        this.getContentPane().removeAll();
        this.getContentPane().add(managerView);
        this.getContentPane().setVisible(true);
    }

    public void showClientView() {
        this.getContentPane().setVisible(false);
        this.getContentPane().removeAll();
        this.getContentPane().add(clientView);
        this.getContentPane().setVisible(true);
    }

    public void showForgotPasswordView() {
        this.getContentPane().setVisible(false);
        this.getContentPane().removeAll();
        this.getContentPane().add(forgotPasswordView);
        this.getContentPane().setVisible(true);
    }

    public void showMainView(){
        this.getContentPane().setVisible(false);
        this.getContentPane().removeAll();
        this.getContentPane().add(startView);
        this.getContentPane().setVisible(true);
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public UserServiceRestClient getUserServiceRestClient() {
        return userServiceRestClient;
    }

    public ReservationService getReservationService() {
        return reservationService;
    }

    public UserDto getCurrentUser() {
        return currentUser;
    }

    public NotificationServiceRestClient getNotificationServiceRestClient() {
        return notificationServiceRestClient;
    }

    public void setCurrentUser(UserDto currentUser) {
        this.currentUser = currentUser;
    }
}
package raf.edu.rs.gui.view;

import raf.edu.rs.gui.MainFrame;
import raf.edu.rs.gui.model.NotificationTableModel;
import raf.edu.rs.gui.model.NotificationTypeTableModel;
import raf.edu.rs.gui.model.UsersTableModel;
import raf.edu.rs.gui.restclient.dto.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AdminView extends JPanel {
    // ban/unban user
    private UsersTableModel blockUserTableModel;
    private JButton blockBtn;
    private JButton listAllUsersBtn;
    private JPanel panel1;

    // rankovi
    private JTextField rankIdField;
    private JTextField rankMinRezField;
    private JTextField rankDiscountField;
    private JButton changeRankBtn;
    private JButton listRanksBtn;
    private JTable rankTable;
    //private JPanel panel2;

    //notifications
    private JButton listNotificationsBtn;
    private JButton filterNotificationsBtn;
    private JTable notificationTable;
    private JTextField notifTypeField;
    private JTextField notifEmailField;
    private JTextField notifDateAfterField;
    private NotificationTableModel notificationTableModel;

    //notification type
    private JButton notifTypeListBtn;
    private JButton notifTypeChangeBtn;
    private JButton notifTypeDeleteBtn;
    private JTable notifTypeTable;
    private NotificationTypeTableModel notificationTypeTableModel;
    private JTextField typeIdField;
    private JTextField typeTextField;

    // parametri
    private JTextField changeUsernameFiled, changePasswordField, changeEmailField, changePhoneNumberField, changeDateOfBirthField, changeFirstNameField, changeLastNameField;
    private JButton changeUserBtn;
    private JButton getUserParamsBtn;

    public AdminView() {
        JPanel panel = new JPanel(null);
        panel.setPreferredSize(new Dimension(900,2000));
        JScrollPane scrollPane = new JScrollPane(panel);
        this.setLayout(new BorderLayout());

        /**
         * listanje notifikacija
         */
        notificationTableModel = new NotificationTableModel();
        notificationTable = new JTable(notificationTableModel);
        notifTypeField = new JTextField();
        notifEmailField = new JTextField();
        notifDateAfterField = new JTextField();


        JLabel lbNotifType = new JLabel("Type:");
        lbNotifType.setBounds(760,50,30,20);
        panel.add(lbNotifType);
        notifTypeField.setBounds(815,50,100,20);
        panel.add(notifTypeField);
        JLabel lbNotifEmail = new JLabel("Email:");
        lbNotifEmail.setBounds(760,80,50,20);
        panel.add(lbNotifEmail);
        notifEmailField.setBounds(815,80,100,20);
        panel.add(notifEmailField);
        JLabel lbNotifDate = new JLabel("After:");
        lbNotifDate.setBounds(760,110,50,20);
        panel.add(lbNotifDate);
        notifDateAfterField.setBounds(815,110,100,20);
        panel.add(notifDateAfterField);

        JScrollPane notifTableScroll = new JScrollPane(notificationTable);

        notificationTable.setPreferredSize(new Dimension(700,300));
        notifTableScroll.setBounds(50,50,700,300);
        panel.add(notifTableScroll);

        listNotificationsBtn = new JButton("List sent notificaions");
        listNotificationsBtn.setBounds(50,20, 200,25);

        filterNotificationsBtn = new JButton("Filter");
        filterNotificationsBtn.setBounds(254,20,200,25);

        listNotificationsBtn.addActionListener((event) -> {
            try {
                notificationTableModel.setRowCount(0);
                notificationTableModel.getSentEmails().clear();
                notificationTableModel.getSentEmails().addAll(MainFrame.getInstance().getNotificationServiceRestClient().getAllNotifications().getSentEmails());
                notificationTableModel.getSentEmails().forEach(sentEmailDto->{
                    notificationTableModel.addRow(new Object[]{sentEmailDto.getEmail(), sentEmailDto.getText(), sentEmailDto.getType(), sentEmailDto.getDateSent()});
                });
            }catch(Exception e){
                JOptionPane.showMessageDialog(null, "Error while listing all notifications", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        filterNotificationsBtn.addActionListener((event) -> {
            try {
                notificationTableModel.setRowCount(0);
                notificationTableModel.getSentEmails().clear();
                notificationTableModel.getSentEmails().addAll(MainFrame.getInstance().getNotificationServiceRestClient()
                        .getAllNotificationsFiltered(notifEmailField.getText(), notifTypeField.getText(), notifDateAfterField.getText()).getSentEmails());
                notificationTableModel.getSentEmails().forEach(sentEmailDto->{
                    notificationTableModel.addRow(new Object[]{sentEmailDto.getEmail(), sentEmailDto.getText(), sentEmailDto.getType(), sentEmailDto.getDateSent()});
                });

            }catch(Exception e){
                JOptionPane.showMessageDialog(null, "Error while filtering notifications", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        panel.add(listNotificationsBtn);
        panel.add(filterNotificationsBtn);

        /**
         * blokiranje korisnika
         **/
        blockUserTableModel = new UsersTableModel();
        JTable blockUserTable = new JTable(blockUserTableModel);
        JScrollPane blockUserScroll = new JScrollPane(blockUserTable);

        blockUserTable.setPreferredSize(new Dimension(700,300));
        blockUserScroll.setBounds(50,430,700,300);

        panel.add(blockUserScroll);

        blockBtn = new JButton("Block/Unblock user");
        blockBtn.setBounds(50,400,200,25);

        listAllUsersBtn = new JButton("List all users");
        listAllUsersBtn.setBounds(255,400,200,25);

        panel.add(blockBtn);
        panel.add(listAllUsersBtn);
        blockUserTable.setRowSelectionAllowed(true);
        blockBtn.addActionListener((event) -> {
            try {
                MainFrame.getInstance().getUserServiceRestClient().blockUser(
                        blockUserTableModel.getUsers().get(blockUserTable.getSelectedRow()).getId());
                listAllUsersBtn.doClick();
                /*
                if (blockUserTableModel.getUsers().get(blockUserTable.getSelectedRow()).getBlokced() == 1)
                    blockUserTableModel.getUsers().get(blockUserTable.getSelectedRow()).setBlokced(0);
                else
                    blockUserTableModel.getUsers().get(blockUserTable.getSelectedRow()).setBlokced(1);

                blockUserTableModel.setRowCount(0);
                blockUserTableModel.getUsers().forEach(userDto -> {
                    blockUserTableModel.addRow(new Object[]{userDto.getId(), userDto.getBlokced(), userDto.getUsername(), userDto.getRole()});
                });*/
            }catch(Exception e){
                JOptionPane.showMessageDialog(null, "Error while blocking/unblocking user", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        listAllUsersBtn.addActionListener((event) -> {
            try {
                AllUsersDto dto = MainFrame.getInstance().getUserServiceRestClient().getAllUsers();
                blockUserTableModel.setRowCount(0);
                blockUserTableModel.setUsers(dto.getUsers());
                dto.getUsers().forEach(userDto->{
                    blockUserTableModel.addRow(new Object[]{userDto.getId(), userDto.getBlocked(), userDto.getUsername(), userDto.getRole()});
                });
            }catch(Exception e){
                JOptionPane.showMessageDialog(null, "Error while trying to list all users", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        /**
         * menjanje ranka
         **/
         rankIdField = new JTextField();
         rankMinRezField = new JTextField();
         rankDiscountField = new JTextField();
         DefaultTableModel rankTableModel = new DefaultTableModel();
         rankTable = new JTable(rankTableModel);

        JScrollPane rankScroll = new JScrollPane(rankTable);

        rankTable.setPreferredSize(new Dimension(500,300));
        rankScroll.setBounds(50,780,500,300);

        rankTableModel.addColumn("Id");
        rankTableModel.addColumn("Name");
        rankTableModel.addColumn("Minimum number of reservations");
        rankTableModel.addColumn("Discount");

        changeRankBtn = new JButton("Change rank");
        changeRankBtn.setBounds(50,750,200,25);
        listRanksBtn = new JButton("List all ranks");
        listRanksBtn.setBounds(255,750,200,25);

        panel.add(rankScroll);
        panel.add(listRanksBtn);
        panel.add(changeRankBtn);

        JLabel lbRankId = new JLabel("Rank id:");
        lbRankId.setBounds(555,780,50,20);
        panel.add(lbRankId);
        JLabel lbMinRez = new JLabel("Minimum number of reservations:");
        lbMinRez.setBounds(555,810,200,20);
        panel.add(lbMinRez);
        JLabel lbRankDiscount = new JLabel("Rank discount:");
        lbRankDiscount.setBounds(555,840,200,20);
        panel.add(lbRankDiscount);

        rankIdField.setBounds(755,780,70,20);
        panel.add(rankIdField);
        rankMinRezField.setBounds(755,810,70,20);
        panel.add(rankMinRezField);
        rankDiscountField.setBounds(755,840,70,20);
        panel.add(rankDiscountField);

        listRanksBtn.addActionListener((event) -> {
            try {
                AllRanksDto ranksDto = MainFrame.getInstance().getUserServiceRestClient().getAllRanks();
                rankTableModel.setRowCount(0);
                ranksDto.getRanks().forEach(dto-> {
                    rankTableModel.addRow(new Object[]{dto.getId(), dto.getName(), dto.getReservationNumMin(), dto.getDiscount()});
                });
            }catch(Exception e){
                JOptionPane.showMessageDialog(null, "Error while listing all ranks", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        changeRankBtn.addActionListener((event) -> {
            try {
                int min, dis;
                Long id;
                if (!rankIdField.getText().equals(""))
                    id = Long.parseLong(rankIdField.getText());
                else
                    id = -1L;
                if (!rankMinRezField.getText().equals(""))
                    min = Integer.parseInt(rankMinRezField.getText());
                else
                    min = -1;
                if (!rankDiscountField.getText().equals(""))
                    dis = Integer.parseInt(rankDiscountField.getText());
                else
                    dis = -1;
                MainFrame.getInstance().getUserServiceRestClient().changeRank(new RankDto(id, min, dis));

                listRanksBtn.doClick();

            }catch(Exception e){
                JOptionPane.showMessageDialog(null, "Error while changing rank", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        /**
         * menjanje parametara korisnika
         **/
        changeUsernameFiled = new JTextField();
        changePasswordField = new JTextField();
        changeEmailField = new JTextField();
        changePhoneNumberField = new JTextField();
        changeDateOfBirthField = new JTextField();
        changeFirstNameField = new JTextField();
        changeLastNameField = new JTextField();
        changeUserBtn = new JButton("Update");

        List<JTextField> changeUserFields= Arrays.asList(changeUsernameFiled, changePasswordField, changeEmailField, changeFirstNameField
                                                        ,changeLastNameField, changeDateOfBirthField, changePhoneNumberField);
        List<JLabel> userLabels1 = Arrays.asList(new JLabel("Username:"), new JLabel("Password:"),new JLabel("Email:"), new JLabel("First name:")
                                                    ,new JLabel("Last name:"), new JLabel("Date of birth:"), new JLabel("Phone number:"));

        for (int i = 0; i < changeUserFields.size(); i++){
            changeUserFields.get(i).setBounds(160,1120+30*i,150,20);
            panel.add(changeUserFields.get(i));
        }
        for (int i = 0; i < userLabels1.size(); i++){
            userLabels1.get(i).setBounds(50,1120+30*i,150,20);
            panel.add(userLabels1.get(i));
        }
        changeUserBtn.setBounds(100,1365,110,20);
        panel.add(changeUserBtn);

        changeUserBtn.addActionListener(e -> {

            UserCreateDto dto = new UserCreateDto();
            dto.setId(MainFrame.getInstance().getCurrentUser().getId());
            dto.setUsername(changeUsernameFiled.getText());
            dto.setPassword(changePasswordField.getText());
            dto.setFirstName(changeFirstNameField.getText());
            dto.setLastName(changeLastNameField.getText());
            dto.setEmail(changeEmailField.getText());
            dto.setPhoneNumber(changePhoneNumberField.getText());
            dto.setDateOfBirth(changeDateOfBirthField.getText());

            try {
                MainFrame.getInstance().getUserServiceRestClient().changeUser(dto);

                if (!dto.getEmail().equals(""))
                    MainFrame.getInstance().getCurrentUser().setEmail(dto.getEmail());
                if (!dto.getFirstName().equals(""))
                    MainFrame.getInstance().getCurrentUser().setFirstName(dto.getFirstName());
                if (!dto.getLastName().equals(""))
                    MainFrame.getInstance().getCurrentUser().setLastName(dto.getLastName());
                if (!dto.getUsername().equals(""))
                    MainFrame.getInstance().getCurrentUser().setUsername(dto.getUsername());
                getUserParamsBtn.doClick();

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error while changing user", "Error", JOptionPane.ERROR_MESSAGE);
            }

        });
        /**
         * listanje korisnickih parametara
         */
        getUserParamsBtn = new JButton("Get user parametars");

        List<JLabel> userLabels2 = Arrays.asList(new JLabel("Username:"), new JLabel("Password:"),new JLabel("Email:"), new JLabel("First name:")
                ,new JLabel("Last name:"), new JLabel("Date of birth:"), new JLabel("Phone number:"));

        for (int i = 0; i < userLabels2.size(); i++){
            userLabels2.get(i).setBounds(390,1120+30*i,150,20);
            panel.add(userLabels2.get(i));
        }

        List<JLabel> userParams = Arrays.asList(new JLabel(""), new JLabel(""),new JLabel(""), new JLabel("")
                ,new JLabel(""), new JLabel(""), new JLabel(""));

        for (int i = 0; i < userLabels2.size(); i++){
            userLabels2.get(i).setBounds(390,1120+30*i,150,20);
            panel.add(userLabels2.get(i));
        }
        for (int i = 0; i < userParams.size(); i++){
            userParams.get(i).setBounds(560,1120+30*i,150,20);
            panel.add(userParams.get(i));
        }

        getUserParamsBtn.setBounds(450,1365,200,20);
        getUserParamsBtn.addActionListener(e -> {

            try{
                UserDto dto = MainFrame.getInstance().getUserServiceRestClient().getCurrentUser(MainFrame.getInstance().getCurrentUser().getId());

                userParams.get(0).setText(dto.getUsername());
                userParams.get(1).setText(dto.getPassword());
                userParams.get(2).setText(dto.getEmail());
                userParams.get(3).setText(dto.getFirstName());
                userParams.get(4).setText(dto.getLastName());
                userParams.get(5).setText(dto.getDateOfBirth());
                userParams.get(6).setText(dto.getPassportNumber());

            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Error while getting user information", "Error", JOptionPane.ERROR_MESSAGE);
            }

        });
        panel.add(getUserParamsBtn);
        /**
         * notification types
        **/
        notifTypeListBtn = new JButton("list all notification types");
        notifTypeChangeBtn = new JButton("Change notificatin type");
        notifTypeDeleteBtn = new JButton("Delete norification type");
        notificationTypeTableModel = new NotificationTypeTableModel();
        notifTypeTable = new JTable(notificationTypeTableModel);
        typeIdField = new JTextField();
        typeTextField = new JTextField();

        JScrollPane notifTypeScroll = new JScrollPane(notifTypeTable);

        notifTypeTable.setPreferredSize(new Dimension(700,300));
        notifTypeScroll.setBounds(50,1500,700,300);

        panel.add(notifTypeScroll);

        typeIdField.setBounds(810,1500, 100,20);
        panel.add(typeIdField);
        typeTextField.setBounds(810,1530, 100,20);
        panel.add(typeTextField);

        JLabel lbTypeId = new JLabel("Id:");
        lbTypeId.setBounds(760,1500,30,20);
        panel.add(lbTypeId);
        JLabel lbTypeText = new JLabel("Text:");
        lbTypeText.setBounds(760,1530,30,20);
        panel.add(lbTypeText);

        notifTypeListBtn.setBounds(50,1470,200,25);
        panel.add(notifTypeListBtn);
        notifTypeChangeBtn.setBounds(255,1470,200,25);
        panel.add(notifTypeChangeBtn);
        notifTypeDeleteBtn.setBounds(460,1470,200,25);
        panel.add(notifTypeDeleteBtn);

        notifTypeListBtn.addActionListener(e -> {
            try {
                notificationTypeTableModel.setTypes(MainFrame.getInstance().getNotificationServiceRestClient().getAllNotificationTypes().getNotifs());
                notificationTypeTableModel.setRowCount(0);
                notificationTypeTableModel.getTypes().forEach(type->{
                    notificationTypeTableModel.addRow(new Object[] {type.getId(), type.getType(), type.getText()});
                });
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Error while listing all notification types", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        notifTypeChangeBtn.addActionListener(e -> {
            try {
                if (typeIdField.getText().equals("") || typeTextField.getText().equals(""))
                    throw new Exception();
                MainFrame.getInstance().getNotificationServiceRestClient().changeNotiftype(Long.parseLong(typeIdField.getText()), typeTextField.getText());
                notifTypeListBtn.doClick();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error while changing notification type", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        notifTypeDeleteBtn.addActionListener(e -> {
            try {
                if (typeIdField.getText().equals(""))
                    throw new Exception();
                MainFrame.getInstance().getNotificationServiceRestClient().deleteNotificationType(Long.parseLong(typeIdField.getText()));
                notifTypeListBtn.doClick();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error while deleting notification type", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });


        this.add(scrollPane, BorderLayout.CENTER);
    }
}
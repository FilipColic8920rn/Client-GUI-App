package raf.edu.rs.gui.view;

import raf.edu.rs.gui.MainFrame;
import raf.edu.rs.gui.restclient.dto.SentEmailDto;
import raf.edu.rs.gui.restclient.dto.SentEmailListDto;
import raf.edu.rs.gui.restclient.dto.UserCreateDto;
import raf.edu.rs.gui.restclient.dto.UserDto;

import javax.swing.*;
import java.io.IOException;
import java.util.List;

public class ManagerView extends JPanel {
    // add new hotel
    private JTextField newHotelCity, newHotelName, newHotelDescription, newHotelNoOfRooms;
    private JButton addHotelBtn;
    private JPanel panel1;

    // update hotel
    private JTextField updateHotelCity, updateHotelName, updateHotelDescription, updateHotelNoOfRooms;
    private JButton updateHotelBtn;
    private JPanel panel2;

    // add new room
    private JTextField newRoomNumber, newRoomType, newRoomPricePerDay;
    private JButton addRoomBtn;
    private JPanel panel3;

    // edit room
    private JTextField currentRoomNumber, updateRoomNumber, updateRoomType, updateRoomPrice;
    private JButton updateRoomBtn;
    private JPanel panel4;

    // set type depending on the prices
    private JTextField startPrice, endPrice, newType;
    private JButton setTypes;
    private JPanel panel5;

    // cancel reservation
    private JTextField reservationId;
    private JButton showReservations, cancelReservation;
    private JPanel panel6;

    // list notifications sent to manager
    private JButton listNotifications;
    private JPanel panel7;

    // update parameters
    private JTextField usernameChange, passChange, emailChange, pNoChange, dateChange, dateEmpChange, fNameChange, lNameChange, psChange;
    private JButton btnChange;
    private JPanel panel8;

    //get user params
    private JPanel panel10;
    private JButton getParams;

    //list notifications
    private JPanel panel11;
    private JTextField notifTypeFilterField;
    private JTextField notifDateAfterField;
    private JButton getNotifsBtn;

    public ManagerView() {
        // add new
        newHotelCity = new JTextField("city");
        newHotelName = new JTextField("hotel name");
        newHotelDescription = new JTextField("description");
        newHotelNoOfRooms = new JTextField("number of rooms");
        addHotelBtn = new JButton("Add new hotel");

        addHotelBtn.addActionListener(e -> {
            try {
                MainFrame.getInstance().getReservationService().addNewHotel(newHotelCity.getText(),
                        newHotelName.getText(), newHotelDescription.getText(),
                        Integer.parseInt(newHotelNoOfRooms.getText()));
            }
            catch (Exception exception) {
                exception.printStackTrace();
            }
        });

        panel1 = new JPanel();
        panel1.add(newHotelCity);
        panel1.add(newHotelName);
        panel1.add(newHotelDescription);
        panel1.add(newHotelNoOfRooms);
        panel1.add(addHotelBtn);

        // update
        updateHotelCity = new JTextField("city");
        updateHotelName = new JTextField("hotel name");
        updateHotelDescription = new JTextField("description");
        updateHotelNoOfRooms = new JTextField("number of rooms");
        updateHotelBtn = new JButton("Update hotel");

        updateHotelBtn.addActionListener(e -> {
            try {
                MainFrame.getInstance().getReservationService().editHotel(updateHotelCity.getText(),
                        updateHotelName.getText(), updateHotelDescription.getText(),
                        Integer.parseInt(updateHotelNoOfRooms.getText()));
            }
            catch (Exception exception) {
                exception.printStackTrace();
            }
        });

        panel2 = new JPanel();
        panel2.add(updateHotelCity);
        panel2.add(updateHotelName);
        panel2.add(updateHotelDescription);
        panel2.add(updateHotelNoOfRooms);
        panel2.add(updateHotelBtn);

        // add new room
        newRoomNumber = new JTextField("room number");
        newRoomType = new JTextField("room type");
        newRoomPricePerDay = new JTextField("price per day");
        addRoomBtn = new JButton("Add new room");

        addRoomBtn.addActionListener(e -> {
            try {
                MainFrame.getInstance().getReservationService().addRoom(Integer.parseInt(newRoomNumber.getText()),
                        newRoomType.getText(), Double.parseDouble(newRoomPricePerDay.getText()));
            }
            catch (Exception exception) {
                exception.printStackTrace();
            }
        });

        panel3 = new JPanel();
        panel3.add(newRoomNumber);
        panel3.add(newRoomType);
        panel3.add(newRoomPricePerDay);
        panel3.add(addRoomBtn);

        // update room
        currentRoomNumber = new JTextField("current num");
        updateRoomNumber = new JTextField("new room num");
        updateRoomType = new JTextField("room type");
        updateRoomPrice = new JTextField("new price per day");
        updateRoomBtn = new JButton("Update existing room");

        updateRoomBtn.addActionListener(e -> {
            try {
                MainFrame.getInstance().getReservationService().editRoom(
                    Integer.parseInt(currentRoomNumber.getText()), Integer.parseInt(updateRoomNumber.getText()),
                        updateRoomType.getText(), Double.parseDouble(updateRoomPrice.getText()));
            }
            catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });

        panel4 = new JPanel();
        panel4.add(currentRoomNumber);
        panel4.add(updateRoomNumber);
        panel4.add(updateRoomType);
        panel4.add(updateRoomPrice);
        panel4.add(updateRoomBtn);

        // set types
        startPrice = new JTextField("start price");
        endPrice = new JTextField("end price");
        newType = new JTextField("room type");
        setTypes = new JButton("Set types");

        setTypes.addActionListener(e -> {
            try {
                MainFrame.getInstance().getReservationService().editRooms(Double.parseDouble(startPrice.getText()),
                        Double.parseDouble(endPrice.getText()), newType.getText());
            }
            catch (NumberFormatException | IOException numberFormatException) {
                numberFormatException.printStackTrace();
            }
        });

        panel5 = new JPanel();
        panel5.add(startPrice);
        panel5.add(endPrice);
        panel5.add(newType);
        panel5.add(setTypes);

        // cancel reservation
        showReservations = new JButton("Show Reservations");
        reservationId = new JTextField("id");
        cancelReservation = new JButton("Cancel Reservation");

        showReservations.addActionListener(e -> {
            try {
                List reservations = MainFrame.getInstance().getReservationService().
                        findRelatedReservations();
                StringBuilder builder = new StringBuilder();
                if (!reservations.isEmpty()) {

                    builder.append("-------RESERVATIONS:\n");

                    for (Object reservation: reservations)
                        builder.append(reservation.toString() + "\n");

                    builder.append("-------ENTER ID.\n");
                }
                JOptionPane.showMessageDialog(null, builder.toString(), "Reservations", JOptionPane.PLAIN_MESSAGE);
            }
            catch (Exception exception) {
                exception.printStackTrace();
            }
        });

        cancelReservation.addActionListener(e -> {
            try {
                MainFrame.getInstance().getReservationService().cancelReservation(
                        Long.parseLong(reservationId.getText())
                );
            }
            catch (Exception exception) {
                exception.printStackTrace();
            }
        });

        panel6 = new JPanel();
        panel6.add(showReservations);
        panel6.add(reservationId);
        panel6.add(cancelReservation);
        /*
        // list notifications
        listNotifications = new JButton("Show notifications received");

        listNotifications.addActionListener(e -> {
            try {
                SentEmailListDto list = MainFrame.getInstance().getNotificationServiceRestClient().
                        getAllNotificationsByEmail(MainFrame.getInstance().getCurrentUser().getEmail());

                StringBuilder sb = new StringBuilder();

                for (SentEmailDto dto: list.getSentEmails())
                    sb.append(dto.getId() + " " + dto.getEmail() + " " + dto.getText() + " " + dto.getType() + "\n");

                JOptionPane.showMessageDialog(null, sb.toString());
            }
            catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        panel7 = new JPanel();*/
        //panel7.add(listNotifications);

        // update parameters
        usernameChange = new JTextField("username");
        passChange = new JTextField("password");
        emailChange = new JTextField("email");
        pNoChange = new JTextField("phone no.");
        dateChange = new JTextField("date of birth");
        dateEmpChange = new JTextField("date of employment");
        fNameChange = new JTextField("first name");
        lNameChange = new JTextField("last name");
        psChange = new JTextField("passport");
        btnChange = new JButton("Update");

        btnChange.addActionListener(e -> {
            UserCreateDto dto = new UserCreateDto();
            dto.setId(MainFrame.getInstance().getCurrentUser().getId());
            if(!usernameChange.getText().equals("") && !usernameChange.getText().equals("username"))
                dto.setUsername(usernameChange.getText());
            if(!passChange.getText().equals("") && !passChange.getText().equals("password"))
                dto.setPassword(passChange.getText());
            if(!fNameChange.getText().equals("") && !fNameChange.getText().equals("first name"))
                dto.setFirstName(fNameChange.getText());
            if(!lNameChange.getText().equals("") && !lNameChange.getText().equals("last name"))
                dto.setLastName(lNameChange.getText());
            if(!emailChange.getText().equals("") && !emailChange.getText().equals("email"))
                dto.setEmail(emailChange.getText());
            if(!pNoChange.getText().equals("") && !pNoChange.getText().equals("phone no."))
                dto.setPhoneNumber(pNoChange.getText());
            if(!dateChange.getText().equals("") && !dateChange.getText().equals("date of birth"))
                dto.setDateOfBirth(dateChange.getText());
            if(!psChange.getText().equals("") && !psChange.getText().equals("passport"))
                dto.setPassportNumber(psChange.getText());
            if(!dateEmpChange.getText().equals("") && !dateEmpChange.getText().equals("date of employment"))
                dto.setDateOfEmployment(dateEmpChange.getText());

            try {
                MainFrame.getInstance().getUserServiceRestClient().changeUser(dto);

                if (dto.getEmail() != null && !dto.getEmail().equals(""))
                    MainFrame.getInstance().getCurrentUser().setEmail(dto.getEmail());
                if (dto.getFirstName() != null && !dto.getFirstName().equals(""))
                    MainFrame.getInstance().getCurrentUser().setFirstName(dto.getFirstName());
                if (dto.getLastName() != null && !dto.getLastName().equals(""))
                    MainFrame.getInstance().getCurrentUser().setLastName(dto.getLastName());
                if (dto.getUsername() != null && !dto.getUsername().equals(""))
                    MainFrame.getInstance().getCurrentUser().setUsername(dto.getUsername());
            }
            catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error while changing user", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        });

        panel8 = new JPanel();
        panel8.add(usernameChange);
        panel8.add(passChange);
        panel8.add(emailChange);
        panel8.add(pNoChange);
        panel8.add(dateChange);
        panel8.add(dateEmpChange);
        panel8.add(fNameChange);
        panel8.add(lNameChange);
        panel8.add(psChange);
        panel8.add(btnChange);


        //dobijanje korisnickih parametara
        panel10 = new JPanel();
        getParams = new JButton("Get user params");
        getParams.addActionListener(e -> {
            try {
                UserDto dto = MainFrame.getInstance().getUserServiceRestClient().getCurrentUser(MainFrame.getInstance().getCurrentUser().getId());
                String params = "";
                params += ("Username: " + dto.getUsername() + "\n");
                params += ("Password: " + dto.getPassword() + "\n");
                params += ("First name: " + dto.getFirstName() + "\n");
                params += ("Last name: " + dto.getLastName() + "\n");
                params += ("Email: " + dto.getEmail() + "\n");
                params += ("Phone number: " + dto.getPhoneNumber() + "\n");
                params += ("Date of birth: " + dto.getDateOfBirth() + "\n");
                params += ("Hotel name: " + dto.getHotelName() + "\n");
                params += ("Date of employment: " + dto.getDateOfEmployment() + "\n");
                JOptionPane.showMessageDialog(null, params, "User parmas", JOptionPane.PLAIN_MESSAGE);
            }
            catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error while getting user information", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        panel10.add(getParams);

        //list notifications
        panel11 = new JPanel();
        notifTypeFilterField = new JTextField("Notif Type");
        notifDateAfterField = new JTextField("Date After");
        getNotifsBtn = new JButton("Get notifications");

        getNotifsBtn.addActionListener(e ->{
            String type = notifTypeFilterField.getText();
            String date = notifDateAfterField.getText();
            if(type.equals("Notif Type") || type.equals(""))
                type = "";
            if(date.equals("Date After") || date.equals(""))
                date = "";

            try {
                SentEmailListDto dto = MainFrame.getInstance().getNotificationServiceRestClient().getAllNotificationsFiltered(MainFrame.getInstance().getCurrentUser().getEmail(),
                        type, date);
                String emails = "";
                for(SentEmailDto s: dto.getSentEmails()){
                    emails += s.getText() + "\n";
                }
                JOptionPane.showMessageDialog(null, emails, "All notifications",JOptionPane.PLAIN_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error while getting notifications", "Error", JOptionPane.ERROR_MESSAGE);
            }

        });

        panel11.add(notifTypeFilterField);
        panel11.add(notifDateAfterField);
        panel11.add(getNotifsBtn);

        // ---- paneli ----
        this.removeAll();
        this.add(panel1);
        this.add(panel2);
        this.add(panel3);
        this.add(panel4);
        this.add(panel5);
        this.add(panel6);
        //this.add(panel7);
        this.add(panel8);
        this.add(panel10);
        this.add(panel11);
    }
}
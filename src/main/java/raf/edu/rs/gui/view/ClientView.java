package raf.edu.rs.gui.view;

import raf.edu.rs.gui.MainFrame;
import raf.edu.rs.gui.restReservation.dto.Review;
import raf.edu.rs.gui.restclient.dto.SentEmailDto;
import raf.edu.rs.gui.restclient.dto.SentEmailListDto;
import raf.edu.rs.gui.restclient.dto.UserCreateDto;
import raf.edu.rs.gui.restclient.dto.UserDto;

import javax.swing.*;
import java.io.IOException;
import java.util.List;

public class ClientView extends JPanel {
    // listanje soba
    private JTextField citySoba, hotelSoba, typeSoba, startDateSoba, endDateSoba, sortSoba;
    private JButton listSoba;
    private JPanel panel1;

    // rezervisanje
    private JTextField hotelRes, roomRes, startDateRes, endDateRes;
    private JButton submitRes;
    private JPanel panel2;

    // cancel reservation
    private JTextField cancelId;
    private JButton cancelBtn;
    private JPanel panel3;

    // new review
    private JTextField resIdField, ratingField, commentField;
    private JButton addReview;
    private JPanel panel4;

    // list reviews
    private JTextField hotelName, cityRev;
    private JButton listReviews;
    private JPanel panel5;

    // update review
    private JTextField revId, ratingFieldUpd, commentFieldUpd;
    private JButton addReviewUpd;
    private JPanel panel6;

    // delete review
    private JTextField delRevId;
    private JButton delRev;
    private JPanel panel7;

    // update parameters
    private JTextField usernameChange, passChange, emailChange, pNoChange, dateChange, fNameChange, lNameChange, psChange;
    private JButton btnChange;
    private JPanel panel8;

    // best hotels
    private JButton bestBtn;
    private JPanel panel9;

    //getUserParams
    private JButton getParams;
    private JPanel panel10;

    //list notifications
    private JPanel panel11;
    private JButton getNotifsBtn;
    private JTextField notifTypeFilterField;
    private JTextField notifDateAfterField;

    public ClientView() {
        // listanje soba
        citySoba = new JTextField("city");
        hotelSoba = new JTextField("hotel name");
        typeSoba = new JTextField("room type");
        startDateSoba = new JTextField("start date");
        endDateSoba = new JTextField("end date");
        sortSoba = new JTextField("asc/desc");
        listSoba = new JButton("List");

        listSoba.addActionListener(e -> {
            try {
                List rooms = MainFrame.getInstance().getReservationService().getAvailableRooms(citySoba.getText(),
                    hotelSoba.getText(), typeSoba.getText(), startDateSoba.getText(), endDateSoba.getText(),
                        sortSoba.getText());

                JOptionPane.showMessageDialog(null, rooms);
            }
            catch (Exception exception) {
                exception.printStackTrace();
            }
        });

        panel1 = new JPanel();
        panel1.add(citySoba);
        panel1.add(hotelSoba);
        panel1.add(typeSoba);
        panel1.add(startDateSoba);
        panel1.add(endDateSoba);
        panel1.add(sortSoba);
        panel1.add(listSoba);

        // rezervacija
        hotelRes = new JTextField("hotel id");
        roomRes = new JTextField("room id");
        startDateRes = new JTextField("start date");
        endDateRes = new JTextField("end date");
        submitRes = new JButton("New reservation");

        submitRes.addActionListener(e -> {
            try {
                // rezervacija
                MainFrame.getInstance().getReservationService().createReservation(Long.parseLong(hotelRes.getText()),
                        Long.parseLong(roomRes.getText()), startDateRes.getText(), endDateRes.getText());
            }
            catch (Exception exception) {
                exception.printStackTrace();
            }
        });

        panel2 = new JPanel();
        panel2.add(hotelRes);
        panel2.add(roomRes);
        panel2.add(startDateRes);
        panel2.add(endDateRes);
        panel2.add(submitRes);

        // cancel res
        cancelId = new JTextField("id");
        cancelBtn = new JButton("Cancel Reservation");

        cancelBtn.addActionListener(e -> {
            try {
                MainFrame.getInstance().getReservationService().cancelReservation(Long.parseLong(cancelId.getText()));
            }
            catch (Exception exception) {
                exception.printStackTrace();
            }
        });

        panel3 = new JPanel();
        panel3.add(cancelId);
        panel3.add(cancelBtn);

        // adding review
        resIdField = new JTextField("reservation id");
        ratingField = new JTextField("rating 1-5");
        commentField = new JTextField("leave your comment here...");
        addReview = new JButton("Add Review");

        addReview.addActionListener(e -> {
            try {
                MainFrame.getInstance().getReservationService().newReview(new Review(MainFrame.getInstance().
                        getCurrentUser().getId(), Long.parseLong(resIdField.getText()),
                        Integer.parseInt(ratingField.getText()), commentField.getText()));
            }
            catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        panel4 = new JPanel();
        panel4.add(resIdField);
        panel4.add(ratingField);
        panel4.add(commentField);
        panel4.add(addReview);

        // update parameters
        usernameChange = new JTextField("username");
        passChange = new JTextField("password");
        emailChange = new JTextField("email");
        pNoChange = new JTextField("phone no.");
        dateChange = new JTextField("date of birth");
        fNameChange = new JTextField("first name");
        lNameChange = new JTextField("last name");
        psChange = new JTextField("passport");
        btnChange = new JButton("Update user");

        btnChange.addActionListener(e -> {
            UserCreateDto dto = new UserCreateDto();
            dto.setId(MainFrame.getInstance().getCurrentUser().getId());
           /* dto.setUsername(usernameChange.getText());
            dto.setPassword(passChange.getText());
            dto.setFirstName(fNameChange.getText());
            dto.setLastName(lNameChange.getText());
            dto.setEmail(emailChange.getText());
            dto.setPhoneNumber(pNoChange.getText());
            dto.setDateOfBirth(dateChange.getText());
            dto.setPassportNumber(psChange.getText());*/
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

            try {
                MainFrame.getInstance().getUserServiceRestClient().changeUser(dto);

                /*if (!dto.getEmail().equals(""))
                    MainFrame.getInstance().getCurrentUser().setEmail(dto.getEmail());
                if (!dto.getFirstName().equals(""))
                    MainFrame.getInstance().getCurrentUser().setFirstName(dto.getFirstName());
                if (!dto.getLastName().equals(""))
                    MainFrame.getInstance().getCurrentUser().setLastName(dto.getLastName());
                if (!dto.getUsername().equals(""))
                    MainFrame.getInstance().getCurrentUser().setUsername(dto.getUsername());*/
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
        panel8.add(fNameChange);
        panel8.add(lNameChange);
        panel8.add(psChange);
        panel8.add(btnChange);

        // list reviews
        hotelName = new JTextField("hotel name");
        cityRev = new JTextField("city    ");
        listReviews = new JButton("List Reviews");

        listReviews.addActionListener(e -> {
            try {
                List rev = MainFrame.getInstance().getReservationService().getAllReviews(
                        hotelName.getText(), cityRev.getText());

                JOptionPane.showMessageDialog(null, rev);
            }
            catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        panel5 = new JPanel();
        panel5.add(hotelName);
        panel5.add(cityRev);
        panel5.add(listReviews);

        // update review
        revId = new JTextField("review id");
        ratingFieldUpd = new JTextField("new rating");
        commentFieldUpd = new JTextField("new comment");
        addReviewUpd = new JButton("Update Review");

        addReviewUpd.addActionListener(e -> {
            try {
                MainFrame.getInstance().getReservationService().updateReview(Long.parseLong(revId.getText()),
                        new Review(MainFrame.getInstance().getCurrentUser().getId(), -1L,
                                Integer.parseInt(ratingFieldUpd.getText()), commentFieldUpd.getText()));
            }
            catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        panel6 = new JPanel();
        panel6.add(revId);
        panel6.add(ratingFieldUpd);
        panel6.add(commentFieldUpd);
        panel6.add(addReviewUpd);

        // delete review
        delRevId = new JTextField("review id");
        delRev = new JButton("Delete review");

        delRev.addActionListener(e -> {
            try {
                MainFrame.getInstance().getReservationService().deleteReview(Long.parseLong(delRevId.getText()));
            }
            catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        panel7 = new JPanel();
        panel7.add(delRevId);
        panel7.add(delRev);

        // best hotels
        bestBtn = new JButton("get best rated hotels");

        bestBtn.addActionListener(e -> {
            try {
                List list = MainFrame.getInstance().getReservationService().getTopRatedHotels();
                JOptionPane.showMessageDialog(null, list);
            }
            catch (IOException ex) {
                ex.printStackTrace();
            }

        });

        panel9 = new JPanel();
        panel9.add(bestBtn);

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
                params += ("Passport number: " + dto.getPassportNumber()+ "\n");
                params += ("Number of reservations: " + dto.getNumberOfReservations() + "\n");
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

        // paneli
        this.removeAll();
        this.add(panel1);
        this.add(panel2);
        this.add(panel3);
        this.add(panel4);
        this.add(panel5);
        this.add(panel6);
        this.add(panel7);
        this.add(panel9);
        this.add(panel8);
        this.add(panel10);
        this.add(panel11);
    }
}
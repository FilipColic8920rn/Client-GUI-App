package raf.edu.rs.gui.model;

import raf.edu.rs.gui.restclient.dto.SentEmailDto;

import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.List;

public class NotificationTableModel extends DefaultTableModel {

    private List<SentEmailDto> sentEmails = new ArrayList<>();

    public NotificationTableModel() throws IllegalAccessError{
        super(new String[]{"Email", "Text", "Type", "Date"}, 0);
    }

    public List<SentEmailDto> getSentEmails() {
        return sentEmails;
    }

    public void setSentEmails(List<SentEmailDto> sentEmails) {
        this.sentEmails = sentEmails;
    }
}

package raf.edu.rs.gui.model;

import raf.edu.rs.gui.restclient.dto.EmailNotificationDto;

import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.List;

public class NotificationTypeTableModel extends DefaultTableModel {
    List<EmailNotificationDto> types = new ArrayList<>();

    public NotificationTypeTableModel() throws IllegalAccessError{
        super(new String[]{"Id", "Type", "Text"}, 0);
    }

    public List<EmailNotificationDto> getTypes() {
        return types;
    }

    public void setTypes(List<EmailNotificationDto> types) {
        this.types = types;
    }
}

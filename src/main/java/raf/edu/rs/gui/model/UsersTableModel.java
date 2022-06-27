package raf.edu.rs.gui.model;

import raf.edu.rs.gui.restclient.dto.UserDto;

import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.List;

public class UsersTableModel extends DefaultTableModel {

    private List<UserDto> users = new ArrayList<>();

    public UsersTableModel() throws IllegalAccessError{
        super(new String[]{"Id", "Blocked", "Username", "Role"}, 0);
    }

    public void refresh(){
        fireTableDataChanged();
    }

    public List<UserDto> getUsers() {
        return users;
    }

    public void setUsers(List<UserDto> users) {
        this.users = users;
    }
}

package raf.edu.rs.gui.restReservation.dto;

public class Hotel {
    private Long managerId;
    private String city;
    private String name;
    private String description;
    private Integer numberOfRooms;

    public Hotel(Long managerId, String city, String name, String description, Integer numberOfRooms) {
        this.managerId = managerId;
        this.city = city;
        this.name = name;
        this.description = description;
        this.numberOfRooms = numberOfRooms;
    }

    public Hotel() {}

    public Long getManagerId() {
        return managerId;
    }

    public void setManagerId(Long managerId) {
        this.managerId = managerId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getNumberOfRooms() {
        return numberOfRooms;
    }

    public void setNumberOfRooms(Integer numberOfRooms) {
        this.numberOfRooms = numberOfRooms;
    }
}
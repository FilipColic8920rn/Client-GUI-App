package raf.edu.rs.gui.restReservation.dto;

public class Reservation {
    private Long id;
    private Long userId;
    private String userEmail;
    private Long hotelId;
    private Long roomId;
    private String startDate;
    private String endDate;
    private Double totalPrice;
    private Boolean sentReminder;

    public Reservation(Long id, Long userId, String userEmail, Long hotelId, Long roomId, String startDate, String endDate,
                       Double price) {
        this.id = id;
        this.userId = userId;
        this.userEmail = userEmail;
        this.hotelId = hotelId;
        this.roomId = roomId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.totalPrice = price;
    }

    public Reservation() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public Long getHotelId() {
        return hotelId;
    }

    public void setHotelId(Long hotelId) {
        this.hotelId = hotelId;
    }

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Boolean getSentReminder() {
        return sentReminder;
    }

    public void setSentReminder(Boolean sentReminder) {
        this.sentReminder = sentReminder;
    }
}
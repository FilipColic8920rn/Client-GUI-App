package raf.edu.rs.gui.restReservation.dto;

public class Review {
    private Long userId;
    private Long reservationId;
    private Integer rating;
    private String comment;

    public Review(Long userId, Long reservationId, Integer rating, String comment) {
        this.userId = userId;
        this.reservationId = reservationId;
        this.rating = rating;
        this.comment = comment;
    }

    public Review() {}

    public Long getReservationId() {
        return reservationId;
    }

    public void setReservationId(Long reservationId) {
        this.reservationId = reservationId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
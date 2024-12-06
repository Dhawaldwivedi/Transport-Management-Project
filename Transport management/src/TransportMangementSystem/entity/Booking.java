package TransportMangementSystem.entity;


import java.util.Date;

public class Booking {

    // Attributes for the Booking entity
    private int bookingId;         // Primary Key for Booking
    private int tripId;            // Foreign Key referencing Trip
    private int passengerId;       // Foreign Key referencing Passenger
    private Date bookingDate;      // Date of the booking
    private String status;         // Status of the booking (Confirmed, Cancelled, Completed)

    // Default constructor
    public Booking() {}

    // Parameterized constructor
    public Booking(int bookingId, int tripId, int passengerId, Date bookingDate, String status) {
        this.bookingId = bookingId;
        this.tripId = tripId;
        this.passengerId = passengerId;
        this.bookingDate = bookingDate;
        this.status = status;
    }

    // Getters and setters for all fields
    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public int getTripId() {
        return tripId;
    }

    public void setTripId(int tripId) {
        this.tripId = tripId;
    }

    public int getPassengerId() {
        return passengerId;
    }

    public void setPassengerId(int passengerId) {
        this.passengerId = passengerId;
    }

    public Date getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(Date bookingDate) {
        this.bookingDate = bookingDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // toString method to display booking information
    @Override
    public String toString() {
        return "Booking [bookingId=" + bookingId +
                ", tripId=" + tripId +
                ", passengerId=" + passengerId +
                ", bookingDate=" + bookingDate +
                ", status=" + status + "]";
    }
}

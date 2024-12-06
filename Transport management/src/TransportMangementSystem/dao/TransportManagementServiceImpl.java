package TransportMangementSystem.dao;

import TransportMangementSystem.entity.*;
import TransportMangementSystem.util.DBConnUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TransportManagementServiceImpl implements TransportManagementService {

    @Override
    public boolean addVehicle(Vehicle vehicle) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DBConnUtil.getDBConn("db.properties");
            String query = "INSERT INTO Vehicles (Model, Capacity, Type, Status) VALUES (?, ?, ?, ?)";
            ps = conn.prepareStatement(query);
            ps.setString(1, vehicle.getModel());
            ps.setDouble(2, vehicle.getCapacity());
            ps.setString(3, vehicle.getType());
            ps.setString(4, vehicle.getStatus());
            int result = ps.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnUtil.close(ps, conn);
        }
        return false;
    }

    @Override
    public boolean updateVehicle(Vehicle vehicle) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DBConnUtil.getDBConn("db.properties");
            String query = "UPDATE Vehicles SET Model=?, Capacity=?, Type=?, Status=? WHERE VehicleID=?";
            ps = conn.prepareStatement(query);
            ps.setString(1, vehicle.getModel());
            ps.setDouble(2, vehicle.getCapacity());
            ps.setString(3, vehicle.getType());
            ps.setString(4, vehicle.getStatus());
            ps.setInt(5, vehicle.getVehicleId());
            int result = ps.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnUtil.close(ps, conn);
        }
        return false;
    }

    @Override
    public boolean deleteVehicle(int vehicleId) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DBConnUtil.getDBConn("db.properties");
            String query = "DELETE FROM Vehicles WHERE VehicleID = ?";
            ps = conn.prepareStatement(query);
            ps.setInt(1, vehicleId);
            int result = ps.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnUtil.close(ps, conn);
        }
        return false;
    }

    @Override
    public boolean scheduleTrip(int vehicleId, int routeId, String departureDate, String arrivalDate) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DBConnUtil.getDBConn("db.properties");
            String query = "INSERT INTO Trips (VehicleID, RouteID, DepartureDate, ArrivalDate, Status) VALUES (?, ?, ?, ?, 'Scheduled')";
            ps = conn.prepareStatement(query);
            ps.setInt(1, vehicleId);
            ps.setInt(2, routeId);
            ps.setString(3, departureDate);
            ps.setString(4, arrivalDate);
            int result = ps.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnUtil.close(ps, conn);
        }
        return false;
    }

    @Override
    public boolean cancelTrip(int tripId) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DBConnUtil.getDBConn("db.properties");
            String query = "UPDATE Trips SET Status='Cancelled' WHERE TripID=?";
            ps = conn.prepareStatement(query);
            ps.setInt(1, tripId);
            int result = ps.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnUtil.close(ps, conn);
        }
        return false;
    }

    @Override
    public boolean bookTrip(int tripId, int passengerId, String bookingDate) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DBConnUtil.getDBConn("db.properties");
            String query = "INSERT INTO Bookings (TripID, PassengerID, BookingDate, Status) VALUES (?, ?, ?, 'Confirmed')";
            ps = conn.prepareStatement(query);
            ps.setInt(1, tripId);
            ps.setInt(2, passengerId);
            ps.setString(3, bookingDate);
            int result = ps.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnUtil.close(ps, conn);
        }
        return false;
    }

    @Override
    public boolean cancelBooking(int bookingId) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DBConnUtil.getDBConn("db.properties");
            String query = "UPDATE Bookings SET Status='Cancelled' WHERE BookingID=?";
            ps = conn.prepareStatement(query);
            ps.setInt(1, bookingId);
            int result = ps.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnUtil.close(ps, conn);
        }
        return false;
    }




    @Override
    public List<Booking> getBookingsByPassenger(int passengerId) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Booking> bookings = new ArrayList<>();
        try {
            conn = DBConnUtil.getDBConn("db.properties");
            String query = "SELECT * FROM Bookings WHERE PassengerID=?";
            ps = conn.prepareStatement(query);
            ps.setInt(1, passengerId);
            rs = ps.executeQuery();
            while (rs.next()) {
                Booking booking = new Booking();
                booking.setBookingId(rs.getInt("BookingID"));
                booking.setTripId(rs.getInt("TripID"));
                booking.setPassengerId(rs.getInt("PassengerID"));
                booking.setBookingDate(rs.getDate("BookingDate"));
                booking.setStatus(rs.getString("Status"));
                bookings.add(booking);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnUtil.close(rs, ps, conn);
        }
        return bookings;
    }

    @Override
    public List<Booking> getBookingsByTrip(int tripId) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Booking> bookings = new ArrayList<>();
        try {
            conn = DBConnUtil.getDBConn("db.properties");
            String query = "SELECT * FROM Bookings WHERE TripID=?";
            ps = conn.prepareStatement(query);
            ps.setInt(1, tripId);
            rs = ps.executeQuery();
            while (rs.next()) {
                Booking booking = new Booking();
                booking.setBookingId(rs.getInt("BookingID"));
                booking.setTripId(rs.getInt("TripID"));
                booking.setPassengerId(rs.getInt("PassengerID"));
                booking.setBookingDate(rs.getDate("BookingDate"));
                booking.setStatus(rs.getString("Status"));
                bookings.add(booking);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnUtil.close(rs, ps, conn);
        }
        return bookings;
    }


}
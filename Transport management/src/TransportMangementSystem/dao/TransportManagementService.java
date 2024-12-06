package TransportMangementSystem.dao;
import TransportMangementSystem.entity.Vehicle;
import java.util.List;
public interface TransportManagementService {
    boolean addVehicle(Vehicle vehicle);
    boolean updateVehicle(Vehicle vehicle);
    boolean deleteVehicle(int vehicleId);
    boolean scheduleTrip(int vehicleId, int routeId, String departureDate, String arrivalDate);
    boolean cancelTrip(int tripId);
    boolean bookTrip(int tripId, int passengerId, String bookingDate);
    boolean cancelBooking(int bookingId);
    List<?> getBookingsByPassenger(int passengerId);
    List<?> getBookingsByTrip(int tripId);
}

package TransportMangementSystem.Main;

import TransportMangementSystem.dao.TransportManagementServiceImpl;
import TransportMangementSystem.entity.Booking;
import TransportMangementSystem.entity.Passenger;
import TransportMangementSystem.entity.Vehicle;
import TransportMangementSystem.util.DBConnUtil;

import java.util.List;
import java.util.Scanner;

public class MainModule {
    public static void main(String[] args) {
        TransportManagementServiceImpl service = new TransportManagementServiceImpl();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("=== Transport Management System ===");
            System.out.println("1. Add Vehicle");
            System.out.println("2. Schedule Trip");
            System.out.println("3. Book Trip");
            System.out.println("4. Cancel Trip");
            System.out.println("5. Cancel Booking");
            System.out.println("6. Get Bookings by Passenger");
            System.out.println("7. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    // Add Vehicle
                    System.out.print("Enter VehicleID: ");
                    int VehicleID = scanner.nextInt();
                     scanner.nextLine() ;
                    System.out.print("Enter Vehicle Model: ");
                    String model = scanner.nextLine();
                    System.out.print("Enter Vehicle Capacity: ");
                    double capacity = scanner.nextDouble();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter Vehicle Type: ");
                    String type = scanner.nextLine();
                    System.out.print("Enter Vehicle Status: ");
                    String status = scanner.nextLine();

                    Vehicle vehicle = new Vehicle(VehicleID,model, capacity, type, status);
                    if (service.addVehicle(vehicle)) {
                        System.out.println("Vehicle added successfully.");
                    } else {
                        System.out.println("Failed to add vehicle.");
                    }
                    break;

                case 2:
                    // Schedule Trip
                    System.out.print("Enter Vehicle ID: ");
                    int vehicleId = scanner.nextInt();
                    System.out.print("Enter Route ID: ");
                    int routeId = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter Departure Date (yyyy-MM-dd HH:mm:ss): ");
                    String departureDate = scanner.nextLine();
                    System.out.print("Enter Arrival Date (yyyy-MM-dd HH:mm:ss): ");
                    String arrivalDate = scanner.nextLine();

                    if (service.scheduleTrip(vehicleId, routeId, departureDate, arrivalDate)) {
                        System.out.println("Trip scheduled successfully.");
                    } else {
                        System.out.println("Failed to schedule trip.");
                    }
                    break;

                case 3:
                    // Book Trip
                    System.out.print("Enter Trip ID: ");
                    int tripId = scanner.nextInt();
                    System.out.print("Enter Passenger ID: ");
                    int passengerId = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter Booking Date (yyyy-MM-dd HH:mm:ss): ");
                    String bookingDate = scanner.nextLine();

                    if (service.bookTrip(tripId, passengerId, bookingDate)) {
                        System.out.println("Trip booked successfully.");
                    } else {
                        System.out.println("Failed to book trip.");
                    }
                    break;

                case 4:
                    // Cancel Trip
                    System.out.print("Enter Trip ID to cancel: ");
                    int tripIdToCancel = scanner.nextInt();
                    if (service.cancelTrip(tripIdToCancel)) {
                        System.out.println("Trip cancelled successfully.");
                    } else {
                        System.out.println("Failed to cancel trip.");
                    }
                    break;

                case 5:
                    // Cancel Booking
                    System.out.print("Enter Booking ID to cancel: ");
                    int bookingIdToCancel = scanner.nextInt();
                    if (service.cancelBooking(bookingIdToCancel)) {
                        System.out.println("Booking cancelled successfully.");
                    } else {
                        System.out.println("Failed to cancel booking.");
                    }
                    break;

                case 6:
                    // Get Bookings by Passenger
                    System.out.print("Enter Passenger ID: ");
                    int passengerIdToFetch = scanner.nextInt();
                    List<Booking> bookings = service.getBookingsByPassenger(passengerIdToFetch);
                    System.out.println("Bookings for Passenger ID " + passengerIdToFetch + ":");
                    for (Booking booking : bookings) {
                        System.out.println("Booking ID: " + booking.getBookingId() +
                                ", Trip ID: " + booking.getTripId() +
                                ", Booking Date: " + booking.getBookingDate() +
                                ", Status: " + booking.getStatus());
                    }
                    break;

                case 7:
                    // Exit
                    System.out.println("Exiting the system. Goodbye!");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}

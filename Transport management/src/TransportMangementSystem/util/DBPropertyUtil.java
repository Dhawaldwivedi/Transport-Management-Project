package TransportMangementSystem.util;

public class DBPropertyUtil {

    public static String getDBConnectionString(String propertyFileName) {
        return "jdbc:mysql://localhost:3306/TransportManagement";
    }
}

package crud;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AnalyticsDAO {

    // Method to get average rating for an event
    public void getAverageRating(int eventId) {
        String query = "SELECT AVG(rating) AS avg_rating FROM feedback WHERE event_id = ?";

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/your_database_name", "root", "!@#$%)(*&^Cy1234567890");
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, eventId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                double avgRating = resultSet.getDouble("avg_rating");
                System.out.println("Average rating for event " + eventId + ": " + avgRating);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

package crud;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FeedbackDAO {

    private static final String URL = "jdbc:mysql://localhost:3306/Feedback";
    private static final String USER = "root";
    private static final String PASSWORD = "!@#$%)(*&^Cy1234567890";

    // Method to save feedback in the database
    public void saveFeedback( int userId, int surveyId, String feedbackText, Timestamp submittedAt, String comments) {
        String query = "INSERT INTO feedback5 ( user_id, survey_id, feedback_text, submitted_at, comments) VALUES ( ?, ?, ?, ?, ?)";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            //preparedStatement.setInt(1, eventId);
            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, surveyId);
            preparedStatement.setString(3, feedbackText);
            preparedStatement.setTimestamp(4, submittedAt);
            preparedStatement.setString(5, comments);

            preparedStatement.executeUpdate();
            System.out.println("Feedback successfully saved!");

        } catch (SQLException e) {
            System.out.println("Error saving feedback: " + e.getMessage());
        }
    }

    // Method to retrieve all feedback comments as a List
    public List<String> getAllFeedback() {
        List<String> feedbackList = new ArrayList<>();
        String query = "SELECT feedback_text, comments FROM feedback";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                //String feedbackText = resultSet.getString("feedback_text");
                String comments = resultSet.getString("comments");
                feedbackList.add(/*"Name: " + feedbackText +*/" | Comments: " + comments);
            }

        } catch (SQLException e) {
            System.out.println("Error fetching feedback: " + e.getMessage());
        }

        return feedbackList;
    }
}

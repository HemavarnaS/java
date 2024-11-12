package crud;

import java.util.List;
import java.util.Scanner;
import java.sql.Timestamp;
import java.util.Date;

public class FeedbackApp {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        FeedbackDAO feedbackDAO = new FeedbackDAO();

        System.out.println("Welcome to the Event Feedback System!");

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Enter Feedback");
            System.out.println("2. Analyst: Analyze Feedback");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    // Collecting feedback details from the user
                    //System.out.print("Enter Event ID (integer): ");
                   // int eventId = scanner.nextInt();

                    System.out.print("Enter User ID (integer): ");
                    int userId = scanner.nextInt();

                    System.out.print("Enter Survey ID (integer): ");
                    int surveyId = scanner.nextInt();
                    scanner.nextLine(); // Consume newline

                    System.out.print("Enter Name: ");
                    String feedbackText = scanner.nextLine();

                    System.out.print("Enter Comments: ");
                    String comments = scanner.nextLine();

                    // Get the current timestamp
                    Timestamp submittedAt = new Timestamp(new Date().getTime());
                    

                    // Confirm to save feedback
                    System.out.print("Do you want to save this feedback to the database? (yes/no): ");
                    String saveFeedbackChoice = scanner.nextLine();

                    if (saveFeedbackChoice.equalsIgnoreCase("yes")) {
                        // Save feedback in the database
                        feedbackDAO.saveFeedback( userId, surveyId, feedbackText, submittedAt, comments);
                    } else {
                        System.out.println("Feedback not saved.");
                    }
                    break;

                case 2:
                    // Analyst reviews and generates the feedback report
                    System.out.println("Analyst: Analyzing feedback...");
                    generateFeedbackReport(feedbackDAO);
                    break;

                case 3:
                    System.out.println("Exiting the system. Goodbye!");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    // Method to generate and display a feedback report
    public static void generateFeedbackReport(FeedbackDAO feedbackDAO) {
        List<String> feedbackList = feedbackDAO.getAllFeedback();

        if (feedbackList.isEmpty()) {
            System.out.println("No feedback available for analysis.");
        } else {
            System.out.println("\n----- Feedback Report -----");
            for (int i = 0; i < feedbackList.size(); i++) {
                System.out.println((i + 1) + ". " + feedbackList.get(i));
            }
            System.out.println("---------------------------");
        }
    }
}
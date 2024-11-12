package connection;
public class DatabaseConfig {
    public static final String URL = "jdbc:mysql://localhost:3306/Feedback";
    public static final String USER = "root";
    public static final String PASSWORD = "!@#$%)(*&^Cy1234567890";

   
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver loaded successfully.");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}


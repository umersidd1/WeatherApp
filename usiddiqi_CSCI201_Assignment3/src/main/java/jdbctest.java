import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class jdbctest {
    private static Connection conn = null;

//    public static void main(String[] args) {
//        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
//            conn = DriverManager.getConnection("jdbc:mysql://localhost/StudentGrades?user=root&password=Warriors123%29%25");
//
//            printStudents();
//            insert(); 
//            System.out.println(); 
//            printStudents(); 
//
//        } catch (ClassNotFoundException e) {
//            System.out.println("JDBC Driver not found: " + e.getMessage());
//        } catch (SQLException e) {
//            System.out.println("Database error: " + e.getMessage());
//        } finally {
//            try {
//                if (conn != null) conn.close();
//            } catch (SQLException e) {
//                System.out.println("Error closing connection: " + e.getMessage());
//            }
//        }
//    }
//
//    private static void printStudents() throws SQLException {
//        String query = "SELECT * FROM Student";
//
//        try (Statement stmt = conn.createStatement();
//             ResultSet rs = stmt.executeQuery(query)) {
//
//            System.out.printf("%-10s %s%n", "SID", "Name");
//            System.out.println("------------------------");
//
//            while (rs.next()) {
//                System.out.printf("%-10d %s%n", 
//                    rs.getInt("SID"), 
//                    rs.getString("Name"));
//            }
//        }
//    }

    public static int insertUser(String username, String password) throws SQLException, ClassNotFoundException {
    	int userId = -1;
    	Connection conn = null; 
    	Statement stmt = null; 
    	ResultSet rs = null; 
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            conn = DriverManager.getConnection(
                "jdbc:mysql://localhost/WeatherConditions",
                "root",
                "Warriors123)%");
            
            
            stmt = null; 
            rs = null; 
            
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM users WHERE username='" + username + "'");
            if(!rs.next()) {
            	rs.close();
            	stmt.execute("INSERT INTO users (username, password) VALUES ('" + username + "', '" + password + "')");
            	rs = stmt.executeQuery("SELECT LAST_INSERT_ID()");
            	rs.next();
            	userId = rs.getInt(1);
            	
            }
            
            
                
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            throw e;
        }
        finally {
        
			rs.close(); 
        	stmt.close();
        	conn.close(); 
        	
        }
        
        
        return userId;
    }
    
    
    public static String loginCheck(String username, String password) throws SQLException, ClassNotFoundException {
      
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            try (Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost/WeatherConditions", 
                "root", 
                "Warriors123)%"
            )) {
                // Check username
                String userQuery = "SELECT * FROM users WHERE username = ?";
                try (PreparedStatement pstmt = conn.prepareStatement(userQuery)) {
                    pstmt.setString(1, username);
                    try (ResultSet rs = pstmt.executeQuery()) {
                        if (!rs.next()) {
                            return "This user does not exist";
                        }
                    }
                }

                // Check password
                String passQuery = "SELECT * FROM users WHERE password = ?";
                try (PreparedStatement pstmt = conn.prepareStatement(passQuery)) {
                    pstmt.setString(1, password);
                    try (ResultSet rs = pstmt.executeQuery()) {
                        if (!rs.next()) {
                            return "Incorrect password";
                        }
                    }
                }

                return "Successful login!";
            }
        } catch (SQLException | ClassNotFoundException e) {
            // Log the actual exception
            e.printStackTrace();
            throw e;
        }
    }
    
    
    public static int getID(String username, String password) throws SQLException, ClassNotFoundException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            try (Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost/WeatherConditions",
                "root",
                "Warriors123)%"
            )) {
                // Query to get userID based on username and password
                String query = "SELECT user_id FROM users WHERE username = ? AND password = ?";
                
                try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                    pstmt.setString(1, username);
                    pstmt.setString(2, password);
                    
                    try (ResultSet rs = pstmt.executeQuery()) {
                        if (rs.next()) {
                            // Return the userID if a matching record is found
                            return rs.getInt("user_id");
                        } else {
                            // Return -1 or throw an exception if no matching user is found
                            return -1;
                        }
                    }
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            throw e;
        }
    }
    
    
    
}
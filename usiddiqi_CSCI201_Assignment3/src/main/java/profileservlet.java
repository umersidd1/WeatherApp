import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.sql.*;
import java.util.*;
import com.google.gson.Gson;

@WebServlet("/profileservlet")
public class profileservlet extends HttpServlet {
    private static final long serialVersionUID = 1L;


    private static final String DB_URL = "jdbc:mysql://localhost/WeatherConditions";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "Warriors123)%";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        

        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "GET");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type");

        PrintWriter out = response.getWriter();
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("MySQL JDBC Driver Loaded Successfully");
        } catch (ClassNotFoundException e) {
            System.err.println("MySQL JDBC Driver Not Found");
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            out.println("JDBC Driver Error: " + e.getMessage());
            return;
        }

        List<SearchHistoryItem> searchHistory = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            System.out.println("Database Connection Established");

           
            try (Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery("SELECT 1")) {
                if (rs.next()) {
                    System.out.println("Database Connection Test Successful");
                }
            }

            
            
            String query = "SELECT search_query FROM searches WHERE user_id = 1 ORDER BY timestamp DESC";

            try (PreparedStatement pstmt = conn.prepareStatement(query);
                 ResultSet rs = pstmt.executeQuery()) {

                while (rs.next()) {
                    SearchHistoryItem item = new SearchHistoryItem(rs.getString("search_query"));
                    searchHistory.add(item);
                }

                System.out.println("Fetched " + searchHistory.size() + " search history items");
            }

         
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");

            Gson gson = new Gson();
            String jsonResponse = gson.toJson(searchHistory);

            out.print(jsonResponse);
            out.flush();

        } catch (SQLException e) {
            System.err.println("Database Error:");
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            out.println("Database Error: " + e.getMessage());
        }
    }

 
    class SearchHistoryItem {
        private String search;
       

        public SearchHistoryItem(String search) {
            this.search = search;
            
        }

        public String getSearch() { return search; }
       
    }
}
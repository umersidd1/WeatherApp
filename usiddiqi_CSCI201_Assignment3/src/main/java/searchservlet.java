import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/searchservlet")
public class searchservlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final String API_KEY = "c317b3cc4d2234547b82e01c9d1f6989";
    
    protected void service(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String locationMode = request.getParameter("location");
        
        if ("latitude".equals(locationMode)) {
            String lat = request.getParameter("name");
            String lon = request.getParameter("longitude");
            
            if (lat != null && lon != null) {
                try {
                    String latURL = "https://api.openweathermap.org/data/2.5/weather" +
                            "?lat=" + lat +
                            "&lon=" + lon + 
                            "&appid=" + API_KEY;
                    URL url = new URL(latURL);
                    
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
        
                    int responseCode = connection.getResponseCode();
                    if (responseCode == HttpURLConnection.HTTP_OK) {
                        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                        StringBuilder apiResponse = new StringBuilder();
                        String inputLine;
                        while ((inputLine = in.readLine()) != null) {
                            apiResponse.append(inputLine);
                        }
                        in.close();
        
                        response.setContentType("application/json");
                        response.setCharacterEncoding("UTF-8");
        
                        PrintWriter out = response.getWriter();
                        out.print(apiResponse.toString());
                        out.flush();
                    } else {
                        response.setStatus(HttpServletResponse.SC_BAD_GATEWAY);
                        PrintWriter out = response.getWriter();
                        out.print("{\"error\": \"Failed to fetch weather data\", \"responseCode\": " + responseCode + "}");
                        out.flush();
                    }
                } catch (Exception e) {
                    response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                    PrintWriter out = response.getWriter();
                    out.print("{\"error\": \"Internal server error\", \"message\": \"" + e.getMessage() + "\"}");
                    out.flush();
                }
            }
        } else if ("city".equals(locationMode)) {
            String city = request.getParameter("name");
            
            if (city != null) {
                try {
                    // URL encode the city name to handle spaces and special characters
                    String encodedCity = URLEncoder.encode(city, "UTF-8");
                    
                    // Construct the city API call with the encoded city name
                    String cityURL = "https://api.openweathermap.org/data/2.5/find?q=" + encodedCity + "&limit=5&appid=" + API_KEY;
                    URL url = new URL(cityURL);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
        
                    int responseCode = connection.getResponseCode();
                    if (responseCode == HttpURLConnection.HTTP_OK) {
                        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                        StringBuilder apiResponse = new StringBuilder();
                        String inputLine;
                        while ((inputLine = in.readLine()) != null) {
                            apiResponse.append(inputLine);
                        }
                        in.close();
        
                        response.setContentType("application/json");
                        response.setCharacterEncoding("UTF-8");
        
                        PrintWriter out = response.getWriter();
                        out.print(apiResponse.toString());
                        out.flush();
                    } else {
                        response.setStatus(HttpServletResponse.SC_BAD_GATEWAY);
                        PrintWriter out = response.getWriter();
                        out.print("{\"error\": \"Failed to fetch weather data\", \"responseCode\": " + responseCode + "}");
                        out.flush();
                    }
                } catch (Exception e) {
                    response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                    PrintWriter out = response.getWriter();
                    out.print("{\"error\": \"Internal server error\", \"message\": \"" + e.getMessage() + "\"}");
                    out.flush();
                }
            }
        }
    }
}

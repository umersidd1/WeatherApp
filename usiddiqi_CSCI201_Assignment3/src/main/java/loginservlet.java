import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



@WebServlet("/loginservlet")
public class loginservlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String responseText = "";
        Integer userId = 0;

        String check = " ";
        try {
            check = jdbctest.loginCheck(username, password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        if(check.equals("This user does not exist")) {
            responseText = "This user does not exist";
        }
        else if(check.equals("Incorrect password")) {
            responseText = "Incorrect password";
        }
        else {
            try {
                userId = jdbctest.getID(username, password);
                responseText = userId.toString();
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
        }


        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");


        PrintWriter out = response.getWriter();
        out.print(responseText);
        out.flush();
    }
}
    	
    	
    	
    	
    	
    	
    	
    	
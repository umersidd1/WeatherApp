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

@WebServlet("/registerservlet")
public class registerservlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    
    protected void service(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
    	
    	Integer userId = 0; 
    	
    	String username = request.getParameter("username"); 
    	String password = request.getParameter("password");
    	String confirm = request.getParameter("confirm");
    	String responseText = "";
    	
    	if(!password.equals(confirm)) {
    		responseText = "password mismatch";
    	}
    	else {
	    	try {
				userId = jdbctest.insertUser(username, password);
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	
	    	if (userId == -1) {
	    		responseText = "username taken";
	    	}
	    	else if (userId != 0) {
	    		responseText = userId.toString();
	    	}
    	}
    	
    	
    	
    
    	
    	
    	
    	response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");

      
        PrintWriter out = response.getWriter();
        out.print(responseText);
        out.flush();
    	
    }
}
    	
    	
    	
    	
    	
    	
    	
    	
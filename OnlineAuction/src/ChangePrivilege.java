

import java.io.IOException;
import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.sql.*;

/**
 * Servlet implementation class ChangePrivilege
 */
@WebServlet("/ChangePrivilege")
public class ChangePrivilege extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at: ").append(request.getContextPath());
		
		try{
		Connection conn = null;
		PrintWriter out = response.getWriter();  
		HttpSession session = request.getSession(); 
		int id = (Integer) (session.getAttribute("id"));
		String type = request.getParameter("usertype");
		Class.forName(ConnectionConstants.driver);
		conn = DriverManager  
                .getConnection(ConnectionConstants.url, ConnectionConstants.userName, 
                		ConnectionConstants.dbpassword); 
		String query = "update user set type = ? where userid =?";
		PreparedStatement ps = conn.prepareStatement(query);
		ps.setString(1, type);
		ps.setInt(2, id);
		ps.executeUpdate();	
		out.println("Privilege successfully Updated");
		ps.close();
		conn.close();
		}catch (SQLException e) {  
            e.printStackTrace();  
        } catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
	}

}


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class UpdatePhoneNumber
 */
@WebServlet("/UpdatePhoneNumber")
public class UpdatePhoneNumber extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String phone = request.getParameter("phone");
		if (phone.matches("[0-9]+") && phone.length() == 10)
		{
			Double mobile=Double.parseDouble(phone);
			 HttpSession session = request.getSession(); 
		     int uid = (Integer)session.getAttribute("id");        	
			 try {
				Class.forName(ConnectionConstants.driver);
				
			 Connection conn = DriverManager  
	                .getConnection(ConnectionConstants.url, ConnectionConstants.userName, 
	                		ConnectionConstants.dbpassword);
			 
			    String query = "update accountdetails set mobile=? where userid =?";
			    PreparedStatement ps = conn.prepareStatement(query);
			    ps.setDouble(1, mobile);
			    ps.setInt(2, uid);
			   ps.executeUpdate();
			   out.println("Phone number updated successfully");
			    ps.close();
			    conn.close();	
			 } catch (ClassNotFoundException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
		}
		else
		{
			out.print("<center><p style=\"color:red\">Please enter a valid phone number</p></center>");    
          //  RequestDispatcher rd=request.getRequestDispatcher("UpdateMobile.jsp");    
          //  rd.include(request,response); 
		}
		
	}
	}
	



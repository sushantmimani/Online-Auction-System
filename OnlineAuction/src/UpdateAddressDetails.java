

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class UpdateAddressDetails
 */
@WebServlet("/UpdateAddressDetails")
public class UpdateAddressDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int addrnum = 0;
		PrintWriter out = response.getWriter();
		String street=request.getParameter("streetname");
        String addressline1=request.getParameter("addLine1");
        String addressline2=request.getParameter("addLine2");
        String city=request.getParameter("city");
        String accept = request.getParameter("accept");
        int zipcode=Integer.parseInt(request.getParameter("zipcode"));
        String country=request.getParameter("country");
        String state=request.getParameter("state");
        HttpSession session = request.getSession(); 
        int uid = (Integer)session.getAttribute("id"); 
       
		try {
			addrnum = getaddrnum(uid);
		
        //out.print(addrnum);}
		
			Class.forName(ConnectionConstants.driver);		
		    Connection conn = DriverManager  
                .getConnection(ConnectionConstants.url, ConnectionConstants.userName, 
                		ConnectionConstants.dbpassword);

		    String query = "update address set street=? , addressline1=?, addressline2=?,"
		    		+ "city=?, state=?, zip=?, country=? where addressnumber =?";
		    PreparedStatement ps = conn.prepareStatement(query);
		    ps.setString(1, street);
		    ps.setString(2, addressline1);
		    ps.setString(3, addressline2);
		    ps.setString(4, city);
		    ps.setString(5, state);
		    ps.setInt(6, zipcode);
		    ps.setString(7, country);
		    ps.setInt(8, addrnum);
		   ps.executeUpdate(); 
		   if(accept.equals("Accept")){
			   request.setAttribute("auctionid", request.getParameter("auctionid"));
			   request.setAttribute("buyerid", (Integer)session.getAttribute("id"));
			   response.setContentType("text/html"); 
			   RequestDispatcher rd = request.getRequestDispatcher("EndOfAuction");
			   rd.forward(request,response);
			   
		   out.println("Bid Accepted");
		   } else {
			   out.println("Address updated successfully");
		   }
		    ps.close();
		    conn.close();		    		
	}catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}

	public static int getaddrnum(int id) throws SQLException, ClassNotFoundException {
		int addrnum = 0;
		Class.forName(ConnectionConstants.driver);
		Connection conn = DriverManager  
                .getConnection(ConnectionConstants.url, ConnectionConstants.userName, 
                		ConnectionConstants.dbpassword);
		String query = "select addressnumber from addressmaster where userid=?";
		PreparedStatement ps = conn.prepareStatement(query);
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();	
		if (rs.next())
		{
			addrnum = rs.getInt(1);
		}
		
		ps.close();
		conn.close();
		return addrnum;
}
	public static void processbid(HttpServletRequest request, HttpServletResponse response) throws SQLException, ClassNotFoundException {
		
}
}
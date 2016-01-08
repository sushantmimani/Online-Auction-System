

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class GetCurrentDetails
 */
@WebServlet("/GetCurrentDetails")
public class GetCurrentDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetCurrentDetails() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			HttpSession session = request.getSession();
			int userid = (Integer) session.getAttribute("id");
			int zipcode =0;
			String street=null;
	        String addressline1=null;
	        String addressline2=null;
	        String city=null;
	        String country=null;
	        String state=null;
	        String cardname=null;
	        String cardnum = null;
			Class.forName(ConnectionConstants.driver);
			Connection conn = DriverManager  
		            .getConnection(ConnectionConstants.url, ConnectionConstants.userName, 
		            		ConnectionConstants.dbpassword);
			
			String query = "select mobile from accountdetails where userid=?";
			String query1 = "select * from creditcarddetails where userid=?";
			String query2 = "select a.* from address a, addressmaster b where b.userid = ? and a.addressnumber = b.addressnumber";

			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, userid);
			PreparedStatement ps1 = conn.prepareStatement(query1);
			ps1.setInt(1, userid);
			PreparedStatement ps2 = conn.prepareStatement(query2);
			ps2.setInt(1, userid);
			ResultSet rs = ps.executeQuery();
			ResultSet rs1 = ps1.executeQuery();
			ResultSet rs2 = ps2.executeQuery();
			if (rs.next()) {
		    	String amobile = rs.getString(1);
			    response.setContentType("TEXT/HTML");
			    request.setAttribute("mobile", amobile);
		    }
			if (rs1.next()) {
				cardname = rs1.getString(2);
				cardnum = rs1.getString(3);
				response.setContentType("TEXT/HTML");
				request.setAttribute("cardname", cardname);
				request.setAttribute("cardnum", cardnum);
		    	
		    }
			if (rs2.next()) {
				street = rs2.getString(2);
		    	addressline1 = rs2.getString(3);
		    	addressline2 = rs2.getString(4);
		    	city = rs2.getString(5);
		    	state = rs2.getString(6);
		    	zipcode = rs2.getInt(7);
		    	country = rs2.getString(8);
		    	response.setContentType("TEXT/HTML");
			    request.setAttribute("street", street);
			    request.setAttribute("addressline1", addressline1);
			    request.setAttribute("addressline2", addressline2);
			    request.setAttribute("city", city);
			    request.setAttribute("zipcode", zipcode);
			    request.setAttribute("country", country);
			    request.setAttribute("state", state);
		    }
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	    
	}

}

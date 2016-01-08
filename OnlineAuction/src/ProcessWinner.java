

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.*;

/**
 * Servlet implementation class ProcessWinner
 */
@WebServlet("/ProcessWinner")
public class ProcessWinner extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		float bidvalue = 0;
		float currentbalance = 0;
		float currentbalance2= 0;
		int auctionid = Integer.parseInt(request.getParameter("auctionid"));
		int uid = (Integer)session.getAttribute("id");
		String query = "select bidvalue from bids where buyerid =? and auctionid=?";
		try {
		Class.forName(ConnectionConstants.driver);		
	    Connection conn = DriverManager  
            .getConnection(ConnectionConstants.url, ConnectionConstants.userName, 
            		ConnectionConstants.dbpassword);
	    String getcurrentbalance= "select accountbalance from accountdetails where userid = ?";
		PreparedStatement psgetcurrentbalance = conn.prepareStatement(getcurrentbalance);
		psgetcurrentbalance.setInt(1, uid);
		ResultSet rscurrentbalance= psgetcurrentbalance.executeQuery();
		if(rscurrentbalance.next()){
			currentbalance = rscurrentbalance.getFloat(1);
		}
		PreparedStatement ps = conn.prepareStatement(query);
		ps.setInt(1, uid);
		ps.setInt(2, auctionid);
		ResultSet rs = ps.executeQuery();
		if(rs.next()){
			bidvalue = rs.getFloat(1);
			float dbidvalue = (float) (bidvalue * 0.9);
			float returnbid = currentbalance + dbidvalue;
			String query1 = "update accountdetails set accountbalance = ? where userid = ?";
			PreparedStatement ps1 = conn.prepareStatement(query1);	
			ps1.setDouble(1, returnbid);
			ps1.setInt(2, uid);
		    ps1.executeUpdate();
			String getcurrentbalance2= "select accountbalance from accountdetails where userid = "
					+ "(select sellerid from auction where auctionid = ?)";
			PreparedStatement psgetcurrentbalance2 = conn.prepareStatement(getcurrentbalance2);
			psgetcurrentbalance2.setInt(1, auctionid);
			ResultSet rscurrentbalance2= psgetcurrentbalance2.executeQuery();
			if(rscurrentbalance2.next()){
				currentbalance2 = rscurrentbalance2.getFloat(1);
				}
			    float a = (float)(0.1 * bidvalue);
				float sellergain = a + currentbalance2;
				PreparedStatement ps2 = conn.prepareStatement("update accountdetails set accountbalance = ? "
						+ "where userid = (select sellerid from auction where auctionid = ?)");
				ps2.setFloat(1, sellergain);
				ps2.setInt(2, auctionid);
				ps2.executeUpdate();
				out.println("Bid amount refunded to your account after deducting 10% for declining");	
				String delquery = "delete from bids where buyerid = ? and auctionid =?";
				PreparedStatement psdel = conn.prepareStatement(delquery);
				psdel.setInt(1, uid);
				psdel.setInt(2, auctionid);
				psdel.executeUpdate();
				ResultSet rrs = ps.executeQuery();
				if(!rrs.next())
					 out.println("/nNO more bids available, Email Sent to the Seller");
				else out.println("/n Email sent to 2nd highest bidder");
		} 		
		
		
		}catch (Exception e) {
		      e.printStackTrace();
		 }
		}
	}


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class EndOfAuction
 */
@WebServlet("/EndOfAuction")
public class EndOfAuction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		String aid = (request.getAttribute("auctionid").toString());
		int auctionid = Integer.parseInt(aid);
		String bid = (request.getAttribute("buyerid").toString());
		int buyerid = Integer.parseInt(bid);
		float currentbalance = 0;
		float bidvalue = 0;
		try {
			// Credit the bid amount to seller account balance
			Class.forName(ConnectionConstants.driver);		
		    Connection conn = DriverManager  
	            .getConnection(ConnectionConstants.url, ConnectionConstants.userName, 
	            		ConnectionConstants.dbpassword);
		String getcurrentbalance= "select accountbalance from accountdetails where userid = "
				+ "(select sellerid from auction where auctionid = ?)";
		PreparedStatement ps1 = conn.prepareStatement(getcurrentbalance);
		ps1.setInt(1, auctionid);
		ResultSet rs1= ps1.executeQuery();
		if(rs1.next()){
			currentbalance = rs1.getFloat(1);
		} 
		String bidquery = "select bidvalue from bids where buyerid =? and auctionid=?";
		PreparedStatement ps2 = conn.prepareStatement(bidquery);
		ps2.setInt(1, buyerid);
		ps2.setInt(2, auctionid);
		ResultSet rs2= ps2.executeQuery();
		if(rs2.next()){
			bidvalue = rs2.getFloat(1);
			float credit = currentbalance + bidvalue;
			String updatemoney = "update accountdetails set accountbalance = ? "
					+ "where userid = (select sellerid from auction where auctionid = ?)";
			PreparedStatement ps3 = conn.prepareStatement(updatemoney);
			ps3.setFloat(1, credit);
			ps3.setInt(2, auctionid);
			ps3.executeUpdate();
		} 	
		//Move the auction to auction history
		String insert = "insert into auctionhistory values (?,?,(select sellerid from auction where auctionid = ?)"
				+ ",?,(select enddate from auction where auctionid = ?))";
		
		/*Insert into auctionHistory (auctionID, BuyerID, sellerID, winningBid,Auctiondate)
		select a.auctionID,w.Buyerid,a.sellerid,
		w.bidvalue,current_date()  from auction a, Todaywinner w where a.auctionID=w.auctionid;*/
		
		PreparedStatement ps4 = conn.prepareStatement(insert);
		ps4.setInt(1, auctionid);
		ps4.setInt(2, buyerid);
		ps4.setInt(3, auctionid);
		ps4.setFloat(4, bidvalue);
		ps4.setInt(5, auctionid);
		ps4.executeUpdate();
		
		//Delete auction from auction table
		String deletebids = "delete from bids where auctionid = ?";
		PreparedStatement ps5 = conn.prepareStatement(deletebids);
		ps5.setInt(1, auctionid);
		ps5.executeUpdate();
		
		String deleteauc = "delete from prodtype where prodid = (select prodid from auction where auctionid=?)";
		PreparedStatement ps6 = conn.prepareStatement(deleteauc);
		ps6.setInt(1, auctionid);
		ps6.executeUpdate();
		
		//Delete bids of that auction from bids table
		   request.setAttribute("auctionid", auctionid);
		   request.setAttribute("buyerid", buyerid);
		   response.setContentType("text/html"); 
		   RequestDispatcher rd = request.getRequestDispatcher("Rating.jsp");
		   rd.forward(request,response);
		} catch (Exception e) {
		      e.printStackTrace();
		 } 
	}

}

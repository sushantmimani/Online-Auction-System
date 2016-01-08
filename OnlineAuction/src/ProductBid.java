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
 * Servlet implementation class ProductBid
 */
@WebServlet("/ProductBid")
public class ProductBid extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductBid() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession(); 
		response.setContentType("text/html");
	 	float bidvalue = Float.parseFloat(request.getParameter("bidvalue"));
	 	float highestbid = Float.parseFloat(request.getParameter("highestbid"));
	 	float minprice = Float.parseFloat(request.getParameter("minprice"));
		int prodid = Integer.parseInt(request.getParameter("prodid"));
		int userid = (Integer) session.getAttribute("id");
		
		/*out.println(bidvalue);
		out.println(prodid);
		out.println(userid);
		out.print(minprice);
	
	}}*/
		Connection conn = null;  
        PreparedStatement pst = null;  
        ResultSet rs = null; 
        float currentbalance = 0;
        int auctionid = 0;
        String url = "jdbc:mysql://localhost:3306/";  
        String dbName = "form";  
        String driver = "com.mysql.jdbc.Driver";  
        String userName = "root";  
        String password = "nainital";  
        try {
		Class.forName(driver);
        conn= DriverManager.getConnection(url+dbName,userName,password);
		
        // get current balance of the buyer
        
		String getcurrentbalance= "select accountbalance from accountdetails where userid = ?";
		PreparedStatement psgetcurrentbalance = conn.prepareStatement(getcurrentbalance);
		psgetcurrentbalance.setInt(1, userid);
		ResultSet rscurrentbalance= psgetcurrentbalance.executeQuery();
		//currentbalance=rscurrentbalance.next(1);
		if(rscurrentbalance.next()){
			currentbalance = rscurrentbalance.getFloat(1);
		}
        
        String query1 = "select bidvalue from bids where buyerid=? and prodid=?";
		PreparedStatement ps1 = conn.prepareStatement(query1);
		ps1.setInt(1,userid);
		ps1.setInt(2,prodid);
		ResultSet rs1 = ps1.executeQuery();
		// check if any past bids of same product is there
		if(rs1.next()) {	
			float pastbid;
			pastbid=rs1.getFloat(1);
					
			// check whether he is eligible enough to bid
			
			if(bidvalue>highestbid)
			{
				if ((currentbalance + pastbid)>= bidvalue && (currentbalance + pastbid - bidvalue>=minprice))
						// update the accountdetails
                    {
					float xx = currentbalance + pastbid - bidvalue;
					String query3 ="update accountdetails"
							+ " set accountbalance = ?"
							+ "where userid = ?";
					PreparedStatement ps3= conn.prepareStatement(query3);
					ps3.setInt(2,userid);
					ps3.setFloat(1, xx);
					ps3.executeUpdate();
					
				String query4= "update bids set bidvalue = ? where buyerid=? and prodid=?";	
					PreparedStatement ps4 = conn.prepareStatement(query4);
					ps4.setFloat(1, bidvalue);
					ps4.setInt(2, userid);	
					ps4.setInt(3, prodid);
					ps4.executeUpdate();
					RequestDispatcher rd=request.getRequestDispatcher("BidPlaced.jsp");    
	                rd.forward(request,response);
					}				
				else{
					out.println("Error in placing bid. Please check account balance and/or minimum bid");
					}
			}
			else
			{
				out.println("Error in placing bid. Please check account balance and/or minimum bid");
			
			}
		}						
		
			else{
				if((currentbalance > minprice) && (currentbalance  - bidvalue>=0))
				{
				if(((highestbid==0) && (bidvalue>minprice)) || ((highestbid>0) && (bidvalue>highestbid)))
				{									
				String query5 = "select auctionid from auction where prodid = ?";
				PreparedStatement ps2 = conn.prepareStatement(query5);
				ps2.setInt(1, prodid);
				ResultSet rs2 = ps2.executeQuery();
				if(rs2.next()){
					 auctionid = rs2.getInt(1);
				}
				
				if(currentbalance > highestbid)
				{
					String query6="Insert into bids (auctionid,prodid,buyerid,bidvalue) "
							+ "value"
							+ "(?,?,?,?)";	
					PreparedStatement ps3 = conn.prepareStatement(query6);
					ps3.setInt(1, auctionid);
					ps3.setInt(2,prodid);
					ps3.setInt(3, userid);
					ps3.setFloat(4, bidvalue);
					ps3.executeUpdate();
					
					float yy = currentbalance - bidvalue;
					String query3 ="update accountdetails"
							+ " set accountbalance = ?"
							+ "where userid = ?";
					PreparedStatement psr= conn.prepareStatement(query3);
					psr.setInt(2,userid);
					psr.setFloat(1, yy);
					psr.executeUpdate();
					RequestDispatcher rd=request.getRequestDispatcher("BidPlaced.jsp");    
	                rd.forward(request,response);
					
			} else{
			     out.println("Error in placing bid. Please check account balance and/or minimum bid");
				}			
				
			}}else {
				out.println("Error in placing bid. Please check account balance and/or minimum bid");
				}
					
			}
	} 
	catch (Exception e) {
	      e.printStackTrace();
	 }finally {  
       if (conn != null) {  
           try {  
               conn.close();  
           } catch (SQLException e) {  
               e.printStackTrace();  
           }  
       }  
       if (pst != null) {  
           try {  
               pst.close();  
           } catch (SQLException e) {  
               e.printStackTrace();  
           }  
       }  
       if (rs != null) {  
           try {  
               rs.close();  
           } catch (SQLException e) {  
               e.printStackTrace();  
           }  
       }  
   } 
	}
}
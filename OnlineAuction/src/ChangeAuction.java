

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

/**
 * Servlet implementation class ChangeAuction
 */
@WebServlet("/ChangeAuction")
public class ChangeAuction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static java.sql.Date sDate;
	static java.sql.Date eDate;
       
    	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 response.setContentType("text/html");  
		PrintWriter out = response.getWriter(); 
		    String url = "jdbc:mysql://localhost:3306/";  
	        String dbName = "form";  
	        String driver = "com.mysql.jdbc.Driver";  
	        String userName = "root";  
	        String dbpassword = "nainital"; 
	        HttpSession session = 	request.getSession(false);
	  //      String sName = (String)session.getAttribute("name");
	        int sellerid = (Integer)session.getAttribute("id");
	        int auctionid = Integer.parseInt(request.getParameter("auctionid"));
	        int prodid    = Integer.parseInt(request.getParameter("prodid")); 
	        String prodName=request.getParameter("prodName");    
	        String prodDesc=request.getParameter("prodDesc"); 
	        String minPrice=request.getParameter("minPrice"); 
	        String startDate = request.getParameter("startDate");
	        String endDate = request.getParameter("endDate");
	        int active = Integer.parseInt(request.getParameter("active"));
	        float price = Float.parseFloat(minPrice);	   	                
	      
	      try {
			sDate = convertJavaDateToSqlDate(startDate);
			eDate = convertJavaDateToSqlDate(endDate);
				} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}     

			             
	        
		       String query = "update prodtype set name = ?, description  = ?, minprice  = ?"
		        		+"where prodid = ?";
		       String query1 = "update auction set auctionid = ?, sellerid = ?, prodid = ?,startdate = ?,enddate = ?,active = ? "
		       		+ "where auctionid = ?";     		
		       		   try {
		        	
					Class.forName(driver);
					Connection conn=DriverManager.getConnection(url + dbName, userName, dbpassword);	
					PreparedStatement pst=conn.prepareStatement(query);
					pst.setString(1,prodName);
					pst.setString(2,prodDesc);
					pst.setFloat(3,price);	
					pst.setInt(4, prodid);
					pst.executeUpdate();
					
					PreparedStatement pst1 = conn.prepareStatement(query1);
					pst1.setInt(1, auctionid);
					pst1.setInt(2, sellerid);
					pst1.setInt(3, prodid);
					pst1.setDate(4, sDate);
					pst1.setDate(5, eDate);
					pst1.setInt(6, active);
					pst1.setInt(7, auctionid);
					pst1.executeUpdate();
					
					
					
					conn.close();		
		        
		        } catch (ClassNotFoundException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		       		        
		       		    out.println("Auction susccessfully Updated!!");
			}
	
	public java.sql.Date convertJavaDateToSqlDate(String date) throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		 java.util.Date sDate = formatter.parse(date);
	    return (new java.sql.Date(sDate.getTime()));
	}

}

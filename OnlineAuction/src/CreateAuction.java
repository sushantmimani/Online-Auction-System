import java.io.*;
import java.sql.*;
import java.text.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;



/**
 * Servlet implementation class CreateAuction
 */
@WebServlet("/CreateAuction")
@MultipartConfig(maxFileSize = 16777216)
public class CreateAuction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	static java.sql.Date sDate;
	static java.sql.Date eDate;
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		    PrintWriter out = response.getWriter();    
		    response.setContentType("text/html");  
		    String url = "jdbc:mysql://localhost:3306/";  
 	        String dbName = "form";  
 	        String driver = "com.mysql.jdbc.Driver";  
 	        String userName = "root";  
 	        String dbpassword = "nainital"; 
	        HttpSession session = 	request.getSession(false);
	        String sName = (String)session.getAttribute("name");
	        String prodName=request.getParameter("prodName");    
	        String prodDesc=request.getParameter("prodDesc"); 
	        String minPrice=request.getParameter("minPrice"); 
	        String startDate = request.getParameter("startDate");
	        String endDate = request.getParameter("endDate");
	        float price = Float.parseFloat(minPrice);	   
	        InputStream inputStream = null;  
	        Part filePart = request.getPart("photo");
	        if (filePart != null) {
	            System.out.println(filePart.getName());
	            System.out.println(filePart.getSize());
	            System.out.println(filePart.getContentType());
	            inputStream = filePart.getInputStream();
	        }
	        
	      
	      try {
			sDate = convertJavaDateToSqlDate(startDate);
			eDate = convertJavaDateToSqlDate(endDate);
				} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}     
	      Boolean mStartDate = ValidateInput.validateStartDateOfAuction(sDate);
          Boolean mEndDate = ValidateInput.validateEndDateOfAuction(sDate,eDate);
          if (!mStartDate){
         	   out.print("<center><p style=\"color:red\">Not a valid Start Date!</p></center>");    
                RequestDispatcher rd=request.getRequestDispatcher("CreateAuction.jsp");    
                rd.include(request,response); 
            }
            else
            {           
 	           if (!mEndDate){
 	        	   out.print("<center><p style=\"color:red\">Not a valid End Date!</p></center>");    
 	               RequestDispatcher rd=request.getRequestDispatcher("CreateAuction.jsp");    
 	               rd.include(request,response); 
 	           }
 	           else{          
			             
	        
		       String query = "insert into prodtype(name, description, minprice, image)"
		        		+"values (?,?,?,?)";
		       String query1 = "insert into auction (sellerid,prodid,startdate,enddate,active) "
		       		+ "values ((select userid from user where username=?),"
		       		+ "(select max(prodid) from prodtype),"
		       		+ "?,?,0)";
		       		
		       		        try {
		        	
					Class.forName(driver);
					Connection conn=DriverManager.getConnection(url + dbName, userName, dbpassword);	
					PreparedStatement pst=conn.prepareStatement(query);
					pst.setString(1,prodName);
					pst.setString(2,prodDesc);
					pst.setFloat(3,price);
					pst.setBlob(4, inputStream);				
					pst.executeUpdate();
					
					/*String query1 = "insert into auction (sellerid,prodid,startdate,enddate,active) "
				       		+ "values ((select userid from user where username=?),"
				       		+ "(select max(prodid) from prodtype),"
				       		+ "?,?,0)";*/
					
					PreparedStatement pst1 = conn.prepareStatement(query1);
					pst1.setString(1, sName);
					pst1.setDate(2, sDate);
					pst1.setDate(3, eDate);
					pst1.executeUpdate();
					
					
					
					conn.close();		
		        
		        } catch (ClassNotFoundException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		       		        
		       		     RequestDispatcher rd=request.getRequestDispatcher("AuctionCreated.jsp");    
			                rd.forward(request,response);  
			} } }
	
	public java.sql.Date convertJavaDateToSqlDate(String date) throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		 java.util.Date sDate = formatter.parse(date);
	    return (new java.sql.Date(sDate.getTime()));
	}
}
	    	
	        
	        
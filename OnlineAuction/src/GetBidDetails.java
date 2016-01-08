import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class GetBidDetails
 */
@WebServlet("/GetBidDetails")
public class GetBidDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetBidDetails() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		String Id = request.getParameter("Id");
			Connection conn = null;  
	        PreparedStatement pst = null;  
	        ResultSet rs = null; 
	        String url = "jdbc:mysql://localhost:3306/";  
	        String dbName = "form";  
	        String driver = "com.mysql.jdbc.Driver";  
	        String userName = "root";  
	        String password = "nainital";  
	        float minprice = 0;
	        Date sDate;
	        Date eDate;

		 try {
			 Class.forName(driver);
	            conn = DriverManager  
	                    .getConnection(url + dbName, userName, password);
	            pst = conn.prepareStatement("select minprice from prodtype where prodid = ?");  
			    pst.setInt(1, Integer.parseInt(Id));
			    rs = pst.executeQuery();
			    if (rs.next()) {
			    	minprice = rs.getFloat(1);
				    response.setContentType("TEXT/HTML");
				    request.setAttribute("minprice", minprice);
			    }
			    
			    String query1 = "select max(bidvalue) from bids where prodid = ?";
			    PreparedStatement pst1 = conn.prepareStatement(query1);  
			    pst1.setInt(1, Integer.parseInt(Id));
			    ResultSet rs1 = pst1.executeQuery();
			    if (rs1.next()) {
			    	float highestbid = rs1.getFloat(1);
				    response.setContentType("TEXT/HTML");
				    request.setAttribute("highestbid", highestbid);}
			    
			    String query2 = "select count(*) from bids where prodid = ?";
			    PreparedStatement pst2 = conn.prepareStatement(query2);  
			    pst2.setInt(1, Integer.parseInt(Id));
			    ResultSet rs2 = pst2.executeQuery();
			    if (rs2.next()) {
			    	int totalbids = rs2.getInt(1);
				    response.setContentType("TEXT/HTML");
				    request.setAttribute("totalbids", totalbids);
				    
				    String query3 = "select startdate, enddate from auction where prodid = ?";
				    PreparedStatement pst3 = conn.prepareStatement(query3);  
				    pst3.setInt(1, Integer.parseInt(Id));
				    ResultSet rs3 = pst3.executeQuery();
				    if (rs3.next()) {
				    	 sDate=rs3.getDate(1);
				    	 eDate = rs3.getDate(2);
				    	
				    	response.setContentType("TEXT/HTML");
					    request.setAttribute("sdate", sDate);
					    request.setAttribute("edate", eDate);
				    
			    }
			 
			    }	    
	} catch (Exception e) {
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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

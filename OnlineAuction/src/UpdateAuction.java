

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

/**
 * Servlet implementation class UpdateAuction
 */
@WebServlet("/UpdateAuction")
public class UpdateAuction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateAuction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
			if (request.getParameter("active").equals("Active"))
			{
				request.setAttribute("active", 1);
			}
			else {
				request.setAttribute("active", 0);
			}
			response.setContentType("text/html");  
	        request.setAttribute("auctionid", request.getParameter("auctionid"));
	        request.setAttribute("prodid", request.getParameter("prodid"));
	        request.setAttribute("name", request.getParameter("name"));
	        request.setAttribute("minprice", request.getParameter("minprice"));
	        request.setAttribute("startdate", request.getParameter("startdate"));
	        request.setAttribute("enddate", request.getParameter("enddate"));
			
	        
    	if (request.getParameter("update") != null) {
    		RequestDispatcher rd=request.getRequestDispatcher("UpdateAuction.jsp");    
            rd.forward(request,response); 
    	}
    	else {
    		if (request.getParameter("delete") != null) {
    			PrintWriter out = response.getWriter(); 
    		    String url = "jdbc:mysql://localhost:3306/";  
    	        String dbName = "form";  
    	        String driver = "com.mysql.jdbc.Driver";  
    	        String userName = "root";  
    	        String dbpassword = "nainital"; 
    	        String query = "delete from prodtype where prodid = ?";
    	        try {
    	        Class.forName(driver);
				Connection conn=DriverManager.getConnection(url + dbName, userName, dbpassword);	
				PreparedStatement pst=conn.prepareStatement(query);
				pst.setInt(1, Integer.parseInt(request.getParameter("prodid")));
				pst.executeUpdate();
				out.println("Auction successfully deleted!!");
				conn.close();		
		        
		        } catch (ClassNotFoundException | SQLException e) {
					// TODO Auto-generated catch block
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

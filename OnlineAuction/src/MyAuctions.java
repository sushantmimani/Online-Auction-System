import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class MyAuction
 */
@WebServlet("/MyAuction")
public class MyAuctions extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyAuctions() {
        super();
        // TODO Auto-generated constructor stub
    }


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
        
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(); 
		PrintWriter out = response.getWriter();
		int userid = (Integer) session.getAttribute("id");
		int auctionid;
		int prodid;
        String name;
     //   String desc;
        float minprice;
        Date startdate;
        Date enddate;
        String active;
        ArrayList<Integer> auctionID= new ArrayList<Integer>();
        ArrayList<Integer> productID= new ArrayList<Integer>();
        ArrayList<String> prodName= new ArrayList<String>();
        ArrayList<Float> MinPrice= new ArrayList<Float>();
        ArrayList<Date> StartDate = new  ArrayList<Date>();
        ArrayList<Date> EndDate = new  ArrayList<Date>();
        ArrayList<String> Active =new ArrayList<String>();
        
		Connection conn = null;  
        PreparedStatement pst = null;  
        ResultSet rs = null;
        String url = "jdbc:mysql://localhost:3306/";  
        String dbName = "form";  
        String driver = "com.mysql.jdbc.Driver";  
        String userName = "root";  
        String password = "nainital"; 
        try { 
        	Class.forName(driver);
            conn= DriverManager.getConnection(url+dbName,userName,password);
            
            String getMyAuctions= "select a.auctionid,p.prodid, p.name,p.minprice,a.startdate, a.enddate, a.active from auction a,prodtype p where a.prodid = p.prodid and a.sellerid = ?";
    		PreparedStatement psgetMyAuctions= conn.prepareStatement(getMyAuctions);
    		psgetMyAuctions.setInt(1, userid);
    		ResultSet rsMyAuctions= psgetMyAuctions.executeQuery();
    		
            
            	while(rsMyAuctions.next())
        		{	auctionid = rsMyAuctions.getInt(1);
        		    prodid= rsMyAuctions.getInt(2);
    			    name = rsMyAuctions.getString(3);
    			    minprice =rsMyAuctions.getFloat(4);
    			    startdate = rsMyAuctions.getDate(5);
    			    enddate = rsMyAuctions.getDate(6);
    			int a=rsMyAuctions.getInt(7);
    			if (a == 1){
    			 active= "Active";
    			}
    			else
    			 active="Inactive";
    		
    			auctionID.add(auctionid);
    			productID.add(prodid);
    			prodName.add(name);
    			MinPrice.add(minprice);
    			StartDate.add(startdate);
    			EndDate.add(enddate);
    			Active.add(active);
        		}	
        			request.setAttribute("auctionid", auctionID);
        			request.setAttribute("prodid", productID);
        		    request.setAttribute("name", prodName);
        		    request.setAttribute("minprice", MinPrice);
        		    request.setAttribute("startdate", StartDate);
        		    request.setAttribute("enddate", EndDate);
        		    request.setAttribute("active",Active);
        			response.setContentType("text/html");
        			RequestDispatcher rd=request.getRequestDispatcher("MyAuction.jsp");    
                    rd.forward(request,response); 
            
            /*else
    		{
    			out.println("No auction found for the user " + session.getAttribute("name"));
    		}*/
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
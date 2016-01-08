

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class MyBids
 */
@WebServlet("/MyBids")
public class MyBids extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyBids() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		HttpSession session = request.getSession(); 
		PrintWriter out = response.getWriter();
		int userid = (Integer) session.getAttribute("id");
		Connection conn = null;  
        PreparedStatement pst = null;  
        ResultSet rs = null;
        ResultSet rs1 = null;
        String url = "jdbc:mysql://localhost:3306/";  
        String dbName = "form";  
        String driver = "com.mysql.jdbc.Driver";  
        String userName = "root";  
        String password = "nainital"; 
        try { 
        	Class.forName(driver);
            conn= DriverManager.getConnection(url+dbName,userName,password);
            pst = conn.prepareStatement("select b.auctionid, p.name, p.prodid, b.bidvalue from bids b, prodtype p where buyerid=? and p.prodid=b.prodid");
    		pst.setInt(1, userid);
    		rs = pst.executeQuery();
    		ArrayList<Integer> auctionid= new ArrayList<Integer>();
    		ArrayList<Integer> prodid= new ArrayList<Integer>();
    		ArrayList<String> prodname= new ArrayList<String>();
    		ArrayList<Float> bidvalue= new ArrayList<Float>();
    		ArrayList<Integer> flag= new ArrayList<Integer>();
    		
    			//if(rs.next()){
    				while(rs.next()){
    			int aid = rs.getInt(1);
    			String name = rs.getString(2);
    			int pid = rs.getInt(3);
    			float bid = rs.getFloat(4);
    			PreparedStatement ps1 = conn.prepareStatement("select b.buyerid from auction a, bids b where a.active = 0 and a.auctionid = ? and b.auctionid = ? and  b.bidvalue = (select max(bidvalue) from bids where auctionid=?)");
    			ps1.setInt(1, aid);
    			ps1.setInt(2, aid);
    			ps1.setInt(3, aid);
    			rs1 = ps1.executeQuery();
    			if(rs1.next()){
    				
    				int winner = rs1.getInt(1);
    				if (userid == winner)
    					flag.add(1); 
    				else  flag.add(0) ;    			
    				
    		} else flag.add(0) ;
    			
    			auctionid.add(aid);
    			prodid.add(pid);
    			bidvalue.add(bid);
    			prodname.add(name);
    		}
    		request.setAttribute("auctionid", auctionid);
		    request.setAttribute("prodid", prodid);
		    request.setAttribute("bidvalue", bidvalue);
		    request.setAttribute("name", prodname);
		    request.setAttribute("flag", flag);
			response.setContentType("text/html");
			RequestDispatcher rd=request.getRequestDispatcher("MyBids.jsp");    
            rd.forward(request,response); 
        
       // }else
    	//	{
    	//		out.println("NO bids found for the user " + session.getAttribute("name"));
    	//	}
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

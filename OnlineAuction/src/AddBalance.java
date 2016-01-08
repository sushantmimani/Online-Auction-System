import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
* Servlet implementation class AddBalance
*/
@WebServlet("/AddBalance")
public class AddBalance extends HttpServlet {
private static final long serialVersionUID = 1L;
      
   
/**
* @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
*/
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

PrintWriter out = response.getWriter();
float balance =Float.parseFloat(request.getParameter("balance"));
       HttpSession session = request.getSession(); 
       int uid = (Integer)session.getAttribute("id");
      // out.print(uid);     
      
try {

Class.forName(ConnectionConstants.driver);	
   Connection conn = DriverManager  
               .getConnection(ConnectionConstants.url, ConnectionConstants.userName, 
                ConnectionConstants.dbpassword);

   String query = "update accountdetails set accountbalance=accountbalance+? where userid =?";
   PreparedStatement ps = conn.prepareStatement(query);
   ps.setFloat(1, balance);
   ps.setInt(2, uid);
   ps.executeUpdate();
   out.println("Balance added");
   ps.close();
   conn.close();	   	
}catch (ClassNotFoundException e) {
// TODO Auto-generated catch block
e.printStackTrace();
} catch (SQLException e) {
// TODO Auto-generated catch block
e.printStackTrace();
}
}
}
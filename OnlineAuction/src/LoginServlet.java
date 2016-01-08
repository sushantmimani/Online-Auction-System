import java.io.IOException;  
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;  
import javax.servlet.ServletException;  
import javax.servlet.http.HttpServlet;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;  
import javax.servlet.http.HttpSession;  
  
  
public class LoginServlet extends HttpServlet{  
	String fname = null;
    private static final long serialVersionUID = 1L;  
  
    public void doPost(HttpServletRequest request, HttpServletResponse response)    
            throws ServletException, IOException {   
    	
    	  
        response.setContentType("text/html");    
        PrintWriter out = response.getWriter();    
        String type = null;
        String n=request.getParameter("username");    
        String p=request.getParameter("userpass"); 
        HttpSession session = request.getSession(false);  
        if(session!=null)  
        {
        	int id=LoginDao.getId(n);
        	session.setAttribute("name", n);
            session.setAttribute("id", id);
            float balance = LoginDao.getBalance(n);
            session.setAttribute("balance",balance);
        }
        
  
        if(LoginDao.validate(n, p)){  
        	
        	try {
				type = LoginDao.getType(n);
				session.setAttribute("type", type);
				switch(type){
	        	
	        	case "both":
	        		RequestDispatcher rd=request.getRequestDispatcher("welcome.jsp");    
	                rd.forward(request,response);  
	                break;
	        	case "seller":
	        		 rd=request.getRequestDispatcher("welcomeSeller.jsp");    
	                rd.forward(request,response);  
	                break;
	        	case "buyer":
	        		 rd=request.getRequestDispatcher("welcomeBuyer.jsp");    
	                rd.forward(request,response);  
	                break;
	                      	
	        	}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        else{    
            out.print("<p style=\"color:red\">Sorry username or password error</p>");    
            RequestDispatcher rd=request.getRequestDispatcher("index.jsp");    
            rd.include(request,response);    
        }    
  
        out.close();    
    }    
}   
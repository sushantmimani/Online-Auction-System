

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
 * Servlet implementation class RegistrationServlet
 */
@WebServlet("/RegistrationServlet")
public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    static String url = "jdbc:mysql://localhost:3306/";  
    static String dbName = "form";  
    static String driver = "com.mysql.jdbc.Driver";  
    static String userName = "root";  
    static String dbpassword = "nainital";
    static Boolean uFlag = false;
    static Boolean eFlag = false;
  
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");    
        PrintWriter out = response.getWriter();
        String fname=request.getParameter("fname");    
        String lname=request.getParameter("lname"); 
        String email=request.getParameter("email");
        String username=request.getParameter("username");
        String password=request.getParameter("password");
        String repassword=request.getParameter("repassword");
        String phone=request.getParameter("phone");
        String type=request.getParameter("usertype"); 
        String street=request.getParameter("streetname");
        String addressline1=request.getParameter("addLine1");
        String addressline2=request.getParameter("addLine2");
        String city=request.getParameter("city");
        String zipcode=request.getParameter("zipcode");
        String country=request.getParameter("country");
        String state=request.getParameter("state");
        String nameoncard=request.getParameter("nameoncard");
        String cardnum=request.getParameter("cardnum");
        String expmonth=request.getParameter("expmonth");
        String expyear=request.getParameter("expyear");
        String cvv=request.getParameter("cvv");
        String accountname=request.getParameter("accountname");
        String accountnum=request.getParameter("account");
        String routingnum=request.getParameter("routing");
        String bankname=request.getParameter("bank");
        
        HttpSession session = request.getSession(false);  
        if(session!=null)  
        session.setAttribute("name", username);  
        session.setAttribute("type", type);
 
        String query = "insert into user (username,password,type) values (?,?,?)";
        String query1 = "insert into accountdetails "
        		+ "(userid,firstname,lastname,mobile,email,accountbalance) "
        		+ "values ((select userid from user where username=?),?,?,?,?,0.0)";      
		String query2 = "insert into addressmaster (userid)"
				+ "select userid from user where username = ?";
		String query3 = " insert into address"
				+ "(addressnumber, street, addressline1, addressline2, city, state, zip, country, type) values "
				+ "((select addressnumber from addressmaster where userid  in"
				+ "(select userid from user where username=?)), ?,?,?,?,?,?,?,'primary')";
		String query4 = "insert into creditcarddetails (userid,name,cardnum,expmonth,expyear,cvv)"
				+ "values ((select userid from user where username=?),?,?,?,?,?)";		   
		String query5 = "insert into bankdetails (userid,accountname,accountnumber,routingnumber,bank)"
				+ "values ((select userid from user where username=?),?,?,?,?)";
		
		try {
			eFlag = ValidateInput.validateEmail(email);
			uFlag = ValidateInput.validateUsername(username);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
           Boolean mFlag = ValidateInput.validatePhone(phone);
           Boolean pFlag = ValidateInput.validatePassword(password, repassword);
           Boolean zFlag = ValidateInput.validateZip(zipcode);
           Boolean accountFlag = ValidateInput.validateAccountnumber(accountnum);
           Boolean routingFlag = ValidateInput.validateRoutingnumber(routingnum);
           
           if(eFlag)
           {
        	   out.print("<center><p style=\"color:red\">This email is already registered!</p></center>");    
               RequestDispatcher rd=request.getRequestDispatcher("UserRegistration.jsp");    
               rd.include(request,response); 
           }
           else
           {           
	           if (uFlag)
	           {
	        	   out.print("<center><p style=\"color:red\">Username already exists! Please choose a new username</p></center>");    
	               RequestDispatcher rd=request.getRequestDispatcher("UserRegistration.jsp");    
	               rd.include(request,response); 
	        	   
	           }
	           else
	           {
	        	   if(!pFlag)
	               {
	            	   out.print("<center><p style=\"color:red\">Sorry passwords don't match</p></center>");    
	                   RequestDispatcher rd=request.getRequestDispatcher("UserRegistration.jsp");    
	                   rd.include(request,response);  
	               }    
	               else{
	            	   if(!mFlag)
	            	   {
	            		   out.print("<center><p style=\"color:red\">Please enter a valid phone number</p></center>");    
	                       RequestDispatcher rd=request.getRequestDispatcher("UserRegistration.jsp");    
	                       rd.include(request,response); 
	            	   }
	            	   else
	            	   {
	            		   if(!zFlag)
		            	   {
		            		   out.print("<center><p style=\"color:red\">Please enter a zip code</p></center>");    
		                       RequestDispatcher rd=request.getRequestDispatcher("UserRegistration.jsp");    
		                       rd.include(request,response); 
		            	   }
	            		   else
	            		   {
	            			   if(!accountFlag)
	    	            	   {
	    	            		   out.print("<center><p style=\"color:red\">Please enter a valid Account Number</p></center>");    
	    	                       RequestDispatcher rd=request.getRequestDispatcher("UserRegistration.jsp");    
	    	                       rd.include(request,response); 
	    	            	   }
	            			   else
	            			   {
	            				   if(!routingFlag)
	        	            	   {
	        	            		   out.print("<center><p style=\"color:red\">Please enter a bank routing number</p></center>");    
	        	                       RequestDispatcher rd=request.getRequestDispatcher("UserRegistration.jsp");    
	        	                       rd.include(request,response); 
	        	            	   }
	            				   else
	            				   {
	            					   try {
	       	                          	Class.forName(driver);
	       	              				Connection conn = DriverManager.getConnection(url + dbName, userName, dbpassword);
	       	              				Double mobile=Double.parseDouble(phone);
	       	              				PreparedStatement pst=conn.prepareStatement(query);
	       	              				pst.setString(1, username);
	       	              				pst.setString(2, password);
	       	              				pst.setString(3, type); 	              				
	       	              				pst.executeUpdate();	              				
	       	              				PreparedStatement pst1 = conn.prepareStatement(query1);
	       	              				pst1.setString(1, username);
	       	              				pst1.setString(2, fname);
	       	              				pst1.setString(3, lname);
	       	              				pst1.setDouble(4, mobile);
	       	              				pst1.setString(5, email);
	       	              				//pst1.setFloat(6, (float) 0.0);
	       	              				pst1.executeUpdate();
	       	              				
	       	              				PreparedStatement pst2=conn.prepareStatement (query2);
	       	              				pst2.setString(1, username);
	       	              				pst2.executeUpdate();
	              				
	                    				
	       	              				int zip=Integer.parseInt(zipcode);
	       	              				PreparedStatement pst3=conn.prepareStatement(query3);
	       	              				pst3.setString(1,username);
	       	              				pst3.setString(2,street);
	       	              				pst3.setString(3,addressline1);
	       	              				pst3.setString(4,addressline2);
	       	              				pst3.setString(5,city);
	       	              				pst3.setString(6,state);
	       	              				pst3.setInt(7,zip);
	       	                		    pst3.setString(8,country);
	       	              				pst3.executeUpdate();
	       	              		
	       	              				
	       	              				double cardno = Double.parseDouble(cardnum);
	       	              				int month = Integer.parseInt(expmonth);
	       	              				int year = Integer.parseInt(expyear);
	       	              				int securitycode = Integer.parseInt(cvv);
	       	              				PreparedStatement pst4=conn.prepareStatement(query4);
	       	              				pst4.setString(1,username);
	       	              				pst4.setString(2,nameoncard);
	       	              				pst4.setDouble(3,cardno);
	       	              				pst4.setInt(4, month);
	       	              				pst4.setInt(5,year);
	       	              				pst4.setInt(6,securitycode);
	       	              				pst4.executeUpdate();
	       	              				
	       	              				double accountnumber=Double.parseDouble(accountnum);
	       	              				double routingnumber=Double.parseDouble(routingnum);
	       	              				PreparedStatement pst5=conn.prepareStatement(query5);
	       	              				pst5.setString(1,username);
	       	              				pst5.setString(2,accountname);
	       	              				pst5.setDouble(3,accountnumber);
	       	              				pst5.setDouble(4,routingnumber);
	       	              				pst5.setString(5,bankname);
	       	              				pst5.executeUpdate();              				

	       	              				conn.close();
	       	              			} catch (SQLException | ClassNotFoundException e) {
	       	              				// TODO Auto-generated catch block
	       	              				e.printStackTrace();
	       	              			} 
	            					   
	            				        	int id=LoginDao.getId(username);
	            				            session.setAttribute("id", id);
	            				            float balance = LoginDao.getBalance(username);
	            				            session.setAttribute("balance", balance);
	            				            
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
	                   	   }
	       	               }
	       	           }
	                  }
	       	}
	       }
	            				   }
	            			   }
	            		   }
	            		   
import java.sql.*;
import java.util.Date;

public class ValidateInput {
	
	static String url = "jdbc:mysql://localhost:3306/";  
	static String dbName = "form";  
    //static String driver = "com.mysql.jdbc.Driver";  
	static String userName = "root";  
	static String password = "nainital"; 
    
	public static boolean validatePassword(String password, String repassword){
		if(password.equals(repassword))
				return true;
		else
				return false;
		
	}
	
	public static boolean validateZip(String zipcode){
		if (zipcode.matches("[0-9]+") && zipcode.length() == 5) 
			return true;
	else
			return false;
	}
	
	public static boolean validateAccountnumber(String accountnum){
		if (accountnum.matches("[0-9]+")) 
			return true;
	else
			return false;
	}
	
	public static boolean validateRoutingnumber(String routingnum){
		if (routingnum.matches("[0-9]+")) 
			return true;
	else
			return false;
	}
	
	
	
	public static boolean validatePhone(String phone){
		if (phone.matches("[0-9]+") && phone.length() == 10) 
				return true;
		else
				return false;
		
	}
	
	public static boolean validateUsername(String user) throws SQLException{
		   Connection conn = DriverManager  
                    .getConnection(url + dbName, userName, password);   
           PreparedStatement pst = conn  
                    .prepareStatement("select * from user where username=? ");  
            pst.setString(1, user); 
            ResultSet rs= pst.executeQuery();
            Boolean status = rs.next();
            conn.close();
            return status;
	}
	
	public static boolean validateEmail(String email) throws SQLException{            
            Connection conn = DriverManager  
                    .getConnection(url + dbName, userName, password);    
           PreparedStatement pst = conn  
                    .prepareStatement("select * from accountdetails where email=? ");  
            pst.setString(1, email); 
            ResultSet rs= pst.executeQuery();
            Boolean status = rs.next();
            conn.close();
            return status;
	}
	
	public static boolean validateStartDateOfAuction(Date startDate){
		Date d1= new Date() ;
		if(startDate.compareTo(d1)>0)
			return true;
		else
		return false;					
	}
	
	public static boolean validateEndDateOfAuction(Date startDate, Date endDate){	
		if (endDate.compareTo(startDate)>= 0)
			return true;
		else
		return false;
	}
}

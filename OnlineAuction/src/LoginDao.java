

import java.sql.*;  
  
public class LoginDao { 
	
	static String url = "jdbc:mysql://localhost:3306/";  
	static String dbName = "form";  
	static String driver = "com.mysql.jdbc.Driver";  
	static String userName = "root";  
    
    static String password = "nainital";  
	
	    public static boolean validate(String name, String pass) {          
        boolean status = false;  
        Connection conn = null;  
        PreparedStatement pst = null;  
        ResultSet rs = null;  
  
        
        try {  
            
        	Class.forName(driver);
            conn = DriverManager  
                    .getConnection(url + dbName, userName, password);  
  
            pst = conn  
                    .prepareStatement("select * from user where username=? and password=?");  
            pst.setString(1, name);  
            pst.setString(2, pass);  
  
            rs = pst.executeQuery();  
            status = rs.next();  
  
        } catch (Exception e) {  
            System.out.println(e);  
        } finally {  
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
        return status;  
    }  
    
    
    
    public static String getType(String name) throws SQLException{          
        
    	boolean status = false;  
        Connection conn = null;  
        PreparedStatement pst = null;  
        ResultSet rs = null; 
        String type = null;
  
        
        try {  
            
        	Class.forName(driver);
            conn = DriverManager  
                    .getConnection(url + dbName, userName, password);  
  
            pst = conn  
                    .prepareStatement("select type from user where username=?");  
            pst.setString(1, name);  
           
            rs = pst.executeQuery(); 
            if (rs.next())
            {
            	 type = rs.getString(1); 
            }
           
  
        } catch (Exception e) {  
            System.out.println(e);  
        } finally {  
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
        return type;  
    }  
   
    public static int getId(String n){
    	
        Connection conn = null;  
        PreparedStatement pst = null;  
        ResultSet rs = null; 
        int id = 0;
  
        
        try {  
            
        	Class.forName(driver);
            conn = DriverManager  
                    .getConnection(url + dbName, userName, password);  
  
            pst = conn  
                    .prepareStatement("select userid from user where username=?");  
            pst.setString(1, n);  
            rs = pst.executeQuery(); 
            if (rs.next())
            {
            	 id = rs.getInt(1); 
            }
           
  
        } catch (Exception e) {  
            System.out.println(e);  
        } finally {  
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
        return id;  
    }   
    
public static float getBalance(String n){
    	
        Connection conn = null;  
        PreparedStatement pst = null;  
        ResultSet rs = null; 
        int id = 0;
        float balance =0;
  
        
        try {  
            
        	Class.forName(driver);
            conn = DriverManager  
                    .getConnection(url + dbName, userName, password);  
           id = getId(n);
            pst = conn  
                    .prepareStatement("select accountbalance from accountdetails where userid=?");  
            pst.setInt(1, id);  
            rs = pst.executeQuery(); 
            if (rs.next())
            {
            	 balance = rs.getInt(1); 
            }
            
            
           
  
        } catch (Exception e) {  
            System.out.println(e);  
        } finally {  
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
        return balance;  
    }   
    }

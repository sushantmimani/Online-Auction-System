import java.io.*;
import java.sql.*;

public class GetPhotosDao {
		
		public static InputStream getPhotos(String Id) throws 
		  IllegalArgumentException, SQLException, ClassNotFoundException {  
			Connection conn = null;  
	        PreparedStatement pst = null;  
	        ResultSet rs = null; 
	        String url = "jdbc:mysql://localhost:3306/";  
	        String dbName = "form";  
	        String driver = "com.mysql.jdbc.Driver";  
	        String userName = "root";  
	        String password = "nainital";  
		    InputStream binaryStream = null;

		    try {

				  Class.forName(driver);
		            conn = DriverManager  
		                    .getConnection(url + dbName, userName, password); 
	            int id = Integer.parseInt(Id);   
			    pst = conn.prepareStatement("select image from prodtype where prodid = ?");  
			    pst.setInt(1, id);
			    rs = pst.executeQuery();
			    while(rs.next()) {
			  binaryStream = rs.getBinaryStream(1);	
			    //image = rs.getBlob(1);
			   // binaryStream = image.getBinaryStream();
			    }

			  } catch (SQLException e) {
			      throw new SQLException(e);
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
			  return binaryStream;
			}

		}
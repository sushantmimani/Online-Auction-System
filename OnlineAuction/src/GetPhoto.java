import java.io.*;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



/**
 * Servlet implementation class GetPhoto
 */
@WebServlet("/GetPhoto")
public class GetPhoto extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetPhoto() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//PrintWriter out = response.getWriter();   
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath()); 
		 try {
			String Id = request.getParameter("Id");
			 //int Id = Integer.parseInt(request.getParameter("Id"));
		     InputStream photoStream = GetPhotosDao.getPhotos(Id);
		      // Prepare streams.
		      BufferedInputStream input = null;
		      BufferedOutputStream output = null;
		      try {

			      // Open streams
			      input = new BufferedInputStream(photoStream, 2);
			      response.setContentType("image/jpg");
			      output = new BufferedOutputStream(response.getOutputStream(),
			    		  2);

			      // Write file contents to response.
			      byte[] buffer = new byte[2];
			      int length;
			      while ((length = input.read(buffer)) > 0) {
			          output.write(buffer, 0, length);
			      }

			      } finally {
			          output.close();
			          input.close();
			      }

			
		   } catch (Exception e) {
		      e.printStackTrace();
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
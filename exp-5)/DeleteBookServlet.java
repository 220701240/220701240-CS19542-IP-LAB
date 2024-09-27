package updatebookservlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class UpdateBookServlet
 */
@WebServlet("/UpdateBookServlet")
public class UpdateBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateBookServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

	    
			try {
				Class.forName("com.mysql.jdbc.Driver");
				String URL = "jdbc:mysql://localhost:3306/library";
				Connection conn = DriverManager.getConnection(URL,"root","student");

			    String title = request.getParameter("title");
			    String author = request.getParameter("author");
			    String query = "DELETE FROM BOOK WHERE AUTHOR= ?";

	            PreparedStatement ps = conn.prepareStatement(query);
	            ps.setString(1,author);
	            // Set ACCNO to identify the book record

	            // Execute the update
	            int result = ps.executeUpdate();

	            // Check if the update was successful
	            if (result > 0) {
	                out.println("<h2>Book with Author" + author + " deleted successfully!</h2>");
	            } else {
	                out.println("<h2>Book with Author " + author + " not found.</h2>");
	            }

	            // Close resources
	            ps.close();
	            conn.close();
	}catch (Exception e) {
    	out.println(e);

}
	}
}

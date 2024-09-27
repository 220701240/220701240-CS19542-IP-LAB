package addBookServlet;

import java.io.IOException;


import java.io.PrintWriter;
import java.math.BigDecimal;
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
 * Servlet implementation class AddBookServlet
 */
@WebServlet("/AddBookServlet")
public class AddBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddBookServlet() {
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
			    String publisher = request.getParameter("publisher");
			    String edition = request.getParameter("edition");
			    String price = request.getParameter("price");
            // Prepare SQL query
            String query = "INSERT INTO BOOK (TITLE, AUTHOR, PUBLISHER, EDITION, PRICE) VALUES (?, ?, ?, ?, ?)";

            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, title);
            ps.setString(2, author);
            ps.setString(3, publisher);
            ps.setString(4, edition);
            ps.setString(5,price);

            // Execute update
            int result = ps.executeUpdate();
            if (result != 0)

            out.println("Student Details Inserted Successfully...");
			else 
				out.println("Student Details Insertion Failure...");
            
            String selectQuery = "SELECT * FROM BOOK";
            PreparedStatement stmt = conn.prepareStatement(selectQuery);
            ResultSet rs = stmt.executeQuery();

            // Display book details in a table
            
            while (rs.next()) {
             
                out.println(rs.getString("TITLE"));
                out.println( rs.getString("AUTHOR") );
                out.println( rs.getString("PUBLISHER") );
                out.println(rs.getString("EDITION"));
                out.println(rs.getBigDecimal("PRICE"));
                
            }
          

            // Close all resources
            rs.close();
            stmt.close();
            // Close connection
            ps.close();
            conn.close();

            // Redirect or display success message

        } catch (Exception e) {
        	out.println(e);
        }
    }
}
	

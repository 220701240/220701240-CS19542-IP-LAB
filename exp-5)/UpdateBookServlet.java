import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class UpdateBookServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Database connection variables
        String url = "jdbc:mysql://localhost:3306/LibraryManagementSystem";
        String user = "root";
        String password = "password";

        // Fetch form data
        String accno = request.getParameter("accno"); // Assuming ACCNO is passed via form
        String newPublisher = request.getParameter("publisher");

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try {
            // Load the MySQL driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish connection
            Connection conn = DriverManager.getConnection(url, user, password);

            // Prepare SQL query to update the publisher for the given account number
            String query = "UPDATE BOOK SET PUBLISHER = ? WHERE ACCNO = ?";

            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, newPublisher);
            ps.setString(2, accno);  // Set ACCNO to identify the book record

            // Execute the update
            int result = ps.executeUpdate();

            // Check if the update was successful
            if (result > 0) {
                out.println("<h2>Book with Account No " + accno + " updated successfully!</h2>");
            } else {
                out.println("<h2>Book with Account No " + accno + " not found.</h2>");
            }

            // Close resources
            ps.close();
            conn.close();

        } catch (SQLException e) {
            out.println("<h2>Database error occurred: " + e.getMessage() + "</h2>");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            out.println("<h2>MySQL Driver not found.</h2>");
            e.printStackTrace();
        } catch (Exception e) {
            out.println("<h2>Error occurred: " + e.getMessage() + "</h2>");
            e.printStackTrace();
        }
    }
}

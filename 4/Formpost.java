package formpost;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Formpost
 */
@WebServlet("/Formpost")
public class Formpost extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
   

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		 String name = request.getParameter("name");
	        String rollno = request.getParameter("rollno");
	        String gender = request.getParameter("gender");
	        String year = request.getParameter("year");
	        String department = request.getParameter("department");
	        String section = request.getParameter("section");
	        String mobile_no = request.getParameter("mobile_no");
	        String email = request.getParameter("email");
	        String address = request.getParameter("address");
	        String city = request.getParameter("city");
	        String country = request.getParameter("country");
	        String pincode = request.getParameter("pincode");
	        out.println("<html><body>");
	        out.println("<h2>Course Registration Details</h2>");
	        out.println("<p><strong>Student Name: </strong>" + name + "</p>");
	        out.println("<p><strong>Roll Number: </strong>" + rollno + "</p>");
	        out.println("<p><strong>Gender: </strong>" + gender + "</p>");
	        out.println("<p><strong>Year: </strong>" + year + "</p>");
	        out.println("<p><strong>Department: </strong>" + department + "</p>");
	        out.println("<p><strong>Section: </strong>" + section + "</p>");
	        out.println("<p><strong>Mobile Number: </strong>" + mobile_no + "</p>");
	        out.println("<p><strong>E-Mail ID: </strong>" + email + "</p>");
	        out.println("<p><strong>Address: </strong>" + address + "</p>");
	        out.println("<p><strong>City: </strong>" + city + "</p>");
	        out.println("<p><strong>Country: </strong>" + country + "</p>");
	        out.println("<p><strong>Pincode: </strong>" + pincode + "</p>");
	        out.println("</body></html>");
	    }
         
         
		
	}

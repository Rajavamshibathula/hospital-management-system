package hospitalmanagementsystem;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/patient_home")
public class Patientdetails extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String firstname=req.getParameter("firstName");
		String lastName=req.getParameter("lastName");
		String age=req.getParameter("age");
		String gender=req.getParameter("gender");
		String disease=req.getParameter("disease");
		String diseaseDescription=req.getParameter("diseaseDescription");
		
		
		String qry="insert into hospitalmanagementsystem.patients values(?,?,?,?,?,?)";
	     
	     try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/hospitalmanagementsystem?user=root&&password=root");
			PreparedStatement ps=con.prepareStatement(qry);
			ps.setString(1, firstname);
			ps.setString(2, lastName);
			ps.setString(3, age);
			ps.setString(4, gender);
			ps.setString(5, disease);
			ps.setString(6,diseaseDescription);
			ps.executeUpdate();
			
			PrintWriter pwWriter=resp.getWriter();
			
			 String fetchQuery = "SELECT * FROM patients";
		        PreparedStatement ps1 = con.prepareStatement(fetchQuery);
		        ResultSet rs = ps1.executeQuery();
		        while (rs.next()) {
		        	pwWriter.println("<html><body>");
		        pwWriter.println("<h3>Patient Records:</h3>");
		        pwWriter.println("<table border='1'><tr><th>First Name</th><th>Last Name</th><th>Age</th><th>Gender</th><th>Disease</th><th>Description</th></tr>");

		        
		            pwWriter.println("<tr>"
		                    + "<td>" + rs.getString("firstName") + "</td>"
		                    + "<td>" + rs.getString("lastName") + "</td>"
		                    + "<td>" + rs.getInt("age") + "</td>"
		                    + "<td>" + rs.getString("gender") + "</td>"
		                    + "<td>" + rs.getString("disease") + "</td>"
		                    + "<td>" + rs.getString("diseaseDescription") + "</td></tr>");
		            
		            PrintWriter pw=resp.getWriter();
		        	pw.println("<form action='delete'>");
		        	pw.println("<input type='hidden' name='firstname' value='" + firstname + "'>");
		        	pw.println("<button type='submit' style='background-color: #dc3545;'>Delete</button>");
		        	pw.println("</form>");
		            
		            pwWriter.println("</table>");
		            pwWriter.println("</body></html>");
		           
		       
		        }  
		
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	     }	     
}
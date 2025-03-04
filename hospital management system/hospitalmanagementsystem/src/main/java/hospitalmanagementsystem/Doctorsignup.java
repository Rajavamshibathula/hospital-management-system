package hospitalmanagementsystem;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/doctor_dashboard")
public class Doctorsignup extends HttpServlet {
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		 String fullname = req.getParameter("full_name");
	     String email = req.getParameter("email");
	     String password = req.getParameter("password");
	     String specialization = req.getParameter("specialization");
	     String license_number = req.getParameter("license_number");
	     String phone = req.getParameter("phone");
	     
	     String qry="insert into hospitalmanagementsystem.doctordetails values(?,?,?,?,?,?)";
	     
	     try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/hospitalmanagementsystem?user=root&&password=root");
			PreparedStatement ps=con.prepareStatement(qry);
			ps.setString(1, fullname);
			ps.setString(2, email);
			ps.setString(3, password);
			ps.setString(4, specialization);
			ps.setString(5, license_number);
			ps.setString(6, phone);
			
			ps.executeUpdate();
			
			RequestDispatcher rd=req.getRequestDispatcher("index.html");
			rd.forward(req, resp);
			
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		
	}

}

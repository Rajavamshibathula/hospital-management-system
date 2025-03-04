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
@WebServlet("/doctor_dashboard.html")
public class Doctorlogin extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		
		String email=req.getParameter("email");
		String password=req.getParameter("password");
		String qry="select email,password from hospitalmanagementsystem.doctordetails where email=? AND password=?";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/hospitalmanagementsystem?user=root&&password=root");
			PreparedStatement ps=con.prepareStatement(qry);
			ps.setString(1, email);
			ps.setString(2, password);
			ResultSet rs=ps.executeQuery();
			if (rs.next()) {
				RequestDispatcher rd=req.getRequestDispatcher("dectorhome.html");
				rd.forward(req, resp);
			}else {
				PrintWriter pw=resp.getWriter();
				pw.println("<html><body bgcolor='red'>");
				pw.println("<h1 style='font-color:'red''> invalid gmail & password<h1>");
				pw.println("</body></html>");
				RequestDispatcher rd=req.getRequestDispatcher("index.html");
				rd.include(req, resp);
				
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
		
	}

	
	
	
	


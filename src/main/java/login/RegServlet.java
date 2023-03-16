package login;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jakarta.servlet.GenericServlet;
import jakarta.servlet.Servlet;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;

public class RegServlet extends GenericServlet implements Servlet {
	private static final long serialVersionUID = 1L;
       
    public RegServlet() {
        super();
    }
    Connection con;
	public void init(ServletConfig config) throws ServletException 
	{
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","tiger");
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException 
	{
	try {
		String s1=request.getParameter("fname");
		String s2=request.getParameter("lname");
		String s3=request.getParameter("uname");
		String s4=request.getParameter("pword");
		PreparedStatement pstmt=con.prepareStatement("insert into uinfo values(?,?,?,?)");
		pstmt.setString(1, s1);
		pstmt.setString(2, s2);
		pstmt.setString(3, s3);
		pstmt.setString(4, s4);
		pstmt.executeUpdate();
		PrintWriter pw=response.getWriter();
		pw.println("<html><body bgcolor=yellow text=green><center>");
		pw.println("<h1>You have Registered Succcessfully</h1><br>");
		pw.println("<a href=login.html>Login</a>");
		pw.println("</center></body></html>");
	} catch (SQLException e) {
		e.printStackTrace();
	}
	}

}

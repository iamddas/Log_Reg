package login;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import jakarta.servlet.GenericServlet;
import jakarta.servlet.Servlet;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;

public class LoginServlet extends GenericServlet implements Servlet {
	private static final long serialVersionUID = 1L;
       
    
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    Connection con;
	public void init(ServletConfig config) throws ServletException 
	{
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","tiger");
			}
		catch(Exception e)
			{
				e.printStackTrace();
			}
	}

	public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException 
	{
		try {
			String s1=request.getParameter("uname");
			String s2=request.getParameter("pword");
			PreparedStatement pstmt=con.prepareStatement("select * from uinfo where uname=? and pword=?");
			pstmt.setString(1, s1);
			pstmt.setString(2, s2);
			ResultSet rs=pstmt.executeQuery();
			PrintWriter pw=response.getWriter();
			pw.println("<html><body bgcolor=black text=white><h1>");
			if(rs.next())
			{
				pw.println("Hey, "+s1+",<br>");
				pw.println("Have a Wonderfull Day!!!&#128525;");
			}
			else
			{
				pw.println("Invalid Username/Password");
			}
			pw.println("</h1></body><html>");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

}

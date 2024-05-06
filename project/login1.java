package vote.sres;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
/**
 * Servlet implementation class login1
 */
@WebServlet("/log")
public class login1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public login1() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String code = request.getParameter("candidate_code");
		String upwd = request.getParameter("password");
		HttpSession session = request.getSession();
		RequestDispatcher dispatcher = null;
		try {
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/voter_login", "root", "dewa@1309");
		PreparedStatement pst = con.prepareStatement("select * from register where code=? and upwd=?");
		pst.setString(1, code);
		pst.setString(2, upwd);
		
		ResultSet rs = pst.executeQuery();
		if(rs.next()) {
			session.setAttribute("email", rs.getObject("uemail"));
			dispatcher = request.getRequestDispatcher("controlpanel.html");
		}else {
			System.out.println("WRONG CREDENTIALS");
			System.out.println("<font color=red size=18>Login Failed!!<br>");
			request.getRequestDispatcher("create.html").forward(request, response);
	
			
		}
		dispatcher.forward(request, response);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}

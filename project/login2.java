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
 * Servlet implementation class login2
 */
@WebServlet("/login")
public class login2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public login2() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String Voter_Id = request.getParameter("voterid");
		String password = request.getParameter("password");
		HttpSession session = request.getSession();
		RequestDispatcher dispatcher = null;
		try {
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/voter_login", "root", "dewa@1309");
		PreparedStatement pst = con.prepareStatement("select * from login where Voter_Id = ? and password = ?");
		pst.setString(1, Voter_Id);
		pst.setString(2, password);
		
		ResultSet rs = pst.executeQuery();
		if(rs.next()) {
			session.setAttribute("name", rs.getObject("uname"));
			dispatcher = request.getRequestDispatcher("voting.html");
		}else {
			request.setAttribute("errorMessage", "Login failed. Please try again.");
            request.getRequestDispatcher("loginvote.html").forward(request, response);			
		}
		dispatcher.forward(request, response);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}

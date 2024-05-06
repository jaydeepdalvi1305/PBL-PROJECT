
package vote.sres;

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


/**
 * Servlet implementation class registration
 */
@WebServlet("/register")
public class registration extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public registration() {
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
		String uemail= request.getParameter("email");
		String upwd=request.getParameter("password");	
		String code=request.getParameter("candidate_code");
		RequestDispatcher dispatcher = null;
		Connection con =null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			 con = DriverManager.getConnection("jdbc:mysql://localhost:3306/voter_login", "root", "dewa@1309");
			PreparedStatement pst = con.prepareStatement("insert into register (uemail,upwd,code) values(?,?,?)");
			pst.setString(1, uemail);
			pst.setString(2, upwd);
			pst.setString(3, code);
			int rowCount = pst.executeUpdate();
			dispatcher = request.getRequestDispatcher("create.html");
			if(rowCount>0) {
				request.setAttribute("status", "success");
				}
			else
			{
				request.setAttribute("status", "failed");
			}
			dispatcher.forward(request, response);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
}
}

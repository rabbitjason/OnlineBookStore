package bookstore.serlvet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;

import javax.json.Json;
import javax.json.JsonBuilderFactory;
import javax.json.JsonObject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bookstore.bean.Member;
import bookstore.factory.DAOFactory;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		Member member = new Member();
		member.setPassword(password);
		member.setEmail(email);
		
		PrintWriter out = response.getWriter();
		JsonObject objResponse = null;
		JsonBuilderFactory factory = null;
		factory = Json.createBuilderFactory(null);
		
		try {
			boolean bRet = DAOFactory.getIMemberDAOInstance().findLogin(member);
			if (bRet) {
				objResponse = factory.createObjectBuilder()
				           .add("status", "0")
				           .add("action", "login")
				           .add("id", member.getId())
				           .build();
				Cookie cookie1 = new Cookie("USERACCOUNT", URLEncoder.encode(member.getEmail(), "UTF-8"));
                Cookie cookie2 = new Cookie("USERFIRSTNAME", URLEncoder.encode(member.getFirstname(), "UTF-8"));
                Cookie cookie3 = new Cookie("USERID", URLEncoder.encode(member.getId(), "UTF-8"));
                cookie1.setMaxAge(24*60*60);
                cookie2.setMaxAge(24*60*60);
                cookie3.setMaxAge(24*60*60);
                response.addCookie(cookie1);
                response.addCookie(cookie2);
                response.addCookie(cookie3);
			} else {
				objResponse = factory.createObjectBuilder()
				           .add("status", "-1")
				           .add("action", "login")
				           .build();
			}
			
			out.println(objResponse);
			out.flush();
			out.close();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

package bookstore.serlvet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.json.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bookstore.bean.Member;
import bookstore.factory.DAOFactory;

/**
 * Servlet implementation class RegistServlet
 */
@WebServlet("/RegistServlet")
public class RegistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegistServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String pass1 = request.getParameter("password1");
		String pass2 = request.getParameter("password2");
		String lastname = request.getParameter("lastname");
	    String firstname = request.getParameter("firstname");
	    String phone = request.getParameter("phone");
	    String address = request.getParameter("address");
	    String email = request.getParameter("email");
	    
	    Member mem = new Member() ;
		mem.setPassword(pass1) ;
		mem.setEmail(email) ;
		mem.setFirstname(firstname);
		mem.setLastname(lastname);
		mem.setMobilephone(phone);
		mem.setAddress(address);
		
		PrintWriter out=response.getWriter();
		JsonObject objResponse = null;
		JsonBuilderFactory factory = null;
		factory = Json.createBuilderFactory(null);
		try {
			boolean bRet = DAOFactory.getIMemberDAOInstance().doCreate(mem);
			if (bRet) {
				objResponse = factory.createObjectBuilder()
				           .add("status", "0")
				           .add("action", "register")
				           .build();		    
			}
			else {
				objResponse = factory.createObjectBuilder()
				           .add("status", "-1")
				           .add("action", "register")
				           .build();
			}
			out.println(objResponse);
			out.close();
		}  catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		
	    //out.println("password: " + re_pass);
		//response.getWriter().append("Served at: 1").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

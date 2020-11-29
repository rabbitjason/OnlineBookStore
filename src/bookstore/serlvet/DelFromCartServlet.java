package bookstore.serlvet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.json.Json;
import javax.json.JsonBuilderFactory;
import javax.json.JsonObject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bookstore.bean.Cart;
import bookstore.factory.DAOFactory;

/**
 * Servlet implementation class DelFromCartServlet
 */
@WebServlet("/DelFromCartServlet")
public class DelFromCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DelFromCartServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		String bookid = request.getParameter("bookid");
		String userid = request.getParameter("userid");
		
		PrintWriter out = response.getWriter();
		JsonObject objResponse = null;
		JsonBuilderFactory factory = null;
		factory = Json.createBuilderFactory(null);
		
		try {
			Cart newcart = new Cart();
			newcart.setBookid(bookid);
			newcart.setUserid(userid);
			
			boolean bRet = false;
			Cart oldcart = DAOFactory.getICartDAOInstance().find(newcart);
			if (oldcart != null) {
				bRet = DAOFactory.getICartDAOInstance().doRemove(oldcart);
			} 
			
			if (bRet) {
				objResponse = factory.createObjectBuilder()
				           .add("status", "0")
				           .add("action", "DelFromCartServlet")
				           .add("bookid", newcart.getBookid())
				           .add("userid", newcart.getUserid())
				           .build();

			} else {
				objResponse = factory.createObjectBuilder()
				           .add("status", "-1")
				           .add("action", "DelFromCartServlet")
				           .build();
			}
			
			out.println(objResponse);
			out.flush();
			out.close();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

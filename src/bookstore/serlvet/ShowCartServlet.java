package bookstore.serlvet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonBuilderFactory;
import javax.json.JsonObject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bookstore.bean.Book;
import bookstore.bean.Cart;
import bookstore.factory.DAOFactory;

/**
 * Servlet implementation class ShowCartServlet
 */
@WebServlet("/ShowCartServlet")
public class ShowCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowCartServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		String userid = request.getParameter("userid");
		
		PrintWriter out = response.getWriter();
		JsonObject objResponse = null;
		JsonBuilderFactory factory = null;
		factory = Json.createBuilderFactory(null);
		
		Book book = new Book();
		
		
		try {
			
			List<Cart> all = DAOFactory.getICartDAOInstance().findAll(userid);
			
			if (all != null) {
				JsonArrayBuilder jarr = Json.createArrayBuilder();
				for (Cart ct:all) {
					book = DAOFactory.getIBookDAOInstance().findById(ct.getBookid());
					jarr.add(Json.createObjectBuilder()
					        .add("bookid", ct.getBookid())
					        .add("title", book.getTitle())
					        .add("userid", ct.getUserid())
					        .add("fee", ct.getFee())
					        .add("quantity", ct.getQuantity())
					        .build());
				}
				
				JsonArray records = jarr.build();
				
				objResponse = factory.createObjectBuilder()
				           .add("status", "0")
				           .add("action", "AddToCartServlet")
				           .add("records", records)
				           .add("userid", userid)
				           .build();

			} else {
				objResponse = factory.createObjectBuilder()
				           .add("status", "-1")
				           .add("action", "AddToCartServlet")
				           .add("userid", userid)
				           .build();
			}
			
//		objResponse = factory.createObjectBuilder()
//		           .add("status", "0")
//		           .add("action", "AddToCartServlet")
//		           .add("userid", userid)
//		           .build();
		
		
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

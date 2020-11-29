package bookstore.serlvet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
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
import bookstore.bean.Member;
import bookstore.bean.Order;
import bookstore.factory.DAOFactory;

/**
 * Servlet implementation class ShowOrderServlet
 */
@WebServlet("/ShowOrderServlet")
public class ShowOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowOrderServlet() {
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
		Book book = null;
		
		SimpleDateFormat formatTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String orderTime = null;
		try {
			Member user = DAOFactory.getIMemberDAOInstance().findById(userid);
			List<String> ordernumbers = DAOFactory.getIOrderDAOInstance().findOrderNums(userid);
			if (ordernumbers != null && user != null) {
				JsonArrayBuilder jsarrOrders = Json.createArrayBuilder();
				for (String num:ordernumbers) {
					
					List<Order> orders = DAOFactory.getIOrderDAOInstance().findAll(num);
					JsonArrayBuilder jsarrBooks = Json.createArrayBuilder();
					for (Order ov:orders) {
						book = DAOFactory.getIBookDAOInstance().findById(ov.getBook_id());
						orderTime = formatTime.format(ov.getOrder_time());
						jsarrBooks.add(Json.createObjectBuilder()
										.add("bookid", book.getBookid())
										.add("title", book.getTitle())
										.add("quantity", ov.getQuantity())
										.add("fee", ov.getFee())
										.build()
									);
					}
					
					JsonArray books = jsarrBooks.build();
					JsonObject order = factory.createObjectBuilder()
							.add("ordernumber", num)
							.add("books", books)
							.add("firstname", user.getFirstname())
							.add("ordertime", orderTime)
							.build();
					
					jsarrOrders.add(order);
				}
				
				JsonArray records = jsarrOrders.build();
				
				objResponse = factory.createObjectBuilder()
				           .add("status", "0")
				           .add("action", "ShowOrderServlet")
				           .add("records", records)
				           .add("userid", userid)
				           .build();
				
			} else {
				objResponse = factory.createObjectBuilder()
				           .add("status", "-1")
				           .add("action", "ShowOrderServlet")
				           .add("userid", userid)
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

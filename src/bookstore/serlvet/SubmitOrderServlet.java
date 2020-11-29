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
import bookstore.bean.Order;
import bookstore.factory.DAOFactory;
import bookstore.util.GenerateNum;
import bookstore.util.SendHTMLMail;

/**
 * Servlet implementation class SubmitOrderServlet
 */
@WebServlet("/SubmitOrderServlet")
public class SubmitOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SubmitOrderServlet() {
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

		try {
			
			boolean bRet = true;
			List<Cart> all = DAOFactory.getICartDAOInstance().findAll(userid);
			
			String ordernum = GenerateNum.getInstance().GenerateOrder();
			Order neworder = new Order();
			
			if (all != null) {
				for (Cart ct:all) {
					neworder.setBook_id(ct.getBookid());
					neworder.setFee(ct.getFee());
					neworder.setQuantity(ct.getQuantity());
					neworder.setUser_id(ct.getUserid());
					neworder.setOrder_number(ordernum);
					
					bRet &= DAOFactory.getIOrderDAOInstance().doCreate(neworder);
					
					bRet &= DAOFactory.getICartDAOInstance().doRemove(ct);
				}
				
				
				
			} else {
				bRet = false;
			}
			
			if (bRet) {
				objResponse = factory.createObjectBuilder()
				           .add("status", "0")
				           .add("action", "Submit Order")
				           .add("userid", userid)
				           .build();

			} else {
				objResponse = factory.createObjectBuilder()
				           .add("status", "-1")
				           .add("action", "Submit Order")
				           .add("userid", userid)
				           .build();
			}
			out.println(objResponse);
			out.flush();
			out.close();
			
			SendHTMLMail.getInstance().Send(ordernum);
			
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

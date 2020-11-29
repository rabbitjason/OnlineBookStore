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
import bookstore.factory.DAOFactory;

/**
 * Servlet implementation class ShowBooksServlet
 */
@WebServlet("/ShowBooksServlet")
public class ShowBooksServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowBooksServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		String categoryid = request.getParameter("categoryid");
		
		PrintWriter out = response.getWriter();
		JsonObject objResponse = null;
		JsonBuilderFactory factory = null;
		factory = Json.createBuilderFactory(null);
		try {
			List<Book> all = DAOFactory.getIBookDAOInstance().findByType(categoryid);
			
			if (all != null) {
				JsonArrayBuilder jarr = Json.createArrayBuilder();
				
				for (Book vo:all) {
					
					jarr.add(Json.createObjectBuilder()
					        .add("bookid", vo.getBookid())
					        .add("title", vo.getTitle())
					        .add("authors", vo.getAuthors())
					        .add("price", vo.getPrice())
					        .add("saletype", vo.getSaleType())
					        .build());
				}
				
				JsonArray records = jarr.build();
				objResponse = factory.createObjectBuilder()
				           .add("status", "0")
				           .add("action", "Show Books")
				           .add("length", records.size())
				           .add("records", records)
				           .build();
			} else {
				
				objResponse = factory.createObjectBuilder()
				           .add("status", "-1")
				           .add("action", "Show Books")
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

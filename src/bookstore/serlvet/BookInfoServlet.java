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
import bookstore.bean.Cart;
import bookstore.bean.Comment;
import bookstore.factory.DAOFactory;

/**
 * Servlet implementation class BookInfoServlet
 */
@WebServlet("/BookInfoServlet")
public class BookInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookInfoServlet() {
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
		Book book = new Book();
		book.setBookid(bookid);
		
		PrintWriter out = response.getWriter();
		JsonObject objResponse = null;
		JsonBuilderFactory factory = null;
		factory = Json.createBuilderFactory(null);
		SimpleDateFormat formatTime = new SimpleDateFormat("yyyy-MM-dd");
		String createdTime = null;
		JsonArray records = null;
		JsonArrayBuilder jsarrComms = Json.createArrayBuilder();;
		try {
			book = DAOFactory.getIBookDAOInstance().findById(bookid);
			List<Comment> comms = DAOFactory.getICommentDAOInstance().findAll(Integer.parseInt(bookid));
			if (comms != null) {
			
				for (Comment ct:comms) {

					createdTime = formatTime.format(ct.getCreatedtime());
					jsarrComms.add(Json.createObjectBuilder()
							.add("id", ct.getId())
							.add("creator", ct.getUser_id())
							.add("content", ct.getContent())
							.add("fullname", ct.getFirstname())
							.add("created", createdTime)
							.add("upvote_count", ct.getUpvote_count())
							.build());
				}
			}
			
			records = jsarrComms.build();
			
			if (book != null) {
				objResponse = factory.createObjectBuilder()
				           .add("status", "0")
				           .add("action", "bookinfo")
				           .add("bookid", book.getBookid())
				           .add("title", book.getTitle())
				           .add("price", book.getPrice())
				           .add("comments", records)
				           .build();

			} else {
				objResponse = factory.createObjectBuilder()
				           .add("status", "-1")
				           .add("action", "bookinfo")
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

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

import bookstore.bean.Comment;
import bookstore.factory.DAOFactory;

/**
 * Servlet implementation class UpdateCommentServlet
 */
@WebServlet("/UpdateCommentServlet")
public class UpdateCommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateCommentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		String comment_id = request.getParameter("comment_id");
		String upvote_count = request.getParameter("upvote_count");
		
		PrintWriter out = response.getWriter();
		JsonObject objResponse = null;
		JsonBuilderFactory factory = null;
		factory = Json.createBuilderFactory(null);
		
		try {
			
			Comment ov = new Comment();
			ov.setId(Integer.parseInt(comment_id));
			ov.setUpvote_count(Integer.parseInt(upvote_count));
			
			boolean bRet = DAOFactory.getICommentDAOInstance().doUpdate(ov);
			
			if (bRet) {
				objResponse = factory.createObjectBuilder()
				           .add("status", "0")
				           .add("action", "Update Comment")
				           .build();
			} else {
				objResponse = factory.createObjectBuilder()
				           .add("status", "-1")
				           .add("action", "Update Comment")
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

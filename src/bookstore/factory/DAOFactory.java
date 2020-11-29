package bookstore.factory;

import bookstore.dao.IBookDAO;
import bookstore.dao.ICartDAO;
import bookstore.dao.ICommentDAO;
import bookstore.dao.IMemberDAO;
import bookstore.dao.IOrderDAO;
import bookstore.proxy.BookDAOProxy;
import bookstore.proxy.CartDAOProxy;
import bookstore.proxy.CommentDAOProxy;
import bookstore.proxy.MemberDAOProxy;
import bookstore.proxy.OrderDAOProxy;

public class DAOFactory {

	public static IMemberDAO getIMemberDAOInstance() throws Exception{
		return new MemberDAOProxy() ; 
	}
	
	public static IBookDAO getIBookDAOInstance() throws Exception{
		return new BookDAOProxy() ;
	}
	
	public static ICartDAO getICartDAOInstance() throws Exception{
		return new CartDAOProxy() ;
	}
	
	public static IOrderDAO getIOrderDAOInstance() throws Exception{
		return new OrderDAOProxy() ;
	}
	
	public static ICommentDAO getICommentDAOInstance() throws Exception{
		return new CommentDAOProxy() ;
	}
}

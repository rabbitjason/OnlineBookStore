package bookstore.proxy;

import java.util.List;

import bookstore.bean.Cart;
import bookstore.bean.Comment;

import bookstore.dao.ICommentDAO;
import bookstore.dao.impl.CommentDAOImpl;
import bookstore.db.DBConnection;

public class CommentDAOProxy implements ICommentDAO {

	private DBConnection dbc = null;
	private ICommentDAO dao = null;
	
	public CommentDAOProxy() throws Exception{
        
		this.dbc = new DBConnection();
		
		this.dao = new CommentDAOImpl(this.dbc.getConnection());
	}

	@Override
	public boolean doCreate(Comment vo) throws Exception {
		boolean flag = false;
		try {
			flag = this.dao.doCreate(vo);
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
		return flag;
	}

	@Override
	public boolean doUpdate(Comment vo) throws Exception {
		boolean flag = false;
		try {
			flag = this.dao.doUpdate(vo);
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
		return flag;
	}

	@Override
	public boolean doRemove(Comment vo) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Comment findById(String id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Comment> findAll(String keyWord) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Comment> findAll(String keyWord, int currentPage, int lineSize) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getAllCount(String keyWord) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Comment> findAll(Integer bookID) throws Exception {
		List<Comment> records = null;
		try {
			records = this.dao.findAll(bookID);
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
		return records;
	}

}

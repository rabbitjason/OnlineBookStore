package bookstore.proxy;

import java.util.List;
import java.util.Set;

import bookstore.bean.Book;
import bookstore.dao.IBookDAO;
import bookstore.dao.impl.BookDAOImpl;
import bookstore.db.DBConnection;

public class BookDAOProxy implements IBookDAO{

	private DBConnection dbc = null;
	private IBookDAO dao = null;
	
	public BookDAOProxy() throws Exception{
        
		this.dbc = new DBConnection();
               
		this.dao = new BookDAOImpl(this.dbc.getConnection());
	}
	
	
	@Override
	public boolean doCreate(Book vo) throws Exception {
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
	public boolean doUpdate(Book vo) throws Exception {
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
	public boolean doRemove(Book vo) throws Exception {
		boolean flag = false;
		try {
			flag = this.dao.doRemove(vo);
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
		return flag;
	}

	@Override
	public Book findById(String id) throws Exception {
		Book pro = null;
		try {
			pro = this.dao.findById(id);
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
		return pro;
	}

	@Override
	public List<Book> findAll(String keyWord) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Book> findAll(String keyWord, int currentPage, int lineSize) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getAllCount(String keyWord) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Book> findAll(Set<Integer> key) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void doUpdateCount(Integer id) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Book> findByType(String keyWord) throws Exception {
		List<Book> all = null;
		try {
			all = this.dao.findByType(keyWord);
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
		
		return all;
	}


	@Override
	public List<Book> findBySearchType(String keyWord, String searchType) throws Exception {
		List<Book> all = null;
		try {
			all = this.dao.findBySearchType(keyWord, searchType);
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
		
		return all;
	}

}

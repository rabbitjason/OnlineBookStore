package bookstore.proxy;

import java.util.List;
import java.util.Set;

import bookstore.bean.Cart;
import bookstore.dao.ICartDAO;
import bookstore.dao.impl.CartDAOImpl;
import bookstore.db.DBConnection;

public class CartDAOProxy implements ICartDAO {

	private DBConnection dbc = null;
	private ICartDAO dao = null;
	
	public CartDAOProxy() throws Exception{
        
		this.dbc = new DBConnection();
		
		this.dao = new CartDAOImpl(this.dbc.getConnection());
	}

	@Override
	public boolean doCreate(Cart vo) throws Exception {
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
	public boolean doUpdate(Cart vo) throws Exception {
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
	public boolean doRemove(Cart vo) throws Exception {
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
	public Cart findById(String id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Cart> findAll(String keyWord) throws Exception {
		List<Cart> records = null;
		try {
			records = this.dao.findAll(keyWord);
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
		return records;
	}

	@Override
	public List<Cart> findAll(String keyWord, int currentPage, int lineSize) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getAllCount(String keyWord) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Cart> findAll(Set<Integer> key) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void doUpdateCount(Integer id) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Cart find(Cart vo) throws Exception {	
		
		Cart ct = null;
		try {
			ct = this.dao.find(vo);
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
		
		return ct;
	}

}

package bookstore.proxy;

import java.util.List;
import java.util.Set;

import bookstore.bean.Order;

import bookstore.dao.IOrderDAO;
import bookstore.dao.impl.OrderDAOImpl;
import bookstore.db.DBConnection;

public class OrderDAOProxy implements IOrderDAO {

	private DBConnection dbc = null;
	private IOrderDAO dao = null;
	
	public OrderDAOProxy() throws Exception{
        
		this.dbc = new DBConnection();
		
		this.dao = new OrderDAOImpl(this.dbc.getConnection());
	}
	
	@Override
	public boolean doCreate(Order vo) throws Exception {
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
	public boolean doUpdate(Order vo) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean doRemove(Order vo) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Order findById(String id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Order> findAll(String keyWord) throws Exception {
		List<Order> records = null;
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
	public List<Order> findAll(String keyWord, int currentPage, int lineSize) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getAllCount(String keyWord) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Order> findAll(Set<Integer> key) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void doUpdateCount(Integer id) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean doUpdateSth(Order vo) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addOrders(List<Order> vs) throws Exception {
		boolean flag = false;
		try {
			flag = this.dao.addOrders(vs);
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
		return flag;
	}

	@Override
	public List<String> findOrderNums(String userid) throws Exception {
		
		List<String> records = null;
		try {
			records = this.dao.findOrderNums(userid);
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
		
		return records;
	}

}

package bookstore.dao.impl;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import bookstore.bean.Cart;
import bookstore.bean.Order;
import bookstore.dao.IOrderDAO;
import bookstore.util.GenerateNum;

public class OrderDAOImpl implements IOrderDAO {

	private Connection conn;
	private PreparedStatement pstmt = null;

	public OrderDAOImpl(Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public boolean doCreate(Order vo) throws Exception {
		boolean flag = false;
		   
		String sql = "INSERT INTO bs_order(user_id, book_id, quantity, fee, order_number)"
				+ " VALUES (?,?,?,?,?) ";
		
		this.pstmt = this.conn.prepareStatement(sql);
		this.pstmt.setString(1, vo.getUser_id());
		this.pstmt.setString(2, vo.getBook_id());
		this.pstmt.setInt(3, vo.getQuantity());
		this.pstmt.setInt(4, vo.getFee());
		this.pstmt.setString(5, vo.getOrder_number());
		
		if (this.pstmt.executeUpdate() > 0) {
			flag = true;
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
		
		List<Order> all = new ArrayList<Order>();
		
		String sql = "SELECT * FROM bs_order WHERE order_number=?";
		this.pstmt = this.conn.prepareStatement(sql);
		this.pstmt.setString(1, keyWord);
		ResultSet rs = this.pstmt.executeQuery() ;
		while (rs.next()) {
			Order ov = new Order();
			ov.setOrder_number(rs.getString("order_number"));
			ov.setBook_id(rs.getString("book_id"));
			ov.setFee(rs.getInt("fee"));
			ov.setQuantity(rs.getInt("quantity"));
			ov.setUser_id(rs.getString("user_id"));
			ov.setOrder_time(rs.getTimestamp("order_time"));
			all.add(ov);
		}

		if (all.size() > 0) {
			return all;
		}

		return null;
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
		// TODO Auto-generated method stub
		boolean flag = false;
		
		String ordernum = GenerateNum.getInstance().GenerateOrder();
		
		for (Order vo:vs) {
			vo.setOrder_number(ordernum);
			flag &= doCreate(vo);
		}
		
		return flag;
	}

	@Override
	public List<String> findOrderNums(String userid) throws Exception {	
		List<String> all = new ArrayList<String>();
		
		String sql = "SELECT distinct order_number FROM bs_order WHERE user_id=?";
		this.pstmt = this.conn.prepareStatement(sql);
		this.pstmt.setString(1, userid);
		ResultSet rs = this.pstmt.executeQuery() ;
		
		while (rs.next()) {
			all.add(rs.getString("order_number"));
		}

		if (all.size() > 0) {
			return all;
		}
		
		return null;
	}

}

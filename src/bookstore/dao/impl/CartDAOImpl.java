package bookstore.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import bookstore.bean.Cart;
import bookstore.dao.ICartDAO;

public class CartDAOImpl implements ICartDAO {

	private Connection conn;
	private PreparedStatement pstmt = null;

	public CartDAOImpl(Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public boolean doCreate(Cart vo) throws Exception {
		boolean flag = false;
	   
		String sql = "INSERT INTO bs_cart(user_id, book_id, quantity, fee)"
				+ " VALUES (?,?,?,?) ";
		this.pstmt = this.conn.prepareStatement(sql);
		this.pstmt.setString(1, vo.getUserid());
		this.pstmt.setString(2, vo.getBookid());
		this.pstmt.setInt(3, vo.getQuantity());
		this.pstmt.setInt(4, vo.getFee());
		
		if (this.pstmt.executeUpdate() > 0) {
			flag = true;
		}
		return flag;
	}

	@Override
	public boolean doUpdate(Cart vo) throws Exception {
		
		boolean flag = false;
		String sql = "UPDATE bs_cart SET quantity=?, fee=? WHERE user_id=? AND book_id=?";
		this.pstmt = this.conn.prepareStatement(sql);
		this.pstmt.setInt(1, vo.getQuantity());
		this.pstmt.setInt(2, vo.getFee());
		this.pstmt.setString(3, vo.getUserid());
		this.pstmt.setString(4, vo.getBookid());

		if (this.pstmt.executeUpdate() > 0) {
			flag = true;
		}
		return flag;
	}

	@Override
	public boolean doRemove(Cart vo) throws Exception {
		boolean flag = false;
		String sql = "DELETE FROM bs_cart WHERE user_id=? AND book_id=? ";
		this.pstmt = this.conn.prepareStatement(sql);
		this.pstmt.setString(1, vo.getUserid());
		this.pstmt.setString(2, vo.getBookid());
		if (this.pstmt.executeUpdate() > 0) {
			flag = true;
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
		List<Cart> all = new ArrayList<Cart>();
		
		String sql = "SELECT * FROM bs_cart WHERE user_id=?";
		this.pstmt = this.conn.prepareStatement(sql);
		this.pstmt.setString(1, keyWord);
		ResultSet rs = this.pstmt.executeQuery() ;

		while (rs.next()) {
			Cart ct = new Cart() ;
			ct.setBookid(rs.getString("book_id"));
			ct.setUserid(rs.getString("user_id"));
			ct.setQuantity(rs.getInt("quantity"));
			ct.setFee(rs.getInt("fee"));
			all.add(ct);
		}
		
		if (all.size() > 0) {
			return all;
		}
		
		return null;
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
		Cart ct = null ;
		String sql = "SELECT * FROM bs_cart WHERE user_id=? and book_id=?";
		this.pstmt = this.conn.prepareStatement(sql);
		this.pstmt.setString(1, vo.getUserid());
		this.pstmt.setString(2, vo.getBookid());
		ResultSet rs = this.pstmt.executeQuery() ;
		if (rs.next()) {
			ct = new Cart() ;
			ct.setBookid(rs.getString("book_id"));
			ct.setUserid(rs.getString("user_id"));
			ct.setQuantity(rs.getInt("quantity"));
			ct.setFee(rs.getInt("fee"));
		}
		
		return ct;
	}

}

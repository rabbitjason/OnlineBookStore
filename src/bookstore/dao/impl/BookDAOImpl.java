package bookstore.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import bookstore.bean.Book;
import bookstore.dao.IBookDAO;

public class BookDAOImpl implements IBookDAO {

	private Connection conn;
	private PreparedStatement pstmt = null;

	public BookDAOImpl(Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public boolean doCreate(Book vo) throws Exception {
		boolean flag = false;
	   
		String sql = "INSERT INTO bs_books "
				+ "(title,category_id,publisher,authors,introduction,price,saleprice,saledate,quantity,sale_type)"
				+ " VALUES (?,?,?,?,?,?,?,?,?) ";
		this.pstmt = this.conn.prepareStatement(sql);
		this.pstmt.setString(1, vo.getTitle());
		this.pstmt.setString(2, vo.getCategoryid());
		this.pstmt.setString(3, vo.getPublisher());
		this.pstmt.setString(4, vo.getAuthors());
		this.pstmt.setString(5, vo.getIntroduction());
		this.pstmt.setInt(6 ,vo.getPrice());
		this.pstmt.setInt(7, vo.getSaleprice());
		this.pstmt.setDate(8, new Date(vo.getSaledate().getTime()));
		this.pstmt.setInt(9, vo.getQuantity());
		this.pstmt.setString(10, vo.getSaleType());
		
		if (this.pstmt.executeUpdate() > 0) {
			flag = true;
		}
		
		return flag;
	}

	@Override
	public boolean doUpdate(Book vo) throws Exception {
		boolean flag = false;
		
		String sql = "UPDATE bs_books SET title=?,category_id=?,sale_type=?,authors=?,"
				+ "publisher=?,introduction=?,price=?,saleprice=?,saledate=?,quantity=? "
				+ "WHERE book_id=? ";
		this.pstmt = this.conn.prepareStatement(sql);
		this.pstmt.setString(1, vo.getTitle());
		this.pstmt.setString(2, vo.getCategoryid());
		this.pstmt.setString(3, vo.getSaleType());
		this.pstmt.setString(4, vo.getAuthors());
		this.pstmt.setString(5, vo.getPublisher());
		this.pstmt.setString(6, vo.getIntroduction());
		this.pstmt.setInt(7, vo.getPrice());
		this.pstmt.setInt(8, vo.getSaleprice());
		this.pstmt.setDate(9, new Date(vo.getSaledate().getTime()));
		this.pstmt.setInt(10, vo.getQuantity());
		this.pstmt.setString(12, vo.getBookid());
		if (this.pstmt.executeUpdate() > 0) {
			flag = true;
		}
		
		return flag;
	}

	@Override
	public boolean doRemove(Book vo) throws Exception {
		boolean flag = false;
		String sql = "DELETE FROM bs_books WHERE book_id=? ";
		this.pstmt = this.conn.prepareStatement(sql);
		this.pstmt.setString(1, vo.getBookid());
		if (this.pstmt.executeUpdate() > 0) {
			flag = true;
		}
		return flag;
	}

	@Override
	public Book findById(String id) throws Exception {
		Book pro = null;
		String sql = "SELECT * FROM bs_books WHERE book_id=?";
		this.pstmt = this.conn.prepareStatement(sql);
		this.pstmt.setInt(1, Integer.parseInt(id));
		ResultSet rs = this.pstmt.executeQuery();
		if (rs.next()) {
			pro = new Book();
			pro.setBookid(id);
			pro.setCategoryid(String.valueOf(rs.getInt("category_id")));
			pro.setTitle(rs.getString("title"));
			pro.setSaleType(rs.getString("sale_type"));
			pro.setAuthors(rs.getString("authors"));
			pro.setPublisher(rs.getString("publisher"));
			pro.setIntroduction(rs.getString("introduction"));
			pro.setPrice(rs.getInt("price"));		
			pro.setSaleprice(rs.getInt("saleprice"));
			pro.setSaledate(rs.getDate("saledate"));
			pro.setQuantity(rs.getInt("quantity"));

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
		String sql = "UPDATE bs_books SET count=count+1 WHERE book_id=?";
		this.pstmt = this.conn.prepareStatement(sql);
		this.pstmt.setInt(1, id);
		this.pstmt.executeUpdate();
	}

	@Override
	public List<Book> findByType(String keyWord) throws Exception {
		List<Book> all = new ArrayList<Book>();
		String sql = "SELECT * FROM bs_books WHERE category_id=?";
		this.pstmt = this.conn.prepareStatement(sql);
		this.pstmt.setInt(1, Integer.parseInt(keyWord));
		ResultSet rs = pstmt.executeQuery();
		
		while (rs.next()) {
			Book pro = new Book();
			pro.setBookid(String.valueOf(rs.getInt("book_id")));
			pro.setCategoryid(String.valueOf(rs.getInt("category_id")));
			pro.setTitle(rs.getString("title"));
			pro.setSaleType(rs.getString("sale_type"));
			pro.setAuthors(rs.getString("authors"));
			pro.setPublisher(rs.getString("publisher"));
			pro.setIntroduction(rs.getString("introduction"));
			pro.setPrice(rs.getInt("price"));		
			pro.setSaleprice(rs.getInt("saleprice"));
			pro.setSaledate(rs.getDate("saledate"));
			pro.setQuantity(rs.getInt("quantity"));
			all.add(pro);
		}
		
		if (all.size() > 0) {
			return all;
		}
		
		return null;
	}

	@Override
	public List<Book> findBySearchType(String keyWord, String searchType) throws Exception {
		List<Book> all = new ArrayList<Book>();
		String sql = "";
		if (Integer.parseInt(searchType) == 1) {//Title
			sql = "SELECT * FROM bs_books WHERE title LIKE ? ORDER BY title DESC";
		} else if (Integer.parseInt(searchType) == 2) {//Authors
			sql = "SELECT * FROM bs_books WHERE authors LIKE ? ORDER BY title ASC";
		}
		
		this.pstmt = this.conn.prepareStatement(sql);
		
		this.pstmt.setString(1, "%" + keyWord + "%");
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			Book pro = new Book();
			pro.setBookid(String.valueOf(rs.getInt("book_id")));
			pro.setCategoryid(String.valueOf(rs.getInt("category_id")));
			pro.setTitle(rs.getString("title"));
			pro.setSaleType(rs.getString("sale_type"));
			pro.setAuthors(rs.getString("authors"));
			pro.setPublisher(rs.getString("publisher"));
			pro.setIntroduction(rs.getString("introduction"));
			pro.setPrice(rs.getInt("price"));		
			pro.setSaleprice(rs.getInt("saleprice"));
			pro.setSaledate(rs.getDate("saledate"));
			pro.setQuantity(rs.getInt("quantity"));
			all.add(pro);
		}
		
		if (all.size() > 0) {
			return all;
		}
		
		return null;
	}

}

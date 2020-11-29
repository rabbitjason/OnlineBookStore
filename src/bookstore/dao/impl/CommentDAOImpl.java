package bookstore.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bookstore.bean.Comment;
import bookstore.dao.ICommentDAO;

public class CommentDAOImpl implements ICommentDAO {
	
	private Connection conn;
	private PreparedStatement pstmt = null;

	public CommentDAOImpl(Connection conn) {
		this.conn = conn;
	}

	@Override
	public boolean doCreate(Comment vo) throws Exception {
		boolean flag = false;
		   
		String sql = "INSERT INTO bs_comments(user_id, firstname, book_id, content, upvote_count)"
				+ " VALUES (?,?,?,?,?) ";
		this.pstmt = this.conn.prepareStatement(sql);
		this.pstmt.setInt(1, vo.getUser_id());
		this.pstmt.setString(2, vo.getFirstname());
		this.pstmt.setInt(3, vo.getBook_id());
		this.pstmt.setString(4, vo.getContent());
		this.pstmt.setInt(5, vo.getUpvote_count());
		
		if (this.pstmt.executeUpdate() > 0) {
			flag = true;
		}
		return flag;
	}

	@Override
	public boolean doUpdate(Comment vo) throws Exception {
		boolean flag = false;
		
		String sql = "UPDATE bs_comments SET upvote_count=? WHERE id=? ";

		this.pstmt = this.conn.prepareStatement(sql);
		this.pstmt.setInt(1, vo.getUpvote_count());
		this.pstmt.setInt(2, vo.getId());
		if (this.pstmt.executeUpdate() > 0) {
			flag = true;
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
		List<Comment> all = new ArrayList<Comment>();
		
		String sql = "SELECT * FROM bs_comments WHERE book_id=?";
		this.pstmt = this.conn.prepareStatement(sql);
		this.pstmt.setInt(1, bookID);
		ResultSet rs = this.pstmt.executeQuery() ;
		
		while (rs.next()) {
			Comment ov = new Comment();
			ov.setId(rs.getInt("id"));
			ov.setBook_id(rs.getInt("book_id"));
			ov.setUser_id(rs.getInt("user_id"));
			ov.setContent(rs.getString("content"));
			ov.setUpvote_count(rs.getInt("upvote_count"));
			ov.setFirstname(rs.getString("firstname"));
			ov.setCreatedtime(rs.getTimestamp("createdtime"));
			all.add(ov);
		}

		if (all.size() > 0) {
			return all;
		}
		
		return null;
	}

}

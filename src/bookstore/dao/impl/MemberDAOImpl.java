package bookstore.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bookstore.bean.Member;
import bookstore.dao.IMemberDAO;

public class MemberDAOImpl  implements IMemberDAO {

	private Connection conn;
	private PreparedStatement pstmt;

	public MemberDAOImpl(Connection conn) {
		this.conn = conn;
	}

	public void doUpdateLoginTime(String id) throws Exception {
		String sql = "UPDATE bs_users SET logintime=? WHERE id=?";
		this.pstmt = this.conn.prepareStatement(sql);
		this.pstmt.setDate(1, new java.sql.Date(new java.util.Date().getTime()));
		this.pstmt.setString(2, id);
		this.pstmt.executeUpdate();
	}

	public boolean findLogin(Member vo) throws Exception {
		boolean flag = false;
		String sql = "SELECT COUNT(id), id, firstname, lastname FROM bs_users WHERE email=? AND password=?";
		this.pstmt = this.conn.prepareStatement(sql);
		this.pstmt.setString(1, vo.getEmail());
		this.pstmt.setString(2, vo.getPassword());
		ResultSet rs = this.pstmt.executeQuery();
		
		if (rs.next()) {
			if (rs.getInt(1) > 0) {
				flag = true;
			}
			int id = rs.getInt(2);
			vo.setId(String.valueOf(id));
			vo.setFirstname(rs.getString("firstname"));
			vo.setLastname(rs.getString("lastname"));
			
		}
		return flag;
	}

	public boolean doCreate(Member vo) throws Exception {
		boolean flag = false;
		String sql = "INSERT INTO bs_users(password,email,lastname,firstname,mobilephone,address) VALUES (?,?,?,?,?,?) ";
		this.pstmt = this.conn.prepareStatement(sql);
		this.pstmt.setString(1, vo.getPassword());
		this.pstmt.setString(2, vo.getEmail());
		this.pstmt.setString(3, vo.getLastname());
		this.pstmt.setString(4, vo.getFirstname());
		this.pstmt.setString(5, vo.getMobilephone());
		this.pstmt.setString(6, vo.getAddress());
		
		//this.pstmt.setDate(4, new java.sql.Date(vo.getRegdate().getTime()));
		//this.pstmt.setString(5, vo.getPaypassword());
		//this.pstmt.setInt(6, vo.getMoney());
		if (this.pstmt.executeUpdate() > 0) {
			flag = true;
		}
		return flag;
	}

	public boolean doRemove(Member vo) throws Exception {
		
		throw new Exception("Undo.");
	}

	public boolean doUpdate(Member vo) throws Exception {
//		boolean flag = false;
//		String sql = "UPDATE member SET money=? WHERE mid=? ";
//		this.pstmt = this.conn.prepareStatement(sql);
//		this.pstmt.setLong(1, vo.getMoney());
//		this.pstmt.setString(2, vo.getMid());
//		
//		if (this.pstmt.executeUpdate() > 0) {
//			flag = true;
//		}
//		return flag;
		throw new Exception("Undo.");
	}

	public List<Member> findAll(String keyWord) throws Exception {
		throw new Exception("Undo.");
	}

	public List<Member> findAll(String keyWord, int currentPage, int lineSize)
			throws Exception {
		throw new Exception("Undo.");
	}

	public Member findById(String id) throws Exception {
		Member mem = null ;
		String sql = "SELECT id,password,email,lastname,firstname,mobilephone FROM bs_users WHERE id=?";
		this.pstmt = this.conn.prepareStatement(sql);
		this.pstmt.setString(1, id) ;
		ResultSet rs = this.pstmt.executeQuery() ;
		if(rs.next()){
			mem = new Member() ;
			mem.setId(rs.getString(1)) ;
			mem.setPassword(rs.getString(2));
			mem.setEmail(rs.getString(3));
			mem.setLastname(rs.getString(4));
			mem.setFirstname(rs.getString(5));
			mem.setMobilephone(rs.getString(6));
			
//			mem.setLastdate(rs.getDate(4)) ;
//			mem.setPaypassword(rs.getString(5)) ;
//			mem.setMoney(rs.getInt(6)) ;
		}
		return mem;
	}

	public long getAllCount(String keyWord) throws Exception {
		throw new Exception("Undo.");
	}
	
	public boolean findEmail(Member vo) throws Exception {
		boolean flag = false;
		//String sql = "SELECT COUNT(id) FROM bs_users WHERE email=? AND password=?";
		String sql = "SELECT COUNT(id) FROM bs_users WHERE email=?";
		this.pstmt = this.conn.prepareStatement(sql);
		this.pstmt.setString(1, vo.getEmail());
		//this.pstmt.setString(2, vo.getPassword());
		ResultSet rs = this.pstmt.executeQuery();
		if (rs.next()) {
			if (rs.getInt(1) > 0) {
				flag = true;
			}
		}
		return flag;
	}
	
	public int PageSize() throws Exception {
		int rowCount=0;
		String sql = "select count(*) from bs_users ";
		this.pstmt = this.conn.prepareStatement(sql);
		//this.pstmt.setString(1, id) ;
		ResultSet rs = this.pstmt.executeQuery() ;
		 while(rs.next())  
         {  
             rowCount=rs.getInt(1);  
         }
		return rowCount;
	}
	
	public List<Member>  findPage(String keyWord) throws Exception {
		List<Member> all = new ArrayList<Member>();
	    int pageSize=3;  
        int pageNow=1;   
        //String keyWord=null;
        String sql = "SELECT id,password,email,lastdate,paypassword,money FROM bs_users "+
				" WHERE id LIKE ? OR password LIKE ? OR email LIKE ? OR lastdate like ? OR paypassword like ? OR money like ?" ; 
		
		this.pstmt = this.conn.prepareStatement(sql);
		this.pstmt.setString(1, "%" + keyWord + "%");
		this.pstmt.setString(2, "%" + keyWord + "%");
		this.pstmt.setString(3, "%" + keyWord + "%");
		this.pstmt.setString(4, "%" + keyWord + "%");
		this.pstmt.setString(5, "%" + keyWord + "%");
		this.pstmt.setString(6, "%" + keyWord + "%");
		
		ResultSet rs = this.pstmt.executeQuery() ;
		
//		if(rs.next()){
//			Member mem = new Member() ;
//			mem.setMid(rs.getString(1)) ;
//			mem.setPassword(rs.getString(2)) ;
//			
//			mem.setEmail(rs.getString(3)) ;
//			
//			mem.setLastdate(rs.getDate(4)) ;
//			mem.setPaypassword(rs.getString(5)) ;
//			mem.setMoney(rs.getInt(6)) ;
//			all.add(mem);
//		}
		
		return all;
	}
}

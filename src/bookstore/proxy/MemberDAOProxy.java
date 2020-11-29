package bookstore.proxy;

import java.util.List;

import bookstore.bean.Member;
import bookstore.dao.IMemberDAO;
import bookstore.dao.impl.MemberDAOImpl;
import bookstore.db.DBConnection;

public class MemberDAOProxy implements IMemberDAO {
	private DBConnection dbc = null;
	private IMemberDAO dao = null;

	public MemberDAOProxy() throws Exception{
              
		this.dbc = new DBConnection();
               
		this.dao = new MemberDAOImpl(this.dbc.getConnection());
	}

	public void doUpdateLoginTime(String id) throws Exception {
		try {
			this.dao.doUpdateLoginTime(id);
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
	}

	public boolean findLogin(Member vo) throws Exception {
		boolean flag = false;
		try {
			flag = this.dao.findLogin(vo);
			if (flag) { // login success
				this.dao.doUpdateLoginTime(vo.getId()); // the latest login time
			}
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
		return flag;
	}

	public boolean doCreate(Member vo) throws Exception {
		boolean flag = false;
		try {
			if (!this.dao.findEmail(vo)) {
				flag = this.dao.doCreate(vo);
			}
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
		return flag;
	}

	public boolean doRemove(Member vo) throws Exception {
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

	public boolean doUpdate(Member vo) throws Exception {
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

	public List<Member> findAll(String keyWord) throws Exception {
		List<Member> all = null;
		try {
			all = this.dao.findAll(keyWord);
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
		return all;
	}

	public List<Member> findAll(String keyWord, int currentPage, int lineSize)
			throws Exception {
		List<Member> all = null;
		try {
			all = this.dao.findAll(keyWord, currentPage, lineSize);
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
		return all;
	}

	public Member findById(String id) throws Exception {
		Member mem = null;
		try {
			mem = this.dao.findById(id);
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
		return mem;
	}

	public long getAllCount(String keyWord) throws Exception {
		long count = 0;
		try {
			count = this.dao.getAllCount(keyWord);
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
		return count;
	}
	
	public boolean findEmail(Member vo) throws Exception {
		boolean flag = false;
		try {
			flag = this.dao.findEmail(vo);
			
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
		return flag;
	}

	@Override
	public int PageSize() throws Exception {
		// TODO Auto-generated method stub
		int rowCount = 0;
		try {
			rowCount = this.dao.PageSize();
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
		return rowCount;
	}

	@Override
	public List<Member> findPage(String keyWord) throws Exception {
		// TODO Auto-generated method stub
		List<Member> all = null;
		try {
			all = this.dao.findPage( keyWord);
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
		return all;
	}


}

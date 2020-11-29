package bookstore.dao;

import java.util.List;

import bookstore.bean.Member;

public interface IMemberDAO extends IDAO<Member, String> {

	public boolean findLogin(Member vo) throws Exception;

	public void doUpdateLoginTime(String id) throws Exception;
	
	public boolean findEmail(Member vo) throws Exception;

	public int PageSize() throws Exception;

	public List<Member> findPage(String keyWord) throws Exception;
}

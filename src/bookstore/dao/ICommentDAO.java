package bookstore.dao;

import java.util.List;

import bookstore.bean.Comment;

public interface ICommentDAO extends IDAO<Comment, Integer>  {
	
	public List<Comment> findAll(Integer bookID) throws Exception;

}

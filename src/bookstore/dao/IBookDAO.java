package bookstore.dao;

import java.util.List;
import java.util.Set;

import bookstore.bean.Book;

public interface IBookDAO extends IDAO<Book, Integer>{
	public List<Book> findAll(Set<Integer> key) throws Exception;

	public void doUpdateCount(Integer id) throws Exception ;

	public List<Book> findByType(String keyWord) throws Exception ;
	
	public List<Book> findBySearchType(String keyWord, String searchType)  throws Exception ;
}


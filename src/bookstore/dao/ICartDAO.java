package bookstore.dao;

import java.util.List;
import java.util.Set;

import bookstore.bean.Cart;

public interface ICartDAO extends IDAO<Cart, Integer> {

	public List<Cart> findAll(Set<Integer> key) throws Exception;

	public void doUpdateCount(Integer id) throws Exception ;
	
	public Cart find(Cart vo) throws Exception;
	
}

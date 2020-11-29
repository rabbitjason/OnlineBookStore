package bookstore.bean;

import java.io.Serializable;
import java.util.Date;

public class Book implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String bookid;
	private String title;
	private String publisher;
	private String authors;
	private String categoryid;
	private String introduction;
	private String saletype;
	private Date saledate;
	private Integer price;
	private Integer saleprice;
	private Integer quantity;
	
	
	public String getBookid() {
		return bookid;
	}
	public void setBookid(String bookid) {
		this.bookid = bookid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public String getAuthors() {
		return authors;
	}
	public void setAuthors(String authors) {
		this.authors = authors;
	}
	public String getCategoryid() {
		return categoryid;
	}
	public void setCategoryid(String categoryid) {
		this.categoryid = categoryid;
	}
	public String getIntroduction() {
		return introduction;
	}
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
	public Date getSaledate() {
		return saledate;
	}
	public void setSaledate(Date saledate) {
		this.saledate = saledate;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public Integer getSaleprice() {
		return saleprice;
	}
	public void setSaleprice(Integer saleprice) {
		this.saleprice = saleprice;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public String getSaleType() {
		return saletype;
	}
	public void setSaleType(String saletype) {
		this.saletype = saletype;
	}
	
	
}

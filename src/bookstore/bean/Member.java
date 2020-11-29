package bookstore.bean;

import java.io.Serializable;



import java.util.Date;

public class Member implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id ;
	private String password ;
	private String email ;
	private String lastname;
	private String firstname;
	private String mobilephone;
	private String address;
	private Date regtime;
	private Date logtime;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getLastname() {
		return lastname;
	}
	
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	
	public String getFirstname() {
		return firstname;
	}
	
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	
	public Date getRegdate() {
		return regtime;
	}
	public void setRegdate(Date regtime) {
		this.regtime = regtime;
	}

	public String getMobilephone() {
		return mobilephone;
	}
	public void setMobilephone(String mobilephone) {
		this.mobilephone = mobilephone;
	}
	public Date getLogtime() {
		return logtime;
	}
	public void setLogtime(Date logtime) {
		this.logtime = logtime;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
}
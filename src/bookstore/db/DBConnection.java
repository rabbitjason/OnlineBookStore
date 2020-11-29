package bookstore.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;


public class DBConnection {

	public static final String DBURL = "jdbc:mysql://127.0.0.1/bookshop";
	public static final String DBUSER = "root" ;
	public static final String DBPASSWORD = "Bs#123456" ;
	
	private Connection conn;
	
	public DBConnection() throws Exception {

		Properties info = new Properties();
		info.setProperty("user", DBUSER);
		info.setProperty("password", DBPASSWORD);
		this.conn = DriverManager.getConnection(DBURL, info);
		//this.conn = DriverManager.getConnection(DBURL, DBUSER, DBPASSWORD) ;
	}
	
	public Connection getConnection(){
		return this.conn ;
	}
	
	public void close() throws Exception {
		if(this.conn != null){
			try{
				this.conn.close() ;
			}catch(Exception e){
				throw e ;
			}
		}
	}
}

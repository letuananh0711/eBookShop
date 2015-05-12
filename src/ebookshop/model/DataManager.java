package ebookshop.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Hashtable;

import com.hp.hpl.jena.db.*;

import ebookshop.beans.Author;
import ebookshop.beans.BookRDF;
import ebookshop.beans.Category;
import ebookshop.beans.Comment;
import ebookshop.beans.Customer;
import ebookshop.beans.ExtraComment;
import ebookshop.beans.Order;
import ebookshop.beans.Publisher;
import ebookshop.model.CategoryPeer;
import ebookshop.beans.Book;
import ebookshop.model.BookPeer;
import ebookshop.beans.CartItem;
import ebookshop.model.OrderDetailsPeer;
import ebookshop.model.OrderPeer;


public class DataManager {

	// Attribute
	private String dbURL = "";
	private String dbUserName = "";
	private String dbPassword = "";
	
	// Method
	public String getDbURL() {
		return dbURL;
	}
	public void setDbURL(String dbURL) {
		this.dbURL = dbURL;
	}
	public String getDbUserName() {
		return dbUserName;
	}
	public void setDbUserName(String dbUserName) {
		this.dbUserName = dbUserName;
	}
	public String getDbPassword() {
		return dbPassword;
	}
	public void setDbPassword(String dbPassword) {
		this.dbPassword = dbPassword;
	}
	
	/*
	 * Connection to DB
	 */
	public Connection getConnection(){
		Connection connection = null;
	
		try {
			connection = DriverManager.getConnection(getDbURL(), getDbUserName(), getDbPassword());
		} catch (SQLException e) {
			System.out.println("Could not connect to database: " + e.getMessage());
		}
		
		return connection;
	}
	
	// Connection to DB for SW
	public IDBConnection getConnectionSW(){
		IDBConnection idbConnection = null;
		
		try{
			Class.forName("com.mysql.jdbc.Driver");
			idbConnection = new DBConnection(getDbURL(), getDbUserName(), getDbPassword(), "MySQL");
		}
		catch (Exception e) {
		}
		return idbConnection;
	}
	
	/*
	 * Close connection
	 */
	public void putConnection(Connection conn){
		if(conn != null){
			try {
				conn.close();
			} catch (SQLException e) {
				System.out.println("Could not close database: " + e.getMessage());
			}
		}
	}
	
	//-------------------Hàm xử lý Category---------------------
	public String getCategoryName(int categoryID) {
	    Category category = CategoryPeer.getCategoryByID(this, categoryID);
	    return (category == null) ? null : category.getNomGenreLivre();
	}

	public ArrayList<Category> getCategories() {
	    return CategoryPeer.getAllCategories(this);
	}
	
	public int insertCategory(Category category){
		
		int result = 0;
		
		Connection connection = getConnection();
		if(connection != null){
			Statement statement = null;
			try{
				connection.setAutoCommit(false);
				statement = connection.createStatement();
				try{
					CategoryPeer.insertCaterogy(statement, category);
					try{statement.close();}
					finally {statement = null;}
					connection.commit();
					result = 1;
				}
				catch (Exception e){
					System.out.println("Could not insert this category: " + e.getMessage());
					try{connection.rollback();}
					catch (Exception ee){ee.getMessage();};
				}
			}
			catch (Exception e){
				System.out.println("Could not insert this category: " + e.getMessage());
			}
			finally{
				if(statement != null){
					try{statement.close();}
					catch (Exception e){}
				}
				putConnection(connection);
			}
		}
		
		return result;
	}
	
	//-------------------Hàm xử lý Book-------------------------
	public ArrayList<Book> getSearchResults(String keyword) {
	    return BookPeer.searchBooks(this, keyword);
	}

	public ArrayList<Book> getBooksInCategory(int categoryID) {
	    return BookPeer.getBooksByCategory(this, categoryID);
	}

	public Book getBookDetails(int bookID) {
	    return BookPeer.getBookByID(this, bookID);
	}
	
	public ArrayList<Book> getBooks(){
		return BookPeer.getAllBooks(this);
	}
	
	public int insertBook(Book book){
		int result = 0;
		
		Connection connection = getConnection();
		if(connection != null){
			Statement statement = null;
			try{
				connection.setAutoCommit(false);
				statement = connection.createStatement();
				try{
					BookPeer.insertBook(book, statement);
					try{statement.close();}
					finally {statement = null;}
					connection.commit();
					result = 1;
				}
				catch (Exception e){
					System.out.println("Could not insert this book: " + e.getMessage());
					try{connection.rollback();}
					catch (Exception ee){ee.getMessage();};
				}
			}
			catch (Exception e){
				System.out.println("Could not insert this book: " + e.getMessage());
			}
			finally{
				if(statement != null){
					try{statement.close();}
					catch (Exception e){}
				}
				putConnection(connection);
			}
		}
		return result;
	}
	
	
	public ArrayList<BookRDF> getBookFromDbPedia(String keyWord){
		return BookRDFPeer.searchBookSW(keyWord);
	}

	public int createRDFDocument(String bName){
		return BookRDFPeer.createRDFDocument(this, bName);
	}
	
	public ArrayList<Book> getNewBooks(){
		return BookPeer.getNewBooks(this);
	} 
	
	public int getRateImage(int bookId){
		return BookPeer.getRateImage(this, bookId);
	}
	
	public ArrayList<Book> getTopRateBooks(){
		return BookPeer.getTopRateBooks(this);
	}
	
	public String getBookAvailability(int bookId){
		return BookPeer.getBookAvailability(this, bookId);
	}
	
	//-----------------------Author-----------------------------
	public Author getAuthorInfo(String aName){
		return AuthorPeer.getAuthorInfo(aName);
	}
	
	//-----------------------Publisher--------------------------
	public Publisher getPublisherInfo(String pName){
		return PublisherPeer.getPublisherInfo(pName);
	}
	
	//-------------------Hàm xử lý Register---------------------
	
	public int isUserNameExist(String userName){
		return RegisterPeer.isUserNameExist(this, userName);
	}
	
	public int insertCustomer(Customer customer){
		
		int result = 0;
		
		Connection connection = getConnection();
		if(connection != null){
			Statement statement = null;
			try{
				connection.setAutoCommit(false);
				statement = connection.createStatement();
				try{
					RegisterPeer.insertCustomer(statement, customer);
					try{statement.close();}
					finally{statement = null;}
					connection.commit();
					result = 1;
				}
				catch (Exception e){
					System.out.println("Could not insert this customer: " + e.getMessage());
					try{connection.rollback();}
					catch (Exception ee){ee.getMessage();};
				}
			}
			catch (Exception e){
				System.out.println("Could not insert this cutomer: " + e.getMessage());
			}
			finally{
				if(statement != null){
					try{statement.close();}
					catch (Exception e){}
				}
				putConnection(connection);
			}
		}
		return result;
	}
	//-------------------Hàm xử lý Login------------------------
	public int checkUserLogin(String userName, String passwd){
		return LoginPeer.userLogin(this, userName, passwd);
	}
	
	//-------------------Hàm xử lý Customer---------------------
	public ArrayList<Customer> getCustomers() {
	    return CustomerPeer.getAllCustomers(this);
	}
	
	public Customer getCustomer(String userName){
		return CustomerPeer.getCustomerByUserName(this, userName);
	}
	
	//-------------------Hàm xử lý Order------------------------
	public ArrayList<Order> getOrders(){
		return OrderPeer.getAllOrders(this);
	}
	
	public long insertOrder(int userID, Hashtable<String, CartItem> shoppingCart){
		long returnValue = 0;
	    long orderId = System.currentTimeMillis();
	    
	    Connection connection = getConnection();
	    if (connection != null) {
	      Statement stmt = null;
	      try {
	        connection.setAutoCommit(false);
	        stmt = connection.createStatement();
	        try {
	          OrderPeer.insertOrder(stmt, orderId, userID);
	          OrderDetailsPeer.insertOrderDetails(stmt, orderId, shoppingCart);
	          try { stmt.close(); }
	          finally { stmt = null; }
	          connection.commit();
	          returnValue = orderId;
	          }
	        catch (SQLException e) {
	          System.out.println("Could not insert order: " + e.getMessage());
	          try { connection.rollback(); }
	          catch (SQLException ee) { }
	          }
	        }
	      catch (SQLException e) {
	        System.out.println("Could not insert order: " + e.getMessage());
	        }
	      finally {
	        if (stmt != null) {
	          try { stmt.close(); }
	          catch (SQLException e) { }
	          }
	        putConnection(connection);
	        }
	      }
	    return returnValue;
	}
	
	//-------------------Hàm xử lý Comment------------------------
	public ArrayList<Comment> getComments(){
		return CommentPeer.getAllComments(this);
	}
	
	public int insertComment(Comment c){
		int result = 0;
		
		Connection connection = getConnection();
		if(connection != null){
			Statement statement = null;
			try{
				connection.setAutoCommit(false);
				statement = connection.createStatement();
				try{
					CommentPeer.insertComment(statement, c);
					try{statement.close();}
					finally {statement = null;}
					connection.commit();
					result = 1;
				}
				catch (Exception e){
					System.out.println("Could not insert this comment: " + e.getMessage());
					try{connection.rollback();}
					catch (Exception ee){ee.getMessage();};
				}
			}
			catch (Exception e){
				System.out.println("Could not insert this comment: " + e.getMessage());
			}
			finally{
				if(statement != null){
					try{statement.close();}
					catch (Exception e){}
				}
				putConnection(connection);
			}
		}
		return result;
	}
	
	public ArrayList<Comment> getValidComments(int bookId){
		return CommentPeer.getValidComments(this, bookId);
	}
	
	public float getBookAvgRate(int bookId){
		return CommentPeer.getBookAvgRate(this, bookId);
	}
	
	public int getNumCommentByBookId(int bookId){
		return CommentPeer.getNumCommentByBookId(this, bookId);
	}
	
	public ArrayList<ExtraComment> getExtraComments(String isbn, String site){
		return ExtraCommentPeer.getExtraComments(isbn, site);
	}
}

package ebookshop.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import ebookshop.beans.Book;

public class BookPeer {

	 //Method search a book from a keyword base on the name of book and the author of book 
	public static ArrayList<Book> searchBooks (DataManager dataManager, String keyword){
		
		ArrayList<Book> books = new ArrayList<>();
		
		Connection connection = dataManager.getConnection();
		
		if(connection != null){
			try {
				Statement s = connection.createStatement();
				String query = "select nomLivre, auteurLivre, editeurLivre, prixLivre, ISBN, idLivre " 
						+ "from Livre "
						+ "where nomLivre like '%" + keyword.trim() + "%' "
						+ "or auteurLivre like '%" + keyword.trim() + "%'";
				try{
					ResultSet rs = s.executeQuery(query);
					try{
						while(rs.next())
						{
							Book book = new Book();
							book.setNomLivre(rs.getString(1));
							book.setAuteurLivre(rs.getString(2));
							book.setEditeurLivre(rs.getString(3));
							book.setPrixLivre(rs.getFloat(4));
							book.setISBN(rs.getString(5));
							book.setIdLivre(rs.getInt(6));
							books.add(book);
						}
					}
					finally{
						rs.close();
					}
				}
				finally{
					s.close();
				}
			} 
			catch (SQLException e) {
				System.out.println("Could not search for book: " + e.getMessage());
			}
			finally{
				dataManager.putConnection(connection);
			}
		}
		return books;
	}
	
	// Method get books by category
	public static ArrayList<Book> getBooksByCategory(DataManager dataManager, int categoryID){
		ArrayList<Book> books = new ArrayList<Book>();
		
		Connection connection = dataManager.getConnection();
		if(connection != null){
			String query = "select nomLivre, auteurLivre, editeurLivre, prixLivre, ISBN, idLivre "
					+ "from Livre where idGenreLivre = " + categoryID;
			try {
				Statement s = connection.createStatement();
				try{
					ResultSet rs = s.executeQuery(query);
					try{
						while(rs.next()){
							Book book = new Book();
							book.setNomLivre(rs.getString(1));
							book.setAuteurLivre(rs.getString(2));
							book.setEditeurLivre(rs.getString(3));
							book.setPrixLivre(rs.getFloat(4));
							book.setISBN(rs.getString(5));
							book.setIdLivre(rs.getInt(6));
							books.add(book);
						}
					}finally{
						rs.close();
					}
				} finally{
					s.close();
				}
			} catch (SQLException e) {
				System.out.println("Could not get any book: " + e.getMessage());
			} finally{
				dataManager.putConnection(connection);
			}
		}
		
		return books;
	}
	
	// Method get book by ID
	public static Book getBookByID(DataManager dataManager, int bookID){
		Book book = new Book();
		
		Connection connection = dataManager.getConnection();
		
		String query = "select nomLivre, auteurLivre, editeurLivre, prixLivre, ISBN, resume, image, idLivre "
				+ "from Livre where idLivre = " + bookID;
		
		if(connection != null){
			try {
				Statement s = connection.createStatement();
				try{
					ResultSet rs = s.executeQuery(query);
					try{
						while(rs.next()){
							book.setNomLivre(rs.getString(1));
							book.setAuteurLivre(rs.getString(2));
							book.setEditeurLivre(rs.getString(3));
							book.setPrixLivre(rs.getFloat(4));
							book.setISBN(rs.getString(5));
							book.setResume(rs.getString(6));
							book.setImage(rs.getString(7));
							book.setIdLivre(rs.getInt(8));
						}
					} finally{
						rs.close();
					}
				} finally{
					s.close();
				}
			} catch (SQLException e) {
				System.out.println("Could not get book: " + e.getMessage());
			} finally{
				dataManager.putConnection(connection);
			}
		}
		return book;
	}
	
	// Method get all book
	public static ArrayList<Book> getAllBooks(DataManager dataManager){
		ArrayList<Book> booksList = new ArrayList<Book>();
		
		Connection connection = dataManager.getConnection();
		
		if(connection != null){
			try {
				Statement s = connection.createStatement();
				String query = "select * from Livre";
				try{
					ResultSet rs = s.executeQuery(query);
					try{
						while(rs.next()){
							Book book = new Book();
							book.setIdLivre(rs.getInt(1));
							book.setNomLivre(rs.getString(2));
							book.setAuteurLivre(rs.getString(3));
							book.setEditeurLivre(rs.getString(4));
							book.setQuantiteLivre(rs.getInt(5));
							book.setPrixLivre(rs.getFloat(6));
							book.setImage(rs.getString(7));
							book.setResume(rs.getString(8));
							book.setISBN(rs.getString(9));
							book.setIdGenreLivre(rs.getInt(10));
							booksList.add(book);
						}
					}finally {rs.close();}
				}
				finally{s.close();}
			} catch (SQLException e) {
				System.out.println("Could not get book list: " + e.getMessage());
			}finally{
				dataManager.putConnection(connection);
			}
		}
		return booksList;
	}
	
	// Insert book
	public static void insertBook(Book book, Statement stm) throws SQLException {
		
		String livreResume = book.getResume().replace("'", "\\'");
		String isbn = book.getISBN().replace("-", "");
		String nomLivre = book.getNomLivre().replace("'", "\\'");
		
		String query = "insert into Livre (nomLivre, auteurLivre, editeurLivre, quantiteLivre, " +
				"prixLivre, image, resume, ISBN, idGenreLivre) values ('"
				+ nomLivre + "', '"
				+ book.getAuteurLivre() + "', '"
				+ book.getEditeurLivre() + "', "
				+ book.getQuantiteLivre() + ", "
				+ book.getPrixLivre() + ", '"
				+ book.getImage() + "', '"
				+ livreResume + "', '"
				+ isbn + "', "
				+ book.getIdGenreLivre() + ")";
			stm.executeUpdate(query);
	}
	
	// Get 4 new books
	public static ArrayList<Book> getNewBooks(DataManager dataManager){
		ArrayList<Book> booksList = new ArrayList<Book>();
		
		Connection connection = dataManager.getConnection();
		
		if(connection != null){
			try {
				Statement s = connection.createStatement();
				String query = "select * from Livre order by idLivre DESC limit 4";
				try{
					ResultSet rs = s.executeQuery(query);
					try{
						while(rs.next()){
							Book book = new Book();
							book.setIdLivre(rs.getInt(1));
							book.setNomLivre(rs.getString(2));
							book.setAuteurLivre(rs.getString(3));
							book.setEditeurLivre(rs.getString(4));
							book.setQuantiteLivre(rs.getInt(5));
							book.setPrixLivre(rs.getFloat(6));
							book.setImage(rs.getString(7));
							book.setResume(rs.getString(8));
							book.setISBN(rs.getString(9));
							book.setIdGenreLivre(rs.getInt(10));
							booksList.add(book);
						}
					}finally {rs.close();}
				}
				finally{s.close();}
			} catch (SQLException e) {
				System.out.println("Could not get new book list: " + e.getMessage());
			}finally{
				dataManager.putConnection(connection);
			}
		}
		return booksList;
	}
	
	// get book rate image
	public static int getRateImage(DataManager dataManager, int bookId){
		int rateImg = 0;
		float avgBookRate = dataManager.getBookAvgRate(bookId);
		
		if(avgBookRate <= 2){
			rateImg = 1;
		}
		else if(avgBookRate > 2 && avgBookRate <= 4){
			rateImg = 2;
		}
		else if(avgBookRate > 4 && avgBookRate <= 7){
			rateImg = 3;
		}
		else if(avgBookRate > 7 && avgBookRate <= 9){
			rateImg = 4;
		}
		else{
			rateImg = 5;
		}
		
		return rateImg;
	}
	
	// get 4 top rate books 
	public static ArrayList<Book> getTopRateBooks(DataManager dataManager){
		ArrayList<Book> booksList = new ArrayList<Book>();
		
		Connection connection = dataManager.getConnection();
		
		if(connection != null){
			try {
				Statement s = connection.createStatement();
				String query = "select l.idLivre, l.nomLivre, l.auteurLivre, l.editeurLivre, l.prixLivre, l.image, l.isbn, round(avg(point), 2) as avgPoint "
							+ "from livre l, commentaire c "
							+ "where l.idLivre = c.idLivre "
							+ "group by (idLivre) "
							+ "order by (avgPoint) DESC "
							+ "limit 4";
				try{
					ResultSet rs = s.executeQuery(query);
					try{
						while(rs.next()){
							Book book = new Book();
							book.setIdLivre(rs.getInt(1));
							book.setNomLivre(rs.getString(2));
							book.setAuteurLivre(rs.getString(3));
							book.setEditeurLivre(rs.getString(4));
							book.setPrixLivre(rs.getFloat(5));
							book.setImage(rs.getString(6));
							book.setISBN(rs.getString(7));
							booksList.add(book);
						}
					}finally {rs.close();}
				}
				finally{s.close();}
			} catch (SQLException e) {
				System.out.println("Could not get new top rate book list: " + e.getMessage());
			}finally{
				dataManager.putConnection(connection);
			}
		}
		return booksList;
	}
	
	// Book availability or not?
	public static String getBookAvailability(DataManager dataManager, int bookId){
		String result = null;
		int quantity = 0;
		
		Connection connection = dataManager.getConnection();
		
		if(connection != null){
			try {
				Statement s = connection.createStatement();
				String query = "select quantiteLivre "
							+ "from livre "
							+ "where idLivre = " + bookId;
				try{
					ResultSet rs = s.executeQuery(query);
					try{
						while(rs.next()){
							quantity = rs.getInt(1);
						}
						if(quantity > 0){
							result = "In stock";
						}
						else{
							result = "Out of stock";
						}
					}finally {rs.close();}
				}
				finally{s.close();}
			} catch (SQLException e) {
				System.out.println("Could not get quantity of this book: " + e.getMessage());
			}finally{
				dataManager.putConnection(connection);
			}
		}
		
		return result;
	}
}

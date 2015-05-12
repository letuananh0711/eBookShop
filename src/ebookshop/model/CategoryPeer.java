package ebookshop.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import ebookshop.beans.Category;

public class CategoryPeer {

	/*
	 * Get all categories
	 */
	public static ArrayList<Category> getAllCategories(DataManager dataManager){
		
		ArrayList<Category> categories = new ArrayList<Category>();
		
		Connection connection = dataManager.getConnection();
		
		if(connection != null)
		{
			try {
				Statement s = connection.createStatement();
				String query = "select * from Genre_Livre";
				try{
					ResultSet rs = s.executeQuery(query);
					try{
						while(rs.next()){
							Category category = new Category(rs.getInt(1), rs.getString(2), rs.getString(3));
							categories.add(category);
						}
					}
					finally{
						rs.close();
					}
				}
				finally{
					s.close();
				}
				
			} catch (SQLException e) {
				System.out.println("Could not get any category" + e.getMessage());
			}
			finally{
				dataManager.putConnection(connection);
			}
		}
		return categories;
	}
	
	
	/*
	 * Get category by ID
	 */
	public static Category getCategoryByID(DataManager dataManager, int categoryID){
		Category category = null;
		
		Connection connection = dataManager.getConnection();
		
		if(connection != null){
			try {
				Statement s = connection.createStatement();
				String query = "select * from Genre_Livre where idGenreLivre = " + categoryID;
				try{
					ResultSet rs = s.executeQuery(query);
					try{
						while(rs.next()){
							category = new Category(rs.getInt(1), rs.getString(2), rs.getString(3));
						}
					}
					finally{
						rs.close();
					}
				}
				finally{
					s.close();
				}
			} catch (SQLException e) {
				System.out.println("Could not get any category " + e.getMessage());
			}
			finally{
				dataManager.putConnection(connection);
			}
		}
		return category;
	}
	
	public static void insertCaterogy(Statement s, Category category) throws SQLException{
		
		String nomCate = category.getNomGenreLivre().replace("'", "\\'");
		String desCate = category.getDescription().replace("'", "\\'");
		
		String query = "insert into Genre_Livre (nomGenreLivre, description) values " +
				"('" + nomCate + "', '" + desCate + "')";
		
		s.executeUpdate(query);
	}
}

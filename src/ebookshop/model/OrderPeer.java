package ebookshop.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import ebookshop.beans.Order;

public class OrderPeer {

	public static ArrayList<Order> getAllOrders(DataManager dataManager){
		ArrayList<Order> ordersList = new ArrayList<Order>();
		
		Connection connection = dataManager.getConnection();
		
		if(connection != null){
			try {
				Statement s = connection.createStatement();
				String query = "select * from Commande";
				try{
					ResultSet rs = s.executeQuery(query);
					try{
						while(rs.next()){
							Order order = new Order();
							order.setIdCommande(rs.getLong(1));
							order.setDateCommande(rs.getString(2));
							order.setIdMembre(rs.getInt(3));
							ordersList.add(order);
						}
					}finally {rs.close();}
				}
				finally{s.close();}
			} catch (SQLException e) {
				System.out.println("Could not get order list: " + e.getMessage());
			}finally{
				dataManager.putConnection(connection);
			}
		}
		return ordersList;
	}
	
	public static void insertOrder(Statement stmt, long orderId, int userID) throws SQLException {
		Date currentDateTime = new Date();
		SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd h:m:s");
		
	    String query = "insert into Commande (idCommande, dateCommande, idMembre) values "
	    		+ "(" + orderId + ", '"
	    		+ dateFormatter.format(currentDateTime) + "', "
	    		+ userID + ")";
	    stmt.executeUpdate(query);
	    }
}

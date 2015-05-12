package ebookshop.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import ebookshop.beans.Customer;

public class CustomerPeer {

	public static ArrayList<Customer> getAllCustomers(DataManager dataManager){
		
		ArrayList<Customer> customersList = new ArrayList<Customer>();
		
		Connection connection = dataManager.getConnection();
		
		if(connection != null){
			try {
				Statement s = connection.createStatement();
				String query = "select * from Membre";
				try{
					ResultSet rs = s.executeQuery(query);
					try{
						while(rs.next()){
							Customer customer = new Customer();
							customer.setIdMembre(rs.getInt(1));
							customer.setPrenomMembre(rs.getString(2));
							customer.setNomMembre(rs.getString(3));
							customer.setSexe(rs.getString(4));
							customer.setDateNaissance(rs.getString(5));
							customer.setEmailMembre(rs.getString(6));
							customer.setTelephone(rs.getString(7));
							customer.setAdresse(rs.getString(8));
							customer.setUserName(rs.getString(9));
							customer.setPasswd(rs.getString(10));
							customer.setDateCreation(rs.getString(11));
							customer.setEtat(rs.getInt(12));
							customer.setPrivilege(rs.getInt(13));
							customersList.add(customer);
						}
					}finally {rs.close();}
				}
				finally{s.close();}
			} catch (SQLException e) {
				System.out.println("Could not get customer list: " + e.getMessage());
			}finally{
				dataManager.putConnection(connection);
			}
		}
		return customersList;
	}
	
	public static Customer getCustomerByUserName(DataManager dataManager, String userName){
		Connection connection = dataManager.getConnection();

		Customer customer = new Customer();
		
		if(connection != null){
			try {
				Statement s = connection.createStatement();
				String query = "select * from Membre where userName = '" + userName + "'";
				try{
					ResultSet rs = s.executeQuery(query);
					try{
						while(rs.next()){
							customer.setIdMembre(rs.getInt(1));
							customer.setPrenomMembre(rs.getString(2));
							customer.setNomMembre(rs.getString(3));
							customer.setSexe(rs.getString(4));
							customer.setDateNaissance(rs.getString(5));
							customer.setEmailMembre(rs.getString(6));
							customer.setTelephone(rs.getString(7));
							customer.setAdresse(rs.getString(8));
							customer.setUserName(rs.getString(9));
							customer.setPasswd(rs.getString(10));
							customer.setDateCreation(rs.getString(11));
							customer.setEtat(rs.getInt(12));
							customer.setPrivilege(rs.getInt(13));
						}
					}finally {rs.close();}
				}
				finally{s.close();}
			} catch (SQLException e) {
				System.out.println("Could not get customer: " + e.getMessage());
			}finally{
				dataManager.putConnection(connection);
			}
		}
		return customer;
	}
	
}

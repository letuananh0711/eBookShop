package ebookshop.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import ebookshop.beans.Customer;


public class LoginPeer {
	
	public static ArrayList<Customer> getAllCustomers(DataManager dataManager){
		
		ArrayList<Customer> customers = new ArrayList<>();
		
		Connection connection = dataManager.getConnection();
		
		if(connection != null){
			try{
				Statement s = connection.createStatement();
				String query = "select userName, passwd from Membre";
				try{
					ResultSet rs = s.executeQuery(query);
					try{
						while(rs.next()){
						
							Customer customer = new Customer();
							customer.setUserName(rs.getString(1));
							customer.setPasswd(rs.getString(2));
							customers.add(customer);
						}
					}finally{rs.close();}
				}finally{s.close();}
			}catch (SQLException e){
				System.out.println("Could not get any customer: " + e.getMessage());
			}finally{
				dataManager.putConnection(connection);
			}
		}
		return customers;
	}
	
	
	
	public static int userLogin(DataManager dataManager, String userName, String passwd){
		int result = 0; // khong tim thay customer nao tuong ung voi username va passwd nhap vao
		
		ArrayList<Customer> listCustomers = getAllCustomers(dataManager);
		
		if(userName != null && passwd != null){
			for(int i = 0; i < listCustomers.size(); i++){
				if(listCustomers.get(i).getUserName().equals(userName) 
						&& listCustomers.get(i).getPasswd().equals(passwd)){
					return result = 1;
				}
			}
		}
		return result;
	}
}

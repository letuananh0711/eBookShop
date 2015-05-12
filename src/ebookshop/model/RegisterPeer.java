package ebookshop.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import ebookshop.beans.Customer;

public class RegisterPeer {

	public static int isUserNameExist(DataManager dataManager, String userName){
		int count = 0;
		int result = 0;
		
		Connection connection = dataManager.getConnection();
		if(connection != null){
			try {
				Statement s = connection.createStatement();
				String query = "select * from Membre where userName = '" + userName + "'";
				try{
					ResultSet rs = s.executeQuery(query);
					try{
						while(rs.next()){
							count++;
						}
						if(count > 0){
							result = 1;
						}
					}
					finally{
						rs.close();
					}
				}
				finally {
					s.close();
				}
			} 
			catch (SQLException e) {
			}
			finally{
				dataManager.putConnection(connection);
			}
			
		}
		return result;
	}
	
	public static void insertCustomer(Statement stm, Customer customer) throws SQLException {
		
		Date currentDateTime = new Date();
		SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd h:m:s");
		
		String query = "insert into Membre (nomMembre, prenomMembre, sexe, "
				+ "dateNaissance, emailMembre, telephone, adresse, userName, passwd, "
				+ "dateCreation, etatCompte, privilege) values ('"
				+ customer.getNomMembre() + "', '"
				+ customer.getPrenomMembre() + "', "
				+ customer.getSexe() + ", '"
				+ customer.getDateNaissance() + "', '"
				+ customer.getEmailMembre() + "', '"
				+ customer.getTelephone() + "', '"
				+ customer.getAdresse() + "', '"
				+ customer.getUserName() + "', '"
				+ customer.getPasswd() + "', '"
				+ dateFormatter.format(currentDateTime) + "', 1, 1" + ")";
		stm.executeUpdate(query);
	}	
}

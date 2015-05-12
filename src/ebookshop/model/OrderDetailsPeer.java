package ebookshop.model;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.Enumeration;
import java.util.Hashtable;

import ebookshop.beans.CartItem;

public class OrderDetailsPeer {
	public static void insertOrderDetails(Statement stmt, long orderId, Hashtable<String, CartItem> shoppingCart) throws SQLException {
	    String query;
	    Enumeration<CartItem> enumList = shoppingCart.elements();
	    while (enumList.hasMoreElements()) {
			CartItem item = enumList.nextElement();
			query = "insert into detail_commande (idCommande, idLivre, quantiteDC, prixDC) values "
			  + "(" + orderId + ", "
			  + item.getIdLivre() + ", "
			  + item.getQuantiteLivre() + ", "
			  + item.getPrixLivre() + ")";
			stmt.executeUpdate(query);
	    }
	}
}

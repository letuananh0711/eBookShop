package ebookshop.beans;

public class Order {
	
	// Attribute
	private long idCommande;
	private String dateCommande;
	private int idMembre;
	
	// Method
	public long getIdCommande() {
		return idCommande;
	}
	public String getDateCommande() {
		return dateCommande;
	}
	public int getIdMembre() {
		return idMembre;
	}
	public void setIdCommande(long idCommande) {
		this.idCommande = idCommande;
	}
	public void setDateCommande(String dateCommande) {
		this.dateCommande = dateCommande;
	}
	public void setIdMembre(int idMembre) {
		this.idMembre = idMembre;
	}
	
	
}

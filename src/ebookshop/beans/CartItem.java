package ebookshop.beans;

public class CartItem {

	// Attribute
	private int idLivre;
	private String nomLivre;
	private String auteurLivre;
	private float prixLivre;
	private String quantiteLivre;
	
	// Method
	public CartItem(){}
	public CartItem (Book book, int quantity){
		this.quantiteLivre = new Integer(quantity).toString();
		idLivre = book.getIdLivre();
		nomLivre = book.getNomLivre();
		auteurLivre = book.getAuteurLivre();
		prixLivre = book.getPrixLivre();
	}

	public int getIdLivre() {
		return idLivre;
	}

	public String getNomLivre() {
		return nomLivre;
	}

	public String getAuteurLivre() {
		return auteurLivre;
	}

	public float getPrixLivre() {
		return prixLivre;
	}

	public String getQuantiteLivre() {
		return quantiteLivre;
	}

	public void setIdLivre(int idLivre) {
		this.idLivre = idLivre;
	}

	public void setNomLivre(String nomLivre) {
		this.nomLivre = nomLivre;
	}

	public void setAuteurLivre(String auteurLivre) {
		this.auteurLivre = auteurLivre;
	}

	public void setPrixLivre(float prixLivre) {
		this.prixLivre = prixLivre;
	}

	public void setQuantiteLivre(String quantiteLivre) {
		this.quantiteLivre = quantiteLivre;
	}
	
	
	
}

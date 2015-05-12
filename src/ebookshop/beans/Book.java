package ebookshop.beans;

public class Book {

	// Attribute
	private int idLivre;
	private String nomLivre;
	private String auteurLivre;
	private String editeurLivre;
	private int quantiteLivre;
	private float prixLivre;
	private String image;
	private String resume;
	private String ISBN;
	private int idGenreLivre;
	
	// Method
	public int getIdLivre() {
		return idLivre;
	}
	public void setIdLivre(int idLivre) {
		this.idLivre = idLivre;
	}
	public String getNomLivre() {
		return nomLivre;
	}
	public void setNomLivre(String nomLivre) {
		this.nomLivre = nomLivre;
	}
	public String getAuteurLivre() {
		return auteurLivre;
	}
	public void setAuteurLivre(String auteurLivre) {
		this.auteurLivre = auteurLivre;
	}
	public String getEditeurLivre() {
		return editeurLivre;
	}
	public void setEditeurLivre(String editeurLivre) {
		this.editeurLivre = editeurLivre;
	}
	public int getQuantiteLivre() {
		return quantiteLivre;
	}
	public void setQuantiteLivre(int quantiteLivre) {
		this.quantiteLivre = quantiteLivre;
	}
	public float getPrixLivre() {
		return prixLivre;
	}
	public void setPrixLivre(float prixLivre) {
		this.prixLivre = prixLivre;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getResume() {
		return resume;
	}
	public void setResume(String resume) {
		this.resume = resume;
	}
	public String getISBN() {
		return ISBN;
	}
	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}
	public int getIdGenreLivre() {
		return idGenreLivre;
	}
	public void setIdGenreLivre(int idGenreLivre) {
		this.idGenreLivre = idGenreLivre;
	}
	
	
}

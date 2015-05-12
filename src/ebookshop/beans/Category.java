package ebookshop.beans;

public class Category {
	
	// Attribute
	private int idGenreLivre;
	private String nomGenreLivre;
	private String description;
	
	// Method
	public Category(){}
	
	public Category(int idGenreLivre, String nomGenreLivre, String description){
		this.idGenreLivre = idGenreLivre;
		this.nomGenreLivre = nomGenreLivre;
		this.description = description;
	}
	
	public int getIdGenreLivre() {return idGenreLivre;}

	public void setIdGenreLivre(int idGenreLivre) {
		this.idGenreLivre = idGenreLivre;
	}

	public String getNomGenreLivre() {return nomGenreLivre;}

	public void setNomGenreLivre(String nomGenreLivre) {
		this.nomGenreLivre = nomGenreLivre;
	}

	public String getDescription() {return description;}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
}

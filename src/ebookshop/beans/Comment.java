package ebookshop.beans;

public class Comment {
	
	// Attribute
	private int idCommentaire;
	private String personneCommentaire;
	private String emailCommentaire;
	private String contenu;
	private int etatConmmentaire;
	private float point;
	private int idLivreCommentaire;
	
	// Method
	public int getIdCommentaire() {
		return idCommentaire;
	}
	public String getPersonneCommentaire() {
		return personneCommentaire;
	}
	public String getEmailCommentaire() {
		return emailCommentaire;
	}
	public String getContenu() {
		return contenu;
	}
	public int getEtatConmmentaire() {
		return etatConmmentaire;
	}
	public float getPoint() {
		return point;
	}
	public int getIdLivreCommentaire() {
		return idLivreCommentaire;
	}
	public void setIdCommentaire(int idCommentaire) {
		this.idCommentaire = idCommentaire;
	}
	public void setPersonneCommentaire(String personneCommentaire) {
		this.personneCommentaire = personneCommentaire;
	}
	public void setEmailCommentaire(String emailCommentaire) {
		this.emailCommentaire = emailCommentaire;
	}
	public void setContenu(String contenu) {
		this.contenu = contenu;
	}
	public void setEtatConmmentaire(int etatConmmentaire) {
		this.etatConmmentaire = etatConmmentaire;
	}
	public void setPoint(float point) {
		this.point = point;
	}
	public void setIdLivreCommentaire(int idLivreCommentaire) {
		this.idLivreCommentaire = idLivreCommentaire;
	}

	
}

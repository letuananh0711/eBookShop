package ebookshop.beans;

public class Customer {
	
	//Attribute
	private int idMembre;
	private String nomMembre;
	private String prenomMembre;
	private String sexe;
	private String dateNaissance;
	private String emailMembre;
	private String telephone;
	private String adresse;
	private String userName;
	private String passwd;
	private String dateCreation;
	private int etat;
	private int privilege;
	
	//Method
		// Getters
	public int getIdMembre() {
		return idMembre;
	}
	public String getNomMembre() {
		return nomMembre;
	}
	public String getPrenomMembre() {
		return prenomMembre;
	}
	public String getSexe() {
		return sexe;
	}
	public String getDateNaissance() {
		return dateNaissance;
	}
	public String getEmailMembre() {
		return emailMembre;
	}
	public String getTelephone() {
		return telephone;
	}
	public String getAdresse() {
		return adresse;
	}
	public String getUserName() {
		return userName;
	}
	public String getPasswd() {
		return passwd;
	}
	public String getDateCreation() {
		return dateCreation;
	}
	public int getEtat() {
		return etat;
	}
	public int getPrivilege() {
		return privilege;
	}
	
		// Setters
	public void setIdMembre(int idMembre) {
		this.idMembre = idMembre;
	}
	public void setNomMembre(String nomMembre) {
		this.nomMembre = nomMembre;
	}
	public void setPrenomMembre(String prenomMembre) {
		this.prenomMembre = prenomMembre;
	}
	public void setSexe(String sexe) {
		this.sexe = sexe;
	}
	public void setDateNaissance(String dateNaissance) {
		this.dateNaissance = dateNaissance;
	}
	public void setEmailMembre(String emailMembre) {
		this.emailMembre = emailMembre;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public void setDateCreation(String dateCreation) {
		this.dateCreation = dateCreation;
	}
	public void setEtat(int etat) {
		this.etat = etat;
	}
	public void setPrivilege(int privilege) {
		this.privilege = privilege;
	}
	
	
	
}

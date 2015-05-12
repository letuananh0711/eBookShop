package ebookshop.beans;

import java.util.ArrayList;

public class Author {

	public String nameAuthor;
	public String birthName;
	public String image;
	public String description;
	public String birthDate;
	public String birthCity;
	public String birthCountry;
	public String homePage;
	public ArrayList<String> books;
	public ArrayList<String> linkBooks;
	public String sameAs;
	public String seeAlso;
	
	public ArrayList<String> getLinkBooks() {
		return linkBooks;
	}
	public void setLinkBooks(ArrayList<String> linkBooks) {
		this.linkBooks = linkBooks;
	}
	public String getNameAuthor() {
		return nameAuthor;
	}
	public String getBirthName() {
		return birthName;
	}
	public String getImage() {
		return image;
	}
	public String getDescription() {
		return description;
	}
	public String getBirthDate() {
		return birthDate;
	}
	public String getBirthCity() {
		return birthCity;
	}
	public String getBirthCountry() {
		return birthCountry;
	}
	public String getHomePage() {
		return homePage;
	}
	public ArrayList<String> getBooks() {
		return books;
	}
	public String getSameAs() {
		return sameAs;
	}
	public String getSeeAlso() {
		return seeAlso;
	}
	public void setNameAuthor(String nameAuthor) {
		this.nameAuthor = nameAuthor;
	}
	public void setBirthName(String birthName) {
		this.birthName = birthName;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}
	public void setBirthCity(String birthCity) {
		this.birthCity = birthCity;
	}
	public void setBirthCountry(String birthCountry) {
		this.birthCountry = birthCountry;
	}
	public void setHomePage(String homePage) {
		this.homePage = homePage;
	}
	public void setBooks(ArrayList<String> books) {
		this.books = books;
	}
	public void setSameAs(String sameAs) {
		this.sameAs = sameAs;
	}
	public void setSeeAlso(String seeAlso) {
		this.seeAlso = seeAlso;
	}
	
	
	
}

package ebookshop.beans;

import java.util.ArrayList;

public class Publisher {

	public String namePublisher;
	public String image;
	public String description;
	public String yearFounded;
	public String founder;
	public String headquaterName;
	public String publication;
	public String homepage;
	public ArrayList<String> books;
	public ArrayList<String> linkBooks;
	public String sameAs;
	public String seeAlso;
	
	public String getSameAs() {
		return sameAs;
	}
	public String getSeeAlso() {
		return seeAlso;
	}
	public void setSameAs(String sameAs) {
		this.sameAs = sameAs;
	}
	public void setSeeAlso(String seeAlso) {
		this.seeAlso = seeAlso;
	}
	public ArrayList<String> getBooks() {
		return books;
	}
	public ArrayList<String> getLinkBooks() {
		return linkBooks;
	}
	public void setBooks(ArrayList<String> books) {
		this.books = books;
	}
	public void setLinkBooks(ArrayList<String> linkBooks) {
		this.linkBooks = linkBooks;
	}
	public String getNamePublisher() {
		return namePublisher;
	}
	public String getImage() {
		return image;
	}
	public String getDescription() {
		return description;
	}
	public String getYearFounded() {
		return yearFounded;
	}
	public String getFounder() {
		return founder;
	}
	public String getHeadquaterName() {
		return headquaterName;
	}
	public String getPublication() {
		return publication;
	}
	public String getHomepage() {
		return homepage;
	}
	public void setNamePublisher(String namePublisher) {
		this.namePublisher = namePublisher;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void setYearFounded(String yearFounded) {
		this.yearFounded = yearFounded;
	}
	public void setFounder(String founder) {
		this.founder = founder;
	}
	public void setHeadquaterName(String headquaterName) {
		this.headquaterName = headquaterName;
	}
	public void setPublication(String publication) {
		this.publication = publication;
	}
	public void setHomepage(String homepage) {
		this.homepage = homepage;
	}
	
	
	
}

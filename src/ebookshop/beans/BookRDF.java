package ebookshop.beans;

public class BookRDF {
	
	private String bookName;
	private String bookCategory;
	private String ISBN;
	private String bookAuthor;
	private String bookPublisher;
	private String bookDescription;
	private String bookImage;
	
	
	public String getBookName() {
		return bookName;
	}
	public String getBookAuthor() {
		return bookAuthor;
	}
	public String getBookPublisher() {
		return bookPublisher;
	}

	public String getBookImage() {
		return bookImage;
	}
	public String getBookDescription() {
		return bookDescription;
	}
	public String getISBN() {
		return ISBN;
	}
	public String getBookCategory() {
		return bookCategory;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public void setBookAuthor(String bookAuthor) {
		this.bookAuthor = bookAuthor;
	}
	public void setBookPublisher(String bookPublisher) {
		this.bookPublisher = bookPublisher;
	}

	public void setBookImage(String bookImage) {
		this.bookImage = bookImage;
	}
	public void setBookDescription(String bookDescription) {
		this.bookDescription = bookDescription;
	}
	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}
	public void setBookCategory(String bookCategory) {
		this.bookCategory = bookCategory;
	}
}

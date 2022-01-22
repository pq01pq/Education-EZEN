package book;

public class BookDTO {
	private String bookName;
	private String writer;
	private String publisher;
	private int price;
	private String indate;

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getIndate() {
		return indate;
	}

	public void setIndate(String indate) {
		this.indate = indate;
	}

	@Override
	public String toString() {
		return "BookDTO [bookName=" + bookName + ", writer=" + writer + ", publisher=" + publisher + ", price=" + price
				+ ", indate=" + indate + "]";
	}
	
}

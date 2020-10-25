package correa;

import java.util.ArrayList;
import java.util.HashMap;

public class InList {
	public ArrayList<Book> bookList;
	public HashMap<String, Book> bookMap;
	
	public InList() {
		bookList = new ArrayList<Book>();
		bookMap = new HashMap<String, Book>();
	}
	public void addBook(Book select) {
		bookList.add(select);
		bookMap.put(select.getSKU(), select);
	}
	public void removeBook(String bookSku) {
		for(int i = 0; i < bookList.size(); i ++) {
			if(bookSku.equals(bookList.get(i).getSKU())) {
				bookList.remove(i);
			}
		}
		bookMap.remove(bookSku);
	}
	public String display() {
		return bookList.toString().replace("[", "").replace("]", "");
	}
	public String displayHash() {
		return bookMap.toString();
	}
	public ArrayList<Book> getBookList() {
		return bookList;
	}
	public void setBookList(ArrayList<Book> bookList) {
		this.bookList = bookList;
	}
	public HashMap<String, Book> getBookMap() {
		return bookMap;
	}
	public void setBookMap(HashMap<String, Book> bookMap) {
		this.bookMap = bookMap;
	}

}

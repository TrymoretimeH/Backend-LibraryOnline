package com.core.book;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {
	@Autowired
	private BookRepository repo;
	
	public List<Book> getBooks() {
		return repo.findAll();
	}
	
	public Optional<Book> getBook(int id) {
		return repo.findById(id);
	}
	
	public Book getBookW(String title, String author) {
		return repo.findBookW(title, author);
	}
	
	public void addBook(Book b) {
		repo.save(b);
	}
	
	public void updateBook(Book b) {
		repo.save(b);
	}
	
	public void deleteBook(int id) {
		repo.deleteById(id);
	}
	
	
}

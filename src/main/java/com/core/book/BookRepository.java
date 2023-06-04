package com.core.book;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
	
//	@Query("SELECT b FROM Book b WHERE b.title LIKE %?1%")
//	public List<Book> search(String keyword);
	@Query("Select b From Book b where b.tieude = ?1 and b.tacgia = ?2")
	public Book findBookW(String title, String author);
	
}

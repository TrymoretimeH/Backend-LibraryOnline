package com.core.book;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;


@CrossOrigin
@RestController
@RequestMapping("/api/book")
public class BookController {
	@Autowired
	private BookService service;
	
	// get all books
	@GetMapping("/all")
	public ResponseEntity<?> getAllBook() {
		return ResponseEntity.ok(service.getBooks());
	}
	
	// get a book with a bookcode
	@GetMapping("/{bookcode}")
	public ResponseEntity<?> getBookByBookCode(@PathVariable int bookcode) {
		Optional<Book> b = service.getBook(bookcode);
		if (b.isPresent()) {
			return ResponseEntity.ok(b.get());
		}
		return ResponseEntity.status(400).body("Không tồn tại sách cần tìm");
	}
	
	// update a book with request from react
	@PutMapping("/save/{id}")
	public ResponseEntity<?> saveBookById(@PathVariable int id ,@RequestBody Book b) {
		Optional<Book> oB = service.getBook(id);
		System.out.println(b.getId());
		System.out.println(b.getTieude());
		if (oB.isPresent()) {
			Book book = oB.get();
			book.setTieude(b.getTieude());
			book.setTacgia(b.getTacgia());
			book.setMota(b.getMota());
			book.setNgayphathanh(b.getNgayphathanh());
			book.setSotrang(b.getSotrang());
			book.setTheloai(b.getTheloai());
			book.setAnh(b.getAnh());
			book.setRate(b.getRate());
			book.setNhanxet(b.getNhanxet());
			service.updateBook(book);
			return ResponseEntity.ok("Bạn đã cập nhật thành công Book " + b.getId());
		}
		return ResponseEntity.status(400).body("Không tồn tại sách cần cập nhật!");
	}
	
	// update user mua hang
	@PutMapping("/pay/cart")
	public ResponseEntity<?> saveBookSold(@RequestBody List<Book> b) {
		var isUpdated = 0;
		for (int i = 0; i < b.size(); i++) {
			Optional<Book> oB = service.getBook(b.get(i).getId());
			if (oB.isPresent()) {
				Book book = oB.get();
				book.setSoluongdaban(book.getSoluongdaban()+b.get(i).getSoluongdaban());
				service.updateBook(book);
				isUpdated++;
			} else {
				isUpdated = 0;
				return ResponseEntity.status(400).body("Có lỗi đã xảy ra. Thanh toán thất bại!");
			}
		}
		return ResponseEntity.ok("Thanh toán thành công! Cảm ơn sự ủng hộ của bạn <3");
	}
	
	// add a book with request from react
	@PostMapping("/save/0")
	public ResponseEntity<?> addBook(@RequestBody Book b) {
		Book oB = service.getBookW(b.getTieude(), b.getTacgia());
		if (oB != null) {
			return ResponseEntity.status(400).body("Đã tồn tại Book: " + b.getTieude() + " , author: " + b.getTacgia());
		}
		service.addBook(b);
		return ResponseEntity.ok("Thêm sách thành công!");
	}
	
	// delete a book with pathvariable
	@DeleteMapping("/delete/{bookcode}")
	public ResponseEntity<?> removeBook(@PathVariable int bookcode) {
		Optional<Book> oB = service.getBook(bookcode);
		if (oB.isPresent()) {
			service.deleteBook(bookcode);
			return ResponseEntity.ok("Đã xóa thành công Book: "+oB.get().getId() + "-" + oB.get().getTieude());
		}
		return ResponseEntity.status(400).body("Sách không tồn tại!");
	}
}

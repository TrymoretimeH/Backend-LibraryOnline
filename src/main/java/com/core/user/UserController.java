package com.core.user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.core.dto.UserDto;
import com.core.entities.User;
import com.core.mappers.UserMapper;

@CrossOrigin
@RestController
@RequestMapping("/api/user")
public class UserController {
	@Autowired
	private UserService service;
	

	@GetMapping("/all")
	public List<User> getAllUser() {
		return service.getAllUser();
	}
	
	// save a user from react
		@GetMapping("/{id}")
		public ResponseEntity<?> updateUser(@PathVariable int id) {
			Optional<User> eU = service.findById(id);
			
			if (eU.isPresent()) {
				System.out.println(eU.get().getRole());
				if (eU.get().getRole().equals("admin")) {
					return ResponseEntity.status(400).body("Không đủ quyền chỉnh sửa ADMIN!");
				}
				return ResponseEntity.ok(eU.get());
			}
			return ResponseEntity.status(400).body("Không tồn tại User: " + id);
		}
	
	
	// save a user from react
	@PutMapping("/save/{id}")
	public ResponseEntity<?> updateUser(@PathVariable int id, @RequestBody User u) {
		List<User> lU = service.findUsersWithUsername(u.getUsername());
		if (lU.size() == 1 && lU.get(0).getId() == u.getId()) {
			lU.get(0).setTen(u.getTen());
			lU.get(0).setEmail(u.getEmail());
			lU.get(0).setUsername(u.getUsername());
			lU.get(0).setPassword(u.getPassword());
			lU.get(0).setRole(u.getRole());
			lU.get(0).setGiohang(u.getGiohang());
			service.updateUser(lU.get(0));
			return ResponseEntity.ok("Đã cập nhật thành công User: " + u.getUsername());
		} else if (lU.size() == 0) {
			Optional<User> eU = service.findById(id);
			if (eU.isPresent()) {
				eU.get().setTen(u.getTen());
				eU.get().setEmail(u.getEmail());
				eU.get().setUsername(u.getUsername());
				eU.get().setPassword(u.getPassword());
				eU.get().setRole(u.getRole());
				eU.get().setGiohang(u.getGiohang());
				service.updateUser(eU.get());
				return ResponseEntity.ok("Đã cập nhật thành công User: " + u.getUsername());
			}
			return ResponseEntity.status(400).body("Không tồn tại User: " + u.getUsername());
		} else {
			return ResponseEntity.status(400).body("Đã tồn tại User: " + u.getUsername());
		}
	}
	
	// save cart
	@PutMapping("/save/cart")
	public ResponseEntity<?> updateCart(@RequestBody User u) {
		List<User> lU = service.findUsersWithUsername(u.getUsername());
		if (lU.size() == 1) {
			lU.get(0).setGiohang(u.getGiohang());
			service.updateUser(lU.get(0));
			return ResponseEntity.ok("Đã cập nhật thành công Giỏ hàng của User " + u.getUsername());
		}
		return ResponseEntity.status(400).body("Cập nhật giỏ hàng thất bại!");
		
	}
	
	// pay cart
	@PutMapping("/pay/cart")
	public ResponseEntity<?> payCart(@RequestBody User u) {
		List<User> lU = service.findUsersWithUsername(u.getUsername());
		if (lU.size() == 1) {
			lU.get(0).setGiohang("[]");
			service.updateUser(lU.get(0));
			return ResponseEntity.ok("Bạn đã thanh toán thành công!");
		}
		return ResponseEntity.status(400).body("Thanh toán thất bại!");	
	}

	// add a user from register form in react
	@PostMapping("/save/0")
	public ResponseEntity<?> addUser(@RequestBody User u) {
		System.out.println(u.getUsername() + " " + u.getPassword());
		User eU = new User();
		try {
			eU = service.findWithUsername(u.getUsername());
			if (eU != null) {
				return ResponseEntity.status(400).body("User " +u.getUsername() + " đã tồn tại!");
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			service.addUser(u);
			return ResponseEntity.ok("Thêm thành công User: " + u.getUsername());	
		}
		
		return ResponseEntity.status(400).body("Có lỗi đã xảy ra!!!");
	}
	
	// delete a user from react
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable int id) {
		Optional<User> eU = service.findById(id);
		if (eU.isPresent()) {
			if (eU.get().getRole().equals("admin")) {
				return ResponseEntity.status(400).body("Không đủ quyền xóa ADMIN!");
			}
			service.deleteUser(id);
			return ResponseEntity.ok("delete success User: " + eU.get().getUsername() + " !");
		}
		return ResponseEntity.status(400).body("Không tồn tại User: " + id);
	}
}

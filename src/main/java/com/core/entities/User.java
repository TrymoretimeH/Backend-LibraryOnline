package com.core.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "account")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false)
	private String username;
	
	private String password;
	
	private String role;
	
	private String ten;
	
	private String email;
	
	private String giohang;
	
	public User() {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getRole() {
		return role;
	}

	public String getTen() {
		return ten;
	}

	public String getEmail() {
		return email;
	}

	public String getGiohang() {
		return giohang;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public void setTen(String ten) {
		this.ten = ten;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setGiohang(String giohang) {
		this.giohang = giohang;
	}

	public User(int id, String username, String password, String role, String ten, String email, String giohang) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.role = role;
		this.ten = ten;
		this.email = email;
		this.giohang = giohang;
	}
	
	
	
}

package com.core.dto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {

    private Long id;
    private String ten;
    private String token;
    private String role;
    private String email;
    private String giohang;
    private String username;
    
    public UserDto(Long id, String ten, String token, String role, String email, String giohang, String username) {
		super();
		this.id = id;
		this.ten = ten;
		this.token = token;
		this.role = role;
		this.email = email;
		this.giohang = giohang;
		this.username = username;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getGiohang() {
		return giohang;
	}

	public void setGiohang(String giohang) {
		this.giohang = giohang;
	}

	public UserDto() {
    	// TODO Auto-generated constructor stub
    }    
    

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public Long getId() {
		return id;
	}
	public String getTen() {
		return ten;
	}
	public String getToken() {
		return token;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setTen(String username) {
		this.ten = username;
	}
	public void setToken(String token) {
		this.token = token;
	}

    
    
}
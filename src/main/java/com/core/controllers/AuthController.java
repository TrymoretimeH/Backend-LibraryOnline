package com.core.controllers;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.core.config.UserAuthenticationProvider;
import com.core.dto.CredentialsDto;
import com.core.dto.SignUpDto;
import com.core.dto.UserDto;
import com.core.entities.User;
import com.core.exceptions.AppException;
import com.core.user.UserService;

import java.net.URI;

@RequiredArgsConstructor
@RestController
public class AuthController {

    private final UserService userService;
    private final UserAuthenticationProvider userAuthenticationProvider;
    @Autowired
    public AuthController (UserService service, UserAuthenticationProvider provider) {
    	this.userAuthenticationProvider = provider;
    	this.userService = service;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse,
            FilterChain filterChain, @RequestBody @Valid CredentialsDto u) {
    	String header = httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION);
    	if (header != null) {
    		String [] elements = header.split(" ");
    		UserDto uDto = userService.findByUsernameAndPassword(u.getUsername(), String.valueOf(u.getPassword()));
    		uDto.setToken(userAuthenticationProvider.createToken(u.getUsername()));
    		if (elements[0] != null && elements[0].equals("Bearer")) {
    			return ResponseEntity.ok(uDto);
    		} else {
    			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Sai tài khoản hoặc mật khẩu!");
    		}
    		
    	}
    	
        UserDto userDto = userService.findByUsernameAndPassword(u.getUsername(), String.valueOf(u.getPassword()));
        userDto.setToken(userAuthenticationProvider.createToken(u.getUsername()));
        return ResponseEntity.ok(userDto);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Valid User u) {
    	User eU = new User();
		try {
			eU = userService.findWithUsername(u.getUsername());
			if (eU != null) {
				return ResponseEntity.status(400).body("User " +u.getUsername() + " đã tồn tại!");
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			userService.addUser(u);
			return ResponseEntity.ok("Thêm thành công User: " + u.getUsername());	
		}
		
		return ResponseEntity.status(400).body("Có lỗi đã xảy ra!!!");
    }

}
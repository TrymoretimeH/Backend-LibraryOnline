package com.core.user;

import java.nio.CharBuffer;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContextException;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.core.dto.CredentialsDto;
import com.core.dto.SignUpDto;
import com.core.dto.UserDto;
import com.core.entities.User;
import com.core.exceptions.AppException;
import com.core.mappers.UserMapper;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {
	@Autowired
    private UserRepository userRepository;
	@Autowired
    private PasswordEncoder passwordEncoder;
	@Autowired
    private UserMapper userMapper;
	
	public List<User> getAllUser() {
		return userRepository.findAll();
	}
	
	public Optional<User> findById(int id) {
		return userRepository.findById(id);
	}

    public UserDto login(CredentialsDto credentialsDto) {
        User user = userRepository.findByUsername(credentialsDto.getUsername())
                .orElseThrow(() -> new AppException("Unknown user", HttpStatus.NOT_FOUND));

        if (passwordEncoder.matches(CharBuffer.wrap(credentialsDto.getPassword()), user.getPassword())) {
            return userMapper.toUserDto(user);
        }
        throw new AppException("Invalid password", HttpStatus.BAD_REQUEST);
    }

    public UserDto register(SignUpDto userDto) {
        Optional<User> optionalUser = userRepository.findByUsername(userDto.getUsername());

        if (optionalUser.isPresent()) {
            throw new AppException("Login already exists", HttpStatus.BAD_REQUEST);
        }

        User user = userMapper.signUpToUser(userDto);
        user.setPassword(passwordEncoder.encode(CharBuffer.wrap(userDto.getPassword())));

        User savedUser = userRepository.save(user);

        return userMapper.toUserDto(savedUser);
    }

    public UserDto findByUsername(String login) {
        User user = userRepository.findByUsername(login)
                .orElseThrow(() -> new AppException("Unknown user", HttpStatus.NOT_FOUND));
        return userMapper.toUserDto(user);
    }
    
    public UserDto findByUsernameAndPassword(String username, String password) {
    	User user = userRepository.findByUsernameAndPassword(username, password)
    					.orElseThrow(() -> new AppException("Wrong username or password", HttpStatus.NOT_FOUND));
    	return userMapper.toUserDto(user);
    }
    
    public User findWithUserAndPass(String username, String password) {
    	return userRepository.findByUsernameAndPassword(username, password).get();
    }
    
    public User findWithUsername(String username) {
    	return userRepository.findByUsername(username).get();
    }
    
    public List<User> findUsersWithUsername(String username) {
    	return userRepository.findUsersByUsername(username);
    }
    
    
    public void updateUser(User u) {
    	userRepository.save(u);
    }
    
    public void addUser(User u) {
    	userRepository.save(u);
    }
    
    public void deleteUser(int id) {
    	userRepository.deleteById(id);
    }

}
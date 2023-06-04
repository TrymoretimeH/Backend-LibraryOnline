package com.core.user;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.core.entities.User;


public interface UserRepository extends JpaRepository<User, Integer> {

	@Query("select u from User u where u.username = ?1")
    public Optional<User> findByUsername(String login);

	@Query("select u from User u where u.username = ?1 and u.password = ?2")
	public Optional<User> findByUsernameAndPassword(String username, String password);
	
	@Query("select u from User u where u.username = ?1")
	public List<User> findUsersByUsername(String username);
}
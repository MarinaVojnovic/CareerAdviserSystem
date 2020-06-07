package com.sbnz.career.adviser.service;


import java.util.List;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.sbnz.career.adviser.entity.User;


public interface UserService extends UserDetailsService {

	User findById(long id);

	List<User> getAll();

	User create(User user);

	void delete(User user);

	User findUserByToken(String token);
	
	User findByUsername(String username);
	
	void save(User user);

	

}


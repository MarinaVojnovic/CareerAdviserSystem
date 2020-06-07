package com.sbnz.career.adviser.serviceImpl;

import java.util.List;

import org.kie.api.KieBase;
import org.kie.api.KieBaseConfiguration;
import org.kie.api.KieServices;
import org.kie.api.conf.EventProcessingOption;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.sbnz.career.adviser.entity.User;
import com.sbnz.career.adviser.enums.Role;
import com.sbnz.career.adviser.events.LoginEvent;
import com.sbnz.career.adviser.model.UserTokenState;
import com.sbnz.career.adviser.repository.UserRepository;
import com.sbnz.career.adviser.security.JwtAuthenticationRequest;
import com.sbnz.career.adviser.service.UserService;




@Service
public class UserServiceImpl implements UserService{


	private final UserRepository userRepository;
	private PasswordEncoder passwordEncoder;
	//private KieSession kieSession;

	public UserServiceImpl( /*@Qualifier("loginKieSession") KieSession kieSession,*/ UserRepository userRepository, PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
		//this.kieSession=kieSession;
	}
	

	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return  userRepository.findByUsername(username);
		
	}

	@Override
	public User findById(long id) {
		System.out.println("Uslo u find user by id");
		return userRepository.findById(id).orElse(null);
	}

	@Override
	public List<User> getAll() {
		System.out.println("Uslo u get all");
		return userRepository.findAll();
	}

	@Override
	public User create(User user) {
		System.out.println("Uslo u create");
		return userRepository.save(user);
	}

	@Override
	public void delete(User user) {
		System.out.println("Uslo u delete");
		userRepository.delete(user);
		
	}

	@Override
	public User findUserByToken(String token) {
		System.out.println("Uslo u find user by token");
		return userRepository.findByToken(token);
	}

	@Override
	public User findByUsername(String username) {
		return this.userRepository.findByUsername(username);
		
				
	}

	@Override
	public void save(User user) {
		this.userRepository.save(user);
		
	}

}

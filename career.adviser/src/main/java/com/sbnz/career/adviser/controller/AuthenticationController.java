package com.sbnz.career.adviser.controller;


import java.io.IOException;


import javax.servlet.http.HttpServletResponse;

import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sbnz.career.adviser.WebSecurityConfig;
import com.sbnz.career.adviser.dto.MessageDto;
import com.sbnz.career.adviser.dto.UserDto;
import com.sbnz.career.adviser.enums.Role;
import com.sbnz.career.adviser.model.UserTokenState;
import com.sbnz.career.adviser.security.JwtAuthenticationRequest;
import com.sbnz.career.adviser.security.TokenHelper;
import com.sbnz.career.adviser.service.UserService;


@RestController
@RequestMapping(value = "/auth")
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
public class AuthenticationController {


	private TokenHelper tokenUtils;
	
	private WebSecurityConfig webSecurityConfig;

	private UserService userService;
	
	
	
	
	
	public AuthenticationController(TokenHelper tokenUtils, WebSecurityConfig webSecurityConfig, UserService userService) {
		super();
		this.tokenUtils = tokenUtils;
		this.webSecurityConfig = webSecurityConfig;
		this.userService=userService;
	
	
	}
	

	@PostMapping(value = "/registerAdmin")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> registerAdmin(@RequestBody UserDto user) {
		System.out.println("Uslo u register admin u kontroleru");
		try{
			this.userService.registerUser(user, Role.ROLE_ADMIN);
			return new ResponseEntity<>(new MessageDto("Admin successfully registrated.", "Success"), HttpStatus.OK);
		}catch(Exception e){
			return new ResponseEntity<>(new MessageDto(e.getMessage(), "Error"), HttpStatus.CONFLICT);
		}
	}



	@PostMapping(value = "/registerUser")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> registerUser(@RequestBody UserDto user) {
		System.out.println("Uslo u register user u kontroleru");

		try{
			boolean result = this.userService.registerUser(user,Role.ROLE_USER);
			return new ResponseEntity<>(result, HttpStatus.OK);
		}catch(Exception e){
			return new ResponseEntity<>(new MessageDto(e.getMessage(), "Error"), HttpStatus.CONFLICT);
		}
	}



	@PostMapping(value = "/login")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtAuthenticationRequest authenticationRequest,
		HttpServletResponse response) throws AuthenticationException, IOException, Exception {

		UserTokenState userTokenState = webSecurityConfig.login(authenticationRequest);
		if (userTokenState == null) {
			return new ResponseEntity<>(new MessageDto("Wrong username or password.", "Error"), HttpStatus.NOT_FOUND);
		}else if (userTokenState.getAccessToken()=="-1") {
			System.out.println("Access forbidden");
			return new ResponseEntity<>(new MessageDto("Access forbidden. Try again in 3 minutes.", "Error"), HttpStatus.FORBIDDEN);
		}
		
		else {
			return new ResponseEntity<>(userTokenState, HttpStatus.OK);
		}
	}

}

package com.sbnz.career.adviser;

import org.kie.api.KieBase;
import org.kie.api.KieBaseConfiguration;
import org.kie.api.KieServices;
import org.kie.api.conf.EventProcessingOption;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.sbnz.career.adviser.entity.User;
import com.sbnz.career.adviser.enums.Role;
import com.sbnz.career.adviser.events.LoginEvent;
import com.sbnz.career.adviser.model.UserTokenState;
import com.sbnz.career.adviser.repository.UserRepository;
import com.sbnz.career.adviser.security.JwtAuthenticationRequest;
import com.sbnz.career.adviser.security.TokenAuthenticationFilter;
import com.sbnz.career.adviser.security.TokenHelper;
import com.sbnz.career.adviser.service.UserService;



@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	private TokenHelper tokenHelper;
	private PasswordEncoder passwordEncoder;
	private UserService userService;
	private KieSession kieSession;
	//private KieContainer kieContainer;


	 public WebSecurityConfig(TokenHelper tokenHelper, PasswordEncoder passwordEncoder, UserService userService, @Qualifier("loginKieSession") KieSession kieSession) {
		
		this.tokenHelper = tokenHelper;
		this.passwordEncoder = passwordEncoder;
		this.userService = userService;
		this.kieSession=kieSession;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http
			// communication between client and server is stateless
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
			.authorizeRequests()
			.antMatchers("/auth/login").permitAll()
			.antMatchers("/auth/registerAdmin").permitAll()
			.antMatchers("/professions/**").permitAll()
			.antMatchers("**").permitAll()
				//.antMatchers("/bookRent/**").permitAll()

				//antMatchers("/auth/registerLibrarian").permitAll()
			//.antMatchers("/books/**").permitAll()
			//.antMatchers("/bookCopies/**").permitAll()
			//.antMatchers("/users/**").permitAll()
			//.antMatchers("/bookRent/**").permitAll()
			// every request needs to be authorized
			.anyRequest().authenticated().and()
			// add filter before every request
			.addFilterBefore(new TokenAuthenticationFilter(tokenHelper, userService),
				BasicAuthenticationFilter.class);
		http.csrf().disable();

	}

	public void configure(WebSecurity web) throws Exception {
		// Token Filter will ignore these paths
		web.ignoring().antMatchers(HttpMethod.POST, "/auth/login", "/h2/**");
		web.ignoring().antMatchers(HttpMethod.GET, "/", "/login", "/h2/**", "/webjars/**", "/*.html", "/favicon.ico",
			"/**/*.html", "/**/*.css", "/**/*.js");

		web.ignoring().antMatchers(
			"/v2/api-docs",
			"/swagger-resources/configuration/ui",
			"/swagger-resources",
			"/swagger-resources/configuration/security",
			"/swagger-ui.html",
			"/webjars/**");
	}


	public UserTokenState login(JwtAuthenticationRequest authenticationRequest) throws Exception {
		System.out.println("LOGIN METHOD CALLED");
		Authentication authentication = null;
		Boolean success = false;
		User user  = userService.findByUsername(authenticationRequest.getUsername());
		
		try {
			if (user!=null){
				authentication = authenticationManagerBean().authenticate(new UsernamePasswordAuthenticationToken(
						authenticationRequest.getUsername(), authenticationRequest.getPassword()));
			}
			//ovde je user uspesno uneo i ime i lozinku
			//moze se desiti da je vec forbidden, to proveramo pomocu getAllowed - ali sve u svemu je success
			LoginEvent loggingEvent = new LoginEvent(user,true);
			kieSession.insert(loggingEvent);
			kieSession.getAgenda().getAgendaGroup("loginEvents").setFocus();
			kieSession.fireAllRules();
			userService.save(user);
			System.out.println("Allowed after successfull action: "+user.getAllowed());
			System.out.println("ID: "+loggingEvent.getUser().getAllowed());
			
			success=true;
		} catch (Exception e) {
			user = userService.findByUsername(authenticationRequest.getUsername());
			if (user!=null) {
				//ovde je user uneo dobro ime ali nije lozinku
				LoginEvent loggingEvent = new LoginEvent(user,false);
				kieSession.insert(loggingEvent);
				kieSession.getAgenda().getAgendaGroup("loginEvents").setFocus();
				kieSession.fireAllRules();
				userService.save(user);
				System.out.println("Allowed after action: "+user.getAllowed());
				System.out.println("ID: "+loggingEvent.getUser().getAllowed());
				success = false;
				
			}
			
			
			
		}
		if (success == true && user.getAllowed()) { //uspesno se prijavio i ime i lozinka i nije forbidden
			user = (User) authentication.getPrincipal();
			SecurityContextHolder.getContext().setAuthentication(authentication);
			String jwt = tokenHelper.generateToken(user.getUsername());
			int expiresIn = tokenHelper.getExpiredIn();
			Role role = null;
			if (user.getAuthoitiesList().get(0).getRole().equals(Role.ROLE_ADMIN)) {
				role = Role.ROLE_ADMIN;
			} else {
				role = Role.ROLE_USER;
			}
			return new UserTokenState(jwt, expiresIn, role);
		}else if (success=true && user.getAllowed()==false){// //uspesno se prijavio ali je forbidden
			String jwt = "-1";
			UserTokenState userToken =  new UserTokenState();
			userToken.setAccessToken(jwt);
			return userToken;
			
		}else {
			return null;
		}
		
	}

	public User changePassword(String oldPassword, String newPassword) throws Exception {
		Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
		String username = currentUser.getName();
		authenticationManagerBean().authenticate(new UsernamePasswordAuthenticationToken(username, oldPassword));
		User user = (User) userService.loadUserByUsername(username);
		user.setPassword(passwordEncoder.encode(newPassword));
		userService.create(user);
		return user;
	}


}


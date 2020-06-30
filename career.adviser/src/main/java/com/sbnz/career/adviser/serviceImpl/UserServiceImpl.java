package com.sbnz.career.adviser.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Random;

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

import com.sbnz.career.adviser.dto.UserDto;
import com.sbnz.career.adviser.entity.Authority;
import com.sbnz.career.adviser.entity.User;
import com.sbnz.career.adviser.enums.Role;
import com.sbnz.career.adviser.events.LoginEvent;
import com.sbnz.career.adviser.model.Mail;
import com.sbnz.career.adviser.model.UserTokenState;
import com.sbnz.career.adviser.repository.UserRepository;
import com.sbnz.career.adviser.security.JwtAuthenticationRequest;
import com.sbnz.career.adviser.service.UserService;


import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;




@Service
public class UserServiceImpl implements UserService{


	static Properties mailServerProperties;
	static Session getMailSession;
	static MimeMessage generateMailMessage;
	
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
	
	public boolean usernameTaken(String username) {
		User user = userRepository.findByUsername(username);

		return user != null;
	}
	
	@Override
	public boolean registerUser(UserDto user, Role userRole) {
		System.out.println("USlo u register user u servisu");
		if (this.usernameTaken(user.getUsername())) {
			return false;
		}
		
		User newUser = new User();
		newUser.setName(user.getName());
		newUser.setUsername(user.getUsername());
		newUser.setEmail(user.getEmail());
		newUser.setAllowed(true);
		newUser.setNewPersTest(true);
		newUser.setNewProfTest(true);
		if (userRole.equals(Role.ROLE_USER)){
			Authority a = new Authority();
			a.setRole(Role.ROLE_USER);
			List<Authority> authorities = new ArrayList<>();
			authorities.add(a);
			newUser.setAuthorities(authorities);
			Random rand = new Random(); 
	        // Generate random integers in range 0 to 999 
	        int rand_int = rand.nextInt(1000); 
	        newUser.setPassword(this.passwordEncoder.encode(""+rand_int));
	        Mail mail = new Mail(user.getEmail(), "Career Adviser System invitation", "Your password is: "+rand_int);
	        try {
				this.sendEmail(mail);
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
			Authority a = new Authority();
			a.setRole(Role.ROLE_ADMIN);
			List<Authority> authorities = new ArrayList<>();
			authorities.add(a);
			newUser.setAuthorities(authorities);
			newUser.setPassword(this.passwordEncoder.encode(user.getPassword()));
		
		}

		this.userRepository.save(newUser);
		
		return true;
	}
	
	public void sendEmail(Mail mail) throws AddressException, MessagingException {
		System.out.println("Uslo u send maaaaaaail");
		mailServerProperties = System.getProperties();
		mailServerProperties.put("mail.smtp.port", "587");
		mailServerProperties.put("mail.smtp.auth", "true");
		mailServerProperties.put("mail.smtp.starttls.enable", "true");
		mailServerProperties.put("mail.smtp.ssl.trust", "smtp.gmail.com");

		getMailSession = Session.getDefaultInstance(mailServerProperties, null);
		generateMailMessage = new MimeMessage(getMailSession);
		generateMailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(mail.getEmailAddress()));
		generateMailMessage.setSubject(mail.getSubject());
		String emailBody = mail.getBody();
		generateMailMessage.setContent(emailBody, "text/html");

		Transport transport = getMailSession.getTransport("smtp");
		transport.connect("smtp.gmail.com", "nvtktsteam3@gmail.com", "nvtktsteam3!maki");
		
		
		transport.sendMessage(generateMailMessage, generateMailMessage.getAllRecipients());
		transport.close();
	}
	

}

package com.sbnz.career.adviser.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(catalog = "db_career_adviser", name = "users")
public class User implements UserDetails{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	String name;
	String surname;
	String username;
	String email;
	String password;
	boolean allowed;
	Boolean newPersTest;
	Boolean newProfTest;
	
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "user_authority", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "authority_id", referencedColumnName = "id"))
	private List<Authority> authorities = new ArrayList<>();
	
	public User() {
		
	}
	public User(Long id, String name, String surname, String email, String password, String username) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.password = password;
		this.username = username;
	}
	
	public User(Long id, String name, String surname, String email, String password, String username, Boolean allowed) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.password = password;
		this.username = username;
		this.allowed=allowed;
		this.newPersTest=true;
		this.newProfTest=true;
	}
	
	
	public User(Long id, String name, String surname, String username, String email, String password, Boolean allowed,
			List<Authority> authorities) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.username = username;
		this.email = email;
		this.password = password;
		this.allowed = allowed;
		this.authorities = authorities;
		this.newPersTest=true;
		this.newProfTest=true;
	}
	
	public User(Long id, String name, String surname, String username, String email, String password, boolean allowed,
			Boolean newPersTest, Boolean newProfTest, List<Authority> authorities) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.username = username;
		this.email = email;
		this.password = password;
		this.allowed = allowed;
		this.newPersTest = newPersTest;
		this.newProfTest = newProfTest;
		this.authorities = authorities;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	public Boolean getNewPersTest() {
		return newPersTest;
	}
	public void setNewPersTest(Boolean newPersTest) {
		this.newPersTest = newPersTest;
	}
	public Boolean getNewProfTest() {
		return newProfTest;
	}
	public void setNewProfTest(Boolean newProfTest) {
		this.newProfTest = newProfTest;
	}
	public boolean getAllowed() {
		return allowed;
	}
	public void setAllowed(boolean allowed) {
		this.allowed = allowed;
	}
	public void setAuthorities(List<Authority> authorities) {
		this.authorities = authorities;
	}
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	public List<Authority> getAuthoitiesList(){
		return  authorities;
	}
	@JsonIgnore
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isEnabled() {
		return true;
	}
	
	
}

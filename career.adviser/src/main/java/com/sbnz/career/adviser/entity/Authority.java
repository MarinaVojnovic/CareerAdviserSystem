package com.sbnz.career.adviser.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sbnz.career.adviser.enums.Role;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;

@Entity
@Table(name = "authority")

public class Authority implements GrantedAuthority {
	
	private static final long serialVersionUID = -3742453340543117722L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Enumerated(EnumType.STRING)
	private Role role;
	
	public Authority() {
		
	}

	public Authority(Long id, Role role) {
		super();
		this.id = id;
		this.role = role;
	}

	public Authority(Role role) {
		this.role = role;
	}

	@Override
	public String getAuthority() {
		return role.name();
	}

	public void setRole(Role role) {
		this.role = role;
	}

	@JsonIgnore
	public Role getRole() {
		return role;
	}

	@JsonIgnore
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}


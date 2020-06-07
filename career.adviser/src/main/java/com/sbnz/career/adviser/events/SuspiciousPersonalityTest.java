package com.sbnz.career.adviser.events;

import org.kie.api.definition.type.Expires;
import org.kie.api.definition.type.Role;

import com.sbnz.career.adviser.entity.User;

@Role(Role.Type.EVENT)
@Expires("1m")
public class SuspiciousPersonalityTest {

	private User user;
	private String reason;
	
	public SuspiciousPersonalityTest() {
		
	}

	public SuspiciousPersonalityTest(User user, String reason) {
		super();
		this.user = user;
		this.reason = reason;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getReason() {
		return reason;
	}
}

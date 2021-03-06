package com.sbnz.career.adviser.events;

import java.util.Date;

import org.kie.api.definition.type.Expires;
import org.kie.api.definition.type.Role;
import org.kie.api.definition.type.Timestamp;

import com.sbnz.career.adviser.entity.User;

@Role(Role.Type.EVENT)
@Timestamp("executionTime")
@Expires("2m")
public class NewProfessionTestEvent {

	private User user;
	private Date executionTime;
	
	public NewProfessionTestEvent(User user) {
		this.executionTime=new Date();
		this.user = user;
	
		
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getExecutionTime() {
		return executionTime;
	}

	public void setExecutionTime(Date executionTime) {
		this.executionTime = executionTime;
	}


	
	
}

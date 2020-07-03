package com.sbnz.career.adviser.events;

import java.io.Serializable;
import java.util.Date;

import org.kie.api.definition.type.Expires;
import org.kie.api.definition.type.Role;
import org.kie.api.definition.type.Timestamp;

import com.sbnz.career.adviser.entity.User;

@Role(Role.Type.EVENT)
@Timestamp("executionTime")
@Expires("1m")
public class LoginEvent implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Date executionTime;
    private User user;
    private Boolean successfull;
 
    
    private static int counter=0;
    
    
    public LoginEvent() {
        super();
    }
    
    public LoginEvent(User user, Boolean successfull) {
        super();
        this.executionTime = new Date();
        this.user = user;
        this.successfull = successfull;
      
    }
    

	

	public Date getExecutionTime() {
		return executionTime;
	}

	public void setExecutionTime(Date executionTime) {
		this.executionTime = executionTime;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Boolean getSuccessfull() {
		return successfull;
	}

	public void setSuccessfull(Boolean successfull) {
		this.successfull = successfull;
	}
    
    
}

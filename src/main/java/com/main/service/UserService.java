package com.main.service;

import com.main.Common.service.GenericService;
import com.main.model.UserDetails;

public interface UserService extends GenericService<UserDetails, Long> {
	
	public UserDetails validUser(String emailId,String password);

	public int isEmailExists(String emailId);

	public String forgotPassword(String emailId);
	
}

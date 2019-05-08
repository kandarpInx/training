package com.main.dao;

import com.main.Common.dao.GenericDAO;
import com.main.model.UserDetails;

public interface UserDAO extends GenericDAO<UserDetails, Long> {
	
	public UserDetails getUser(String emailId, String password);

}

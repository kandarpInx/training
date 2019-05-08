package com.main.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.main.Common.service.GenericsServiceImpl;
import com.main.dao.UserDAO;
import com.main.model.UserDetails;

@Service
public class UserServiceImpl extends GenericsServiceImpl<UserDetails, Long> implements UserService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private UserDAO userDAO;
	
	@Transactional
	public UserDetails validUser(String emailId, String password) {
		return userDAO.getUser(emailId, password);
	}
	

}

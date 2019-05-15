package com.main.dao;

import java.util.List;

import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.main.Common.dao.GenericDAOImpl;
import com.main.model.UserDetails;

@Repository
public class UserDAOImpl<E,I> extends GenericDAOImpl<UserDetails, Long> implements UserDAO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public UserDetails getUser(String emailId, String password) {
		Query<UserDetails> query = sessionFactory.getCurrentSession()
				.createQuery("FROM UserDetails u where u.emailId=:emailId and u.password=:password",
						UserDetails.class);
		
        query.setParameter("emailId", emailId);
        query.setParameter("password", password);
        return (UserDetails) query.uniqueResult();
	}

	public int getEmail(String emailId) {
		
		Query<UserDetails> query = sessionFactory.getCurrentSession()
				.createQuery("FROM UserDetails u where u.emailId=:emailId",
						UserDetails.class);
        query.setParameter("emailId", emailId);
        
        List<UserDetails> list = query.getResultList();
		
		return list.size(); 
	}

	public String forgotPassword(String emailId) throws IndexOutOfBoundsException {
		
		Query<UserDetails> query = sessionFactory.getCurrentSession()
				.createQuery("FROM UserDetails u where u.emailId=:emailId",
						UserDetails.class);
        query.setParameter("emailId", emailId);
        
        
        List<UserDetails> ud = query.getResultList();
        
        
        return ud.get(0).getPassword();
	}

}

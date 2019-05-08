package com.main.dao;

import org.springframework.stereotype.Repository;

import com.main.Common.dao.GenericDAOImpl;
import com.main.model.Address;

@Repository
public class AddressDAOImpl<E,I> extends GenericDAOImpl<Address, Long> implements AddressDAO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}

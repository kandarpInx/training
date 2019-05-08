package com.main.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.main.Common.service.GenericsServiceImpl;
import com.main.dao.AddressDAO;
import com.main.model.Address;
/**
 * @author Kandarp Dave
 * 
 * The Service class AddressServiceImplementation
 */
@Service
public class AddressServiceImpl extends GenericsServiceImpl<Address, Long> implements AddressService{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private AddressDAO addressDAO;

}

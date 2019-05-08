package com.main.Common.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.main.Common.dao.GenericDAO;

@Service
public abstract class GenericsServiceImpl<E,I extends Serializable> implements GenericService<E, I> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private GenericDAO<E, I> genericDAO;
	
	@Transactional
	public List<E> getAll() {
		return genericDAO.getAll();
	}

	@Transactional
	public E get(I id) {
		return genericDAO.get(id);
	}

	@Transactional
	public void save(E entity) {
		genericDAO.save(entity);
	}

	@Transactional
	public void delete(E entity) {
		genericDAO.delete(entity);
	}

	@Transactional
	public void update(E entity) {
		genericDAO.update(entity);
	}
	
}

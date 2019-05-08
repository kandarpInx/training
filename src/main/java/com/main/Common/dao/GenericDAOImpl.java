package com.main.Common.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public abstract class GenericDAOImpl<E, I extends Serializable> implements GenericDAO<E, I> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	protected SessionFactory sessionFactory;
	
	private Class<E> classs;
	
	@SuppressWarnings("unchecked")
	public GenericDAOImpl() {
		this.classs = ((Class<E>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0]);
	}

	public Class<E> getClasss() {
		return classs;
	}

	/*
	 * public void setClasss(Class<E> classs) { this.classs = classs;
	 * System.out.println("Setting class object of " + this.classs +
	 * " using GenericDAO"); }
	 */
	
	@SuppressWarnings("unchecked")
	public List<E> getAll() {
		return sessionFactory.getCurrentSession().createQuery("from " + classs.getName()).list();
	}

	public E get(I id) {
		return (E) sessionFactory.getCurrentSession().get(classs, id);
	}

	public void save(E entity) {
		sessionFactory.getCurrentSession().save(entity);
	}
	
	public void delete(E entity) {
		sessionFactory.getCurrentSession().delete(entity);
	}

	public void update(E entity) {
		sessionFactory.getCurrentSession().update(entity);
	}

	public void flush() {
		sessionFactory.getCurrentSession().flush();
	}

}

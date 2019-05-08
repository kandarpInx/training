package com.main.Common.dao;

import java.io.Serializable;
import java.util.List;

public interface GenericDAO<E,I> extends Serializable {
	
	public List<E> getAll();

	public E get(I id);
	
	public void save(E e);
	
	public void delete(E e);
	
	public void update(E e);

	public void flush();
}

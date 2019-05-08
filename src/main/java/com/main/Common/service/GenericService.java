package com.main.Common.service;

import java.io.Serializable;
import java.util.List;

public interface GenericService<E,I> extends Serializable {
	
	public List<E> getAll();
	
	public E get(I id);
	
	public void save(E entity);
	
	public void delete(E entity);
	
	public void update(E entity);

}

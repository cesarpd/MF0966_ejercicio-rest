package com.mf0966.examen.ejercicio.repos;

public interface Dao<T> {
	Iterable<T> getAll();
	T getById (Integer id);
	
	Integer insert(T objeto);
	void update(T objeto);
	void delete(Integer id);

}

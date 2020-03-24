package com.mf0966.examen.ejercicio.repos;

import com.mf0966.examen.ejercicio.models.Curso;

public interface FabricaDao {
	
	Dao<Curso> getCursoDao();

}

package com.mf0966.examen.ejercicio.repos;

import com.mf0966.examen.ejercicio.models.Curso;
import com.mf0966.examen.ejercicio.models.Resena;

public interface FabricaDao {
	
	Dao<Curso> getCursoDao();

	Dao<Resena> getResenaDao();

}

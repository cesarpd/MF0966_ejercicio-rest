package com.mf0966.examen.ejercicio.api;

import java.util.List;
import java.util.logging.Logger;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.mf0966.examen.ejercicio.models.Curso;
import com.mf0966.examen.ejercicio.repos.Globales;

@Path("/cursos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)

public class CursosApi {
	private static final Logger LOGGER = Logger.getLogger(CursosApi.class.getCanonicalName());
	
	private static List<Curso> listaCursos = (List<Curso>) Globales.daoCursos.getAll();
	

	
	/**
	 * URL: http://localhost:8080/MF0966_Examen-REST_Ejercicio-Casa/api/cursos
	 * @return Response list Cursos
	 */
	@GET
	public Response getCursos() {
		//listaCursos = (List<Curso>) Globales.daoCursos.getAll();
		LOGGER.info("Listado de Cursos con su profesor");
		return Response.ok(listaCursos).build();
	}


	/**
	 * URL: http://localhost:8080/MF0966_Examen-REST_Ejercicio-Casa/api/cursos/5
	 * @return Response list Resenas
	 */
	@GET
	@Path("/{id: \\d+}")
	public Response getResenaById(@PathParam("id") Integer id) {
		
		Curso cursoEncontrado = null;
		
		for (int i = 0; i<listaCursos.size(); i++) {
			if (listaCursos.get(i).getId().equals(id)) {
				LOGGER.info("Se encontró un curso con id: " + id);
				
				cursoEncontrado = Globales.daoCursos.getById(id);
			}
		}
		
		if (cursoEncontrado == null) {
			LOGGER.info("NO se encontró el curso con id: " + id);
			return Response.status(Status.BAD_REQUEST).entity("No se encuentra la reseña").build();
		} else {
			return Response.ok(cursoEncontrado).build();
		}
	}
	
}

package com.mf0966.examen.ejercicio.api;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.mf0966.examen.ejercicio.models.Curso;
import com.mf0966.examen.ejercicio.repos.Globales;

@Path("/cursos")
@Produces("application/json")
@Consumes("application/json")
public class CursosApi {
	private static final Logger LOGGER = Logger.getLogger(CursosApi.class.getCanonicalName());
	
	private static List<Curso> listaCursos = new ArrayList<Curso>();

	/**
	 * URL: hhttp://localhost:8080/UF2215_Examen-REST/api/libros
	 * @return Response list Cursos
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUsers() {
		listaCursos = (List<Curso>) Globales.daoCursos.getAll();
		LOGGER.info("Listado de Libros con su autor");
		return Response.ok(listaCursos).build();
	}
	
}

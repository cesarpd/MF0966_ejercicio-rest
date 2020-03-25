package com.mf0966.examen.ejercicio.api;

import java.util.ArrayList;
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

import com.mf0966.examen.ejercicio.models.Resena;
import com.mf0966.examen.ejercicio.repos.Globales;

@Path("/resenas")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)

public class ResenasApi {
private static final Logger LOGGER = Logger.getLogger(ResenasApi.class.getCanonicalName());
	
	private static List<Resena> listaResenas = new ArrayList<Resena>();
	
	/**
	 * URL: http://localhost:8080/MF0966_Examen-REST_Ejercicio-Casa/api/resenas
	 * @return Response list Resenas
	 */
	@GET
	public Response getResenas() {
		listaResenas = (List<Resena>) Globales.daoResenas.getAll();
		LOGGER.info("Listado de Resenas con curso");
		return Response.ok(listaResenas).build();
	}
	/**
	 * URL: http://localhost:8080/MF0966_Examen-REST_Ejercicio-Casa/api/resenas/5
	 * @return Response list Resenas
	 */
	@GET
	@Path("/{id}")
	public Response getResenaById(@PathParam("id") Integer id) {
		listaResenas = (List<Resena>) Globales.daoResenas.getAll();
		
		Resena resenaEncontrado = null;
		
		for (int i = 0; i<listaResenas.size(); i++) {
			if (listaResenas.get(i).getId().equals(id)) {
				LOGGER.info("Se encontró una reseña con id: " + id);
				resenaEncontrado = listaResenas.get(i);
			}
		}
		
		if (resenaEncontrado == null) {
			LOGGER.info("NO se encontró una reseña con id: " + id);
			return Response.status(Status.BAD_REQUEST).entity("No se encuentra la reseña").build();
		} else {
			return Response.ok(resenaEncontrado).build();
		}
	}

}

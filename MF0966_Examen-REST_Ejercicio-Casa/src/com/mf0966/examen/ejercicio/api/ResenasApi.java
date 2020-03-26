package com.mf0966.examen.ejercicio.api;

import java.util.List;
import java.util.logging.Logger;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
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
	
	private static List<Resena> listaResenas = (List<Resena>) Globales.daoResenas.getAll();
	
	/**
	 * URL: http://localhost:8080/MF0966_Examen-REST_Ejercicio-Casa/api/resenas
	 * @return Response list Resenas
	 */
	@GET
	public Iterable<Resena> getResenas() {
		LOGGER.info("Listado de Resenas con curso");
		//return Response.ok(listaResenas).build();
		return Globales.daoResenas.getAll();
	}
	/**
	 * URL: http://localhost:8080/MF0966_Examen-REST_Ejercicio-Casa/api/resenas/5
	 * @return Response list Resenas
	 */
	@GET
	@Path("/{id: \\d+}")
	public Response getResenaById(@PathParam("id") Integer id) {
		
		Resena resenaEncontrado = null;
		
		for (int i = 0; i<listaResenas.size(); i++) {
			if (listaResenas.get(i).getId().equals(id)) {
				LOGGER.info("Se encontró una reseña con id: " + id);
				Globales.daoResenas.getById(id);
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
	/**
	 * URL: http://localhost:8080/MF0966_Examen-REST_Ejercicio-Casa/api/resenas/crear
	 * Parameters in Postman:
	 * {
	 * "resena": "Bueno",
	 * 	"curso": 3,
	 * 	"alumno": 2
	 * }
	 * 
	 * @param Resena
	 * @return Response list NOTA: Si no existe el constructor vacío de Resena, da un
	 *         error y el userRequest viene null.
	 */
	
	@POST
	@Path("/crear")

	public Response createResena(Resena resena) {

		try {
			Globales.daoResenas.insert(resena);
		} catch (NullPointerException e) {
			LOGGER.info("Null pointer exception al insertar la sentencia: " + e);
			return Response.status(Status.BAD_REQUEST).entity("Faltan argumentos en la sentencia").build();			
		}
		return Response.ok(resena).build();

	}	
	@DELETE
	@Path("/borrar/{id: \\d+}")
	
	public Response deleteResena(@PathParam("id") Integer id) {
		
		Resena resenaEncontrado = null;
		
		for (int i = 0; i<listaResenas.size(); i++) {
			if (listaResenas.get(i).getId().equals(id)) {
				LOGGER.info("Se encontró una reseña con id: " + id);
				resenaEncontrado = listaResenas.get(i);
				Globales.daoResenas.delete(id);
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

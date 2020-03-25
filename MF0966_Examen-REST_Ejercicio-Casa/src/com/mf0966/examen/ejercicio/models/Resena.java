package com.mf0966.examen.ejercicio.models;

import java.util.logging.Logger;

public class Resena {
	private static final Logger LOGGER = Logger.getLogger(Curso.class.getCanonicalName());

	private Integer id;
	private String resena;

	private Curso curso;
	private Alumno alumno;

	public Resena(Integer id, String resena, Curso curso, Alumno alumno) {
		setId(id);
		setResena(resena);
		setCurso(curso);
		setAlumno(alumno);
	}

	public Resena(String id, String resena, String curso, String alumno) {
		setId(id);
		setResena(resena);
		setCurso(curso);
		setAlumno(alumno);
	}

	// Constructor vacio
	public Resena() {
	}

	// Strings Getters and Setters
	private void setId(String id) {
		try {
			if (id == null || id.trim().length() == 0) {
				setId((Integer) null);
			} else {
				setId(Integer.parseInt(id));
			}
		} catch (NumberFormatException e) {
			LOGGER.info("El id del curso debe ser numérico");
		}
	}

	private void setCurso(String curso) {
		if (curso == null) {
			LOGGER.info("Debes seleccionar un profesor");
		} else {
			try {
				setCurso(new Curso(Integer.parseInt(curso), null, null, null, null));
			} catch (NumberFormatException e) {
				LOGGER.info("El id del curso debe ser numérico");
			}
		}
	}

	private void setAlumno(String alumno) {
		if (alumno == null) {
			LOGGER.info("Debes seleccionar un alumno");
		} else {
			try {
				setAlumno(new Alumno(Integer.parseInt(alumno), null, null));
			} catch (NumberFormatException e) {
				LOGGER.info("El id del alumno debe ser numérico");
			}
		}
	}

	// Getters and Setters
	public void setResena(String resena) {
		this.resena = resena;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public String getResena() {
		return resena;
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	public Alumno getAlumno() {
		return alumno;
	}

	public void setAlumno(Alumno alumno) {
		this.alumno = alumno;
	}

}

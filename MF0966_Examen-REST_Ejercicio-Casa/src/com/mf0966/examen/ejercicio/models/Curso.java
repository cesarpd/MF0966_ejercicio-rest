package com.mf0966.examen.ejercicio.models;

import java.util.logging.Logger;


public class Curso {
	
	private static final Logger LOGGER = Logger.getLogger(Curso.class.getCanonicalName());
	
	private Integer id;
	private String nombre, identificador, horas;
	
	private Profesor profesor;
	private Resena resena;
	

	public Curso(Integer id, String nombre, String identificador, String horas, Profesor profesor) {
		setId(id);
		setNombre(nombre);
		setIdentificador(identificador);
		setHoras(horas);
		setProfesor(profesor);
	}
	public Curso(Integer id, String nombre, String identificador, String horas, Profesor profesor, Resena resena) {
		setId(id);
		setNombre(nombre);
		setIdentificador(identificador);
		setHoras(horas);
		setProfesor(profesor);
		setResena(resena);
	}
	public Curso(String id, String nombre, String identificador, String horas, String profesor) {
		setId(id);
		setNombre(nombre);
		setIdentificador(identificador);
		setHoras(horas);
		setProfesor(profesor);
	}

	public Curso(String id, String nombre, String identificador, String horas, String profesor, String resena) {
		setId(id);
		setNombre(nombre);
		setIdentificador(identificador);
		setHoras(horas);
		setProfesor(profesor);
		setResena(resena);
	}

	
	// Set for Strings
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
	
	private void setProfesor(String profesor) {
		if (profesor == null) {
			LOGGER.info("Debes seleccionar un profesor");
		} else {
			try {
				setProfesor(new Profesor(Integer.parseInt(profesor), null, null));
			} catch (NumberFormatException e) {
				LOGGER.info("El id de profesor debe ser numérico");
			}
		}
		
	}
	private void setResena(String resena) {
		if (resena == null) {
			LOGGER.info("Debes seleccionar una resena");
		} else {
			try {
				setResena(new Resena(Integer.parseInt(resena), null, null, null));
			} catch (NumberFormatException e) {
				LOGGER.info("El id de la resena debe ser numérico");
			}
		}
		
	}

	public Integer getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public String getIdentificador() {
		return identificador;
	}

	public String getHoras() {
		return horas;
	}

	public Profesor getProfesor() {
		return profesor;
	}
	public Resena getResena() {
		return resena;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setIdentificador(String identificador) {
		this.identificador = identificador;
	}

	public void setHoras(String horas) {
		this.horas = horas;
	}

	public void setProfesor(Profesor profesor) {
		this.profesor = profesor;
	}

	public void setResena(Resena resena) {
		this.resena = resena;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((horas == null) ? 0 : horas.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((identificador == null) ? 0 : identificador.hashCode());
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		result = prime * result + ((profesor == null) ? 0 : profesor.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Curso other = (Curso) obj;
		if (horas == null) {
			if (other.horas != null)
				return false;
		} else if (!horas.equals(other.horas))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (identificador == null) {
			if (other.identificador != null)
				return false;
		} else if (!identificador.equals(other.identificador))
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		if (profesor == null) {
			if (other.profesor != null)
				return false;
		} else if (!profesor.equals(other.profesor))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "Curso [id=" + id + ", nombre=" + nombre + ", identificador=" + identificador + ", horas=" + horas
				+ ", profesor=" + profesor + "]";
	}
	
	
	
	
}

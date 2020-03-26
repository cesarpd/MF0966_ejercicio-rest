package com.mf0966.examen.ejercicio.repos;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.mf0966.examen.ejercicio.exceptions.RepositoriosException;
import com.mf0966.examen.ejercicio.models.Curso;
import com.mf0966.examen.ejercicio.models.Profesor;

public class CursosSQL implements Dao<Curso> {

	private static final String SQL_GET_ALL = "CALL ApiCursos_GetAll()";
	private static final String SQL_GET_BY_ID = "CALL ApiCursos_GetById(?)";

	private final String url, usuario, password;

	private static DataSource pool;
	
	
	// "SINGLETON"
	private CursosSQL(String url, String usuario, String password) {
			this.url = url;
			this.usuario = usuario;
			this.password = password;
		}

	private static CursosSQL instancia;

	/**
	 * Se usará para inicializar la instancia
	 * 
	 * @param url
	 * @param usuario
	 * @param password
	 * @return La instancia
	 */
	public static CursosSQL getInstancia(String url, String usuario, String password) {
		// Si no existe la instancia...
		if (instancia == null) {
			// ...la creamos
			instancia = new CursosSQL(url, usuario, password);
			// Si existe la instancia, pero sus valores no concuerdan...
		} else if (!instancia.url.equals(url) || !instancia.usuario.equals(usuario)
				|| !instancia.password.contentEquals(password)) {
			// ...lanzar un error
			throw new RepositoriosException("No se pueden cambiar los valores de la instancia una vez inicializada");
		}

		// Devolver la instancia recién creada o la existente (cuyos datos coinciden con
		// los que tiene)
		return instancia;
	}

	/**
	 * Se usará para recuperar la instancia ya existente
	 * 
	 * @return devuelve la instancia ya existente
	 */
	public static CursosSQL getInstancia() {
		// Si no existe la instancia...
		if (instancia == null) {
			// ...no se puede obtener porque no sabemos los datos de URL, usuario y password
			throw new RepositoriosException("Necesito que me pases URL, usuario y password");
		}

		// Si ya existe, se devuelve
		return instancia;
	}

	/**
	 * Usaremos un pool de conexiones determinado
	 * 
	 * @return devuelve la instancia del pool de conexiones
	 */
	public static CursosSQL getInstancia(String entorno) {
		InitialContext initCtx;
		try {
			initCtx = new InitialContext();

			Context envCtx = (Context) initCtx.lookup("java:comp/env");
			DataSource dataSource = (DataSource) envCtx.lookup(entorno);

			CursosSQL.pool = dataSource;

			if (instancia == null) {
				instancia = new CursosSQL(null, null, null);
			}

			return instancia;
		} catch (NamingException e) {
			throw new RepositoriosException("No se ha podido conectar al Pool de conexiones " + entorno);
		}
	}

	// FIN "SINGLETON"

	// Conexion a BD
	private Connection getConexion() {
		try {
			if (pool == null) {
				new com.mysql.cj.jdbc.Driver();
				return DriverManager.getConnection(url, usuario, password);
			} else {
				return pool.getConnection();
			}
		} catch (SQLException e) {
			System.err.println("IPARTEK: Error de conexión a la base de datos: " + url + ":" + usuario + ":" + password);
			e.printStackTrace();

			throw new RepositoriosException("No se ha podido conectar a la base de datos", e);
		}
	}

	// Metodos a implementar


	@Override
	public Iterable<Curso> getAll() {
		try (Connection con = getConexion()) {
			try (CallableStatement s = con.prepareCall(SQL_GET_ALL)) {
				try (ResultSet rs = s.executeQuery()) {
					ArrayList<Curso> cursos = new ArrayList<>();

					Curso curso;
					Profesor profesor;

					while (rs.next()) {

						profesor = new Profesor(rs.getInt("p.codigo"), rs.getString("p.nombre"), rs.getString("p.apellidos"));
						
						curso = new Curso(rs.getInt("c.codigo"), rs.getString("c.nombre"),
								rs.getString("c.identificador"), rs.getString("c.nHoras"), profesor);

						cursos.add(curso);
					}

					return cursos;
				} catch (SQLException e) {
					throw new RepositoriosException("Error al acceder a los registros", e);
				}
			} catch (SQLException e) {
				throw new RepositoriosException("Error al crear la sentencia", e);
			}
		} catch (SQLException e) {
			throw new RepositoriosException("Error al conectar", e);
		}
	}

	@Override
	public Curso getById(Integer id) {
		try (Connection con = getConexion()) {
			try (CallableStatement s = con.prepareCall(SQL_GET_BY_ID)) {
				try (ResultSet rs = s.executeQuery()) {

					Curso curso = null;
					Profesor profesor = null;

					if (rs.next()) {
						profesor = new Profesor(rs.getInt("p.codigo"), rs.getString("p.nombre"), rs.getString("p.apellidos"));
						
						curso = new Curso(rs.getInt("c.codigo"), rs.getString("c.nombre"),
								rs.getString("c.identificador"), rs.getString("c.nHoras"), profesor);
					}

					return curso;
				} catch (SQLException e) {
					throw new RepositoriosException("Error al acceder a los registros", e);
				}
			} catch (SQLException e) {
				throw new RepositoriosException("Error al crear la sentencia", e);
			}
		} catch (SQLException e) {
			throw new RepositoriosException("Error al conectar", e);
		}
	}

	@Override
	public Integer insert(Curso objeto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Curso objeto) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		
	}


	

}

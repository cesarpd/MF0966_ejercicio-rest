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
import com.mf0966.examen.ejercicio.models.Alumno;
import com.mf0966.examen.ejercicio.models.Curso;
import com.mf0966.examen.ejercicio.models.Profesor;
import com.mf0966.examen.ejercicio.models.Resena;

public class ResenaSQL implements Dao<Resena> {
	private static final String SQL_GET_ALL = "CALL resenasGetAll()";
	private static final String SQL_GET_BY_ID = "CALL Resenas_GetById(?)";
	private static final String SQL_INSERT = "CALL resenasInsert(?,?,?,?)";
	private static final String SQL_DELETE = "CALL resenasDelete(?)";

	private final String url, usuario, password;

	private static DataSource pool;

	// "SINGLETON"
	private ResenaSQL(String url, String usuario, String password) {
		this.url = url;
		this.usuario = usuario;
		this.password = password;
	}

	private static ResenaSQL instancia;

	/**
	 * Se usará para inicializar la instancia
	 * 
	 * @param url
	 * @param usuario
	 * @param password
	 * @return La instancia
	 */
	public static ResenaSQL getInstancia(String url, String usuario, String password) {
		// Si no existe la instancia...
		if (instancia == null) {
			// ...la creamos
			instancia = new ResenaSQL(url, usuario, password);
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
	public static ResenaSQL getInstancia() {
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
	public static ResenaSQL getInstancia(String entorno) {
		InitialContext initCtx;
		try {
			initCtx = new InitialContext();

			Context envCtx = (Context) initCtx.lookup("java:comp/env");
			DataSource dataSource = (DataSource) envCtx.lookup(entorno);

			ResenaSQL.pool = dataSource;

			if (instancia == null) {
				instancia = new ResenaSQL(null, null, null);
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
			// TODO: Eliminar el Usuario Contraseña en producción
			System.err
					.println("IPARTEK: Error de conexión a la base de datos: " + url + ":" + usuario + ":" + password);
			e.printStackTrace();

			throw new RepositoriosException("No se ha podido conectar a la base de datos", e);
		}
	}

	// Metodos a implementar

	@Override
	public Iterable<Resena> getAll() {
		try (Connection con = getConexion()) {
			try (CallableStatement s = con.prepareCall(SQL_GET_ALL)) {
				try (ResultSet rs = s.executeQuery()) {
					ArrayList<Resena> resenas = new ArrayList<>();

					Resena resena;
					Curso curso;
					Profesor profesor;
					Alumno alumno;

					while (rs.next()) {
						profesor = new Profesor(rs.getInt("p.codigo"), rs.getString("p.nombre"),
								rs.getString("p.apellidos"));
						alumno = new Alumno(rs.getInt("a.codigo"), rs.getString("a.nombre"),
								rs.getString("a.apellidos"));

						curso = new Curso(rs.getInt("c.codigo"), rs.getString("c.nombre"),
								rs.getString("c.identificador"), rs.getString("c.nHoras"), profesor);

						resena = new Resena(rs.getInt("r.codigo"), rs.getString("r.texto"), curso, alumno);

						resenas.add(resena);
					}

					return resenas;
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
	public Resena getById(Integer id) {
		try (Connection con = getConexion()) {
			try (CallableStatement s = con.prepareCall(SQL_GET_BY_ID)) {
				try (ResultSet rs = s.executeQuery()) {

					Resena resena = null;
					Curso curso;
					Profesor profesor;
					Alumno alumno;

					if (rs.next()) {
						profesor = new Profesor(rs.getInt("p.codigo"), rs.getString("p.nombre"),
								rs.getString("p.apellidos"));
						alumno = new Alumno(rs.getInt("a.codigo"), rs.getString("a.nombre"),
								rs.getString("a.apellidos"));

						curso = new Curso(rs.getInt("c.codigo"), rs.getString("c.nombre"),
								rs.getString("c.identificador"), rs.getString("c.nHoras"), profesor);

						resena = new Resena(rs.getInt("r.codigo"), rs.getString("r.texto"), curso, alumno);

					}

					return resena;
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
	public Integer insert(Resena resena) {
		try (Connection con = getConexion()) {
			try (CallableStatement s = con.prepareCall(SQL_INSERT)) {
				s.setString(1, resena.getResena());
				s.setInt(2, resena.getAlumno().getId());
				s.setInt(3, resena.getCurso().getId());

				s.registerOutParameter(4, java.sql.Types.INTEGER);

				int numeroRegistrosModificados = s.executeUpdate();

				if (numeroRegistrosModificados != 1) {
					throw new RepositoriosException("Número de registros modificados: " + numeroRegistrosModificados);
				}

				return s.getInt(4);

			} catch (SQLException e) {
				throw new RepositoriosException("Error al crear la sentencia", e);
			}
		} catch (SQLException e) {
			throw new RepositoriosException("Error al conectar", e);
		}
	}

	@Override
	public void delete(Integer id) {
		try (Connection con = getConexion()) {
			try (CallableStatement s = con.prepareCall(SQL_DELETE)) {
				s.setInt(1, id);

				int numeroRegistrosModificados = s.executeUpdate();

				if (numeroRegistrosModificados != 1) {
					throw new RepositoriosException("Número de registros modificados: " + numeroRegistrosModificados);
				}

			} catch (SQLException e) {
				throw new RepositoriosException("Error al crear la sentencia", e);
			}
		} catch (SQLException e) {
			throw new RepositoriosException("Error al conectar", e);
		}
	}

	@Override
	public void update(Resena resena) {
		// TODO Auto-generated method stub

	}

}

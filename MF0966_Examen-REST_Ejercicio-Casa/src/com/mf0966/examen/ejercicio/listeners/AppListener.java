package com.mf0966.examen.ejercicio.listeners;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.mf0966.examen.ejercicio.repos.FabricaDao;
import com.mf0966.examen.ejercicio.repos.FabricaDaoProperties;
import com.mf0966.examen.ejercicio.repos.Globales;

/**
 * Application Lifecycle Listener implementation class AppListener
 *
 */
@WebListener
public class AppListener implements ServletContextListener {


    public void contextDestroyed(ServletContextEvent sce)  { }

    public void contextInitialized(ServletContextEvent sce)  { 
		String pathConfiguracion = sce.getServletContext().getRealPath("/WEB-INF/") + "dao.properties";

		FabricaDao fabricaDao = FabricaDaoProperties.getInstancia(pathConfiguracion);
		
		Globales.daoCursos = fabricaDao.getCursoDao();
	}
	
}

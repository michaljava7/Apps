package utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class DBconfig implements ServletContextListener {

	private static EntityManagerFactory emf;

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		if (emf != null && emf.isOpen()) {
			emf.close();
		}
	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {

		emf = Persistence.createEntityManagerFactory("Forum");
	}

	public static EntityManager createEntityManager() {
		if (emf != null) {
			return emf.createEntityManager();
		} else
			return null;
	}

	public static EntityManagerFactory getEMF() {
		return emf;
	}
}

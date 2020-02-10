package db;


import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class DBconfig implements ServletContextListener {
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		if(emf!=null) {
			emf.close();
		}
	}
	private static EntityManagerFactory emf;
	
	public static EntityManagerFactory getEMF() {
		return emf;
	}
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		
		emf=Persistence.createEntityManagerFactory("DB");
	}
}

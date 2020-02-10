package utils;

import javax.persistence.EntityManager;
import javax.servlet.ServletRequest;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;

import dao.TematyDAO;
import dao.UzytkownicyDAO;
import dao.WpisyDAO;

public class InicjatorDB implements ServletRequestListener {

	@Override
		public void requestInitialized(ServletRequestEvent sre) {
			EntityManager em=DBconfig.createEntityManager()	;	
			UzytkownicyDAO uzytkownicyDAO=new UzytkownicyDAO(em);
			TematyDAO tematyDAO=new TematyDAO(em);
			WpisyDAO wpisyDAO=new WpisyDAO(em);
			
			ServletRequest req=sre.getServletRequest();
			req.setAttribute("tematyDAO", tematyDAO);
			req.setAttribute("uzytkownicyDAO", uzytkownicyDAO);
			req.setAttribute("wpisyDAO", wpisyDAO);
			
			
	}

	@Override
	public void requestDestroyed(ServletRequestEvent sre) {
		
	}
}

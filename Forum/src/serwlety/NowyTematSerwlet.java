package serwlety;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.TematyDAO;
import encje.Temat;
import encje.Uzytkownik;

@WebServlet("/nowyTemat")
public class NowyTematSerwlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       


	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/widok/nowyTemat.jsp").forward(request, response); 
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String tytul=request.getParameter("tytul");
		String tresc=request.getParameter("tresc");
		if(! "".equals(tytul)&&(! "".equals(tytul))){
			Timestamp t=new Timestamp(new Date().getTime());
			Uzytkownik zalogowany= (Uzytkownik) request.getSession().getAttribute("uzytkownik");
			TematyDAO tematyDAO= (TematyDAO) request.getAttribute("tematy");
			Temat temat=new Temat();
			temat.setData(t);
			temat.setTresc(tresc);
			temat.setTytul(tytul);
			temat.setUzytkownik(zalogowany);
			if(tematyDAO.dodajTemat(temat)) {
				response.sendRedirect(request.getContextPath()+"/temat?id="+temat.getId());
			}
			
			
		}
		
	}

}

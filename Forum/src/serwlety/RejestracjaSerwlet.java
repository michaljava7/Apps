package serwlety;

import java.io.IOException;

import javax.persistence.NoResultException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UzytkownicyDAO;
import encje.Uzytkownik;


@WebServlet("/rejestruj")
public class RejestracjaSerwlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("WebContent/WEB-INF/widok/rejestracja.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	String login=request.getParameter("login");
	String haslo=request.getParameter("haslo");
	String haslo2=request.getParameter("haslo2");
	if(login!=null&&haslo!=null&&haslo2!=null&&haslo.equals(haslo2)&&!"".equals(haslo)) {
		UzytkownicyDAO dao= (UzytkownicyDAO) request.getAttribute("uzytkownicyDAO"); 
		try {
			dao.pobierzPoLoginie(login);
			request.setAttribute("blad", "login istnieje!!!" );
			doGet(request, response);
			return;
		}catch (NoResultException e) {
			Uzytkownik u=new Uzytkownik();
			u.setLogin(login);
			u.setHaslo(haslo);
			if(dao.dodajUzytkownika(u)) {
				response.sendRedirect(request.getContextPath()+"/index");
			}
			else {
				request.setAttribute("blad", "nie udalo sie zarejestrowac");
			}
		}
	}else {
		request.setAttribute("blad", "blad danych logowania");
	}
	
	}

}

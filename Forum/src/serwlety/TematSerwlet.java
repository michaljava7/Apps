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
import dao.WpisyDAO;
import encje.Temat;
import encje.Uzytkownik;
import encje.Wpis;

@WebServlet("/temat")
public class TematSerwlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String stringId = req.getParameter("id");
		if (stringId != null) {
			int id = Integer.parseInt(stringId);
			TematyDAO dao = (TematyDAO) req.getAttribute("tematyDAO");
			Temat t = dao.pobierzTemat(id);
			req.setAttribute("temat", t);
			req.getRequestDispatcher("WEB-INF/widok/temat.jsp").forward(req, resp);
		} else {
			resp.sendRedirect(req.getContextPath() + "/");
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String tresc = req.getParameter("tresc");
		String stringId = req.getParameter("id");
		if (tresc != null && stringId != null) {
			int id = Integer.parseInt(stringId);
			WpisyDAO wpisyDAO = (WpisyDAO) req.getAttribute("wpisyDAO");
			TematyDAO tematyDAO = (TematyDAO) req.getAttribute("tematyDAO");
			Uzytkownik zalogowany = (Uzytkownik) req.getSession().getAttribute("uzytkownik");
			Temat temat = tematyDAO.pobierzTemat(id);
			Wpis wpis = new Wpis();
			wpis.setData(new Timestamp(new Date().getTime()));
			wpis.setTresc(tresc);
			wpis.setUzytkownik(zalogowany);
			wpis.setTemat(temat);
			wpisyDAO.dodajWpis(wpis);
			
		}
		doGet(req, resp);
	}
}

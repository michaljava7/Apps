package serwlety;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import dao.TematyDAO;

import encje.Temat;
	@WebServlet("/index")
public class IndexSerwlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		TematyDAO tematyDAO= (TematyDAO) req.getAttribute("rola2DAO");
		 List<Temat> tematy = ((TematyDAO) tematyDAO).pobierzTematy();
		 req.setAttribute("tematy", tematy);
		 req.getRequestDispatcher("/WEB-INF/widok/index.jsp").forward(req, resp); 
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}

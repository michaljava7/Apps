import javax.servlet.http.*;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.*;
// SERWLET POBIERANIA PARAMETR�W

public class GetParamsServ extends HttpServlet {

	private ServletContext context;
	private String resBundleServ; // nazwa serwletu przygotowuj�cego
									// sparametryzowan� informacje

	// Inicjacja
	public void init() {
		context = getServletContext();
		resBundleServ = context.getInitParameter("resBundleServ");
	}

	// Obs�uga zlece�
	public void serviceRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// W��czenie serwletu przygotowuj�cego informacje z z zasob�w
		// (ResourceBundle). Informacja b�dzie dost�pna poprzez
		// statyczne metody klasy BundleInfo

		RequestDispatcher disp = context.getRequestDispatcher(resBundleServ);
		disp.include(req, resp);

		// Pobranie potrzebnej informacji
		// ktora zosta�a wczesniej przygotowana
		// przez klas� BundleInfo na podstawie zlokalizowanych zasob�w

		// Zlokalizowana strona kodowa
		String charset = BundleInfo.getCharset();

		// Napisy nag��wkowe
		String[] headers = BundleInfo.getHeaders();

		// Nazwy parametr�w (pojawi� si� w formularzu,
		// ale r�wnie� s� to nazwy parametr�w dla Command)
		String[] pnames = BundleInfo.getCommandParamNames();

		// Opisy parametr�w - aby by�o wiadomo co w formularzu wpisywa�
		String[] pdes = BundleInfo.getCommandParamDescr();

		// Napis na przycisku
		String submitMsg = BundleInfo.getSubmitMsg();

		// Ew. ko�cowe napisy na stronie
		String[] footers = BundleInfo.getFooters();

		// Ustalenie w�a�ciwego kodowania zlecenia
		// - bez tego nie b�dzie mo�na w�asciwie odczyta� parametr�w
		req.setCharacterEncoding(charset);

		// Pobranie aktualnej sesji
		// w jej atrybutach s�/b�d� przechowywane
		// warto�ci parametr�w

		HttpSession session = req.getSession();

		// Generowanie strony

		resp.setCharacterEncoding(charset);
		PrintWriter out = resp.getWriter();

		out.println("<center><h2>");
		for (int i = 0; i < headers.length; i++)
			out.println(headers[i]);
		out.println("</center></h2><hr>");

		// formularz
		out.println("<form method=\"post\">");
		for (int i = 0; i < pnames.length; i++) {
			out.println(pdes[i] + "<br>");
			out.print("<input type=\"text\" size=\"30\" name=\"" + pnames[i] + "\"");

			// Jezeli s� ju� warto�ci parametr�w - poka�emy je w formularzu
			String pval = (String) session.getAttribute("param_" + pnames[i]);
			if (pval != null)
				out.print(" value=\"" + pval + "\"");
			out.println("><br>");
		}
		out.println("<br><input type=\"submit\" value=\"" + submitMsg + "\">");
		out.println("</form>");

		// Pobieranie parametr�w z formularza

		for (int i = 0; i < pnames.length; i++) {
			String paramVal = req.getParameter(pnames[i]);
			// Je�eli brak parametru (�w) - konczymy
			if (paramVal == null)
				return;

			// Jest parametr - zapiszmy jego warto�� jako atrybut sesji.
			// Zostanie on pobrany przez Controller
			// kt�ry ustali te warto�ci dla wykonania Command

			session.setAttribute("param_" + pnames[i], paramVal);

		}
	}
	// ..metody doGet i doPost - wywo�uj� serviceRequest
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		serviceRequest(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		serviceRequest(request, response);
	}
	
}
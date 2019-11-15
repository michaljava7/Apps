package formularze;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class Regex1 extends HttpServlet {

	// Początek HTML i właściwości <body> - tło, kolor tekstu i linków
	private String prolog = "<html>\r\n" + "<head>\r\n"
			+ " <meta http-equiv=\"Content-Type\" content=\"text/html; charset=windows-1250\">\r\n"
			+ " <title>Testowanie</title>\r\n" + "</head>\r\n" + "<body>\r\n"
			+ "<center><h2>Testowanie wyrażeń regularnych</h2></center>\r\n" + "<hr>\r\n"
			+ "<form method=\"post\" action=\"http://localhost:8080/serwlety1/regex\">\r\n" + "Wzorzec: <br>\r\n"
			+ "<input type=\"text\" size=\"30\" name=\"regex\"><br>\r\n" + "Tekst:<br>\r\n"
			+ "<input type=\"text\" size=\"50\" name=\"input\"><br><br>  \r\n"
			+ "<input type=\"submit\" value=\"Pokaż wynik wyszukiwania\">\r\n" + "</form>\r\n";

	// Znaczniki zamykające
	private String epilog = "</body></html>";

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		serviceRequest(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		serviceRequest(request, response);
	}

	public void serviceRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String charset = "windows-1250";

//  ustalenie  strony kodowej zlecenia

		req.setCharacterEncoding(charset);

		resp.setContentType("text/html; charset=" + charset);

		PrintWriter out = resp.getWriter();
		out.println(prolog);
		out.println(epilog);
		String regex = req.getParameter("regex");
		String input = req.getParameter("input");

		if (regex == null || input == null) {
			out.println("<h2>Wadliwe argumenty wywołania</h2>");
			out.close();
			return;
		}

		out.println("<h3>Wyrażenie:<br>\"" + regex + "\"</h3>");
		out.println("<h3>Tekst:<br>\"" + input + "\"</h3>");
		out.println("<hr>");

		try {
			Pattern pattern = Pattern.compile(regex);
			Matcher matcher = pattern.matcher(input);
			boolean found = matcher.find();
			if (!found)
				out.println("<h3>Nie znaleziono żadnego podłańcucha " + "pasującego do wzorca</h3>");
			else {
				out.println("<h3>Dopasowano:</h3>");
				out.println("<ol>");
				do {
					out.println("<li>podłańcuch \"" + matcher.group() + "\" od pozycji " + matcher.start()
							+ " do pozycji " + (matcher.end() - 1) + "</li>");
				} while (matcher.find());
				out.println("</ol>");
			}
		} catch (PatternSyntaxException exc) {
			out.println("<h2>Błąd w wyrażeniu</h2>");
		} finally {
			out.close();
		}
	}

}
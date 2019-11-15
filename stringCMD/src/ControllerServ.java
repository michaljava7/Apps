
import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ControllerServ extends HttpServlet {

	private ServletContext context;
	private Command command; // obiekt klasy dzialania
	private String presentationServ; // nazwa serwlet prezentacji
	private String getParamsServ; // mazwa serwletu pobierania parametr�w
	private Object lock = new Object(); // semafor dla synchronizacji
										// odwo�a� wielu w�tk�w
	
	public void init() {
		context = getServletContext();
		presentationServ = context.getInitParameter("presentationServ");
		getParamsServ = context.getInitParameter("getParamsServ");
		String commandClassName = context.getInitParameter("commandClassName");
		// Za�adowanie klasy Command i utworzenie jej egzemplarza
		// kt�ry b�dzie wykonywa� prac�
		try {
			Class commandClass = Class.forName(commandClassName);
			command = (Command) commandClass.newInstance();
		} catch (Exception exc) {
			throw new NoCommandException("Nie mog� stworzy� obiektu klasy " + commandClassName);
		}
	}

	public void serviceRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		resp.setContentType("text/html");
		
// Wywolanie serwletu pobierania parametr�w
		RequestDispatcher disp = context.getRequestDispatcher(getParamsServ);
		disp.include(req, resp);

// Pobranie bie��cej sesji
// i z jej atrybut�w - warto�ci parametr�w
// ustalonych przez servlet pobierania parametr�w
// R�ne informacje o aplikacji (np. nazwy parametr�w)
// s� wygodnie dost�pne poprzez w�asn� klas� BundleInfo

		HttpSession ses = req.getSession();

		String[] pnames = BundleInfo.getCommandParamNames();
		for (int i = 0; i < pnames.length; i++) {

			String pval = (String) ses.getAttribute("param_" + pnames[i]);

			if (pval == null)
				return; // jeszcze nie ma parametr�w

// Ustalenie tych parametr�w dla Command
			command.setParameter(pnames[i], pval);
		}

// Wykonanie dzia�a� definiowanych przez Command
// i pobranie wynik�w
// Poniewa� do serwletu mo�e naraz odwo�ywa� sie wielu klient�w
// (wiele watk�w) - potrzebna jest synchronizacja

		synchronized (lock) {
// wykonanie
			command.execute();

// pobranie wynik�w
			List results = (List) command.getResults();

// Pobranie i zapami�tanie kodu wyniku (dla servletu prezentacji)
			ses.setAttribute("StatusCode", new Integer(command.getStatusCode()));

// Wyniki - b�d� dost�pne jako atrybut sesji
			ses.setAttribute("Results", results);
		}

// Wywo�anie serwletu prezentacji
		disp = context.getRequestDispatcher(presentationServ);
		disp.forward(req, resp);
	}
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		serviceRequest(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		serviceRequest(request, response);
	}
}
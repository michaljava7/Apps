package serwlety;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.Enumeration;
import java.util.Map;

public class Msg extends HttpServlet {

	// Poczštek HTML i właciwoci <body> - tło, kolor tekstu i linków
	private String prolog = "<html>\r\n" + 
			"<head>\r\n" + 
			" <meta http-equiv=\"Content-Type\" content=\"text/html; charset=ISO8859-2\">\r\n" + 
			" <title>Testowanie</title>\r\n" + 
			"</head>\r\n" + 
			"<body>\r\n" + 
			"<center><h2>Testowanie parametrów</h2></center>\r\n" + 
			"<hr>\r\n" + 
			"<form method=\"post\" action=\"http://localhost:8080/serwlety1/msg\">\r\n" + 
			"id<input type=\"text\" size=\"50\" name=\"ident\"><br>\r\n" + 
			"p1<input type=\"text\" size=\"50\" name=\"p1\"><br>\r\n" + 
			"p2<input type=\"text\" size=\"50\" name=\"p2\"><br>\r\n" + 
			"p3<input type=\"text\" size=\"50\" name=\"p3\"><br>\r\n" + 
			"p4<input type=\"text\" size=\"50\" name=\"p4\"><br>\r\n" + 
			"p5<input type=\"text\" size=\"50\" name=\"p5\"><br>\r\n" + 
			"p6<input type=\"text\" size=\"50\" name=\"p6\"><br>\r\n" + 
			" _\r\n" + 
			"<br><input type=\"submit\" value=\"Wylij formularz\">\r\n" + 
			"</form>\r\n" + 
			"</body></html>";

	// Znaczniki zamykajšce


	// Metoda obsługi zlecenia GET
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		serviceRequest(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		serviceRequest(request, response);
	}

	public void serviceRequest(HttpServletRequest req,
			HttpServletResponse resp) throws ServletException, IOException {
	req.setCharacterEncoding("ISO8859-2");
	resp.setContentType("text/html; charset=ISO8859-2");
		 PrintWriter out = resp.getWriter();
		   out.println(prolog);
		   out.println("Metoda: " + req.getMethod());
		    // Przy zleceniu POST
		    // Albo uzyskujemy parametry przez metody getParameter...
		    // albo czytamy je ze strumienia jako "ciało" zlecenia
		    // ale nie równoczenie i to i to

		    boolean readBodyStream = false 	;  // czytamy ze strumienia

		    if (!readBodyStream) {
		    	  out.println("Metoda: " + req.getMethod());

		          out.println("Query : " + req.getQueryString());
		          out.println("Parametry:");
		          Enumeration pnams = req.getParameterNames();
		          while (pnams.hasMoreElements()) {
		            String name = (String) pnams.nextElement();
		            String value = req.getParameter(name);
		            out.println(name + " = "  + value);
		          }
		          out.println("Dostęp przez mapę");
		          Map map = req.getParameterMap();
		          String[] val = (String[]) map.get("ident");
		          out.println("Parametr o nazwie ident");
		          out.println("- z mapy uzyskujemy tablice String[]");
		          out.println("- jej rozmiar " + val.length );
		          out.println("- jej elementy:" );
		          for (int i=0; i<val.length; i++) out.println(val[i]);

		         out.close();
		    }
		    else {
		      out.println("Czytanie tresci zlecenia ze strumienia:");

		      BufferedReader br = req.getReader();
		      String line;
		      while ((line = br.readLine()) != null) out.println(line);
		      br.close();
		    }

		    out.close();
		  }
	}

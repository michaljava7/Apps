package serwlety;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;
import java.text.*;

public class Time1 extends HttpServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, java.io.IOException {
// preferowany jêzyk klienta?
		Locale locale = request.getLocale();

// uzyskanie  zlokalizowanego zasobu
		ResourceBundle msg = ResourceBundle.getBundle("international.Messages", locale);

//  komunikaty i odpowiednia strona kodowa
		String hello = msg.getString("hello");
		String now = msg.getString("now");
		String charset = msg.getString("charset");

// ustalenie typu i kodowania odpowiedzi musi byæ ustalone przed uzyskaniem strumienia wyjœciowego
		response.setContentType("text/html; charset=" + charset);

// Pobranie strumienia wyjœciowego z ustawieniem w³asciwego kodowania

		PrintWriter out = new PrintWriter(new OutputStreamWriter(response.getOutputStream(), charset), true);

		out.println("<h2>" + hello + "<br>" + now + "<br>");
		out.println(getDate(locale) + "</h2>");
		out.close();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, java.io.IOException {

		Locale locale = request.getLocale();

		// Uzyskanie zlokalizowanego zasobu
		ResourceBundle msg = ResourceBundle.getBundle("international.Messages", locale);

		String hello = msg.getString("hello");
		String now = msg.getString("now");
		String charset = msg.getString("charset");

		response.setContentType("text/html; charset=" + charset);

		PrintWriter out = new PrintWriter(new OutputStreamWriter(response.getOutputStream(), charset), true);

		out.println("<h2>" + hello + "<br>" + now + "<br>");
		out.println(getDate(locale) + "</h2>");
		out.close();
	}

	private String getDate(Locale loc) {
		Date data = new Date();
		DateFormat df = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.MEDIUM, loc);
		return df.format(data);
	}

}
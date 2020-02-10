package utils;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UzytkownicyDAO;
import encje.Uzytkownik;

@WebFilter("/*")
public class FiltrZalogowany implements Filter {

	public void destroy() {
		
	}

	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req=(HttpServletRequest) request;
		HttpServletResponse res=(HttpServletResponse) response;
		req.setCharacterEncoding("UTF-8");
		String login=req.getRemoteUser();
		if(login!=null) {
			Uzytkownik u=(Uzytkownik) req.getSession().getAttribute("uzytkownik");
			if(u==null) {
				UzytkownicyDAO dao= (UzytkownicyDAO) req.getAttribute("uzytkownicyDAO");
				dao.pobierzPoLoginie(login);
				req.setAttribute("uzytkownik", u);
			}
		}
		
		
		
		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
	
	}

}

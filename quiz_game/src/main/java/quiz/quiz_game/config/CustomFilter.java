package quiz.quiz_game.config;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/*")
public class CustomFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        Integer session = (Integer) req.getSession().getAttribute("id");
        String requestUri = req.getRequestURI();

        if (
                session != null ||
                        requestUri.endsWith(".css") ||
                        requestUri.endsWith(".jpg") ||
                        requestUri.endsWith("login") ||
                        requestUri.endsWith("register")
        ) {
            filterChain.doFilter(req, resp);
        }else{
            resp.sendRedirect(resp.encodeRedirectURL("/login"));
        }
    }

    @Override
    public void destroy() {

    }
}

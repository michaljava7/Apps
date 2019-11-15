import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.*;


public class ResultPresent extends HttpServlet {


  public void serviceRequest(HttpServletRequest req,
                             HttpServletResponse resp)
                             throws ServletException, IOException
  {
    ServletContext context = getServletContext();

    // W��czenie strony generowanej przez serwlet pobierania parametr�w
    // (formularz)
    String getParamsServ = context.getInitParameter("getParamsServ");
    RequestDispatcher disp = context.getRequestDispatcher(getParamsServ);
    disp.include(req,resp);

    // Uzyskanie wynik�w i wyprowadzenie ich
    // Controller po wykonaniu Command zapisa� w atrybutach sesji
    // - referencje do listy wynik�w jako atrybut "Results"
    // - warto�c kodu wyniku wykonania jako atrybut "StatusCode"

    HttpSession ses = req.getSession();
    List results = (List) ses.getAttribute("Results");
    Integer code = (Integer) ses.getAttribute("StatusCode");

    PrintWriter out = resp.getWriter();
    out.println("<hr>");

    // Uzyskanie napisu w�a�ciwego dla danego "statusCode"
    String msg = BundleInfo.getStatusMsg()[code.intValue()];
    out.println("<h2>" + msg + "</h2>");

    // Elementy danych wyj�ciowych (wynik�w) mog� by�
    // poprzedzane jakimi� opisami (zdefiniowanymi w ResourceBundle)
    String[] dopiski = BundleInfo.getResultDescr();

    // Generujemy raport z wynik�w
    out.println("<ul>");
    for (Iterator iter = results.iterator(); iter.hasNext(); ) {
      out.println("<li>");

      int dlen = dopiski.length;  // d�ugo�� tablicy dopisk�w
      Object res = iter.next();
      if (res.getClass().isArray()) {  // jezeli element wyniku jest tablic�
        Object[] res1 = (Object[]) res;
        int i;
        for (i=0; i < res1.length; i++) {
          String dopisek = (i < dlen ? dopiski[i] + " " : "");
          out.print(dopisek + res1[i] + " ");
        }
        if (dlen > res1.length) out.println(dopiski[i]);
      }
      else {                                      // mo�e nie by� tablic�
        if (dlen > 0) out.print(dopiski[0] + " ");
        out.print(res);
        if (dlen > 1) out.println(" " + dopiski[1]);
      }
      out.println("</li>");
    }
    out.println("</ul>");
  }
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		serviceRequest(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		serviceRequest(request, response);
	}
}
package serwlety;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;
import java.net.*;

public class Msg2 extends HttpServlet {

  private ServletContext context;
  private PrintWriter out;

  public void doGet(HttpServletRequest req, HttpServletResponse resp)
                       throws ServletException, IOException
  {
	 context = this.getServletContext();
    out = resp.getWriter();
    out.println("To jest strona wygenerowana przez serwlet " +
                 this.getClass().getName()+ ".class");
    out.println("-------------------------------------------------------");

    //  URL  zlecenia
    String requestURL = req.getRequestURL().toString();
    out.println("RequestURL: " + requestURL);

    //  kontekst
    

    // informacje o serwerze z kontekstu
    out.println("\nServer info\n" + context.getServerInfo() );

    //  nazwa aplikacji okreœlona w <display-name>

    out.println("\nAplikacja ma nazwe: " + context.getServletContextName() );

    //   œcie¿ki
    String contextPath = req.getContextPath();
    String servletPath = req.getServletPath();

   //  Od kontekstu uzyskujemy fizyczne œcie¿ki prowadzace do "wirtualnych" URLI

    out.println("\nInformacja o sciezkach");
    msg("ContextPath", contextPath);
    msg("ServletPath", servletPath);

  //   I nasze pliki "zasobowe" (HTML, JPG)
    msg("Plik Bye.html", "/Bye.html");
    msg("Plik os2.jpg", "/images/os2.jpg");

    // Lista zasobów aplikacji
    out.println("\nLista zasobow aplikacji");
    listResources("/");

    // operacje IO
    InputStream in = context.getResourceAsStream("/WEB-INF/web.xml");
    BufferedReader br = new BufferedReader( new InputStreamReader(in));
    out.println("\nPierwszy wiersz pliku web.xml");
    out.println(br.readLine());
    br.close();

    // katalogu  serwletu
    File dir = new File(".");
    out.println("\nA serwlet dzialal w katalogu: " + dir.getAbsolutePath());


    out.close();
  }

   // Listuje zasoby app
  private void listResources(String path)  {
    if (path == null) return;
    Set res = context.getResourcePaths(path);
    for (Iterator iter = res.iterator(); iter.hasNext(); ) {
      String resItem = (String) iter.next();
      if (resItem.endsWith("/")) listResources(resItem);
      else out.println(resItem);
    }
  }

  private void msg(String info, String path) {
    out.println("------------------------------------------");
    out.println(info);
    String realPath = context.getRealPath(path);
    out.println("Virtual: " + path);
    out.println("Real   : " + realPath);
  }

}

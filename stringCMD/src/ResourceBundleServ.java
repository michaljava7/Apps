import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

class BundleInfo {
  static private String[] commandParamNames;
  static private String[] commandParamDescr;
  static private String[] statusMsg;
  static private String[] headers;
  static private String[] footers;
  static private String[] resultDescr;
  static private String charset;
  static private String submitMsg;
//  private static RegexParamsDef_pl rb=new RegexParamsDef_pl();   !!!!!!!!!!!!!!!!!moje!!!!!!!!!!!
  
  static void generateInfo(ResourceBundle rb) {

    synchronized (BundleInfo.class) {  // konieczne ze wzgl�du
                                       // na mo�liwo�� odwo�a�
      List cpn = new ArrayList();      // z wielu egzemplarzy serwlet�w
      List cpv = new ArrayList();
      Enumeration keys = rb.getKeys();
      while (keys.hasMoreElements()) {
        String key = (String) keys.nextElement();
        if (key.startsWith("param_")) {
          cpn.add(key.substring(6));
          cpv.add(rb.getString(key));
        }
        else if (key.equals("header")) headers = rb.getStringArray(key);
        else if (key.equals("footer")) footers = rb.getStringArray(key);
        else if (key.equals("resCode")) statusMsg = rb.getStringArray(key);
        else if (key.equals("resDescr")) resultDescr = rb.getStringArray(key);
        else if (key.equals("charset")) charset = rb.getString(key);
        else if (key.equals("submit")) submitMsg = rb.getString(key);
      }
      commandParamNames = (String[]) cpn.toArray(new String[0]);
      commandParamDescr = (String[]) cpv.toArray(new String[0]);
    }
  }

  public static String getCharset() {
    return charset;
  }

  public static String getSubmitMsg() {
    return submitMsg;
  }

    public static String[] getCommandParamNames() {
    return commandParamNames;
  }

  public static String[] getCommandParamDescr() {
    return commandParamDescr;
  }

  public static String[] getStatusMsg() {
    return statusMsg;
  }

  public static String[] getHeaders() {
    return headers;
  }

  public static String[] getFooters() {
    return footers;
  }

  public static String[] getResultDescr() {
    return resultDescr;
  }

}


// Serwlet w��czany wy��cznie z serwletu pobierania parametr�w
// �aduje  ResourceBundle i przekazuje go klasie BundleInfo,
// kt�ra odczytuje info i daje wygodn� form� jej pobierania
// w innych serwletach.
// �adowanie zasob�w i ich przygotowanie przez klas� BundleInfo
// nast�puje tylko raz na sesj�.

public class ResourceBundleServ extends HttpServlet {

  private String resBundleName;

  public void init() {
    resBundleName = getServletContext().getInitParameter("resBundleName");
  }

  public void serviceRequest(HttpServletRequest req,
                             HttpServletResponse resp)
                             throws ServletException, IOException
  {
    HttpSession ses = req.getSession();
    ResourceBundle paramsRes = (ResourceBundle) ses.getAttribute("resBundle");

    // W tej sesji jeszcze nie odczytali�my zasob�w
    if (paramsRes == null) {
       Locale loc = req.getLocale();
       paramsRes = ResourceBundle.getBundle(resBundleName, loc);
      
       
       // Przygotowanie zasob�w w wygodnej do odczytu formie
       BundleInfo.generateInfo(paramsRes);
       ses.setAttribute("resBundle", paramsRes);
    }

    

}
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		serviceRequest(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		serviceRequest(request, response);
	}}
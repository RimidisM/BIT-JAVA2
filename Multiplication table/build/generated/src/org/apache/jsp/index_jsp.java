package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class index_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <title>Daugyba</title>\n");
      out.write("        <style>\n");
      out.write("            table {\n");
      out.write("                font-family: arial, sans-serif;\n");
      out.write("                border-collapse: collapse;\n");
      out.write("                width: 100%;\n");
      out.write("\n");
      out.write("            }\n");
      out.write("\n");
      out.write("            th {\n");
      out.write("                border: 1px solid #dddddd;\n");
      out.write("                text-align: left;\n");
      out.write("                padding: 8px;\n");
      out.write("            }\n");
      out.write("            h1 {\n");
      out.write("                text-align: center;\n");
      out.write("                padding: 20px;\n");
      out.write("                color: red;\n");
      out.write("            }\n");
      out.write("            h3 {\n");
      out.write("                text-align: center;\n");
      out.write("                padding: 20px;\n");
      out.write("                color: green;\n");
      out.write("            }\n");
      out.write("            form {\n");
      out.write("                width: 100%;\n");
      out.write("                text-align: center;\n");
      out.write("                font-size: 13px;\n");
      out.write("                text-transform: uppercase;  \n");
      out.write("                padding: 20px;\n");
      out.write("                color: blue;\n");
      out.write("            }\n");
      out.write("\n");
      out.write("        </style>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        <h1>Kintamo dydžio daugybos lentelė!</h1>\n");
      out.write("\n");
      out.write("        ");

            int nuo, iki;
            try {
                nuo = Math.abs(Integer.parseInt(request.getParameter("nuo")));
            } catch (Exception ex) {
                nuo = 1;
            }
            try {
                iki = Math.abs(Integer.parseInt(request.getParameter("iki")));
            } catch (Exception ex) {
                iki = 10;
            }
            if (nuo > iki) {
                iki = Math.abs(Integer.parseInt(request.getParameter("nuo")));
                nuo = Math.abs(Integer.parseInt(request.getParameter("iki")));
            }
        
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("        <table>\n");
      out.write("            <tr>\n");
      out.write("                <th style=\"border: none;\"></th>\n");
      out.write("                    ");

                        for (int i = nuo; i <= iki; i++) {
                    
      out.write("\n");
      out.write("                <th style=background-color:blanchedalmond>");
      out.print(i);
      out.write("</th>\n");
      out.write("                    ");

                        }
                    
      out.write("\n");
      out.write("            </tr>\n");
      out.write("            ");

                for (int k = nuo; k <= iki; k++) {
            
      out.write("\n");
      out.write("            <tr> <th style=background-color:blanchedalmond>");
      out.print(k);
      out.write("</th> \n");
      out.write("                    ");

                        for (int j = nuo; j <= iki; j++) {
                            int a = k * j;
                    
      out.write("\n");
      out.write("                <th >");
      out.print(a);
      out.write("</th>\n");
      out.write("                    ");

                        }
                    
      out.write("\n");
      out.write("            </tr>\n");
      out.write("            ");

                }
            
      out.write("\n");
      out.write("        </table>\n");
      out.write("        <h3>Įveskite norimus skaičius</h3>\n");
      out.write("        <form>\n");
      out.write("            nuo:<input name =\"nuo\" value=\"");
      out.print(nuo);
      out.write("\">\n");
      out.write("            iki:<input name=\"iki\" value=\"");
      out.print(iki);
      out.write("\">\n");
      out.write("            <input type=\"submit\" value=\"Vykdyti\">\n");
      out.write("        </form>\n");
      out.write("\n");
      out.write("    </body>\n");
      out.write("</html>\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}

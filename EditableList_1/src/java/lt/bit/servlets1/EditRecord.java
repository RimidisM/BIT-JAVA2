/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lt.bit.servlets1;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lt.bit.data1.Record;

/**
 *
 * @author rimid
 */
@WebServlet(name = "EditRecord", urlPatterns = {"/edit"})
public class EditRecord extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            String na = request.getParameter("na");
            String sn = request.getParameter("sn");
            Integer by = Math.abs(Integer.parseInt(request.getParameter("by")));
            Integer bm = Math.abs(Integer.parseInt(request.getParameter("bm")));
            Integer bd = Math.abs(Integer.parseInt(request.getParameter("bd")));
            String sa = request.getParameter("sa");
            String ids = request.getParameter("id");
            Integer id;

            Pattern pattern = Pattern.compile("[^A-Za-z0-9]");
            Matcher match1 = pattern.matcher(na);
            boolean naTest = match1.find();
            Matcher match2 = pattern.matcher(sn);
            boolean snTest = match2.find();
            Matcher match3 = pattern.matcher(sa);
            boolean saTest = match3.find();

            id = new Integer(ids);

            if (by < 1900 || bm > 12 || bd > 31 || bm <= 0 || bd <= 0) {
                System.out.println("Blogai nurodyta gimimo data~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

            } else if (na.length() > 2 && 2 < sn.length()
                    && 0 <= sa.length() && !naTest && !snTest
                    && !na.matches(".*\\d.*") && !sn.matches(".*\\d.*")) {
                Record.editInfo(id, na, sn, by, bm, bd, sa);
            }
        } catch (NumberFormatException e) {
            //ToDO
            System.out.println("Neivesti duomenis arba neteisingas ju formatas~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        }
        response.sendRedirect("index.jsp");
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

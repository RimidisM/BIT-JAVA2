/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lt.bit.servlets1;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lt.bit.data22.Person;

/**
 *
 * @author rimid
 */
@WebServlet(name = "AddRecord", urlPatterns = {"/add"})
public class AddRecord extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * @throws java.sql.SQLException
     * @throws java.text.ParseException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, ParseException {
        EntityManager em = (EntityManager) request.getAttribute("em");
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {
            String na = request.getParameter("na");
            String sn = request.getParameter("sn");
            String bd = request.getParameter("bd");
            BigDecimal sa = new BigDecimal(request.getParameter("sa"));

            SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
            Date date = java.sql.Date.valueOf(bd);
            Calendar c = Calendar.getInstance();
            c.setTime(date);
            c.set(Calendar.HOUR, 12);
            date = c.getTime();

            Pattern pattern = Pattern.compile("[^A-Za-z0-9]");
            Matcher match1 = pattern.matcher(na);
            boolean naTest = match1.find();
            Matcher match2 = pattern.matcher(sn);
            boolean snTest = match2.find();

            if (na.length() > 2 && 2 < sn.length()
                    && 0 <= bd.length() && !naTest && !snTest
                    && !na.matches(".*\\d.*") && !sn.matches(".*\\d.*")) {
                Person p = new Person();
                p.setFirstName(na);
                p.setLastName(sn);
                p.setBirthDate(new java.sql.Date(date.getTime()));
                p.setSalary(sa);
                em.persist(p);
                em.flush();
                tx.commit();
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
        try {
            processRequest(request, response);
        } catch (SQLException | ParseException ex) {
            Logger.getLogger(AddRecord.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (SQLException | ParseException ex) {
            Logger.getLogger(AddRecord.class.getName()).log(Level.SEVERE, null, ex);
        }
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

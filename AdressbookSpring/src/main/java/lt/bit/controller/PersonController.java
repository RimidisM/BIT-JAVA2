/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lt.bit.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;
import lt.bit.data.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author rimid
 */
@Controller
public class PersonController {

    @Autowired
    private HttpServletRequest request;

    @GetMapping("/")
    public ModelAndView listPersons() {
        ModelAndView mav = new ModelAndView("index");
        EntityManager em = (EntityManager) request.getAttribute("em");
        Query q = em.createQuery("select p from Person p order by p.id");
        List<Person> l = q.getResultList();
        mav.addObject("list", l);

        return mav;
    }

    @PostMapping("/remove")
    public String deletePerson(@PathParam("id") Integer id) {
        EntityManager em = (EntityManager) request.getAttribute("em");
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Person p = em.find(Person.class, id);
            em.remove(p);
            em.flush();
            tx.commit();
        } catch (NumberFormatException ex) {
            //Ignored
        }

        return "redirect:/";
    }

    @PostMapping("/add")
    public String addPerson(@PathParam("na") String na, @PathParam("sn") String sn, @PathParam("bd") String bd, @PathParam("sa") String sa) {
        EntityManager em = (EntityManager) request.getAttribute("em");
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
        BigDecimal salary = new BigDecimal(sa);
        try {
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
                p.setSalary(salary);
                em.persist(p);
                em.flush();
                tx.commit();
            }
        } catch (NumberFormatException e) {
            //ToDO
            System.out.println("Neivesti duomenis arba neteisingas ju formatas~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        }
        return "redirect:/";
    }

    @PostMapping("/edit")
    public String editPerson(@PathParam("id") Integer id, @PathParam("na") String na, @PathParam("sn") String sn, @PathParam("bd") String bd, @PathParam("sa") String sa) {
        EntityManager em = (EntityManager) request.getAttribute("em");
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
        BigDecimal salary = new BigDecimal(sa);
        try {
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
                    && 9 <= bd.length() && !naTest && !snTest
                    && !na.matches(".*\\d.*") && !sn.matches(".*\\d.*")) {
                Person p = em.find(Person.class, id);
                p.setFirstName(na);
                p.setLastName(sn);
                p.setBirthDate(new java.sql.Date(date.getTime()));
                p.setSalary(salary);
                em.flush();
                tx.commit();
            }
        } catch (NumberFormatException e) {
            //ToDO
            System.out.println("Neivesti duomenis arba neteisingas ju formatas~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        }
        return "redirect:/";
    }
}

//delte grazins stringa  ir yra spec view redirect:/ discorde
//spring web date
//spring security
//spring webservice
//sesijos
//

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lt.bit.controller;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;
import lt.bit.data.Contact;
import lt.bit.data.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author rimid
 */
@Controller
public class ContactController {

    @Autowired
    private HttpServletRequest request;

    @GetMapping("/contact")
    public ModelAndView listPersonsContacts(@PathParam("id") Integer id) {
        ModelAndView mav = new ModelAndView("contacts");
        EntityManager em = (EntityManager) request.getAttribute("em");
        Person p = em.find(Person.class, id);
        List<Contact> l = p.getContacts();
        mav.addObject("listContacts", l);
        mav.addObject("personName", p);
        System.out.println(mav);
        return mav;
    }

    @PostMapping("/removecontact")
    public ModelAndView deleteContact(@PathParam("ids") Integer ids, @PathParam("id") Integer id) {
        EntityManager em = (EntityManager) request.getAttribute("em");
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Contact a = em.find(Contact.class, ids);
            em.remove(a);
            em.flush();
            tx.commit();
        } catch (NumberFormatException ex) {
            //Ignored
        }

        ModelMap model = new ModelMap();
        model.addAttribute("id", id);
        return new ModelAndView("redirect:/contact", model);
    }

    @PostMapping("/addcontact")
    public ModelAndView addContact(@PathParam("typ") String typ, @PathParam("con") String con,
            @PathParam("id") Integer id) {
        EntityManager em = (EntityManager) request.getAttribute("em");
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {
            if (typ.length() > 2 && 2 < con.length()) {
                Contact a = new Contact();
                Person p = em.find(Person.class, id);
                a.setType(typ);
                a.setContact(con);
                a.setPerson(p);
                p.getContacts().add(a);

                em.flush();
                tx.commit();
            }
        } catch (NumberFormatException e) {
            //ToDO
            System.out.println("Neivesti duomenis arba neteisingas ju formatas~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        }

        ModelMap model = new ModelMap();
        model.addAttribute("id", id);
        return new ModelAndView("redirect:/contact", model);
    }

    @PostMapping("/uppdatecontact")
    public ModelAndView editContact(@PathParam("ids") Integer ids, @PathParam("id") Integer id,
            @PathParam("typ") String typ, @PathParam("con") String con) {
        EntityManager em = (EntityManager) request.getAttribute("em");
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            if (typ.length() > 2 && 2 < con.length()) {
                Contact a = em.find(Contact.class, ids);
                a.setType(typ);
                a.setContact(con);
                em.flush();
                tx.commit();
            }
        } catch (NumberFormatException e) {
            //ToDO
            System.out.println("Neivesti duomenis arba neteisingas ju formatas~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        }
        ModelMap model = new ModelMap();
        model.addAttribute("id", id);
        return new ModelAndView("redirect:/contact", model);
    }

    @PostMapping("/backToContactList")
    public ModelAndView back(@PathParam("id") Integer id) {
        ModelMap model = new ModelMap();
        model.addAttribute("id", id);
        return new ModelAndView("redirect:/contact", model);
    }
}

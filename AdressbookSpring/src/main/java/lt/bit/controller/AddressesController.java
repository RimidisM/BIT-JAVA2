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
import lt.bit.data.Address;
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
public class AddressesController {

    @Autowired
    private HttpServletRequest request;

    @GetMapping("/address")
    public ModelAndView listPersonsAddresses(@PathParam("id") Integer id) {
        ModelAndView mav = new ModelAndView("address");
        EntityManager em = (EntityManager) request.getAttribute("em");
        Person p = em.find(Person.class, id);
        List<Address> l = p.getAddresses();
        mav.addObject("listAddress", l);
        mav.addObject("personName", p);
        System.out.println(mav);
        return mav;
    }

    @PostMapping("/removeaddress")
    public ModelAndView deleteAddress(@PathParam("ids") Integer ids, @PathParam("id") Integer id) {
        EntityManager em = (EntityManager) request.getAttribute("em");
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Address a = em.find(Address.class, ids);
            em.remove(a);
            em.flush();
            tx.commit();
        } catch (NumberFormatException ex) {
            //Ignored
        }

        ModelMap model = new ModelMap();
        model.addAttribute("id", id);
        return new ModelAndView("redirect:/address", model);
    }

    @PostMapping("/addaddress")
    public ModelAndView addAddress(@PathParam("adr") String adr, @PathParam("cty") String cty,
            @PathParam("pos") String pos, @PathParam("id") Integer id) {
        EntityManager em = (EntityManager) request.getAttribute("em");
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            if (adr.length() > 2 && 2 < cty.length()
                    && 0 < pos.length()) {
                Address a = new Address();
                Person p = em.find(Person.class, id);

                a.setAddress(adr);
                a.setCity(cty);
                a.setPostalCode(pos);
                a.setPerson(p);
                p.getAddresses().add(a);
                em.flush();
                tx.commit();
            }
        } catch (NumberFormatException e) {
            //ToDO
            System.out.println("Neivesti duomenis arba neteisingas ju formatas~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        }
        ModelMap model = new ModelMap();
        model.addAttribute("id", id);
        return new ModelAndView("redirect:/address", model);
    }

    @PostMapping("/updateaddress")
    public ModelAndView editAddress(@PathParam("ids") Integer ids, @PathParam("id") Integer id,
            @PathParam("adr") String adr, @PathParam("cty") String cty, @PathParam("pos") String pos) {
        EntityManager em = (EntityManager) request.getAttribute("em");
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            if (adr.length() > 2 && 2 < cty.length()
                    && 0 < pos.length()) {
                Address a = em.find(Address.class, ids);
                a.setAddress(adr);
                a.setCity(cty);
                a.setPostalCode(pos);
                em.flush();
                tx.commit();
            }
        } catch (NumberFormatException e) {
            //ToDO
            System.out.println("Neivesti duomenis arba neteisingas ju formatas~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        }
        ModelMap model = new ModelMap();
        model.addAttribute("id", id);
        return new ModelAndView("redirect:/address", model);
    }

    @PostMapping("/backToAddressList")
    public ModelAndView back(@PathParam("id") Integer id) {
        ModelMap model = new ModelMap();
        model.addAttribute("id", id);
        return new ModelAndView("redirect:/address", model);
    }

    @PostMapping("/back")
    public String back() {
        return "redirect:/";
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lt.bit.data1;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author rimid
 */
public class Record {

    private static final List<Person> INFO = new ArrayList();

    static {
        INFO.add(new Person("Algis", "Algenis", 1999, 1, 23, "100.00"));
        INFO.add(new Person("Marius", "Marelis", 1985, 1, 1, "500.00"));
        INFO.add(new Person("Jonas", "Jonaitis", 1965, 9, 15, "1500.52"));

    }

    public static List<Person> getInfo() {
        return INFO;
    }

    public static void removeInfo(Integer id) {

        if (id == null) {
            return;
        }
        int found = -1;
        for (int i = 0; i < INFO.size(); i++) {
            Person p = INFO.get(i);
            if (id.equals(p.getId())) {
                found = i;
                break;
            }
        }
        if (found >= 0) {
            INFO.remove(found);
        }
    }

    public static void addInfo(String fna, String lna, Integer year, Integer month, Integer day, String sal) {
        INFO.add(new Person(fna, lna, year, month, day, sal));
    }

    public static void editInfo(Integer id, String fna, String lna, Integer year, Integer month, Integer day, String sal) {

        if (id == null) {
            return;
        }
        int found = -1;
        for (int i = 0; i < INFO.size(); i++) {
            Person p = INFO.get(i);
            if (id.equals(p.getId())) {
                found = i;
                break;
            }
        }
        if (found >= 0) {

            INFO.get(found).setId(found);
            INFO.get(found).setFirstName(fna);
            INFO.get(found).setLastName(lna);

            Calendar c = Calendar.getInstance();
            c.set(Calendar.YEAR, year);
            c.set(Calendar.MONTH, (month - 1));
            c.set(Calendar.DAY_OF_MONTH, day);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            INFO.get(found).setPrintDate(sdf.format(c.getTime()));

            INFO.get(found).setBirthYear(year);
            INFO.get(found).setBirthMonth(month);
            INFO.get(found).setBirthDay(day);
            INFO.get(found).setSalary(sal);

        }
    }

    public static Person getPerson(Integer id) {
        for (int i = 0; i < INFO.size(); i++) {
            Person p = INFO.get(i);
            if (p.getId().equals(id)) {
                return p;
            }
        }
        return null;
    }

    public static List<Person> getPersons() {
        return INFO;
    }

//~~~~~~~~~Addresses~~~~~~~~~~~~
    //person id
    public static List<Address> getPersonAddresses(Integer id) {
        Person p = getPerson(id);

        if (p != null) {
            return p.getAddresses();
        }
        return new ArrayList();
    }

    public static Address getAddress(Integer id) {
        for (Person p : getPersons()) {
            for (Address a : p.getAddresses()) {
                if (a.getId().equals(id)) {
                    return a;
                }
            }
        }
        return null;
    }

    public static void removeAddress(Integer id) {
        for (Person p : getPersons()) {
            for (Address a : p.getAddresses()) {
                if (a.getId().equals(id)) {
                    p.getAddresses().remove(a);
                    return;
                }
            }
        }
    }
//Person id

    public static void addAddress(Integer id, Address a) {
        Person p = getPerson(id);
        if (p == null) {
            return;
        }
        if (a == null || getAddress(a.getId()) != null) {
            return;
        }
        p.getAddresses().add(a);
    }

    public static void uppdateAddress(Address a) {
        if (a == null) {
            return;
        }
        Address existing = getAddress(a.getId());

        if (existing != null) {
            existing.setAddress(a.getAddress());
            existing.setAddress(a.getAddress());
            existing.setCity(a.getCity());
            existing.setPostalCode(a.getPostalCode());
        }
    }

//~~~~~~~~~CONTACTS~~~~~~~~~~~~
    //person id
    public static List<Contact> getPersonContacts(Integer id) {
        Person p = getPerson(id);

        if (p != null) {
            return p.getContacts();
        }
        return new ArrayList();
    }

    public static Contact getContact(Integer id) {
        for (Person p : getPersons()) {
            for (Contact c : p.getContacts()) {
                if (c.getId().equals(id)) {
                    return c;
                }
            }
        }
        return null;
    }

    public static void removeContact(Integer id) {
        for (Person p : getPersons()) {
            for (Contact c : p.getContacts()) {
                if (c.getId().equals(id)) {
                    p.getContacts().remove(c);
                    return;
                }
            }
        }
    }
//Person id

    public static void addContact(Integer id, Contact c) {
        Person p = getPerson(id);
        if (p == null) {
            return;
        }
        if (c == null || getContact(c.getId()) != null) {
            return;
        }
        p.getContacts().add(c);
    }

    public static void uppdateContact(Contact c) {
        if (c == null) {
            return;
        }
        Contact existing = getContact(c.getId());

        if (existing != null) {
            existing.setType(c.getType());
            existing.setContact(c.getContact());
        }
    }
}

//for each ciklai naudojasi iteratoriais, galima gauti Concurent exeption jis
//pamatys kad buvo  ir pasidare , nes jau pries tai iteracijoje istryneme ji.


//dbf failai, tai vienas failas viena lentele

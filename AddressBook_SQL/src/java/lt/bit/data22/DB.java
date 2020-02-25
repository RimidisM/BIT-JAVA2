/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lt.bit.data22;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rimid
 */
public class DB {

    private static final List<Person> INFO = new ArrayList();

//    static {
//        INFO.add(new Person("Algis", "Algenis", 1999, 1, 23, "100.00"));
//        INFO.add(new Person("Marius", "Marelis", 1985, 1, 1, "500.00"));
//        INFO.add(new Person("Jonas", "Jonaitis", 1965, 9, 15, "1500.52"));
//
//    }
//    public static List<Person> getInfo() {
//        return INFO;
//    }
//
//    public static void removeInfo(Integer id) {
//
//        if (id == null) {
//            return;
//        }
//        int found = -1;
//        for (int i = 0; i < INFO.size(); i++) {
//            Person p = INFO.get(i);
//            if (id.equals(p.getId())) {
//                found = i;
//                break;
//            }
//        }
//        if (found >= 0) {
//            INFO.remove(found);
//        }
//    }
//    public static void addInfo(String fna, String lna, Integer year, Integer month, Integer day, String sal) {
//        INFO.add(new Person(fna, lna, year, month, day, sal));
//    }
//
//    public static void editInfo(Integer id, String fna, String lna, Integer year, Integer month, Integer day, String sal) {
//
//        if (id == null) {
//            return;
//        }
//        int found = -1;
//        for (int i = 0; i < INFO.size(); i++) {
//            Person p = INFO.get(i);
//            if (id.equals(p.getId())) {
//                found = i;
//                break;
//            }
//        }
//        if (found >= 0) {
//
//            INFO.get(found).setId(found);
//            INFO.get(found).setFirstName(fna);
//            INFO.get(found).setLastName(lna);
//
//            Calendar c = Calendar.getInstance();
//            c.set(Calendar.YEAR, year);
//            c.set(Calendar.MONTH, (month - 1));
//            c.set(Calendar.DAY_OF_MONTH, day);
//            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//            INFO.get(found).setPrintDate(sdf.format(c.getTime()));
//
//            INFO.get(found).setBirthYear(year);
//            INFO.get(found).setBirthMonth(month);
//            INFO.get(found).setBirthDay(day);
//            INFO.get(found).setSalary(sal);
//
//        }
//    }
//    public static Person getPerson(Integer id) {
//        for (int i = 0; i < INFO.size(); i++) {
//            Person p = INFO.get(i);
//            if (p.getId().equals(id)) {
//                return p;
//            }
//        }
//        return null;
//    }
//
//    public static List<Person> getPersons() {
//        return INFO;
//    }
//
////~~~~~~~~~Addresses~~~~~~~~~~~~
//    //person id
//    public static List<Address> getPersonAddresses(Integer id) {
//        Person p = getPerson(id);
//
//        if (p != null) {
////            return p.getAddresses();
//        }
//        return new ArrayList();
//    }
//
//    public static Address getAddress(Integer id) {
//        for (Person p : getPersons()) {
////            for (Address a : p.getAddresses()) {
////                if (a.getId().equals(id)) {
////                    return a;
////                }
////            }
//        }
//        return null;
//    }
//
//    public static void removeAddress(Integer id) {
//        for (Person p : getPersons()) {
////            for (Address a : p.getAddresses()) {
////                if (a.getId().equals(id)) {
////                    p.getAddresses().remove(a);
////                    return;
////                }
////            }
//        }
//    }
////Person id
//
//    public static void addAddress(Integer id, Address a) {
//        Person p = getPerson(id);
//        if (p == null) {
//            return;
//        }
//        if (a == null || getAddress(a.getId()) != null) {
////            return;
//        }
////        p.getAddresses().add(a);
//    }
//
//    public static void uppdateAddress(Address a) {
//        if (a == null) {
//            return;
//        }
//        Address existing = getAddress(a.getId());
//
//        if (existing != null) {
//            existing.setAddress(a.getAddress());
//            existing.setAddress(a.getAddress());
//            existing.setCity(a.getCity());
//            existing.setPostalCode(a.getPostalCode());
//        }
//    }
//
////~~~~~~~~~CONTACTS~~~~~~~~~~~~
//    //person id
//    public static List<Contact> getPersonContacts(Integer id) {
//        Person p = getPerson(id);
//
//        if (p != null) {
////            return p.getContacts();
//        }
//        return new ArrayList();
//    }
//
//    public static Contact getContact(Integer id) {
//        for (Person p : getPersons()) {
////            for (Contact c : p.getContacts()) {
////                if (c.getId().equals(id)) {
////                    return c;
////                }
////            }
//        }
//        return null;
//    }
//
//    public static void removeContact(Integer id) {
//        for (Person p : getPersons()) {
////            for (Contact c : p.getContacts()) {
////                if (c.getId().equals(id)) {
////                    p.getContacts().remove(c);
////                    return;
////                }
////            }
//        }
//    }
////Person id
//
//    public static void addContact(Integer id, Contact c) {
//        Person p = getPerson(id);
//        if (p == null) {
//            return;
//        }
//        if (c == null || getContact(c.getId()) != null) {
////            return;
//        }
////        p.getContacts().add(c);
//    }
//
//    public static void uppdateContact(Contact c) {
//        if (c == null) {
//            return;
//        }
//        Contact existing = getContact(c.getId());
//
//        if (existing != null) {
//            existing.setType(c.getType());
//            existing.setContact(c.getContact());
//        }
//    }
    // ~~~~~~~~~~~~~~~~~~~~~~~~~SQL~~~~~~~~~~~~~~~~~~~~~~~~~~
    public static List<Person> getPersons(Connection conn) throws SQLException {
        try (Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery("select * from person")) {
            List<Person> ret = new ArrayList<>();
            while (rs.next()) {
                Person p = new Person();
                p.setId(rs.getInt("id"));
                p.setFirstName(rs.getString("first_name"));
                p.setLastName(rs.getString("last_name"));
                p.setBirthDate(rs.getDate("birth_date"));
                p.setSalary(rs.getBigDecimal("salary"));
                ret.add(p);
            }
            return ret;
        }
    }

    public static List<Address> getPersonAddresses(Connection conn, Integer id) throws SQLException {
        try (PreparedStatement pst = conn.prepareStatement("select * from address where person_id=?")) {
            if (id == null) {
                pst.setNull(1, Types.INTEGER);
            } else {
                pst.setInt(1, id);
            }
            try (ResultSet rs = pst.executeQuery()) {
                List<Address> ret = new ArrayList<>();
                while (rs.next()) {
                    Address a = new Address();
                    a.setId(rs.getInt("id"));
                    a.setAddress(rs.getString("address"));
                    a.setCity(rs.getString("city"));
                    a.setPostalCode(rs.getString("postal_code"));

                    ret.add(a);
                }

                return ret;
            }
        }
    }

    public static List<Contact> getPersonContacts(Connection conn, Integer id) throws SQLException {
        try (PreparedStatement pst = conn.prepareStatement("select * from contact where person_id=?")) {
            if (id == null) {
                pst.setNull(1, Types.INTEGER);
            } else {
                pst.setInt(1, id);
            }
            try (ResultSet rs = pst.executeQuery()) {
                List<Contact> ret = new ArrayList<>();
                while (rs.next()) {
                    Contact a = new Contact();
                    a.setId(rs.getInt("id"));
                    a.setContact(rs.getString("contact"));
                    a.setType(rs.getString("contact_type"));

                    ret.add(a);
                }

                return ret;
            }
        }
    }

    public static String getPerson(Connection conn, Integer id) throws SQLException {
        try (PreparedStatement pst = conn.prepareStatement("select * from person where id = ?")) {
            if (id == null) {
                pst.setNull(1, Types.INTEGER);
            } else {
                pst.setInt(1, id);

                try (ResultSet rs = pst.executeQuery()) {
                    String pers = "";
                    while (rs.next()) {
                        pers = " id " + rs.getInt("id") + " " + rs.getString("first_name") + " " + rs.getString("last_name");
                    }
                    return pers;
                }
            }
        }
        return null;
    }

    public static List<Address> getAddresses(Connection conn) throws SQLException {
        try (Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery("select * from address")) {
            List<Address> ret = new ArrayList<>();
            while (rs.next()) {
                Address a = new Address();
                a.setId(rs.getInt("id"));
                a.setAddress(rs.getString("address"));
                a.setCity(rs.getString("city"));
                a.setPostalCode(rs.getString("postal_code"));
                ret.add(a);
            }
            return ret;
        }
    }

    public static void addPerson(Connection conn, String fn, String ln, String bd, BigDecimal sa) throws SQLException, ParseException {
        try (PreparedStatement pst = conn.prepareStatement("insert into person (first_name, last_name, birth_date, salary ) values (?,?,?,?)")) {

            SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
            Date date = java.sql.Date.valueOf(bd);

            pst.setString(1, fn);
            pst.setString(2, ln);
            pst.setDate(3, date);
            pst.setBigDecimal(4, sa);
            pst.execute();
        }
    }

    public static void addPersonAddress(Connection conn, Integer id, String address, String city, String postalCode) throws SQLException, ParseException {
        try (PreparedStatement pst = conn.prepareStatement("insert into address (person_id, address, city, postal_code ) values (?,?,?,?)")) {

            pst.setInt(1, id);
            pst.setString(2, address);
            pst.setString(3, city);
            pst.setString(4, postalCode);
            pst.execute();
        }
    }

    public static void addPersonContact(Connection conn, Integer id, String contact, String contactType) throws SQLException, ParseException {
        try (PreparedStatement pst = conn.prepareStatement("insert into contact (person_id, contact, contact_type) values (?,?,?)")) {

            pst.setInt(1, id);
            pst.setString(2, contact);
            pst.setString(3, contactType);
            pst.execute();
        }
    }

    public static void addPersonConatct(Connection conn, Integer id, String contact, String contactType) throws SQLException, ParseException {
        try (PreparedStatement pst = conn.prepareStatement("insert into contact (person_id, contact, contact_type ) values (?,?,?)")) {

            pst.setInt(1, id);
            pst.setString(2, contact);
            pst.setString(3, contactType);
            pst.execute();
        }
    }

    public static void updatePerson(Connection conn, Integer id, String fna, String lna, String bd, BigDecimal sal) throws SQLException {
        if (id == null) {
            return;
        }

        if (bd.length() > 5) {

            SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
            Date date = java.sql.Date.valueOf(bd);

            try (PreparedStatement pst = conn.prepareStatement("update person set first_name = ?, last_name = ?, birth_date = ?, salary = ? where id = ?")) {
                pst.setString(1, fna);
                pst.setString(2, lna);
                pst.setDate(3, date);
                pst.setBigDecimal(4, sal);
                pst.setInt(5, id);

                pst.execute();

            }
        } else {
            System.out.println("Bad date inserted");
        }
    }

    public static void updateAddress(Connection conn, Integer id, String adr, String cty, String pos) throws SQLException {
        if (id == null) {
            return;
        }

        try (PreparedStatement pst = conn.prepareStatement("update address set address = ?, city = ?, postal_code = ? where id = ?")) {
            pst.setString(1, adr);
            pst.setString(2, cty);
            pst.setString(3, pos);
            pst.setInt(4, id);

            pst.execute();
        }
    }

    public static void updateContact(Connection conn, Integer id, String cont, String contp) throws SQLException {
        if (id == null) {
            return;
        }

        try (PreparedStatement pst = conn.prepareStatement("update contact set contact = ?, contact_type = ? where id = ?")) {
            pst.setString(1, cont);
            pst.setString(2, contp);
            pst.setInt(3, id);

            pst.execute();
        }
    }

    public static void deletePerson(Connection conn, Integer id) throws SQLException, ParseException {
        try (PreparedStatement pst = conn.prepareStatement("delete from person where id=?")) {

            pst.setInt(1, id);
            pst.execute();
        }
    }

    public static void deletePersonAddress(Connection conn, Integer id) throws SQLException, ParseException {
        try (PreparedStatement pst = conn.prepareStatement("delete from address where id=?")) {

            pst.setInt(1, id);
            pst.execute();
        }
    }

    public static void deletePersonContact(Connection conn, Integer id) throws SQLException, ParseException {
        try (PreparedStatement pst = conn.prepareStatement("delete from contact where id=?")) {

            pst.setInt(1, id);
            pst.execute();
        }
    }

    public static void uppdateContact(Connection conn, Integer ids, String typ, String con) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

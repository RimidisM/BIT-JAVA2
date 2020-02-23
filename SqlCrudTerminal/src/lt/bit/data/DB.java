package lt.bit.data;

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

public class DB {

    private static final List<Person> records = new ArrayList();

    // ~~~~~~~~~~~~~~~~~~~~~~~~~SQL~~~~~~~~~~~~~~~~~~~~~~~~~~
    public static List<Person> getPersons(Connection conn) throws SQLException {
        try (Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery("select * from person")) {
            List<Person> ret = new ArrayList<>();
            while (rs.next()) {
                Person p = new Person();
                p.setFN(rs.getString("first_name"));
                p.setLN(rs.getString("last_name"));
                ret.add(p);
            }
            return ret;
        }
    }

    public static List<Address> getPersonsAddressesss(Connection conn, Integer id) throws SQLException {
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
                    a.setPostCode(rs.getString("postal_code"));

                    ret.add(a);
                }

                return ret;
            }
        }
    }

    public static List<Contact> getPersonContactsss(Connection conn, Integer id) throws SQLException {
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

    public static List<Address> getAddresses(Connection conn) throws SQLException {
        try (Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery("select * from address")) {
            List<Address> ret = new ArrayList<>();
            while (rs.next()) {
                Address a = new Address();
                a.setId(rs.getInt("id"));
                a.setAddress(rs.getString("address"));
                a.setCity(rs.getString("city"));
                a.setPostCode(rs.getString("postal_code"));
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
            pst.close();
        }
    }

    public static void addPersonAddress(Connection conn, Integer id, String address, String city, String postalCode) throws SQLException, ParseException {
        try (PreparedStatement pst = conn.prepareStatement("insert into address (person_id, address, city, postal_code ) values (?,?,?,?)")) {

            pst.setInt(1, id);
            pst.setString(2, address);
            pst.setString(3, city);
            pst.setString(4, postalCode);
            pst.execute();
            pst.close();
        }
    }

    public static void addPersonConatct(Connection conn, Integer id, String contact, String contactType) throws SQLException, ParseException {
        try (PreparedStatement pst = conn.prepareStatement("insert into contact (person_id, contact, contact_type ) values (?,?,?)")) {

            pst.setInt(1, id);
            pst.setString(2, contact);
            pst.setString(3, contactType);
            pst.execute();
            pst.close();
        }
    }

    public static void deletePerson(Connection conn, Integer id) throws SQLException, ParseException {
        try (PreparedStatement pst = conn.prepareStatement("delete from person where id=?")) {

            pst.setInt(1, id);
            pst.execute();
            pst.close();
        }
    }
    
    public static void deletePersonAddress(Connection conn, Integer id) throws SQLException, ParseException {
        try (PreparedStatement pst = conn.prepareStatement("delete from address where id=?")) {

            pst.setInt(1, id);
            pst.execute();
            pst.close();
        }
    }
    
    public static void deletePersonContact(Connection conn, Integer id) throws SQLException, ParseException {
        try (PreparedStatement pst = conn.prepareStatement("delete from contact where id=?")) {

            pst.setInt(1, id);
            pst.execute();
            pst.close();
        }
    }
}

package sqlcrudterminal;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;
import java.util.Scanner;
import lt.bit.data.DB;

/**
 *
 * @author rimid
 */
public class SqlCrudTerminal {

    /**
     * @param args the command line arguments
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     * @throws java.text.ParseException
     */
    public static void main(String[] args) throws ClassNotFoundException, SQLException, ParseException {

        System.out.println("        MENIU");
        System.out.println("Command No.      Description");
        System.out.println("");
        System.out.println("1               Print Persons");
        System.out.println("");
        System.out.println("2               Print Person addresses");
        System.out.println("");
        System.out.println("3               Print Persons contacts");
        System.out.println("");
        System.out.println("4               Add person");
        System.out.println("");
        System.out.println("5               Add address");
        System.out.println("");
        System.out.println("6               Add contact");
        System.out.println("");
        System.out.println("7               Delete person");
        System.out.println("");
        System.out.println("8               Delete address");
        System.out.println("");
        System.out.println("9               Delete contact");
        System.out.println("");
        System.out.println("0               Exit");
        System.out.println("____________________________________________________");

        Class.forName("com.mysql.cj.jdbc.Driver");
        try (Scanner sc = new Scanner(System.in)) {

            boolean exit = true;

            while (exit) {
                System.out.println("Insert command No:");
                int pas = sc.nextInt();
                try (Connection conn = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/addressbook?serverTimezone=UTC", "root", "root")) {

                    switch (pas) {
                        case 1:
                            System.out.println("");
                            System.out.println("Command 1 Print Persons");
                            printPersons(conn);
                            break;
                        case 2:
                            System.out.println("");
                            System.out.println("Command 2 Print Person addresses");
                            printPersonAddresses(conn);
                            break;
                        case 3:
                            System.out.println("");
                            System.out.println("Command 3 Print Persons contacts");
                            printPersonContacts(conn);
                            break;
                        case 4:
                            System.out.println("");
                             System.out.println("Command 4 Add person");
                            addPerson(conn);
                            break;
                        case 5:
                            System.out.println("");
                            System.out.println("Command 5 Add address");
                            addPersonAddresss(conn);
                            break;
                        case 6:
                             System.out.println("");
                            System.out.println("Command 6 Add contact");
                            addPersonContact(conn);
                            break;
                        case 7:
                            System.out.println("");
                            System.out.println("Command 7 Delete person");
                            deletePerson(conn);
                            break;
                        case 8:
                            System.out.println("");
                             System.out.println("Command 8 Delete address");
                            deletePersonAddress(conn);
                            break;
                        case 9:
                            System.out.println("");
                             System.out.println("Command 9 Delete contact");
                            deletePersonContact(conn);
                            break;
                        case 0:
                            exit = false;
                            System.out.println("Good bye!!!");
                            break;
                        default:
                            break;
                    }
                }
            }
        }
    }

    private static void printPersons(Connection conn) throws SQLException {
        DB db = new DB();
        System.out.println("Persons list:");
        List persons = db.getPersons(conn);
        System.out.println(persons);
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }

    private static void printPersonAddresses(Connection conn) throws SQLException {
        System.out.println("Insert Person id");
        Scanner idsc = new Scanner(System.in);
        int insertId = idsc.nextInt();

        DB db = new DB();
        System.out.println("Person addresses:");
        List persons = db.getPersonsAddressesss(conn, insertId);
        System.out.println(persons);
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }

    private static void printPersonContacts(Connection conn) throws SQLException {
        System.out.println("Insert Person id");
        Scanner idsc = new Scanner(System.in);
        int insertId = idsc.nextInt();

        DB db = new DB();
        System.out.println("Person contacts:");
        List persons = db.getPersonContactsss(conn, insertId);
        System.out.println(persons);
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }

    private static void addPerson(Connection conn) throws SQLException, ParseException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Insert Person first name");
        String insertFN = sc.nextLine();
        System.out.println("Insert Person last name");
        String insertLN = sc.nextLine();
        System.out.println("Insert Person birth date");
        String insertDT = sc.nextLine();
        System.out.println("Insert Person salary");
        BigDecimal insertSL = sc.nextBigDecimal();

        DB db = new DB();
        db.addPerson(conn, insertFN, insertLN, insertDT, insertSL);
        System.out.println("Person added");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }

    private static void addPersonAddresss(Connection conn) throws SQLException, ParseException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Insert person ID");
        int insertPerson = sc.nextInt();

        System.out.println("Insert address");
        String insertAddr = sc.next();
        System.out.println("Insert city");
        String insertCity = sc.next();
        System.out.println("Insert postal code");
        String insertPostalCode = sc.next();

        DB db = new DB();
        db.addPersonAddress(conn, insertPerson, insertAddr, insertCity, insertPostalCode);
        System.out.println("Address added");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }

    private static void addPersonContact(Connection conn) throws SQLException, ParseException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Insert person ID");
        int insertPerson = sc.nextInt();
        System.out.println("Insert contact");
        String insertContact = sc.next();
        System.out.println("Insert contact type");
        String insertContactType = sc.next();

        DB db = new DB();
        db.addPersonConatct(conn, insertPerson, insertContact, insertContactType);
        System.out.println("Conatct added");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }

    private static void deletePerson(Connection conn) throws SQLException, ParseException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Insert person ID");
        int insertPerson = sc.nextInt();

        DB db = new DB();
        db.deletePerson(conn, insertPerson);
        System.out.println("Person deleted");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }

    private static void deletePersonAddress(Connection conn) throws SQLException, ParseException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Insert address ID");
        int insertPerson = sc.nextInt();

        DB db = new DB();
        db.deletePersonAddress(conn, insertPerson);
        System.out.println("Address deleted");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }

    private static void deletePersonContact(Connection conn) throws SQLException, ParseException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Insert contact ID");
        int insertPerson = sc.nextInt();

        DB db = new DB();
        db.deletePersonContact(conn, insertPerson);
        System.out.println("Contact deleted");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }
}

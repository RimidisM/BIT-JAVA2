/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lt.bit.data1;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author rimid
 */
public class Person {

    private static int next = 0;

    private Integer id;
    private String firstName;
    private String lastName;
    private Date birthDate;
    private String printDate;
    private Integer birthYear;
    private Integer birthMonth;
    private Integer birthDay;
    private BigDecimal salary;

    private List<Address> addresses = new ArrayList();
    private List<Contact> contacts = new ArrayList();

    public Person(String fna, String lna, Integer year, Integer month, Integer day, String sal) {

        this.firstName = fna;
        this.lastName = lna;
        this.birthYear = year;
        this.birthMonth = month;
        this.birthDay = day;
        this.salary = new BigDecimal(sal);
        this.id = next++;
        List<Address> addresses1 = this.addresses;
        List<Contact> contacts1 = this.contacts;

        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, this.birthYear);
        c.set(Calendar.MONTH, (this.birthMonth - 1));
        c.set(Calendar.DAY_OF_MONTH, this.birthDay);
        this.birthDate = c.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        this.printDate = sdf.format(this.birthDate);

    }

    public Integer getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public String getPrintDate() {
        return printDate;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public Integer getBirthYear() {
        return birthYear;
    }

    public Integer getBirthMonth() {
        return birthMonth;
    }

    public Integer getBirthDay() {
        return birthDay;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public void setSalary(String salary) {
        this.salary = new BigDecimal(salary);
    }

    public void setBirthYear(Integer birthYear) {
        this.birthYear = birthYear;
    }

    public void setBirthMonth(Integer birthMonth) {
        this.birthMonth = birthMonth;
    }

    public void setBirthDay(Integer birthDay) {
        this.birthDay = birthDay;
    }

    public void setPrintDate(String printDate) {
        this.printDate = printDate;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 11 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Person other = (Person) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

}

//Adresu sarasa ir redagavimas
//Kontaktu sarasas ir redagavimas

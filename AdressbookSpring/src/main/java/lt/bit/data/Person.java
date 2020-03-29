/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lt.bit.data;


import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "person")
/**
 *
 * @author rimid
 */
public class Person implements Serializable {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Temporal(TemporalType.DATE)
    @Column(name = "birth_date")
    private Date birthDate;
    @Column(name = "salary")
    private BigDecimal salary;

    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "person")
    private List<Contact> contacts;
    //Transient praleidzia
    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "person")
    private List<Address> addresses;

    public Person(String fna, String lna, Date birthDate, BigDecimal sal) {

        this.firstName = fna;
        this.lastName = lna;
        this.birthDate = birthDate;
        this.salary = sal;
        this.addresses = new ArrayList();
        this.contacts = new ArrayList();
    }

    public Person() {

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

    public BigDecimal getSalary() {
        return salary;
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

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
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

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
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

    @Override
    public String toString() {
        return "Person_id = " + id + "\n"
                + "First name = " + firstName + ", " + "\n"
                + "Last name = " + lastName + ", " + "\n"
                + "Birth date = " + birthDate + ", " + "\n"
                + "Salary= " + salary + ", " + "\n"
                + "===============================" + "\n";
    }

}

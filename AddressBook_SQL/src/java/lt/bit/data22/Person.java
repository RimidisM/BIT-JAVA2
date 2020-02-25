/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lt.bit.data22;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

/**
 *
 * @author rimid
 */
public class Person {

    private Integer id;
    private String firstName;
    private String lastName;
    private Date birthDate;
    private BigDecimal salary;

//    private List<Address> addresses = new ArrayList();
//    private List<Contact> contacts = new ArrayList();

    public Person(String fna, String lna, Date birthDate, BigDecimal sal) {

        this.firstName = fna;
        this.lastName = lna;
        this.birthDate = birthDate;
        this.salary = sal;
        
//        List<Address> addresses1 = this.addresses;
//        List<Contact> contacts1 = this.contacts;

    }
    
    public Person(){
        
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

//    public List<Address> getAddresses() {
//        return addresses;
//    }
//
//    public List<Contact> getContacts() {
//        return contacts;
//    }

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

}



package lt.bit.data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Person {

    private static int next = 1;
    private String FN;
    private String LN;
    private Date BD;
    private BigDecimal salary;
    private Integer id;
    private List<Contact> contacts = new ArrayList();
    private List<Address> addresses = new ArrayList();
   
    public Person() {
        this.id = next++;
    }

    public Person(String FN, String LN, Date BD, BigDecimal salary) {
        this.id = next++;
        this.FN = FN;
        this.LN = LN;
        this.BD = BD;
        this.salary = salary;
    }
    
    public List<Address> getAddresses() {
        return addresses;
    }
    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFN() {
        return FN;
    }

    public void setFN(String FN) {
        this.FN = FN;
    }

    public String getLN() {
        return LN;
    }

    public void setLN(String LN) {
        this.LN = LN;
    }

    public Date getBD() {
        return BD;
    }

    public void setBD(Date BD) {
        this.BD = BD;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal Salary) {
        this.salary = Salary;
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + Objects.hashCode(this.id);
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
        return FN + " " + LN;
    }

    
    
    
}

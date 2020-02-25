/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lt.bit.data22;

import java.util.Objects;

/**
 *
 * @author rimid
 */
public class Contact {

    private static int next = 0;

    private Integer id;
    private Integer personId;
    private String type;
    private String contact;

    public Contact(String type, String contact) {

        this.id = next++;
        this.type = type;
        this.contact = contact;
    }

    public Contact() {

    }

    public static int getNext() {
        return next;
    }

    public Integer getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getContact() {
        return contact;
    }

    public static void setNext(int next) {
        Contact.next = next;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.id);
        hash = 97 * hash + Objects.hashCode(this.type);
        hash = 97 * hash + Objects.hashCode(this.contact);
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
        final Contact other = (Contact) obj;
        if (!Objects.equals(this.type, other.type)) {
            return false;
        }
        if (!Objects.equals(this.contact, other.contact)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

}
// ir i contact ir i adres idet equals

package crud.core.model;

import java.util.Set;
import java.util.Map;

public class Person {
    private int id;
    private Name name;
    private Address address;
    private Date birthday;
    private double gwa;
    private Date dateHired;
    private char employed;
    private Map contacts;
    private Set roles;
    
    public Person(){
    }

    public String getName() {
        return name.getTitle() + ", " + name.getFirstName() + " " + name.getMiddleName() + " " + name.getLastName();    
    }
    
    public String getAddress() {
        return address.getStreet() + " Brgy. " + address.getBrgy() + " " + address.getCity() + ", " + address.getZip();    
    }

    public Date getBirthday() {
        return this.birthday;    
    }

    public double getGwa() {
        return this.gwa;    
    }

    public Date getDateHired() {
        return this.dateHired;    
    }
    
    public char getEmployStatus() {
        return this.employed;    
    }

    public void setName(String firstName, String lastName, String middleName, String title) {
        name = new Name(firstName, lastName, middleName, title);    
    }

    public void setAddress(String street, int brgy, String city, String zip) {
        address = new Address(street, brgy, city, zip);    
    }
    
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
    
    public void setGwa(double gwa) {
        this.gwa = gwa;    
    } 

    public void setDateHired(Date dateHired) {
        this.dateHired = dateHired;
    }  

    public void setEmployStatus(char employed) {
        this.employed = employed;    
    }
    
}

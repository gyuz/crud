package crud.core.model;

import java.util.*;

public class Person {
    public enum Title {
        MR, MS, MRS, DR, JR, SR;    
    } 

    private int id;
    private Name name;
    private Address address;
    private Date birthDate;
    private double gwa;
    private Date dateHired;
    private boolean employed;
    private List<Contact> contacts;
    private Set<Role> roles;
    private Title title;
    
    public Person(){
        name = new Name();
        address = new Address();
        contacts = new ArrayList<Contact>();
        roles = new HashSet<Role>();
    }

    public String getName() {
        return title + ", " + name.getFirstName() + " " + name.getMiddleName() + " " + name.getLastName();    
    }
    
    public String getAddress() {
        return address.getStreet() + " Brgy. " + address.getBrgy() + " " + address.getCity() + ", " + address.getZip();    
    }

    public Date getbirthDate() {
        return this.birthDate;    
    }

    public double getGwa() {
        return this.gwa;    
    }

    public Date getDateHired() {
        return this.dateHired;    
    }
    
    public boolean getEmployStatus() {
        return this.employed;    
    }

    public Title getTitle() {
        return this.title;    
    }    
    
    public void setName(String firstName, String lastName, String middleName) {  
        name.setFirstName(firstName);
        name.setLastName(lastName);
        name.setMiddleName(middleName);
    }

    public void setAddress(String street, int brgy, String city, String zip) {
        address.setStreet(street);
        address.setBrgy(brgy);
        address.setCity(city);
        address.setZip(zip);   
    }
    
    public void setbirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }
    
    public void setGwa(double gwa) {
        this.gwa = gwa;    
    } 

    public void setDateHired(Date dateHired) {
        this.dateHired = dateHired;
    }  

    public void setEmployStatus(boolean employed) {
        this.employed = employed;    
    }
    
    public void setTitle(Title title) {
        this.title = title;
    }
    
}

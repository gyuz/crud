package crud.core.model;

import java.util.*;

public class Person { 
    private int id;
    private Name name;
    private Address address;
    private Date birthDate;
    private double gwa;
    private Date dateHired;
    private boolean employed;
    private Set<Contact> contacts;
    private Set<Role> roles;
    private Title title;
    
    public Person(){
        name = new Name();
        address = new Address();
        contacts = new HashSet<Contact>();
        roles = new HashSet<Role>();
    }
    
    public int getId(){
        return id;        
    }

    public Date getBirthDate() {
        return birthDate;    
    }

    public double getGwa() {
        return gwa;    
    }

    public Date getDateHired() {
        return dateHired;    
    }
    
    public boolean getEmployed() {
        return employed;    
    }

    public Title getTitle() {
        return title;    
    }   
    
    public Name getName(){ 
        return name;    
    } 
    
    public Address getAddress(){
        return address;    
    }
    
    public Set getContacts(){
        return contacts;    
    }
    
    public Set getRoles(){
        return roles;
    }
    
    public void setId(int id){
        this.id = id;
    }    

    public void setName(Name name) {  
        this.name = name;
    }   

    public void setAddress(Address address) {
        this.address = address;   
    }
    
    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }
    
    public void setGwa(double gwa) {
        this.gwa = gwa;    
    } 

    public void setDateHired(Date dateHired) {
        this.dateHired = dateHired;
    }  

    public void setEmployed(boolean employed) {
        this.employed = employed;    
    }
    
    public void setTitle(Title title) {
        this.title = title;
    }
    
    public void setContacts(Set contacts){
        this.contacts = contacts;    
    }

    public void setRoles(Set Roles){
        this.roles = roles;    
    }
}

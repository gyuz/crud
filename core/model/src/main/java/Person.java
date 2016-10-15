package crud.core.model;

import java.util.Set;
import java.util.HashSet;
import java.util.Date;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "PERSON")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE, region="Person")
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
    private int ctr;
    
    public Person(){
        name = new Name();
        address = new Address();
        contacts = new HashSet<Contact>();
        roles = new HashSet<Role>();
    }
    
    @Id 
    @SequenceGenerator(
        name="PERSON_ID_SEQ",
        sequenceName="PERSON_ID_SEQ",
        allocationSize=1
    )
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PERSON_ID_SEQ")
    @Column(name = "ID")
    public int getId(){
        return id;        
    }

    @Column(name = "BIRTH_DATE")
    public Date getBirthDate() {
        return birthDate;    
    }

    @Column(name = "GWA")
    public double getGwa() {
        return gwa;    
    }
    
    @Column(name = "DATE_HIRED")
    public Date getDateHired() {
        return dateHired;    
    }
    
    
    @Column(name = "EMPLOYED")
    public boolean getEmployed() {
        return employed;    
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "TITLE")
    public Title getTitle() {
        return title;    
    }   
    
    @Embedded
    public Name getName(){ 
        return name;    
    } 
    
    @Embedded
    public Address getAddress(){
        return address;    
    }
    
    @OneToMany(mappedBy = "person", 
               cascade = {CascadeType.ALL},
               fetch = FetchType.EAGER)
    public Set<Contact> getContacts(){
        return contacts;    
    }
    
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE},
                fetch = FetchType.EAGER)
    @JoinTable(name = "PERSON_ROLES",
               joinColumns = @JoinColumn(name = "PERSON_ID"), 
               inverseJoinColumns = @JoinColumn(name = "ROLE_ROLE_ID"))
    public Set<Role> getRoles(){
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

    public void setRoles(Set roles){
        this.roles = roles;    
    }
}

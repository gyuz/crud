package crud.core.model;

import javax.persistence.*;

@Embeddable
public class Name {

    private String firstName;
    private String lastName;
    private String middleName;

    public Name() {
    }

    public Name(String firstName, String lastName, String middleName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
    }

    @Column(name = "FIRST_NAME")
    public String getFirstName() {
        return firstName;    
    }
    
    @Column(name = "LAST_NAME")
    public String getLastName() {
        return lastName;    
    }
    
    @Column(name = "MIDDLE_NAME")    
    public String getMiddleName() {
        return middleName;    
    }

    public void setFirstName(String firstName) {   
        this.firstName = firstName;
    }
    
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }
    
}

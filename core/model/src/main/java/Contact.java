package crud.core.model;

import javax.persistence.*;

@Entity
@Table(name = "CONTACT")
public class Contact {
    private int contactId;
    private Types contactType;
    private String details;
    private Person person;

    public Contact() {
    }
    
    @ManyToOne()
    @JoinColumn(name = "PERSON_ID", nullable = false)
    public Person getPerson(){
        return person;
    }    
    
    @Id
    @SequenceGenerator(
        name="CONTACT_SEQ",
        sequenceName="CONTACT_SEQ",
        allocationSize=1
    ) 
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "CONTACT_SEQ")
    @Column(name = "CONTACT_ID")
    public int getContactId() {
        return contactId;    
    }    

    @Enumerated(EnumType.STRING)
    @Column(name = "CONTACT_TYPE")
    public Types getContactType() {
        return contactType;    
    }
    
    @Column(name = "CONTACT_DETAILS")
    public String getDetails(){
        return details;    
    }

    public void setPerson(Person person){
        this.person = person;    
    }    

    public void setContactId(int contactId) {
        this.contactId = contactId;    
    }

    public void setContactType(Types contactType) {
        this.contactType = contactType;
    }
    
    public void setDetails(String details) {
        this.details = details;    
    }
        
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (!this.getClass().equals(obj.getClass())) return false;
        Contact obj2 = (Contact) obj;
        if(this.contactType.equals(obj2.contactType) 
            && this.details.equals(obj2.details)
            && this.person.equals(obj2.person))
        {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int tmp = 0;
        tmp = ( person.getId() + contactType.name() + details ).hashCode();
        return tmp;
    }

}

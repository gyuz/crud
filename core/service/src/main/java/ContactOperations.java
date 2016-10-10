package crud.core.service;

import java.util.Set;
import java.util.LinkedHashSet;
import crud.core.model.Contact;
import crud.core.model.Person;
import crud.core.model.Types;

public class ContactOperations{
    Contact contact;
    
    public ContactOperations(){
        contact = new Contact();    
    }

    public void saveContact(String type, String details, Person person){
        contact = new Contact();        
        contact.setContactType(Types.valueOf(type));
        contact.setDetails(details);
        contact.setPerson(person);    
    }
    
    public void setDetail(String details){
        contact.setDetails(details);  
    }
    
    public String getContactDetails(){
        return contact.getDetails();    
    }

    public String getContactType(){
        return contact.getContactType().toString();    
    }

    public Contact getContact(){
        return this.contact;    
    }
    
    public void setContact(Contact c){
        this.contact = c;    
    }
}


package crud.core.service;

import crud.core.model.Contact;
import crud.core.model.Types;

public class ContactOperations{
    private Contact contact;
    private PersonOperations personCrud;
    
    public ContactOperations(PersonOperations personCrud){
        contact = new Contact();
        this.personCrud = personCrud;
    }
    
    public boolean contactSaved(String type, String detail){
        contact.setContactType(Types.valueOf(type));
        contact.setDetails(detail);
        if(personCrud.contactExist(contact)) return false;
        else {
            personCrud.addContact(contact);
        }
        return true;
    } 
}

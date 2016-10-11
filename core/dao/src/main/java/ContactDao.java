package crud.core.dao;

import crud.core.model.Contact;

public class ContactDao extends CrudImpl<Contact> {
    public ContactDao(){ 
        sessions = new SessionGroup();
    } 

}

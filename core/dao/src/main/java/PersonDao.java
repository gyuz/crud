package crud.core.dao;

import crud.core.model.Person;
import crud.core.model.Title;
import java.util.Date;

public class PersonDao extends CrudImpl<Person> {
    public PersonDao(){ 
        sessions = new SessionGroup();
    } 
    
    public Person getPersonById(int id) {
        sessions.openSessionTransaction();
        Person person = (Person) sessions.getCurrentSession().get(Person.class, id);
        if(person == null) {
            sessions.closeSessionTransaction(); 
        }
        return person;
    }       
}

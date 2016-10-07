package crud.core.dao;

import crud.core.model.Person;

public class PersonDao extends CrudImpl<Person> {
    public PersonDao(){ 
        sessions = new SessionGroup();
    } 
    
    public boolean checkPersonId(int id){
        Person person = getPersonById(id);        
        if (person != null) return true;
        return false;    
    }
    
    public boolean deleteById(int id){
        Person person = getPersonById(id); 
        if (person != null) {
            delete(person);     
            return true;
        }
        return false;             
    }     
    
    public Person getPersonById(int id) {
        sessions.openSessionTransaction();
        Person person = (Person) sessions.getCurrentSession().get(Person.class, id);
        sessions.closeSessionTransaction();
        return person;
    }       
}

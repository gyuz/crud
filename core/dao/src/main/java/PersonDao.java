package crud.core.dao;

import crud.core.model.Person;
import crud.core.model.Title;
import java.util.Date;

public class PersonDao extends CrudImpl<Person> {
    public PersonDao(){ 
        sessions = new SessionGroup();
    } 
    
    public boolean checkPersonId(int id){
        Person person = getPersonById(id);        
        if (person != null) return true;
        return false;    
    }
    
    public void updateFirstName(int id, String s){
        sessions.openSessionTransaction();
        Person person = (Person) sessions.getCurrentSession().get(Person.class, id);
        person.getName().setFirstName(s);
        sessions.getCurrentSession().update(person);
        sessions.closeSessionTransaction(); 
    }

    public void updateMiddleName(int id, String s){
        sessions.openSessionTransaction();
        Person person = (Person) sessions.getCurrentSession().get(Person.class, id);
        person.getName().setMiddleName(s);
        sessions.getCurrentSession().update(person);
        sessions.closeSessionTransaction();
    }
    
    public void updateLastName(int id, String s){
        sessions.openSessionTransaction();
        Person person = (Person) sessions.getCurrentSession().get(Person.class, id);
        person.getName().setLastName(s);
        sessions.getCurrentSession().update(person);
        sessions.closeSessionTransaction();
    }

    public void updateTitle(int id, Title t){
        sessions.openSessionTransaction();
        Person person = (Person) sessions.getCurrentSession().get(Person.class, id);
        person.setTitle(t);
        sessions.getCurrentSession().update(person);
        sessions.closeSessionTransaction();
    }

    public void updateBirthDate(int id, Date d){
        sessions.openSessionTransaction();
        Person person = (Person) sessions.getCurrentSession().get(Person.class, id);
        person.setBirthDate(d);
        sessions.getCurrentSession().update(person);
        sessions.closeSessionTransaction(); 
    }
    
    public void updateDateHired(int id, Date d){
        sessions.openSessionTransaction();
        Person person = (Person) sessions.getCurrentSession().get(Person.class, id);
        person.setDateHired(d);
        sessions.getCurrentSession().update(person);
        sessions.closeSessionTransaction(); 
    }

    public void updateStreet(int id, String s){
        sessions.openSessionTransaction();
        Person person = (Person) sessions.getCurrentSession().get(Person.class, id);
        person.getAddress().setStreet(s);
        sessions.getCurrentSession().update(person);
        sessions.closeSessionTransaction(); 
    }
    
    public void updateCity(int id, String s){
        sessions.openSessionTransaction();
        Person person = (Person) sessions.getCurrentSession().get(Person.class, id);
        person.getAddress().setCity(s);
        sessions.getCurrentSession().update(person);
        sessions.closeSessionTransaction(); 
    }

    public void updateBrgy(int id, int n){
        sessions.openSessionTransaction();
        Person person = (Person) sessions.getCurrentSession().get(Person.class, id);
        person.getAddress().setBrgy(n);
        sessions.getCurrentSession().update(person);
        sessions.closeSessionTransaction(); 
    }

    public void updateZip(int id, int n){
        sessions.openSessionTransaction();
        Person person = (Person) sessions.getCurrentSession().get(Person.class, id);
        person.getAddress().setZip(n);
        sessions.getCurrentSession().update(person);
        sessions.closeSessionTransaction(); 
    }
    
    public void updateEmployed(int id,  boolean b){
        sessions.openSessionTransaction();
        Person person = (Person) sessions.getCurrentSession().get(Person.class, id);
        person.setEmployed(b);
        sessions.getCurrentSession().update(person);
        sessions.closeSessionTransaction(); 
    }

    public void updateGwa(int id, double n){
        sessions.openSessionTransaction();
        Person person = (Person) sessions.getCurrentSession().get(Person.class, id);
        person.setGwa(n);
        sessions.getCurrentSession().update(person);
        sessions.closeSessionTransaction(); 
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

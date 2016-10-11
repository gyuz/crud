package crud.core.dao;

import crud.core.model.Person;
import crud.core.model.Name;
import crud.core.model.Title;
import org.hibernate.Hibernate;
import org.hibernate.Criteria;
import org.hibernate.criterion.*;
import java.util.Date;
import java.util.List;

public class PersonDao extends CrudImpl<Person> {
    public PersonDao(){ 
        sessions = new SessionGroup();
    }
    
    public Person initializeContactSet(int id){
        sessions.startSession();
        Person person = (Person) sessions.getCurrentSession().get(Person.class, id);
        Hibernate.initialize(person.getContacts());
        sessions.closeSession(); 
        return person;
    }    

    public Person initializeRoleSet(int id){
        sessions.openSessionTransaction();
        Person person = (Person) sessions.getCurrentSession().get(Person.class, id);
        System.out.println(person);        
        Hibernate.initialize(person.getRoles());
        System.out.println(person.getRoles());
        sessions.closeSessionTransaction(); 
        return person;
    }    
    
    public List<Person> listAscendingComponent(String component, String column){
        sessions.startSession();
        Criteria personCriteria = sessions.getCurrentSession().createCriteria(Person.class);
        Criteria componentCriteria = personCriteria.createCriteria(component);
        componentCriteria.addOrder(Order.asc(column));
        List<Person> personList = personCriteria.list();
        sessions.closeSession(); 
        return personList;
    }
    
    public List<Person> listAscending(String column){
        sessions.startSession();
        List<Person> personList = sessions.getCurrentSession().createCriteria(Person.class)
                              .addOrder(Order.asc(column))
                              .list();
        sessions.closeSession(); 
        return personList;
    }

     public List<Person> listDescending(String column){
        sessions.startSession();
        List<Person> personList = sessions.getCurrentSession().createCriteria(Person.class)
                              .addOrder(Order.desc(column))
                              .list();
        sessions.closeSession(); 
        return personList;
    }

    public Person getPersonById(int id) {
        sessions.startSession();
        Person person = (Person) sessions.getCurrentSession().get(Person.class, id);
        sessions.closeSession(); 
        return person;
    }  
}

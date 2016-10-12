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

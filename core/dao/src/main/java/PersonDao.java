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
        sessions.startTransaction();
        Person person = (Person) sessions.getCurrentSession().get(Person.class, id);
        Hibernate.initialize(person.getContacts());
        sessions.closeTransactionRollback(); 
        return person;
    }     
    
    public List<Person> listAscending(String column){
        sessions.openSessionTransaction();
        List<Person> personList = sessions.getCurrentSession().createCriteria(Person.class)
                              .addOrder(Order.asc(column))
                              .list();
        sessions.closeSessionTransactionRollback();
        return personList;
    }

     public List<Person> listDescending(String column){
        sessions.openSessionTransaction();
        List<Person> personList = sessions.getCurrentSession().createCriteria(Person.class)
                              .addOrder(Order.desc(column))
                              .list();
        sessions.closeSessionTransactionRollback(); 
        return personList;
    }

    public Person getPersonById(int id) {
        sessions.openSessionTransaction();
        Person person = (Person) sessions.getCurrentSession().get(Person.class, id);
        if(person != null){
            sessions.closeTransactionRollback();
        } else {
            sessions.closeSessionTransactionRollback();
        } 
        return person;
    }  
}

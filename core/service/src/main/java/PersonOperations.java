package crud.core.service;

import crud.core.model.Person;
import crud.core.model.Person.Title;

public class PersonOperations{
    Person person;

    public PersonOperations(String firstName, String lastName, String middleName, String title, boolean employed){
        person = new Person();
        try {
            Person.Title personTitle = Person.Title.valueOf(title.toUpperCase());          
            person.setName(firstName, lastName, middleName);
            person.setTitle(personTitle);
            person.setEmployStatus(employed);
            System.out.print(person.getName() + " " + person.getEmployStatus());  
    
        } catch (IllegalArgumentException iae) {
           System.out.print("\nInvalid Title!");         
        }        
        

    }
}

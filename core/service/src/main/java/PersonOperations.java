package crud.core.service;

import crud.core.model.Person;
import crud.core.model.Name;
import crud.core.model.Address;
import crud.core.model.Title;

public class PersonOperations{
    Person person;
    Name name;

    public PersonOperations(String firstName, String lastName, String middleName, String title, boolean employed){
        person = new Person();
        name = new Name(firstName, lastName, middleName);
        try {
            Title personTitle = Title.valueOf(title.toUpperCase());          
            person.setName(name);
            person.setTitle(personTitle);
            person.setEmployed(employed);
            System.out.print(person.getFullName() + " " + person.getEmployed());  
    
        } catch (IllegalArgumentException iae) {
           System.out.print("\nInvalid Title!");         
        }        
    }
}

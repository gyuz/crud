package crud.app;

import java.util.*;
import crud.core.service.PersonOperations;

public class PersonDetails{
    Scanner input = new Scanner(System.in);    
    PersonOperations personCrud;    
        
    public PersonDetails(){
    }
    
    public void create(){
        String firstName = "";
        String lastName = "";
        String middleName = "";
        String title = "";
        char employed;
        Date birthDate;
        double gwa = 0.0;
        /*     
        System.out.print("\nEnter First Name: ");
        firstName = input.next();
        System.out.print("\nEnter Middle Name: ");
        middleName = input.next();    
        System.out.print("\nEnter Last Name: ");
        lastName = input.next(); 
        System.out.print("\nEnter title: ");  
        title = input.next();
        System.out.print("\nEmployed?[Y/N]: "); 
        employed = input.next().charAt(0); 
        switch(Character.toUpperCase(employed)){
            case 'Y':  
                    personCrud.setEmploy(true); 
                    break;
            case 'N':  
                    personCrud.setEmploy(false); 
                    break;
            default: System.out.print("\n 'Y' or 'N' only!"); break;         
        }   

        personCrud = new PersonOperations(firstName, middleName, lastName, title, employed);
        */
        personCrud = new PersonOperations("Grace", "Chong", "Yu", "Ms", false);
    }
}

package crud.app;

import java.util.Scanner;
import crud.core.service.ContactOperations;
import crud.core.service.PersonOperations;

public class PersonContact{
    Scanner input = new Scanner(System.in);
    private ContactOperations contactCrud;
    private String contactType;
    private String details;
    
    PersonContact(PersonOperations p){
        contactCrud = new ContactOperations(p);
        contactType = "";
        details = "";
    }
    
    public void addContact(){
        int type = 0;
        char choice = 'Y';
        do{
            try {
                System.out.print("\nChoose contact type: ");
                type = Integer.parseInt(input.nextLine());
                switch(type){
                    case 1: contactType = "LANDLINE";
                            enterNumber();
                            break;
                    case 2: contactType = "MOBILE";
                            enterNumber();
                            break; 
                    case 3: contactType = "EMAIL";
                            enterEmail();
                            break; 
                    default: 
                            System.out.print("Invalid type! 1-3 only.\n");
                            choice = 'Y'; 
                }
            } catch (NumberFormatException nfe) {
                System.out.print("Invalid type! 1-3 only.\n");
                choice = 'Y';
            } 
            if (!contactCrud.contactSaved(contactType, details)){
                System.out.print("Contact already exist!\n");
                type = 'Y';
            } else {
                do{
                    System.out.print("Add another contact?[Y/N]");
                    choice = Character.toUpperCase(input.nextLine().charAt(0));
                    switch(choice){
                        case 'Y':
                        case 'N': break;
                        default: System.out.print("Y or N only!\n");
                    }
                 } while(choice != 'Y' && choice != 'N');
            }  
        }while(choice == 'Y');
    }
    
    public void enterNumber(){
        System.out.print("Enter Number: ");
        details = input.nextLine();
        details = numericOnly(details);
    }
    
    public void enterEmail(){
        System.out.print("Enter Email: ");
        details = input.nextLine();
    }
    
    protected String numericOnly(String number){
        while(!number.matches(("[0-9]+"))){
            System.out.print("Only numbers are allowed. \nKindly input again: ");            
            number = input.nextLine();
        }    
        return number;
    }

}


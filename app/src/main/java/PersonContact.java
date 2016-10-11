package crud.app;

import java.util.Scanner;
import crud.core.service.PersonOperations;

public class PersonContact{
    Scanner input = new Scanner(System.in);
    private PersonOperations contactCrud;
    private String contactType;
    private String details;
    private int contactId;
    
    PersonContact(PersonOperations p){
        contactCrud = p;
        contactType = "";
        details = "";
        contactId = 0;
    }
    
    public void addContact(){
        int type = 0;
        char choice = 'Y';
        do{
            try {
                System.out.print("\nChoose contact type: " + contactCrud.printTypeList() +"\nChoice: ");
                type = Integer.parseInt(input.nextLine());
                switch(type){
                    case 1: contactType = "LANDLINE";
                            enterNumber(1);
                            break;
                    case 2: contactType = "MOBILE";
                            enterNumber(2);
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
            if (!contactCrud.addContact(contactType, details)){
                System.out.print("Contact already exist!\n");
                choice = 'Y';
            } else {
                do{
                    System.out.print("Add another contact?[Y/N]: ");
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
    

    public void updateContact(){
        char choice = 'Y';
        do{
            enterContactId();
            String type = contactCrud.getContactType();
            if(type.equals("LANDLINE")){
                enterNumber(1);
            } else if (type.equals("MOBILE")) {
                enterNumber(2);            
            } else {
                enterEmail();
            }
            if (!contactCrud.updateContact(details)){
                System.out.print("Contact already exist!\n");
                choice = 'Y';
            }
            do{
                 System.out.print("Update another contact?[Y/N]: ");
                 choice = Character.toUpperCase(input.nextLine().charAt(0));
                 switch(choice){
                    case 'Y':
                    case 'N': break;
                    default: System.out.print("Y or N only!\n");
                 }
            } while(choice != 'Y' && choice != 'N'); 
         }while(choice == 'Y'); 
    }
    
    public void deleteContact(){
        char choice = 'Y';
        do{
            enterContactId();
            contactCrud.deleteContact();
            do{
                 System.out.print("Delete another contact?[Y/N]: ");
                 choice = Character.toUpperCase(input.nextLine().charAt(0));
                 switch(choice){
                    case 'Y':
                    case 'N': break;
                    default: System.out.print("Y or N only!\n");
                 }
            } while(choice != 'Y' && choice != 'N'); 
         }while(choice == 'Y');         
    }
    
    private boolean detailInvalid(String number, int type){
        if(type == 1 && number.length() != 7){
            return true;        
        } else if (type == 2 && number.length() != 11){
            return true;        
        }
        return false;
    }

    private void enterNumber(int type){
        System.out.print("Enter Number: ");
        details = input.nextLine();
        details = numericOnly(details, type);
    }
    
    private void enterEmail(){
        System.out.print("Enter Email: ");
        details = input.nextLine();
        details = validateEmail(details);
    }
            
    private void enterContactId(){
       boolean back = true;
       do{
            try{
                System.out.print("Enter contact ID: ");
                contactId = Integer.parseInt(input.nextLine());
                back = true;
            } catch (NumberFormatException nfe) {
                System.out.print("Invalid ID!\n");
                back = false;            
            }
            if (!contactCrud.contactIdExist(contactId)) {
                System.out.print("ID does not exist!\n");
                back = false;
            }
        }while(!back);
            
    }

    public void list(){
        System.out.println("\nCONTACT_ID\tTYPE\t\tNUMBER/EMAIL" + contactCrud.printContactList());   
    }    
    
    protected String numericOnly(String number, int type){
        while(!number.matches(("[0-9]+")) || detailInvalid(number, type)){
            System.out.print("Error! \nKindly input again: ");            
            number = input.nextLine();
        }    
        return number;
    }

    protected String validateEmail(String email){
        while(!email.matches(("^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$"))){
            System.out.print("Invalid email. \nKindly input again: ");            
            email = input.nextLine();
        }    
        return email;
    }

}


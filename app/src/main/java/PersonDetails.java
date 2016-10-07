package crud.app;

import java.util.*;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import crud.core.service.PersonOperations;

public class PersonDetails{
    Scanner input = new Scanner(System.in);    
    private PersonOperations personCrud; 
    private String firstName;
    private String lastName;
    private String middleName;
    private String title;
    private String street;
    private int brgy;
    private String city;
    private int zip;
    private char employed;
    private Date birthDate = new Date();
    private Date dateHired = new Date();
    private double gwa;
    private boolean repeat = false;
    private boolean back = false; 
    private static final SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");            
         
    public PersonDetails(){
        personCrud = new PersonOperations();
        firstName = "";
        lastName = "";
        middleName = "";
        title = "";
        street = "";
        brgy = 0;
        city = "";
        zip = 0;
        birthDate = new Date();
        dateHired = new Date();
        gwa = 0.0;
    }
    
    public void create(){ 
        personCrud = new PersonOperations();
        do{
            enterFirstName();
            enterMiddleName();
            enterLastName();
                    
            if(!personCrud.isDuplicate(firstName, lastName, middleName)){                    
                enterTitle();
                enterBirthDate(); 
                enterStreet();
                enterBrgy();
                enterCity();
                enterZip();
                enterGwa();
                enterEmployStatus();
                personCrud.savePerson(firstName, lastName, middleName, title, birthDate, street, brgy, city, zip, gwa, employed);
                System.out.print("New person created");
                back = true;
            } else {
                System.out.print("Person already exists!");
                back = false;
            }
        } while(!back);
    }
    
    public void update(){
        int id = 0;
        int choice = 0;
        do{ 
            back = true;
            System.out.print("Enter person ID for update: ");
            id = input.nextInt();
            if (personCrud.idExist(id)) {
                do{
                    System.out.print("Which you like to update:\n(1) First Name \n(2) Middle Name \n(3) Last Name \n(4) Birth Date \n(5) Street \n(6) Barangay  \n(7) City \n(8) Zip \n(9) Employ Status \n(10) Hire Date \n(11) GWA \nChoice: " );
                    choice = input.nextInt();
                    switch(choice){
                        case 1: enterFirstName();
                                personCrud.update();
                                break;   
   
                        case 2: enterMiddleName();
                                personCrud.update();
                                break;

                        case 3: enterLastName();
                                personCrud.update();
                                break;

                        case 4: enterBirthDate();
                                personCrud.update();
                                break;

                        case 5: enterStreet();
                                personCrud.update();            
                                break;

                        case 6: enterBrgy();
                                personCrud.update();
                                break;

                        case 7: enterCity();
                                personCrud.update();
                                break;

                        case 8: enterZip();
                                personCrud.update();
                                break;

                        case 9: enterEmployStatus();
                                personCrud.update();
                                break;

                        case 10: enterHireDate();
                                 personCrud.update();
                                 break;

                        case 11: enterGwa();
                                 personCrud.update();
                                 break; 

                        default: System.out.print("Invalid choice. 1 - 11 only!\n");              
                    }
                    System.out.print("Update another field?[Y/N] " );
                    if(Character.toUpperCase(input.next().charAt(0)) == 'Y') {
                        choice = 0;                   
                    }
                } while(choice < 1 || choice > 11);
            
            } else {
                System.out.print("ID not found!\n");
                back = false;
            }
        }while(!back);
    }

    public void delete(){
        int id = 0;
        back = true;
        do {
            try{
                System.out.print("\nEnter ID for deletion: ");
                id = input.nextInt();  
                if(!personCrud.delete(id)){
                    System.out.print("Person ID does not exist!");
                    back = false;                
                } else {
                    System.out.print("Person ID "+id+" deleted");
                    back = true;                
                }
            } catch (InputMismatchException ime) {
                System.out.print("Not a valid ID!\n");
                back = false;            
            }      
        } while(!back);
    }
    
    public void list(){
        System.out.print(personCrud.printPersonList());
    }

    private void enterFirstName(){
        System.out.print("\nEnter First Name: ");
        firstName = input.next();
        firstName = alphabetOnly(firstName); 
    }

    private void enterMiddleName(){
        System.out.print("Enter Middle Name: ");
        middleName = input.next();
        middleName = alphabetOnly(middleName);    
    }

    private void enterLastName(){
        System.out.print("Enter Last Name: ");
        lastName = input.next();    
        lastName = alphabetOnly(lastName); 
    }

    private void enterTitle(){
         System.out.print("Enter title: ");  
         title = input.next().toUpperCase();
         while(!personCrud.titleExist(title)){
            System.out.print("Invalid Title! Possible values are: "+personCrud.printTitleList()+"\nKindly input again: ");            
            title = input.next().toUpperCase();
         }
    }

    private void enterBirthDate(){
        do{
           try{                    
                System.out.print("Enter birthday [MM/DD/YYYY]: ");
                birthDate = formatter.parse(input.next());
                repeat = false;
            } catch(ParseException pe) {
                System.out.print("Invalid date\n");
                repeat = true;
            }  
         } while(repeat); 
    }

    private void enterStreet(){
        System.out.print("Enter current street address: ");
        street = input.next();       
    }

    private void enterBrgy(){
        System.out.print("Enter current barangay number: ");
        brgy = input.nextInt();        
    }

    private void enterCity(){
         System.out.print("Enter current city/municipality: ");
         city = input.next();   
    }
    
    private void enterZip(){
        System.out.print("Enter zip code: ");
        zip = input.nextInt();   
    }

    private void enterGwa(){
        System.out.print("Enter GWA: ");
        gwa = input.nextDouble();   
    }

    private void enterEmployStatus(){
        do{                    
            System.out.print("Currently employed?[Y/N]: ");
            employed = Character.toUpperCase(input.next().charAt(0));
            if (employed == 'Y'){
                enterHireDate();
                personCrud.setDateHired(dateHired);
            } else if (employed != 'N') {
               System.out.print("Invalid input! Y/N only.\n");                        
            }
       } while(employed != 'Y' && employed != 'N');       
    }

    private void enterHireDate(){   
        boolean repeat = false;
        do{
            try{
                 System.out.print("Enter hire date [MM/DD/YYYY]: ");
                 dateHired = formatter.parse(input.next());
                 repeat = false;
            } catch(ParseException pe) {
                 System.out.print("Invalid date\n");
                 repeat = true;
            }
        } while(repeat);      
    }

    public void changePersonRole(){
        boolean back = false;
        int choice = 0;
        do {
            System.out.print("\n------------------Person Role Screen----------------");        
            System.out.print("\n(1) Add \n(2) Delete \n(3) List \n(4) Back to Person \nChoice: ");
             try{
                choice = input.nextInt();
                switch(choice) {
                    case 1:
                    case 2:
                    case 3:
                    case 4: 
                            back = true; 
                            break;
                    default:
                        System.out.print("\nInvalid choice!");          
                }
             } catch (InputMismatchException ime) {
                System.out.print("\nInvalid choice!");
                choice = 0;
             } 
        } while (!back);
    }
    
    public void contactScreen(){
        boolean back = false;
        int choice = 0;
        do {
            System.out.print("\n------------------Person Contacts Screen----------------");        
            System.out.print("\n(1) Add \n(2) Update \n(3) Delete \n(4) List \n(5) Back to Person \nChoice: ");
             try{
                choice = input.nextInt();
                switch(choice) {
                    case 1:
                    case 2:
                    case 3:
                    case 4: 
                    case 5:
                            back = true; 
                            break;
                    default:
                        System.out.print("\nInvalid choice!");          
                }
             } catch (InputMismatchException ime) {
                System.out.print("\nInvalid choice!");
                choice = 0;
             } 
        } while (!back);
    }
    
    protected String alphabetOnly(String text){
        while(!text.matches("[a-zA-Z]+")){
            System.out.print("Only letters are allowed. \nKindly input again: ");            
            text = input.next();
        }   
        return text; 
    }
    
    protected String numericOnly(String text){
        while(!text.matches(("[0-9]+"))){
            System.out.print("Only numbers are allowed. \nKindly input again: ");            
            text = input.next();
        }    
        return text;
    }
}

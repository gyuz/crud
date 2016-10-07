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
            try{
                back = true;
                System.out.print("Enter person ID for update: ");
                id = Integer.parseInt(input.nextLine());
                if (personCrud.idExist(id)) {
                    do{
                        try{
                            System.out.print("Which would you like to update:\n(1) First Name \n(2) Middle Name \n(3) Last Name \n(4) Birth Date \n(5) Street \n(6) Barangay  \n(7) City \n(8) Zip \n(9) Employ Status \n(10) Hire Date \n(11) GWA \n(12) Back \nChoice: " );
                            choice = Integer.parseInt(input.nextLine());
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
                                case 12: break;

                                default: System.out.print("Invalid choice. 1 - 11 only!\n");              
                            }
    
                            if(choice != 12){
                                System.out.print("Update another field?[Y/N] " );
                                if(Character.toUpperCase(input.next().charAt(0)) == 'Y') {
                                    choice = 0;                   
                                }
                            }
                        } catch (NumberFormatException nfe) {
                            System.out.print("Invalid choice!\n");
                            choice = 0;                        
                        }
                    } while(choice == 0);                
                } else {
                    System.out.print("ID not found!\n");
                    back = false;
                }
            } catch (NumberFormatException nfe) {
                System.out.print("Invalid ID!\n");
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
                id = Integer.parseInt(input.nextLine());  
                if(!personCrud.delete(id)){
                    System.out.print("Person ID does not exist!");
                    back = false;                
                } else {
                    System.out.print("Person ID "+id+" deleted");
                    back = true;                
                }
            } catch (NumberFormatException nfe) {
                System.out.print("Not a valid ID!\n");
                back = false;            
            }      
        } while(!back);
    }
    
    public void list(){
        System.out.print(personCrud.printPersonList()+"\n");
    }

    private void enterFirstName(){
        System.out.print("\nEnter First Name: ");
        firstName = input.nextLine();
        firstName = alphabetOnly(firstName).toUpperCase(); 
    }

    private void enterMiddleName(){
        System.out.print("Enter Middle Name: ");
        middleName = input.nextLine();
        middleName = alphabetOnly(middleName).toUpperCase();    
    }

    private void enterLastName(){
        System.out.print("Enter Last Name: ");
        lastName = input.nextLine();    
        lastName = alphabetOnly(lastName).toUpperCase(); 
    }

    private void enterTitle(){
         System.out.print("Enter title: ");  
         title = input.next().toUpperCase();
         while(!personCrud.titleExist(title)){
            System.out.print("Invalid Title! Possible values are: "+personCrud.printTitleList()+"\nKindly input again: ");            
            title = input.next();
         }
    }

    private void enterBirthDate(){
        do{
           try{                    
                System.out.print("Enter birthday [MM/DD/YYYY]: ");
                birthDate = formatter.parse(input.next());
                input.nextLine();  
                repeat = false;
            } catch(ParseException pe) {
                System.out.print("Invalid date\n");
                repeat = true;
            }  
         } while(repeat); 
    }

    private void enterStreet(){
        System.out.print("Enter current street address: ");
        street = input.nextLine().toUpperCase();       
    }

    private void enterBrgy(){
        do{
            try{
                System.out.print("Enter current barangay number: ");
                brgy = Integer.parseInt(input.nextLine());
                repeat = false;
            } catch(NumberFormatException nfe) {
                System.out.print("Invalid barangay number!\n");
                repeat = true;            
            }       
        } while(repeat); 
    }

    private void enterCity(){
         System.out.print("Enter current city/municipality: ");
         city = input.nextLine().toUpperCase();   
    }
    
    private void enterZip(){
        do{ 
            try{
                System.out.print("Enter zip code: ");
                zip = Integer.parseInt(input.nextLine()); 
                repeat = false;
            } catch(NumberFormatException nfe) {
                System.out.print("Invalid zip code!\n");
                repeat = true;            
            }       
        } while(repeat);  
    }

    private void enterGwa(){
        do{
            try{
                System.out.print("Enter GWA: ");
                gwa = Double.parseDouble(input.nextLine());
                repeat = false; 
            } catch(NumberFormatException nfe) {
                System.out.print("Invalid GWA!\n");
                repeat = true;            
            }       
        } while(repeat);  
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
        int choice = 0;
        do {
            System.out.print("\n------------------Person Role Screen----------------");        
            System.out.print("\n(1) Add \n(2) Delete \n(3) List \n(4) Back to Person \nChoice: ");
             try{
                choice = Integer.parseInt(input.nextLine());
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
             } catch (NumberFormatException nfe) {
                System.out.print("\nInvalid choice!");
                back = false;
             } 
        } while (!back);
    }
    
    public void contactScreen(){
        int choice = 0;
        do {
            System.out.print("\n------------------Person Contacts Screen----------------");        
            System.out.print("\n(1) Add \n(2) Update \n(3) Delete \n(4) List \n(5) Back to Person \nChoice: ");
             try{
                choice = Integer.parseInt(input.nextLine());
                switch(choice) {
                    case 1:
                    case 2:
                    case 3:
                    case 4: 
                    case 5:
                            choice = 0; 
                            break;
                    default:
                        System.out.print("\nInvalid choice!");          
                }
             } catch (NumberFormatException nfe) {
                System.out.print("\nInvalid choice!");
                choice = 0;
             } 
        } while (choice == 0);
    }
    
    protected String alphabetOnly(String text){
        while(!text.matches("[a-zA-Z ]*")){
            System.out.print("Only letters are allowed. \nKindly input again: ");            
            text = input.nextLine();
        }   
        return text; 
    }
    
    protected String numericOnly(String text){
        while(!text.matches(("[0-9]+"))){
            System.out.print("Only numbers are allowed. \nKindly input again: ");            
            text = input.nextLine();
        }    
        return text;
    }
}

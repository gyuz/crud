package crud.app;

import java.util.*;
import java.util.Calendar;
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
    private String brgy;
    private String city;
    private int zip;
    private char employed;
    private Date birthDate = new Date();
    private Date dateHired = new Date();
    private double gwa;
    private boolean repeat = false;
    private boolean back = false; 
    private int id = 0;
    private static SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");            
    private PersonContact personContact;
    private PersonRole personRole;
          
    public PersonDetails(){
        personCrud = new PersonOperations();
        personContact = new PersonContact(personCrud);
        personRole = new PersonRole(personCrud);
        firstName = "";
        lastName = "";
        middleName = "";
        title = "";
        street = "";
        brgy = "";
        city = "";
        zip = 0;
        birthDate = null;
        dateHired = null;
        gwa = 0.0;
        formatter.setLenient(false);
    }
    
    public void create(){ 
        personCrud = new PersonOperations();
        personContact = new PersonContact(personCrud);
        personRole = new PersonRole(personCrud);
        
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
                personContact.addContact();
                personRole.addRole();
                personCrud.savePerson(firstName, lastName, middleName, title, birthDate, street, brgy, city, zip, gwa, employed);
                personCrud.createNewPerson();
                System.out.print("Person ID#"+personCrud.getId()+" created");
                back = true;
            } else {
                System.out.print("Person already exists!");
                back = false;
            }
        } while(!back);
    }
    
    public void update(){
        int choice = 0;
        do{
            enterPersonId();
            back = true;  
            loadPerson();                  
                 do{
                    try{
                        System.out.print("Which would you like to update:\n(1) First Name \n(2) Middle Name \n(3) Last Name \n(4) Birth Date \n(5) Street \n(6) Barangay  \n(7) City \n(8) Zip \n(9) Employ Status \n(10) Hire Date \n(11) GWA \n(12) Title \n(13) Back \nChoice: " );
                        choice = Integer.parseInt(input.nextLine().trim());
                        switch(choice){
                            case 1: enterFirstName();
                                    break;   
               
                            case 2: enterMiddleName();
                                    break;

                            case 3: enterLastName();
                                    break;

                            case 4: enterBirthDate();
                                    break;

                            case 5: enterStreet();
                                    break;

                            case 6: enterBrgy();
                                    break;

                            case 7: enterCity();
                                    break;

                            case 8: enterZip();
                                    break;

                            case 9: enterEmployStatus();
                                     if(employed == 'N') {
                                        System.out.print("Setting employed status to NO will also clear hire date.\nAre you sure you want to update? [Y/N]: ");
                                        if(input.nextLine().charAt(0) == 'Y') {
                                            dateHired = null; 
                                            personCrud.setDateHired(dateHired);                     
                                        }                              
                                      }
                                      break;

                             case 10: enterHireDate();
                                      break;

                             case 11: enterGwa();
                                      break; 
                                    
                             case 12: enterTitle();
                                      break;

                             case 13: personCrud.closeSession();
                                      break;

                             default: System.out.print("Invalid choice. 1 - 13 only!\n");
                                      choice = 0;              
                      }
                                
                      if(choice > 0 && choice < 13){
                        char updateAgain;
                        do{
                            System.out.print("Update another field?[Y/N]: " );
                            updateAgain = Character.toUpperCase(input.nextLine().charAt(0));
                            if(updateAgain == 'Y') {
                                choice = 0;                   
                            } else if (updateAgain == 'N') {
                                personCrud.savePerson(firstName, lastName, middleName, title, birthDate, street, brgy, city, zip, gwa, employed);
                                personCrud.update();
                                personCrud.closeSession();
                            } else {
                                System.out.print("Y or N only!\n");
                            }
                         } while(updateAgain != 'Y' && updateAgain != 'N');
                     }          
                    } catch (NumberFormatException nfe) {
                        System.out.print("Invalid choice!\n");
                        choice = 0;                        
                    }
                } while(choice == 0); 
        }while(!back);
    }
    
    public void enterPersonId(){
        do{
            try{
                System.out.print("Enter person ID: ");
                id = Integer.parseInt(input.nextLine().trim());
                back = true;
            } catch (NumberFormatException nfe) {
                System.out.print("Invalid ID!\n");
                back = false;            
            }
            if (!personCrud.idExist(id)) {
                System.out.print("ID does not exist!\n");
                back = false;
            }
        }while(!back);
    }
    
    public void loadPerson(){
        firstName = personCrud.getFirstName();
        lastName = personCrud.getLastName();
        middleName = personCrud.getMiddleName();
        title = personCrud.getTitle();
        street = personCrud.getStreet();
        brgy = personCrud.getBrgy();
        city = personCrud.getCity();
        zip = personCrud.getZip();
        birthDate = personCrud.getBirthDate();
        dateHired = personCrud.getDateHired();
        gwa = personCrud.getGwa();
        employed = personCrud.getEmployed();
    }

    public void delete(){
        enterPersonId(); 
        personCrud.delete(id);
        System.out.print("Person ID "+id+" deleted");   
    }

     public void contactScreen(){
        int choice = 0;
        do{
            enterPersonId();
            personCrud.startContactSession();
            do{
                System.out.print("\nName: "+ personCrud.getFirstName() + " " + personCrud.getMiddleName() + " " + personCrud.getLastName());
                System.out.print("\n------------------Person Contacts Screen----------------");        
                System.out.print("\n(1) Add \n(2) Update \n(3) Delete \n(4) List \n(5) Change Person ID \n(6) Back to Person Screen \nChoice: ");
                 try{
                    choice = Integer.parseInt(input.nextLine().trim());
                    switch(choice) {
                        case 1: personContact.addContact();
                                personCrud.saveContact();
                                break;
                        case 2: personContact.updateContact();
                                break;
                        case 3: personContact.deleteContact();
                                break;
                        case 4: personContact.list();
                                break;
                        case 5: personCrud.closeContactSession();
                                personCrud.closeSession();
                                repeat = true;
                                break;
                        case 6: personCrud.closeContactSession();
                                personCrud.closeSession();
                                repeat = false;
                                break;
                        default:
                            System.out.print("\nInvalid choice!");
                            choice = 0;          
                    }
                 } catch (NumberFormatException nfe) {
                    System.out.print("\nInvalid choice!");
                    choice = 0;
                 } 
            } while (choice < 5);
           
        }while(repeat);
    }
    
    public void changePersonRole(){
        int choice = 0;
        do{
            enterPersonId();
            do{
                System.out.print("\nName: " + personCrud.getFirstName() + " " + personCrud.getMiddleName() + " " + personCrud.getLastName());
                System.out.print("\n------------------Person Role Screen----------------");        
                System.out.print("\n(1) Add \n(2) Delete \n(3) List \n(4) Change Person ID \n(5) Back to Person \nChoice: ");
                 try{
                    choice = Integer.parseInt(input.nextLine().trim());
                    switch(choice) {
                        case 1: personRole.addRole();
                                personCrud.update();
                                break;
                        case 2: personRole.deleteRole();
                                break;
                        case 3: personRole.list();
                                break;
                        case 4: personCrud.closeSession();
                                repeat = true;
                                break;
                        case 5: personCrud.closeSession();
                                repeat = false;
                                break;
                        default:
                            System.out.print("\nInvalid choice!");          
                    }
                 } catch (NumberFormatException nfe) {
                    System.out.print("\nInvalid choice!");
                    repeat = false;
                 } 
            } while (choice < 4);
       }while(repeat);
    }
    
    public void list(){
        int listChoice = 0;
        int order = 0; 
        do{     
            System.out.print("\nList Person by:");        
            System.out.print("\n(1) GWA \n(2) Last Name \n(3) Date Hired \n(4) Back to Person \nChoice: ");
            try{
                listChoice = Integer.parseInt(input.nextLine().trim());
                if(listChoice == 4){
                    repeat = false;                    
                } else if(listChoice < 1 || listChoice > 4) {   
                    System.out.print("\nInvalid choice!");
                    repeat = true;         
                } else {
                    do{
                        System.out.print("\nOrder by: \n(1)Ascending \n(2)Descending \nChoice: ");
                        try{
                            order = Integer.parseInt(input.nextLine());
                            System.out.print("\nID\tTITLE\tFIRST_NAME\tMIDDLE_NAME\tLAST_NAME\tBIRTHDATE\tSTREET\tBRGY\tCITY\\MUNICIPALITY\tZIP\tGWA\tEMPLOYED\tDATE_HIRED"+personCrud.printPersonList(listChoice, order)+"\n");
        
                        } catch (NumberFormatException nfe) {
                            System.out.print("\nInvalid choice!");
                            order = 0;
                        } 
                    }while(order < 1 || order > 2);
                }
            } catch (NumberFormatException nfe) {
                System.out.print("\nInvalid choice!");
                repeat = true;
            } 
        } while (repeat);
    }

    private void enterFirstName(){
        System.out.print("\nEnter First Name: ");
        firstName = input.nextLine();
        firstName = alphabetOnly(firstName).toUpperCase().trim(); 
    }

    private void enterMiddleName(){
        System.out.print("Enter Middle Name: ");
        middleName = input.nextLine();
        middleName = alphabetOnly(middleName).toUpperCase().trim();    
    }

    private void enterLastName(){
        System.out.print("Enter Last Name: ");
        lastName = input.nextLine();    
        lastName = alphabetOnly(lastName).toUpperCase().trim(); 
    }

    private void enterTitle(){
        int titleChoice = 0;
         System.out.print("Choose title: "+personCrud.printTitleList()+"\nEnter Title: ");  
         title = input.nextLine().toUpperCase();
         while(!personCrud.titleExist(title)){
            System.out.print("Invalid Title! Please choose from the given list. \nEnter Title: ");            
            title = input.nextLine().toUpperCase().trim();
         }
    }

    private void enterBirthDate(){
        do{
           try{                    
                System.out.print("Enter birthday [MM/DD/YYYY]: ");
                birthDate = formatter.parse(input.next().trim());
                input.nextLine();  
                if(validDate(birthDate)) {
                    repeat = false;
                } else {
                    repeat = true;                    
                }   
            } catch(ParseException pe) {
                System.out.print("Invalid date\n");
                repeat = true;
            }  
         } while(repeat); 
    }

    private void enterStreet(){
        System.out.print("Enter current street address: ");
        street = input.nextLine().toUpperCase().trim();       
    }

    private void enterBrgy(){
        System.out.print("Enter current barangay: ");
        brgy = input.nextLine().toUpperCase().trim();
    }

    private void enterCity(){
         System.out.print("Enter current city/municipality: ");
         city = input.nextLine().toUpperCase().trim();   
    }
    
    private void enterZip(){
        do{ 
            try{
                System.out.print("Enter zip code: ");
                zip = Integer.parseInt(input.nextLine().trim()); 
                if(((int) Math.log10(zip) + 1) != 4){
                    System.out.print("Invalid zip code!\n");
                    repeat = true;                
                } else {
                    repeat = false;
                }
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
                gwa = Double.parseDouble(input.nextLine().trim());
                if(gwa < 1 || gwa > 100){
                    System.out.print("Invalid GWA!\n");
                    repeat = true;                
                } else {
                    repeat = false;
                } 
            } catch(NumberFormatException nfe) {
                System.out.print("Invalid GWA!\n");
                repeat = true;            
            }       
        } while(repeat);  
    }

    private void enterEmployStatus(){
        do{                    
            System.out.print("Currently employed?[Y/N]: ");
            employed = input.nextLine().charAt(0);
            employed = Character.toUpperCase(employed);
            if (employed == 'Y'){
                enterHireDate();
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
                 dateHired = formatter.parse(input.next().trim());
                 input.nextLine();
                 if(validDate(dateHired)) {
                    repeat = false;
                } else {
                    repeat = true;                    
                } 
            } catch(ParseException pe) {
                 System.out.print("Invalid date\n");
                 repeat = true;
            }
        } while(repeat);   
        personCrud.setDateHired(dateHired);   
    }
    
    private boolean validDate(Date d){
        Calendar c = Calendar.getInstance();
        Calendar current = Calendar.getInstance();
        c.setTime(d);
        if(c.get(Calendar.YEAR) < 1970 || c.get(Calendar.YEAR) > current.get(Calendar.YEAR)) {
            System.out.println("Invalid Year entered");
            return false;
        }    
        return true;
    }

    protected String alphabetOnly(String text){
        while(text.equals("") || !text.matches("[a-zA-Z ]*")){
            System.out.print("Invalid input. \nKindly input again: ");            
            text = input.nextLine();
        }   
        return text; 
    }
}

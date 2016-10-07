package crud.app;

import java.util.*;
import crud.core.service.InitializeSessionFactory;

public class Main{
    Scanner input = new Scanner(System.in);    
    
    public static void main(String[] args){
        Main menu = new Main(); 
    }
    
    public Main(){   
        int choice = 0;
        boolean exit = false;
        InitializeSessionFactory crudSession = new InitializeSessionFactory();
        crudSession.startFactory();

        do{
            System.out.print("\nMake changes on: \n(1) Person \n(2) Roles \n(3) Exit \nChoice: "); 
            try{            
                choice = input.nextInt();                
                switch(choice){
                    case 1: 
                            exit = personScreen(); 
                            break;
                    case 2: 
                            exit = roleScreen();
                            break;
                    case 3: 
                            exit = true; 
                            break;
                    default: 
                        System.out.print("\nInvalid choice! 1 or 2 only.");
                }
            } catch (InputMismatchException ime) {
                System.out.print("\nInvalid choice! 1 or 2 only.");
                choice=0;
            }
        } while(!exit); 
    
        crudSession.stopFactory();  
    }
    
    public boolean personScreen() {
        boolean back = false;
        boolean exit = false;
        int choice = 0;
        PersonDetails personDetails = new PersonDetails();
        do {            
            System.out.print("\n------------------Person Screen----------------");        
            System.out.print("\n(1) Create \n(2) Update \n(3) Delete \n(4) Roles \n(5) Contacts \n(6) List \n(7) Back to Main \n(8) Exit Program \nChoice: ");
             try{
                choice = input.nextInt();
                switch(choice) {
                    case 1: 
                            personDetails.create(); 
                            break;
                    case 2: 
                            personDetails.update();
                            break;
                    case 3:
                            personDetails.delete();
                            break;
                    case 4: 
                            personDetails.changePersonRole();
                            break;
                    case 5: 
                            personDetails.contactScreen();
                            break;
                    case 6:
                            personDetails.list();
                            break;
                    case 7: 
                            back = true; 
                            break;
                    case 8: 
                            back = true; 
                            exit = true; 
                            break;
                    default:
                        System.out.print("\nInvalid choice!");          
                }
             } catch (InputMismatchException ime) {
                System.out.print("\nInvalid choice!");
                choice = 0;
             } 
        } while (!back);
        
        return exit;
    }

    public boolean roleScreen() {
        boolean back = false;
        boolean exit = false;
        int choice = 0;
        RoleDetails roleDetails = new RoleDetails();
        do {            
            System.out.print("\n------------------Role Screen----------------");        
            System.out.print("\n(1) Create \n(2) Update \n(3) Delete \n(4) List \n(5) Back to Main \n(6) Exit Program \nChoice: ");
             try{
                choice = input.nextInt();
                switch(choice) {
                    case 1: 
                            roleDetails.create(); 
                            break;
                    case 2:
                            roleDetails.update(); 
                            break;
                    case 3:
                            roleDetails.delete(); 
                            break;
                    case 4:
                            roleDetails.list(); 
                            break;
                    case 5: 
                            back = true; 
                            break;
                    case 6: 
                            back = true; 
                            exit = true; 
                            break;
                    default:
                        System.out.print("Invalid choice!");          
                }
             } catch (InputMismatchException ime) {
                System.out.print("Invalid choice!");
                choice = 0;
             } 
        } while (!back);
        
        return exit;
    }
}


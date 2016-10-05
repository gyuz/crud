package crud.app;

import java.util.*;

public class Main{
    Scanner input = new Scanner(System.in);    
    public static void main(String[] args){
        Main menu = new Main();
    }
    
    public Main(){   
        int choice = 0;
        boolean exit = false;
        do{
            System.out.print("Make changes to: \n(1) Person \n(2) Roles \n(3) Exit \nChoice: "); 
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
    }
    
    public boolean personScreen() {
        boolean back = false;
        boolean exit = false;
        int choice = 0;
        PersonDetails personDetails = new PersonDetails();
        System.out.print("\n------------------Person Screen----------------");
        do {        
            System.out.print("\n(1) Create \n(2) Update \n(3) Delete \n(4) Roles \n(5) Contacts \n(6) List \n(7) Back to Main \n(8) Exit Program \nChoice: ");
             try{
                choice = input.nextInt();
                switch(choice) {
                    case 1: 
                            personDetails.create(); 
                            break;
                    case 2:
                    case 3:
                    case 4: 
                            addPersonRole();
                            break;
                    case 5: 
                            contactScreen();
                            break;
                    case 6:
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
        System.out.print("\n------------------Role Screen----------------");
        do {        
            System.out.print("\n(1) Create \n(2) Update \n(3) Delete \n(4) List \n(5) Back to Main \n(6) Exit Program \nChoice: ");
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
                    case 6: 
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

    public void addPersonRole(){
        boolean back = false;
        int choice = 0;
        System.out.print("\n------------------Person Role Screen----------------");
        do {        
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
        System.out.print("\n------------------Person Contacts Screen----------------");
        do {        
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
}


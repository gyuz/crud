package crud.app;

import java.util.*;

public class PersonScreen {
    Scanner input = new Scanner(System.in);
    public PersonScreen() {
        int choice = 0;
        System.out.print("\n------------------Person Screen----------------");
        do {        
        System.out.print("\n(1)Create \n(2)Update \n(3)Delete \n(4)Add Role \n(5)Contact \n(6)List \nChoice: ");
         try{
            choice = input.nextInt();
            switch(choice) {
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                default:
                    Syste.out.print("\nInvalid choice!");          
            }
         } catch (InputMismatchException ime) {
            Syste.out.print("\nInvalid choice!");
            choice = 0;
         } 
        } while (choice < 1 || choice > 6);
    }

}

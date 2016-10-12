package crud.app;

import java.util.Scanner;
import crud.core.service.PersonOperations;
import crud.core.service.RoleOperations;

public class PersonRole{
    Scanner input = new Scanner(System.in);
    private PersonOperations personOps;
    private RoleOperations roleOps;
    private int roleId;
    
    PersonRole(PersonOperations p){
        personOps = p;
        roleOps = new RoleOperations();
        roleId = 0;
    }
    
    public void addRole(){
        char choice = 'Y';
        do{
            System.out.print("\nChoose Role: " + roleOps.printRoleList() + "\n");
            enterRoleId();
            if (!personOps.addRole(roleOps.getRole())){
                System.out.print("Role already exist for this person!\n");
                choice = 'Y';
            } else {
                do{
                    System.out.print("Add another role?[Y/N]: ");
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
    

    private void enterRoleId(){
       boolean back = true;
       do{
            try{
                System.out.print("Enter Role ID: ");
                roleId = Integer.parseInt(input.nextLine().trim());
                back = true;
            } catch (NumberFormatException nfe) {
                System.out.print("Invalid ID!\n");
                back = false;            
            }
            if (!roleOps.idExist(roleId)) {
                System.out.print("ID does not exist!\n");
                back = false;
            }
        }while(!back);   
    }

    public void list(){
        System.out.println("\nROLE_ID\tROLE_NAME" + personOps.printPersonRoleList());   
    } 
    
    public void deleteRole(){
        char choice = 'Y';
        do{
            System.out.print("Which would you like to delete:");
            list();
            enterRoleId();
            personOps.deleteRole(roleOps.getRole());
            do{
                 System.out.print("Delete another role?[Y/N]: ");
                 choice = Character.toUpperCase(input.nextLine().charAt(0));
                 switch(choice){
                    case 'Y':
                    case 'N': break;
                    default: System.out.print("Y or N only!\n");
                 }
            } while(choice != 'Y' && choice != 'N'); 
         }while(choice == 'Y');        
    }
    
}


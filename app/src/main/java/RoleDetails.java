package crud.app;

import java.util.*;
import crud.core.service.RoleOperations;

public class RoleDetails{
    Scanner input = new Scanner(System.in);    
    RoleOperations roleCrud;
    boolean back = true;    
    
    public RoleDetails(){
        roleCrud = new RoleOperations();
    }

    public void create(){
        String roleName = "";
        do{
            try{
                System.out.print("\nEnter name of new role: ");
                roleName = input.nextLine().toUpperCase().trim();
                if(!roleCrud.isDuplicate(roleName)){
                    roleCrud.addRoleName(roleName);
                    System.out.print("New role "+roleName+" created");
                    back = true;
                } else {
                    System.out.print("Role already exists!");
                    back = false;
                }
            } catch (InputMismatchException ime) {
                System.out.print("Enter valid role name"); 
                back = false;       
            }
        } while (!back);
    }

    public void update(){
        int id = 0;
        boolean repeat = false;   
        do {
            try{
                System.out.print("\nEnter Role ID to edit: ");
                id = Integer.parseInt(input.nextLine());  
                if(roleCrud.idExist(id)){
                    do{                        
                        try{
                            System.out.print("Enter new role name: ");
                            String newRoleName = input.nextLine().toUpperCase();
                            if (roleCrud.update(id, newRoleName)){ 
                                System.out.print("Update done"); 
                                repeat = false;
                             } else {
                                System.out.print("Role already exist!\n");
                                repeat = true;
                             }  
                        } catch (IllegalArgumentException ime) {
                            System.out.print("Enter valid role name"); 
                            repeat = true;       
                        } 
                    } while(repeat); 
                    back = true;                  
                } else {
                    System.out.print("Role ID does not exist!");
                    back = false;                
                }
            } catch (NumberFormatException nfe) {
                System.out.print("Not a valid ID!");
                back = false;            
            }      
        } while(!back);
    }
    
    public void delete(){
        int id = 0;
        back = true;
       do {
            try{
                System.out.print("\nEnter Role ID for deletion: ");
                id = Integer.parseInt(input.nextLine());  
                if(!roleCrud.delete(id)){
                    System.out.print("Role ID does not exist!");
                    back = false;                
                } else {
                    System.out.print("Role ID "+id+" deleted");
                    back = true;                
                }
            } catch (NumberFormatException nfe) {
                System.out.print("Not a valid ID!");
                back = false;            
            }      
        } while(!back);
    }
    
    public void list(){
        System.out.println(roleCrud.printRoleList()+"\n");
    }
}

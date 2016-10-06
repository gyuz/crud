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
                roleName = input.next();
                roleCrud.setRoleName(roleName.toUpperCase());
            } catch (IllegalArgumentException ime) {
                System.out.print("\nEnter valid role name"); 
                back = false;       
            }
        } while (!back);
    }

    public void update(){
        int choice = 0;
        do {
            System.out.print("\nUpdate by (1) Role ID (2) Role Name \nChoic: ");
            choice = input.nextInt();        
            switch(choice){
                case 1: 
                        System.out.print("\nEnter role ID to edit: ");
                        break; 
                case 2: 
                        System.out.print("\nEnter role name to edit: ");
                        break;
                default: 
                        System.out.print("\nInvalid Choice");
                        back = false;
                        break;        
            }
        } while(!back);
    }
    
    public void delete(){
        int choice = 0;
        do {
            System.out.print("\nDelete by (1) Role ID (2) Role Name \nChoic: ");
            choice = input.nextInt();        
            switch(choice){
                case 1: 
                        System.out.print("\nEnter role ID to delete: ");
                        break; 
                case 2: 
                        System.out.print("\nEnter role name to delete: ");
                        break;
                default: 
                        System.out.print("\nInvalid Choice");
                        back = false;
                        break;        
            }
        } while(!back);
    }
    
    public void list(){
        System.out.println(roleCrud.getRoleList());
    }
}

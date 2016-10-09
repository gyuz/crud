package crud.core.service;

import crud.core.model.Role;
import crud.core.dao.RoleDao;
import org.apache.commons.lang3.text.StrBuilder;
import java.util.List;
import java.util.Iterator;

public class RoleOperations {
    Role role;
    RoleDao roleDao;
    
    public RoleOperations(){   
        role = new Role();
        roleDao = new RoleDao();
    }      

    public RoleOperations(String roleName) {
        role = new Role(roleName);
        roleDao = new RoleDao();
        roleDao.add(role);
    }
    
    public void addRoleName(String roleName) {
        role.setRoleName(roleName);
        roleDao.add(role);        
    }
    
    public boolean idExist(int id) {
        role = roleDao.getRoleById(id); 
        if (role != null) return true;
        return false;     
    }
    
    public boolean update(int id, String newRoleName) {
        boolean exist = isDuplicate(newRoleName); 
        if(!exist) {
            role = roleDao.getRoleById(id); 
            role.setRoleName(newRoleName);
            roleDao.update(role);
        }
        return !exist;    
    }
    
    public boolean delete(int id) {
       role = roleDao.getRoleById(id);
       if (role != null) {
            roleDao.delete(role);     
            return true;
        }
        return false;  
    }

    public boolean isDuplicate(String roleName){
        List roleList = roleDao.getList("Role"); 
        for (Iterator iterator1 = roleList.iterator(); iterator1.hasNext();){
            Role roles = (Role) iterator1.next();
            if( roles.getRoleName().equals(roleName)) return true;
       }
       return false;
    }
    
    public String printRoleList(){
       StrBuilder strBuilder = new StrBuilder();
       List roleList = roleDao.getList("Role"); 
       strBuilder.append("\nID\tROLE_NAME");
       for (Iterator iterator1 = roleList.iterator(); iterator1.hasNext();){
         Role roles = (Role) iterator1.next();
         strBuilder.append("\n"+roles.getRoleId()+"\t"+roles.getRoleName());
       }
       return strBuilder.toString();   
    }
}

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
    
    public void setRoleName(String roleName) {
        role.setRoleName(roleName);
        roleDao.add(role);        
    }
    
    public boolean idExist(int id) {
       return roleDao.checkRoleId(id);     
    }
    
    public void update(int id, String newRoleName) {
        roleDao.update(id, newRoleName);    
    }

    public boolean delete(int id) {
        return roleDao.deleteById(id);  
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

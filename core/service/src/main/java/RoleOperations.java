package crud.core.service;

import crud.core.model.Role;
import crud.core.dao.RoleDao;
import org.apache.commons.lang3.text.StrBuilder;
import java.util.List;

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
    
    public Role getRole(){
        return role;    
    }   
    
    public void setRole(Role role){
        this.role = role;    
    } 
    
    public void closeSession(){
        roleDao.closeSession();    
    }
   
    public void addRoleName(String roleName) {
        setRoleName(roleName);
        roleDao.add(role);        
    }

    public void setRoleName(String roleName){
        role.setRoleName(roleName);    
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
        RoleDao roleDao2 = new RoleDao();
        List roleList = roleDao2.getList("Role where roleName = '"+roleName+"'"); 
        if(roleList.isEmpty()) return false;
        return true;
    }
    
    public String printRoleList(){
       RoleDao roleDao2 = new RoleDao();
       StrBuilder strBuilder = new StrBuilder();
       List<Role> roleList = roleDao2.getList("Role ORDER BY ROLE_ID"); 
       strBuilder.append("\nID\tROLE_NAME");
       for (Role roles : roleList){
         strBuilder.append("\n"+roles.getRoleId()+"\t"+roles.getRoleName());
       }
       return strBuilder.toString();   
    }
}

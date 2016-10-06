package crud.core.service;

import crud.core.model.Role;
import crud.core.dao.RoleDao;
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
    
    public String getRoleList(){
       roleDao.loadAll();  
       return roleDao.getStringBuilder();
    }
}

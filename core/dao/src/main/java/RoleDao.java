package crud.core.dao;

import crud.core.model.Role;

public class RoleDao extends CrudImpl<Role>{   
    public RoleDao(){
        sessions = new SessionGroup();   
    }
    
    public boolean checkRoleId(int id){
        Role role = getRoleById(id);        
        if (role != null) return true;
        return false;    
    }
    
    public boolean deleteById(int id){
        Role role = getRoleById(id); 
        if (role != null) {
            delete(role);     
            return true;
        }
        return false;             
    }   
    
    public void update(int id, String roleName) {
        sessions.openSessionTransaction();
        Role role = (Role) sessions.getCurrentSession().get(Role.class, id);
        role.setRoleName(roleName);
        sessions.getCurrentSession().update(role);
        sessions.closeSessionTransaction();
    }    
    
    public Role getRoleById(int id) {
        sessions.openSessionTransaction();
        Role role = (Role) sessions.getCurrentSession().get(Role.class, id);
        sessions.closeSessionTransaction();
        return role;
    }
}

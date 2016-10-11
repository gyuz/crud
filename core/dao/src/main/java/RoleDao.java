package crud.core.dao;

import crud.core.model.Role;
import org.hibernate.Hibernate;

public class RoleDao extends CrudImpl<Role>{   
    public RoleDao(){
        sessions = new SessionGroup();   
    }  
    
    public Role getRoleById(int id) {
        sessions.startSession();
        Role role = (Role) sessions.getCurrentSession().get(Role.class, id);
        sessions.closeSession();
        return role;
    }
}

package crud.core.dao;

import crud.core.model.Role;

public class RoleDao extends CrudImpl<Role>{   
    public RoleDao(){
        sessions = new SessionGroup();   
    }  

    public Role getRoleById(int id) {
        sessions.openSessionTransaction();
        Role role = (Role) sessions.getCurrentSession().get(Role.class, id);
        if (role == null) {
            sessions.closeSessionTransaction();
        }
        return role;
    }
}

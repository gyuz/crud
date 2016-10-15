package crud.core.dao;

import crud.core.model.Role;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Hibernate;

public class RoleDao extends CrudImpl<Role>{   
    public RoleDao(){
        sessions = new SessionGroup();   
    }  
    
    public void update(Role role) {
        sessions.openSessionTransaction();
		sessions.getCurrentSession().update(role);
		sessions.closeSessionTransaction(); 
	}

	public void delete(Role role) {
        sessions.openSessionTransaction();
        sessions.getCurrentSession().delete(role);
        sessions.closeSessionTransaction();
	}

    public Role getRoleById(int id) {
        sessions.openSessionTransaction();
        Role role = (Role) sessions.getCurrentSession().get(Role.class, id);
        System.out.println(role.getPersons());        
        sessions.closeSessionTransactionRollback();
        return role;
    }
}

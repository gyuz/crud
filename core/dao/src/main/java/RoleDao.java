package crud.core.dao;

import crud.core.model.Role;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Hibernate;

public class RoleDao extends CrudImpl<Role>{   
    public RoleDao(){
        sessions = new SessionGroup();   
    }  
    
     public List<Role> initializeRoleSet(int id){
        sessions.startSession();
        Query query = sessions.getCurrentSession().createQuery("select r from Person p join p.roles r where p.id = :id");
        query.setInteger("id", id);
        List<Role> roleList = query.list();
        sessions.closeSession(); 
        return roleList;
    } 

    public Role getRoleById(int id) {
        sessions.startSession();
        Role role = (Role) sessions.getCurrentSession().get(Role.class, id);
        sessions.closeSession();
        return role;
    }
}

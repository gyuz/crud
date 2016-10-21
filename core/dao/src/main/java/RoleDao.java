package crud.core.dao;

import crud.core.model.Role;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class RoleDao extends CrudImpl<Role>{   
    public RoleDao(){  
    }  

    public Role getRoleById(int id) {
        Session session2 = sessionGroup.getSession();
        Transaction tx = session2.beginTransaction();
        Role role = (Role) session2.get(Role.class, id);
        tx.rollback();
        session2.close();
        return role;
    }
}

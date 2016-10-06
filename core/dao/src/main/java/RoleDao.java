package crud.core.dao;

import crud.core.model.Role;
import java.util.List;
import java.util.Iterator;  
import org.apache.commons.lang3.text.StrBuilder;


public class RoleDao extends CrudImpl<Role>{   
    public RoleDao(){
        super.sessions = new SessionGroup();   
    }
    
    public void loadAll() {
        strBuilder = new StrBuilder();
        sessions.openSessionTransaction();
		List<Role> roles = (List<Role>) sessions.getCurrentSession().createQuery("from Role").list();
        super.strBuilder.append("\nID\tROLE_NAME");
        for (Iterator iterator1 = roles.iterator(); iterator1.hasNext();){
            Role role = (Role) iterator1.next();
            super.strBuilder.append("\n"+role.getRoleId()+"\t"+role.getRoleName());
        }
        sessions.closeSessionTransaction();
	}
    
}

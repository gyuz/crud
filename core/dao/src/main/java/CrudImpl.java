package crud.core.dao;

import org.hibernate.cfg.*;
import org.hibernate.Session.*;
import org.hibernate.SessionFactory.*;
import java.util.List;

public abstract class CrudImpl<T> implements CrudInterface<T> {
	SessionGroup sessions;

    public void setSessiongroup(SessionGroup sessions) {
        this.sessions = sessions;    
    }
    
    public SessionGroup getSessionGroup() {
        return sessions;    
    }  
    
    public void add(T entity) {
        sessions.openSessionTransaction();
		sessions.getCurrentSession().save(entity);
        sessions.closeSessionTransaction();
	}

	public void update(T entity) {
        sessions.openSessionTransaction();
		sessions.getCurrentSession().update(entity);
		sessions.closeSessionTransaction(); 
	}

	public void delete(T entity) {
        sessions.openSessionTransaction();
        sessions.getCurrentSession().delete(entity);
        sessions.closeSessionTransaction();
	}
     
    public List<T> getList(String refObj) {
        sessions.openSessionTransaction();
		List<T> entity = (List<T>) sessions.getCurrentSession().createQuery("from "+refObj).list();      
        sessions.closeSessionTransactionRollback(); 
        return entity; 
	}
}

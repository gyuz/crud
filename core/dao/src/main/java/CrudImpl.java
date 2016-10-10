package crud.core.dao;

import org.hibernate.*;
import java.util.List;

public abstract class CrudImpl<T> implements CrudInterface<T> {
	SessionGroup sessions;

    public void setSessiongroup(SessionGroup sessions) {
        this.sessions = sessions;    
    }
    
    public SessionGroup getSessionGroup() {
        return sessions;    
    }  
    
    public void closeSessionTransaction(){
        sessions.closeSessionTransaction();   
    }
    
    public void closeSession(){
        sessions.closeSession();   
    }

    public void add(T entity) {
        sessions.openSessionTransaction();
		sessions.getCurrentSession().save(entity);
        sessions.closeSessionTransaction();
	}

	public void update(T entity) {
		sessions.getCurrentSession().update(entity);
		sessions.closeSessionTransaction(); 
	}

	public void delete(T entity) {
        sessions.getCurrentSession().delete(entity);
        sessions.closeSessionTransaction();
	}
     
    public List<T> getList(String refObj) {
        sessions.openSessionTransaction();
		List<T> entity = (List<T>) sessions.getCurrentSession().createQuery("from "+refObj).list();
        sessions.closeSessionTransaction();
        return entity; 
	}
}

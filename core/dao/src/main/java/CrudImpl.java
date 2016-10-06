package crud.core.dao;

import org.hibernate.*;
import java.util.List;
import java.util.Iterator;  
import org.apache.commons.lang3.text.StrBuilder;

public abstract class CrudImpl<T> implements CrudInterface<T> {
	SessionGroup sessions;
    StrBuilder strBuilder;
    
    public void setSessiongroup(SessionGroup sessions){
        this.sessions = sessions;    
    }
    
    public SessionGroup getSessionGroup(){
        return sessions;    
    }   

    public void add(T entity) {
        sessions.openSessionTransaction();
		sessions.getCurrentSession().save(entity);
        sessions.closeSessionTransaction();
	}

	public void update(T entity) {
		sessions.getCurrentSession().update(entity);
	}

	public void delete(T entity) {
        sessions.getCurrentSession().delete(entity);
	}
     
    public void loadAll() {
        strBuilder = new StrBuilder();
        sessions.openSessionTransaction();
		List<T> people = (List<T>) sessions.getCurrentSession().createQuery("from Person").list();
        sessions.closeSessionTransaction(); 
	}
    
    public String getStringBuilder(){
        return this.strBuilder.toString();    
    } 
}

package crud.core.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class SessionGroup{
    private static SessionFactory sessionFactory;
 
    public SessionGroup(){   
    }

    public static void newSessions() {
	    sessionFactory = new Configuration().configure().buildSessionFactory();
	}

    public static void closeSessionFactory() {
        sessionFactory.close();
    }
    
    public Session getSession(){
        return sessionFactory.openSession();
    }
}

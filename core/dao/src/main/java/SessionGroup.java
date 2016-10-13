package crud.core.dao;

import org.hibernate.*;
import org.hibernate.cfg.*;

public class SessionGroup{
    private static SessionFactory sessionFactory;
    private Session session;
    private Transaction transact;

    public SessionGroup(){   
    }

    public static void newSessions() {
	    sessionFactory = new Configuration().configure().buildSessionFactory();
	}

    public static void closeSessionFactory() {
        sessionFactory.close();
    }
        
    public void startSession(){
        session = sessionFactory.openSession();
    }
    
    public void closeSession(){
        session.close();
    } 
    
    public void startTransaction(){
        transact = session.beginTransaction();
    }
    
    public void closeTransaction(){
        transact.commit();
    }
	
	protected void openSessionTransaction() {
		session = sessionFactory.openSession();
		transact = session.beginTransaction();
	}
    
	protected void closeSessionTransaction() {
		transact.commit();
		session.close();
	}

    protected void closeSessionTransactionRollback() {
		transact.rollback();
		session.close();
	}

	protected Session getCurrentSession() {
		return session;
	}

	protected void setCurrentSession(Session currentSession) {
		this.session = currentSession;
	}

	protected Transaction getCurrentTransaction() {
		return transact;
	}

	protected void setCurrentTransaction(Transaction currentTransaction) {
		this.transact = currentTransaction;
	}
}

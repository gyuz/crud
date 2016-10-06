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

    public static void stopSessions() {
        sessionFactory.close();
    }

	protected Session openSessionTransaction() {
		session = sessionFactory.openSession();
		transact = session.beginTransaction();
		return session;
	}

	protected void closeSessionTransaction() {
		transact.commit();
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

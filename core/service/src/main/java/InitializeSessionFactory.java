package crud.core.service;

import crud.core.dao.*;

public class InitializeSessionFactory{ 
    SessionGroup sessions;   
    public InitializeSessionFactory(){
       sessions = new SessionGroup();
    }
    
    public void startFactory(){
        sessions.newSessions();
    }  

    public void stopFactory(){
        sessions.closeSessionFactory();
    }
}

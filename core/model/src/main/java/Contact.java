package crud.core.model;

public class Contact {
    private int contactId;
    private Types contactType;
    private String details;

    public Contact() {
    }
        
    public int getContactId() {
        return contactId;    
    }    

    public Types getContactType() {
        return contactType;    
    }
    
    public String getDetails(){
        return details;    
    }
    
    public void setContactId(int contactId) {
        this.contactId = contactId;    
    }

    public void setContactType(Types contactType) {
        this.contactType = contactType;
    }
    
    public void setDetails(String details) {
        this.details = details;    
    }

}

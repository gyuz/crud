package crud.core.model;

public class Contact() {
    
    public enum Types {
        LANDLINE, MOBILE, EMAIL;    
    } 
    
    private int contactId;
    private Types contactType;
    private String details;

    public Contact(){
    }
    
    public Type getContactType() {
        return this.contactType;    
    }

    public int getDetails() {
        return this.details;    
    }
    
    public String getEmail() {
        return this.email;    
    }

    public void setContactType(Types contactType) {
        this.contactType = contactType;
    }
    
    public void setDetails(String details) {
        this.details = details;    
    }
}

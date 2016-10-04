public class Contact() {
    private int contactId;
    private String contactType;
    private int details;
    private String email;
        
    public Contact(){
    }
    
    public String getContactType() {
        return this.contactType;    
    }

    public int getDetails() {
        return this.details;    
    }
    
    public String getEmail() {
        return this.email;    
    }

    public void setContactType(String contactType) {
        this.contactType = contactType;
    }
    
    public void setDetails(int details) {
        this.details = details;    
    }

    public void setEmail(String email) {
        this.email = email;    
    }
}

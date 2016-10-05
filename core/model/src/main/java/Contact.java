package crud.core.model;

public class Contact {
    
    public enum Types {
        LANDLINE, MOBILE, EMAIL;    
    } 
    
    private int contactId;
    private Types contactType;
    private int number;
    private String email;

    public Contact(){
    }
    
    public Types getContactType() {
        return this.contactType;    
    }

    public int getNumber() {
        return this.number;    
    }
    
    public String getEmail(){
        return this.email;    
    }

    public void setContactType(Types contactType) {
        this.contactType = contactType;
    }
    
    public void setNumber(int number) {
        this.number = number;    
    }
    
    public void setEmail(String email) {
        this.email = email;    
    }
}

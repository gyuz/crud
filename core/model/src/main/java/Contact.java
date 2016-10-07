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
        
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (!this.getClass().equals(obj.getClass())) return false;
        Contact obj2 = (Contact) obj;
        if(this.contactType.equals(obj2.contactType) && this.details.equals(obj2.details))
        {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int tmp = 0;
        tmp = ( contactType + details ).hashCode();
        return tmp;
    }

}

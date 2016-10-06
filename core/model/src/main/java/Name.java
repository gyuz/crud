package crud.core.model;

public class Name {

    private String firstName;
    private String lastName;
    private String middleName;

    public Name() {
    }

    public Name(String firstName, String lastName, String middleName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
    }

    public String getFirstName() {
        return firstName;    
    }
    
    public String getLastName() {
        return lastName;    
    }
        
    public String getMiddleName() {
        return middleName;    
    }

    public void setFirstName(String firstName) {   
        this.firstName = firstName;
    }
    
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }
    
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (!this.getClass().equals(obj.getClass())) return false;
        Name obj2 = (Name) obj;
        if(this.firstName.equals(obj2.firstName) 
            && this.lastName.equals(obj2.lastName) 
            && this.middleName.equals(obj2.middleName))
        {
            return true;
        }
        return false;
    }

    public int hashCode() {
    int tmp = 0;
    tmp = ( firstName + lastName + middleName ).hashCode();
    return tmp;
    }
    
}

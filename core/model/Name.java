public class Name {
    private String firstName;
    private String lastName;
    private String middleName;
    private String title;

    public Name() {
    }

    public Name(String firstName, String lastName, String middleName, String title) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.title = title;
    }

    public String getFirstName() {
        return this.firsName;    
    }
    
    public String getLastName() {
        return this.lastName;    
    }
        
    public String getMiddleName() {
        return this.middleName;    
    }
    
    public String getTitle() {
        return this.title;    
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
    
    public void setTitle(String title) {
        this.title = title;
    }
}

package crud.core.model;

public class Name {
    private enum Title {
        MR, MS, MRS, DR, JR, SR;    
    }
    
    private String firstName;
    private String lastName;
    private String middleName;
    private Title title;

    public Name() {
    }

    public Name(String firstName, String lastName, String middleName, Title title) {
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
    
    public Title getTitle() {
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
    
    public void setTitle(Title title) {
        this.title = title;
    }
}

package crud.core.model;

public class Address{
    private String street;
    private String brgy;
    private String city;
    private int zip;

    public Address() {    
    }   

    public Address(String street, String brgy, String city, int zip) {
        this.street = street;
        this.brgy = brgy;
        this.city = city;
        this.zip = zip;    
    }

    public String getStreet() {
        return street;    
    } 

    public String getBrgy() {
        return brgy;    
    }

     public String getCity() {
        return city;    
    }

     public int getZip() {
        return zip;    
    }

    public void setStreet(String street) {
        this.street = street;    
    }

    public void setCity(String city) {
        this.city = city;    
    }

    public void setZip(int zip) {
        this.zip = zip;    
    }

    public void setBrgy(String brgy) {
        this.brgy = brgy;    
    }
}

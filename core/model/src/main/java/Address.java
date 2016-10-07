package crud.core.model;

public class Address{
    private String street;
    private int brgy;
    private String city;
    private int zip;

    public Address() {    
    }   

    public Address(String street, int brgy, String city, int zip) {
        this.street = street;
        this.brgy = brgy;
        this.city = city;
        this.zip = zip;    
    }

    public String getStreet() {
        return street;    
    } 

    public int getBrgy() {
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

    public void setBrgy(int brgy) {
        this.brgy = brgy;    
    }
}

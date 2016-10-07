package crud.core.service;

import crud.core.model.Person;
import crud.core.model.Title;
import crud.core.dao.PersonDao;
import org.apache.commons.lang3.text.StrBuilder;
import java.util.List;
import java.util.Iterator;
import java.util.Date;
import java.text.SimpleDateFormat;

public class PersonOperations{
    private static final SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");     
    
    Person person;
    PersonDao personDao;
    
    public PersonOperations(){
        person = new Person();
        personDao = new PersonDao();
    }
    /*
    public void setFirstName(String firstName){
        person.getName().setFirstName(firstName);
    }
    
    public void setLastName(String lastName){
        person.getName().setLastName(lastName);
    }
    
    public void setMiddleName(String middleName){
        person.getName().setMiddleName(middleName);
    }

    public void setTitle(String title){
        person.setTitle(Title.valueOf(title));     
    }
    
    public void setStreet(String street){
        person.getAddress().setStreet(street);
    }

     public void setBrgy(int brgy){
        person.getAddress().setBrgy(brgy);
    }
    
     public void setCity(String city){
        person.getAddress().setCity(city);
    }
    
     public void setZip(int zip){
        person.getAddress().setZip(zip);
    }

    public void setGwa(double gwa){
        person.setGwa(gwa);    
    }    
    
    public void setBirthDate(Date birthDate){
        person.setBirthDate(birthDate);    
    }
     */
    public void setDateHired(Date dateHired){
        person.setDateHired(dateHired);    
    }
   
    public void setEmployed(char employed){
        switch(employed) {
            case 'Y' :  person.setEmployed(true); 
                        break;
            case 'N' : person.setEmployed(false); 
                       break;        
        }    
    }
    
    public boolean idExist(int id) {
       return personDao.checkPersonId(id);     
    }
    
    public String printTitleList(){
        StrBuilder strBuilder = new StrBuilder();
        for (Title t : Title.values()) {
            strBuilder.append("\n"+ t.name());
        }
        return strBuilder.toString();   
    }    
    
    public boolean titleExist(String title){
        for (Title t : Title.values()) {
            if (t.name().equals(title)) {
                return true;
            }
        }
        return false;
    }
    
    public void savePerson(String firstName, String lastName, String middleName, String title, Date birthDate, String street, int brgy, String city, int zip, double gwa, char employed){
        person.getName().setFirstName(firstName);
        person.getName().setLastName(lastName);
        person.getName().setMiddleName(middleName);
        person.setTitle(Title.valueOf(title));     
        person.getAddress().setStreet(street);
        person.getAddress().setBrgy(brgy);
        person.getAddress().setCity(city);
        person.getAddress().setZip(zip);
        person.setGwa(gwa);    
        person.setBirthDate(birthDate);     
        setEmployed(employed);   
        personDao.add(person);        
    }
    
    public void update(){
      personDao.update(person); 
    }

    public boolean delete(int id) {
        return personDao.deleteById(id);  
    }

    public boolean isDuplicate(String firstName, String lastName, String middleName){
        List personList = personDao.getList("Person"); 
        for (Iterator iterator1 = personList.iterator(); iterator1.hasNext();){
            Person persons = (Person) iterator1.next();
            if( persons.getName().getFirstName().equalsIgnoreCase(firstName)
                &&  persons.getName().getLastName().equalsIgnoreCase(lastName)
                &&  persons.getName().getMiddleName().equalsIgnoreCase(middleName)
              ) return true;
       }
       return false;
    }
    
    public String printPersonList(){
       StrBuilder strBuilder = new StrBuilder();
       List personList = personDao.getList("Person"); 
       strBuilder.append("\nID\tTITLE\tFIRST_NAME\tMIDDLE_NAME\tLAST_NAME\tBIRTHDATE\tSTREET\tBRGY\tCITY\\MUNICIPALITY\tZIP\tGWA\tEMPLOYED\tDATE_HIRED");
       for (Iterator iterator1 = personList.iterator(); iterator1.hasNext();){
         Person persons = (Person) iterator1.next();
         char employ = 'N';
         if(persons.getEmployed()) employ = 'Y';
           
         strBuilder.append("\n"+persons.getId() +
                           "\t"+persons.getTitle() +
                           "\t"+persons.getName().getFirstName() +
                           "\t"+persons.getName().getMiddleName() +
                           "\t"+persons.getName().getLastName() +
                           "\t"+formatter.format(persons.getBirthDate()) + 
                           "\t"+persons.getAddress().getStreet() + 
                           "\t"+persons.getAddress().getBrgy() + 
                           "\t"+persons.getAddress().getCity() + 
                           "\t"+persons.getAddress().getZip() + 
                           "\t"+persons.getGwa() + 
                           "\t"+employ + 
                           "\t");
        
        if(persons.getDateHired() != null){
            strBuilder.append(formatter.format(persons.getDateHired()));       
        }
       }
       return strBuilder.toString();   
    }
}

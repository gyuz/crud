package crud.core.service;

import crud.core.model.Person;
import crud.core.model.Contact;
import crud.core.model.Role;
import crud.core.model.Title;
import crud.core.dao.PersonDao;
import org.apache.commons.lang3.text.StrBuilder;
import java.util.List;
import java.util.Set;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Date;
import java.text.SimpleDateFormat;

public class PersonOperations{
    private static final SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");     
    
    private Person person;
    private PersonDao personDao;
    private Set<Role> roles;
    private Set<Contact> contacts;    

    public PersonOperations(){
        person = new Person();
        personDao = new PersonDao();
        roles = new HashSet<Role>();
        contacts = new HashSet<Contact>();
    }
    
    public Person getPerson(){
        return this.person;
    }
    
    public void setPerson(int id){
        this.person = personDao.getPersonById(id);
    }
    
    public void setDateHired(Date dateHired){
        person.setDateHired(dateHired);    
    }
   
    public boolean parseEmployed(char employed){
        if(employed == 'Y') return true;
        return false;  
    }
    
    public boolean idExist(int id) {
       this.person = personDao.getPersonById(id);
       System.out.println(person.getId());
       if (person != null) return true;
       return false;     
    }
    
    public boolean titleExist(String title){
        for (Title t : Title.values()) {
            if (t.name().equals(title)) {
                return true;
            }
        }
        return false;
    }
    
    public void addRole(Role role){
        roles.add(role);
    }
    
    public void addContact(Contact contact){
        contacts.add(contact);
    }
    
    public boolean contactExist(Contact contact){
        return contacts.contains(contact);
    }
    
    public void setContacts(){
        person.setContacts(contacts);
        update();
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
        person.setEmployed(parseEmployed(employed));              
    }
    
    public void createNewPerson(){
        personDao.add(person); 
    }
    
    public void update(){
        personDao.update(person);
    }
    
    public String getFirstName(){
       return person.getName().getFirstName();
    }

    public String getMiddleName(){
       return person.getName().getMiddleName();
    }
    
    public String getLastName(){
       return person.getName().getLastName(); 
    }

    public String getTitle(){
       return person.getTitle().toString(); 
    }

    public Date getBirthDate(){
       return person.getBirthDate();  
    }

    public Date getDateHired(){
       return person.getDateHired();  
    }
    
    public String getStreet(){
       return person.getAddress().getStreet();
    }
    
    public String getCity(){
       return person.getAddress().getCity();
    }

    public int getBrgy(){
       return person.getAddress().getBrgy();
    }

    public int getZip(){
       return person.getAddress().getZip(); 
    }
    
    public char getEmployed(){
       if(person.getEmployed()){
            return 'Y';
       }
       return 'N';
    }

    public double getGwa(){
        return person.getGwa();
    }

    public void delete(int id) {
        personDao.delete(person); 
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
    
    public String printTitleList(){
        StrBuilder strBuilder = new StrBuilder();
        for (Title t : Title.values()) {
            strBuilder.append("\n"+ t.name());
        }
        return strBuilder.toString();   
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

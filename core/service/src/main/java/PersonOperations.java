package crud.core.service;

import crud.core.model.Person;
import crud.core.model.Contact;
import crud.core.model.Role;
import crud.core.model.Title;
import crud.core.model.Types;
import crud.core.dao.PersonDao;
import crud.core.dao.RoleDao;
import org.apache.commons.lang3.text.StrBuilder;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import java.util.Collections;
import java.util.Date;
import java.text.SimpleDateFormat;

public class PersonOperations{
    private static final SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");     
    
    private Person person;
    private PersonDao personDao;
    private ContactOperations contactOps;
   
    public PersonOperations(){
        person = new Person();
        personDao = new PersonDao();
        contactOps = new ContactOperations();
    }
    
    public Person getPerson(){
        return this.person;
    }
    
    public PersonDao getPersonDao(){
        return this.personDao;    
    }    
    
    public boolean idExist(int id) {
       this.person = personDao.getPersonById(id);
       if (person != null) {
        return true;
       }
       
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
    
    public void savePerson(String firstName, String lastName, String middleName, String title, Date birthDate, String street, String brgy, String city, int zip, double gwa, char employed){
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
    
    public int getId(){
        return person.getId();    
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

    public String getBrgy(){
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
        personDao.closeSession();
    }

    public boolean isDuplicate(String firstName, String lastName, String middleName){
        PersonDao personDao2 = new PersonDao();
        List<Person> personList = personDao2.getList("Person where Name.firstName = '"+firstName.toUpperCase()+"' AND Name.lastName='"+lastName.toUpperCase()+"' AND Name.middleName='"+middleName.toUpperCase()+"'"); 
       if(personList.isEmpty()) return false;
       return true;
    }
    
    public String printTitleList(){
        StrBuilder strBuilder = new StrBuilder();
        for (Title t : Title.values()) {
            strBuilder.append("\n"+ t.name());
        }
        return strBuilder.toString();   
    }  
      
    public String printPersonList(int listChoice, int order){
       PersonDao personDao2 = new PersonDao();
       StrBuilder strBuilder = new StrBuilder();
       List<Person> personList = new ArrayList<Person>();
 
       if(listChoice == 1){
          personList = personDao2.getList("Person"); 
          if(order == 2) {
            Collections.sort(personList, new ReverseGwaComparator());
          } else {
            Collections.sort(personList, new GwaComparator());
          }
       } else if (listChoice == 2) {
          if (order == 1) {
            personList = personDao2.listAscending("Name.lastName");
          } else {
            personList = personDao2.listDescending("Name.lastName");
          }
       } else {
          if (order == 1){
            personList = personDao2.listAscending("dateHired");
          } else {
            personList = personDao2.listDescending("dateHired");
          }
       }
       
       for (Person persons : personList){
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

    public void closeSession(){
        personDao.closeSession();    
    }

    public boolean addRole(Role role){
        if(roleExistInSet(role)) return false;
        else {
            person.getRoles().add(role);
        }
        return true;
    }
    
    public void deleteRole(Role role){
        person.getRoles().remove(role); 
        personDao.update(person);    
    }

    public boolean roleExistInSet(Role role){
        return person.getRoles().contains(role);
    }

    public String printPersonRoleList(){
        Set<Role> roleSet = person.getRoles();
        StrBuilder strBuilder = new StrBuilder(); 
        for(Role r : roleSet){
          strBuilder.append("\n"+r.getRoleId() + "\t" + r.getRoleName());
        } 
        return strBuilder.toString();   
    }  
    
    public boolean contactExist(Contact contact){
        return person.getContacts().contains(contact);
    }

    public String getContactType(){
        return contactOps.getContactType();    
    }

    public boolean addContact(String type, String detail){
        contactOps.setContactDetails(type, detail, person);
        Contact contact = contactOps.contact;
        if(contactExist(contact)) return false;
        else {
             person.getContacts().add(contact);
        }
        return true;
    } 
    
    public void saveContact(){
            personDao.update(person);    
    }

    public boolean updateContact(String detail){
        Contact contact = contactOps.contact;
        person.getContacts().remove(contact);
        contactOps.setDetail(detail);
        contact = contactOps.contact;
        if(contactExist(contact)) return false;
        else {
            person.getContacts().add(contact);
            personDao.update(person);   
        }
        return true;
    }
    
    public void deleteContact(){
        person.getContacts().remove(contactOps.contact);
        contactOps.delete();
        personDao.update(person);   
    }

    public boolean contactIdExist(int id){
        Set<Contact> contactSet = person.getContacts();
        for(Contact c : contactSet){
          if(c.getContactId() == id) {
            contactOps.contact = c;
            return true;
          }
        } 
        return false;
    }
    
    public String printTypeList(){
        int ctr = 1;
        StrBuilder strBuilder = new StrBuilder();
        for (Types t : Types.values()) {
            strBuilder.append("\n("+(ctr++) +") " + t.name());
        }
        return strBuilder.toString();   
    }  
    
    public String printContactList(){
        Set<Contact> contactSet = person.getContacts();
        StrBuilder strBuilder = new StrBuilder(); 
        for(Contact c : contactSet){
          strBuilder.append("\n"+c.getContactId() + "\t\t" + c.getContactType() + "\t\t" + c.getDetails());
        } 
        return strBuilder.toString();   
    }
}

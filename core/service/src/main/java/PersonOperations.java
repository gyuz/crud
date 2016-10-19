package crud.core.service;

import crud.core.model.Person;
import crud.core.model.Contact;
import crud.core.model.Role;
import crud.core.model.Title;
import crud.core.model.Types;
import crud.core.dao.PersonDao;
import crud.core.dao.RoleDao;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import java.util.Map;
import java.util.HashMap;
import java.util.Collections;
import org.joda.time.LocalDate;

public class PersonOperations{   
    private Person person;
    private PersonDao personDao;
    private ContactOperations contactOps;
    public List<Integer> personIdList;
    public List<String> firstNameList;
    public List<String> lastNameList;
    public List<String> middleNameList;
    public List<Integer> personRoleIds;
    public List<Integer> personContactIds;
    public List<String> personRoleNames;
    public List<String> personContactTypes;
    public List<String> personContactDetails;
    public Map<Integer, LocalDate> dateHiredMap;
   
    public PersonOperations(){
        person = new Person();
        personDao = new PersonDao();
        contactOps = new ContactOperations();
    }
    /*
    public Person getPerson(){
        return this.person;
    }*/
    
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
    
    public void savePerson(String firstName, String lastName, String middleName, String title, LocalDate birthDate, String street, String brgy, String city, int zip, double gwa, char employed){
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
    
    public void setDateHired(LocalDate dateHired){
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

    public LocalDate getBirthDate(){
       return person.getBirthDate();  
    }

    public LocalDate getDateHired(){
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
        List<Person> personList = personDao2.getList("Person where name.firstName = '"+firstName.toUpperCase()+"' AND name.lastName='"+lastName.toUpperCase()+"' AND name.middleName='"+middleName.toUpperCase()+"'"); 
       if(personList.isEmpty()) {
         return false;
       }
       return true;
    }
    
    public List printTitleList(){
       List titleList = new ArrayList<String>();
        for (Title t : Title.values()) {
            titleList.add(t.name());
        }
        return titleList; 
    }  
      
    public void printPersonList(int listChoice, int order){
       PersonDao personDao2 = new PersonDao();
       List<Person> personList = new ArrayList<Person>();
       personIdList = new ArrayList<Integer>();
       firstNameList = new ArrayList<String>();
       lastNameList = new ArrayList<String>();
       middleNameList = new ArrayList<String>();
       dateHiredMap = new HashMap<Integer, LocalDate>();
 
       if(listChoice == 1){
            personList = personDao2.getList("Person"); 
            if(order == 1) {
                Collections.sort(personList);
            } else {
                Collections.sort(personList, Collections.reverseOrder());
            }
       } else if (listChoice == 2) {
            if (order == 1) {
                personList = personDao2.listAscending("name.lastName");
            } else {
                personList = personDao2.listDescending("name.lastName");
            }
       } else if (listChoice == 3) {
            if (order == 1){
                personList = personDao2.listAscending("dateHired");
            } else {
                personList = personDao2.listDescending("dateHired");
            }
       } else{
            personList = personDao2.listAscending("id");
       }
       
       for (Person persons : personList){
            char employ = 'N';
            if(persons.getEmployed()) {
                employ = 'Y';
            }  
            personIdList.add(persons.getId());
            firstNameList.add(persons.getName().getFirstName());
            lastNameList.add(persons.getName().getLastName());
            middleNameList.add(persons.getName().getMiddleName());
            
            if(persons.getDateHired() != null){
                dateHiredMap.put(person.getId(), person.getDateHired());   
            }
       }
    }

    public void closeSession(){
        personDao.closeSession();    
    }

    public boolean addRole(Role role){
        if(roleExistInSet(role)) { 
            return false;
        } else {
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

    public void printPersonRoleList(){
        Set<Role> roleSet = person.getRoles();
        personRoleIds = new ArrayList<Integer>();
        personRoleNames = new ArrayList<String>();
        for(Role r : roleSet){
          personRoleIds.add(r.getRoleId());
          personRoleNames.add(r.getRoleName());
        }  
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
        if(contactExist(contact)) {
            return false;
        } else {
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
        if(contactExist(contact)) {
            return false;
        } else {
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
    
    public List printTypeList(){
        int ctr = 1;
        List typeList = new ArrayList<String>();
        for (Types t : Types.values()) {
            typeList.add(t.name());
        }
        return typeList; 
    }  
    
    public void printContactList(){
        Set<Contact> contactSet = person.getContacts();
        personContactIds = new ArrayList<Integer>();
        personContactTypes = new ArrayList<String>();
        personContactDetails = new ArrayList<String>();
        for(Contact c : contactSet){
          personContactIds.add(c.getContactId());
          personContactTypes.add(c.getContactType().name()); 
          personContactDetails.add(c.getDetails());
        }   
    }
}

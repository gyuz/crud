package crud.core.service;

import crud.core.model.Person;
import java.util.Comparator;

public class ReverseGwaComparator implements Comparator<Person>
{
    public int compare(Person o1, Person o2)
    {
       return Double.compare(o2.getGwa(),o1.getGwa());
   }
}

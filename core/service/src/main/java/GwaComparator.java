package crud.core.service;

import crud.core.model.Person;
import java.util.Comparator;

public class GwaComparator implements Comparator<Person>
{
    public int compare(Person o1, Person o2)
    {
       return Double.compare(o1.getGwa(),o2.getGwa());
   }
}

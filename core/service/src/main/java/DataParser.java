package crud.core.service;

import org.joda.time.LocalDate;

public class DataParser{
     public int stringToInt(String value){
        int n = 0;
        try{
            n = Integer.parseInt(value);
        } catch(NumberFormatException ne) {
            n = 0;
        }   
        return n;
    }

}

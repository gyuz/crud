package crud.app;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.List;
import crud.core.service.PersonOperations;

public class PersonDetails extends HttpServlet {
    private PersonOperations personOps = new PersonOperations();

    public void doGet(HttpServletRequest request,
                  HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter(); 
        
        personOps.printPersonList(4,1);
        List<Integer> personIds = personOps.personIdList;
        List<String> firstNames = personOps.firstNameList;
        List<String> lastNames = personOps.lastNameList;
        List<String> middleNames = personOps.middleNameList;
        
        out.println("<title>Crud Application</title>");
        out.println("<h1>Person</h1>");
        out.println("<form action='PersonOps' method='POST'>");
        out.println("<button type='submit' name='action' value='CREATE'>CREATE NEW</button><br><br>");
        out.println("Search Person:<br>");
        out.println("Choose Person: <select name='personId'>");
        for(int i = 0; i < personIds.size(); i++){
            out.println("<option value='"+personIds.get(i)+"'>"+ personIds.get(i) + " - " + firstNames.get(i) + " " + middleNames.get(i) + " " + lastNames.get(i) + "</option>");
        }
        out.println("</select>");        
        out.println("<button type='submit' name='action' value='SEARCH'>SEARCH</button>");
        out.println("<button type='submit' name='action' value='DELETE'>DELETE SELECTED PERSON</button><br><br>");
        out.println("View Person List by:<br>");
        out.println("<input type='radio' name='list' value='1'>GWA<br>");
        out.println("<input type='radio' name='list' value='2'>Last Name<br>");
        out.println("<input type='radio' name='list' value='3'>Date Hired<br>");
        out.println("<input type='radio' name='list' value='4' checked>Person ID<br>");
        out.println("Sort By: <br>");
        out.println("<input type='radio' name='order' value='1'>Ascending");
        out.println("<input type='radio' name='order' value='2'>Descending<br>");
        out.println("<input type='submit' name='action' value='LIST'><br><br>");
        out.println("<button type='submit' name='action' value='BACK'>BACK TO MAIN</button>");
        out.println("</form>");
    }
    
}

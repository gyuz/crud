package crud.app;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.List;
import crud.core.service.PersonOperations;
import crud.core.service.DataParser;

public class PersonOps extends HttpServlet {
    private PersonOperations personOps;
    private DataParser dataParser;
    
    public void doPost(HttpServletRequest request,
                  HttpServletResponse response)
    throws ServletException, IOException {
        int id = 0;
        String action = request.getParameter("action");
        String personId = request.getParameter("id");
        String list = request.getParameter("list");
        String order = request.getParameter("order");
        personOps = new PersonOperations();
        dataParser = new DataParser();
        
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        RequestDispatcher dispatcher;
        
        if ("CREATE".equals(action)) {
            response.sendRedirect("CreatePerson");
        } else if ("SEARCH".equals(action)) {
            dispatcher = request.getRequestDispatcher("UpdatePerson");
            dispatcher.forward(request, response);
            /*
            id = dataParser.stringToInt(personId);            
            if(id != 0){
                if(personOps.idExist(id)) {
                    dispatcher = request.getRequestDispatcher("/person.html");
                    dispatcher.forward(request, response);
                } else {
                    out.println("ID does not Exist!");    
                }
            } else {
                  out.println("Invalid ID");
            }*/  
        } else if ("LIST".equals(action)) {
            personOps.printPersonList(dataParser.stringToInt(list), dataParser.stringToInt(order));
            List<Integer> personIds = personOps.personIdList;
            List<String> firstNames = personOps.firstNameList;
            out.println("<table border='1'>");
            out.println("<tr><td>PERSON ID</td><td>FIRST NAME</td></tr>");
            for(int i = 0; i < personIds.size(); i++){
                out.println("<tr><td>" + personIds.get(i) + "</td>");
                out.println("<td>" + firstNames.get(i) + "</td></tr>");
            }
            out.println("</table>");
        } else if ("DELETE".equals(action)) {
             id = dataParser.stringToInt(personId);
             if(id != 0){
                if(personOps.idExist(id)) {
                    personOps.delete(id);
                } else {
                    out.println("ID does not Exist!");    
                }
             } else {
                  out.println("Invalid ID");
             } 
        }
    }
    
}

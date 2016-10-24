package crud.app;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import org.joda.time.LocalDate;
import crud.core.service.PersonOperations;
import crud.core.service.RoleOperations;
import crud.core.service.DataParser;

public class NewPerson extends HttpServlet {

    public void doGet(HttpServletRequest request,
                  HttpServletResponse response)
                throws ServletException, IOException {
        createPersonForm(response);
    }
    
    public void doPost(HttpServletRequest request,
                  HttpServletResponse response)
                throws ServletException, IOException {
        createPersonForm(response);
    }
    
    protected void createPersonForm(HttpServletResponse response)
                throws ServletException, IOException{
        PersonOperations personOps = new PersonOperations();
        RoleOperations roleOps = new RoleOperations();
        roleOps.printRoleList();
        List<String> typeList = personOps.printTypeList();
        List<Integer> masterRoleList = roleOps.getRoleIdList();
        List<String> masterRoleNameList = roleOps.getRoleNameList();
        List<String> titleList = personOps.printTitleList();
        
        response.setContentType("text/html");
        PrintWriter out = response.getWriter(); 
        out.println("<title>Crud Application</title>"+
                    "<h1>Create Person</h1>");       
   
        out.println("<form action='PersonOps' name='personForm' method='POST'>"+
                    "Title: <select name='title' required>"+
                    "<option disabled selected value> -- Title -- </option>");
        for(int i = 0; i < titleList.size(); i++){
            out.println("<option value='"+titleList.get(i)+"'>"+ titleList.get(i) + "</option>");
        }
        
        out.println("</select><br>"+
                    "First Name: <input type='text' name='firstName' required><br>"+
                    "Middle Name: <input type='text' name='middleName'><br>"+
                    "Last Name:  <input type='text' name='lastName' required><br>"+
                    "BirthDate: <input type='date' name='birthDate' required placeholder='MM/DD/YYYY'><br>"+
                    "Street: <input type='text' name='street' required><br>"+
                    "Brgy: <input type='text' name='brgy' required><br>"+
                    "City:  <input type='text' name='city' required><br>"+
                    "Zip: <input type='number' name='zip' required><br>"+
                    "GWA: <input type='text' name='gwa'><br>"+
                    "Employed: <select name='employed' required>"+
                                "<option disabled selected value> -- Status -- </option>"+
                                "<option value='Y'>Y</option>"+
                                "<option value='N'>N</option>"+
                    "</select><br>");
                   
        out.println("Date Hired: <input type='date' name='dateHired' placeholder='MM/DD/YYYY'>"); 
        out.println("<br><br>Add Contacts:<br>"+
                    "<table>");
        for(int j = 0; j<3; j++){            
            out.println("<tr>"+
                        "<td><select name='newContactType'>"+
                        "<option disabled selected value> -- Contact Type -- </option>");
            for(int i = 0; i < typeList.size(); i++){
                out.println("<option value='"+typeList.get(i)+"'>"+ typeList.get(i) + "</option>");
            }
            out.println("</select></td>"+
                        "<td><input type='text' name='newContactDetail' placeholder='number/email' size=20></td>");
        }
        out.println("</table><br>");
        out.println("Add Roles:<br>"+
                    "<table>");
        for(int j = 0; j<3; j++){  
            out.println("<tr>"+
                    "<td><select name='newRoleId'>"+
                    "<option disabled selected value> -- Role -- </option>");
            for(int i = 0; i < masterRoleList.size(); i++){
                out.println("<option value='"+masterRoleList.get(i)+"'>"+ masterRoleList.get(i) + " - "+ masterRoleNameList.get(i) +"</option>");
                        }
            out.println("</select></td></tr>");
        }
        out.println("</table><br>");
        out.println("<br><button type='submit' name='action' value='CREATE'>SAVE</button><br>");
        out.println("</form>");
        out.println("<form action='PersonDispatch' method='GET'>"+
                    "<button type='submit' name='action' value='BACKP'>BACK TO PERSON</button>"+
                    "</form>");
        out.close();
    }
   
    
}
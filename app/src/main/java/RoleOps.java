package crud.app;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.List;
import crud.core.service.RoleOperations;
import crud.core.service.DataParser;

public class RoleOps extends HttpServlet {
    private RoleOperations roleOps;
    private DataParser dataParser;
    
    public void doPost(HttpServletRequest request,
                  HttpServletResponse response)
    throws ServletException, IOException {
        String action = request.getParameter("action");
        String roleId = request.getParameter("roleId");
        String roleName = request.getParameter("roleName");
        roleOps = new RoleOperations();
        dataParser = new DataParser();
        int id = 0;
        
        response.setContentType("text/html");
        PrintWriter out = response.getWriter(); 
        
        if ("BACK".equals(action)) {
            response.sendRedirect("../../index.html");
        } else {
            if ("UPDATE".equals(action)) {
                id = dataParser.stringToInt(roleId);            
                if(id != 0 ){
                    if(roleOps.idExist(id)){
                        if(!roleOps.update(id, roleName)){
                            out.println("Update failed! Role already exist");
                        } else {
                            out.println("Update Succesful!<br>Role ID: "+id+"<br>Role Name: "+roleName);
                        } 
                    } else {
                        out.println("ID does not exist");
                    }   
                } else {
                  out.println("Invalid ID");
                } 
            } else if ("CREATE".equals(action)) {
                if(!roleOps.isDuplicate(roleName)) {
                    roleOps.addRoleName(roleName);
                    out.println("Role "+roleName+" created.");
                } else {
                    out.println("Creation failed. Role already exist");
                }
            } else if ("DELETE".equals(action)) {
                id = dataParser.stringToInt(roleId);         
                if(id != 0){
                    if(!roleOps.delete(id)){
                         out.println("Role ID could not be deleted!<br>ID may not exist or role is still associated with a person.");
                    } else {
                        out.println("ID "+id+" deleted");
                    }   
                } else {
                  out.println("Invalid ID");
                } 
            } else if("LIST".equals(action)) {
                roleOps.printRoleList();
                List<Integer> roleIds = roleOps.roleIdList;
                List<String> roleNames = roleOps.roleNameList;
                out.println("<table>");
                out.println("<tr><td>ROLD ID</td><td>ROLE NAME</td></tr>");
                for(int i = 0; i < roleIds.size(); i++){
                    out.println("<tr><td>" + roleIds.get(i) + "</td>");
                    out.println("<td>" + roleNames.get(i) + "</td></tr>");
                }
                out.println("</table>");
            }
            out.println("<br><br>");
            out.println("<form action='RoleDetails' method='GET'>");
            out.println("<button type='submit' name='action' value='BACK'>BACK TO ROLES</button>");
            out.println("</form>");
        }
    }
}

package crud.app;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.List;
import crud.core.service.RoleOperations;

public class RoleDetails extends HttpServlet {
    private RoleOperations roleOps = new RoleOperations();

    public void doGet(HttpServletRequest request,
                  HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter(); 
        
        roleOps.printRoleList();
        List<Integer> roleIds = roleOps.roleIdList;
        List<String> roleNames = roleOps.roleNameList;
        out.println("<title>Crud Application</title>");
        out.println("<h1>Roles</h1>");
        out.println("<form action='RoleOps' method='POST'>");
        out.println("Create/Update/Delete/List Role:<br><br>");
        out.println("Choose Role: <select name='roleId'>");
        for(int i = 0; i < roleIds.size(); i++){
            out.println("<option value='"+roleIds.get(i)+"'>"+ roleIds.get(i) +" - "+ roleNames.get(i) +"</option>");
        }
        out.println("</select><br>");
        out.println("Enter new role name: <input type='text' name='roleName' size=20 placeholder='ROLENAME'><br><br>");
        out.println("<button type='submit' name='action' value='CREATE'>CREATE</button>");
        out.println("<button type='submit' name='action' value='UPDATE'>UPDATE</button>");
        out.println("<button type='submit' name='action' value='LIST'>LIST ROLES</button>");
        out.println("<button type='submit' name='action' value='DELETE'>DELETE</button><br><br>");
        out.println("<button type='submit' name='action' value='BACK'>BACK TO MAIN</button>");
        out.println("</form>");
    }
    
}

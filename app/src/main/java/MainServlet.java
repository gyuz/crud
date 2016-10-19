package crud.app;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.io.IOException;

import crud.core.service.InitializeSessionFactory;

public class MainServlet extends HttpServlet {
    private InitializeSessionFactory crudSession;
    public void init() throws ServletException{
        if (crudSession == null) {
            crudSession = new InitializeSessionFactory();
            crudSession.startFactory();
        }
    }

    public void doGet(HttpServletRequest request,
                  HttpServletResponse response)
    throws ServletException, IOException {
        String action = request.getParameter("action");
        
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        if ("PERSON".equals(action)) {
            response.sendRedirect("PersonDetails");
        } else if ("ROLE".equals(action)) {
            response.sendRedirect("RoleDetails");
        }
    }
    
}

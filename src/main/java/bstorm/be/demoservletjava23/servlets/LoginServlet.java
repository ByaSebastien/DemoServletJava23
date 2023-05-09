package bstorm.be.demoservletjava23.servlets;

import java.io.*;

import bstorm.be.demoservletjava23.domain.dtos.ConnectedUserDTO;
import bstorm.be.demoservletjava23.domain.entities.User;
import bstorm.be.demoservletjava23.domain.forms.UserLoginForm;
import bstorm.be.demoservletjava23.exceptions.EntityNotFoundException;
import bstorm.be.demoservletjava23.exceptions.InvalidPasswordUserException;
import bstorm.be.demoservletjava23.services.UserService;
import bstorm.be.demoservletjava23.services.UserServiceImpl;
import bstorm.be.demoservletjava23.utils.DatabaseConnectionManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "login", urlPatterns = "/login", loadOnStartup = 1)
public class LoginServlet extends HttpServlet {

    private UserServiceImpl userService = new UserServiceImpl();

    @Override
    public void init() {
        String connectionString = this.getServletContext().getInitParameter("CONNECTION_STRING");
        String dbUSer = this.getServletContext().getInitParameter("DB_USER");
        String dbPassword = this.getServletContext().getInitParameter("DB_PASSWORD");
        System.out.println(connectionString);
        System.out.println(dbUSer);
        System.out.println(dbPassword);
        DatabaseConnectionManager.initDatabaseContext(connectionString,dbUSer,dbPassword);
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setAttribute("login", "");
        request.getRequestDispatcher("WEB-INF/pages/login.jsp").forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String login = request.getParameter("login");
        String password = request.getParameter("password");
        UserLoginForm userForm = new UserLoginForm(login, password);

        try {
            User user = userService.login(userForm.toEntity());
            request.getSession(true).setAttribute("connectedUser", ConnectedUserDTO.fromEntity(user));
            response.sendRedirect(request.getContextPath() + "/");
            return;
        } catch (EntityNotFoundException e){
            request.setAttribute("errorMessage","Login non valide");
        } catch (InvalidPasswordUserException e){
            request.setAttribute("errorMessage","Password non valide");
        } catch (RuntimeException e){
            request.setAttribute("errorMessage","Veuillez réessayer");
        }
        request.setAttribute("login", login);
        request.getRequestDispatcher("WEB-INF/pages/login.jsp").forward(request, response);

    }

    @Override
    public void destroy() {
    }
}
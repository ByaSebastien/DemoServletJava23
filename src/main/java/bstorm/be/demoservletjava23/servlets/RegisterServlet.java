package bstorm.be.demoservletjava23.servlets;

import bstorm.be.demoservletjava23.domain.dtos.ConnectedUserDTO;
import bstorm.be.demoservletjava23.domain.entities.User;
import bstorm.be.demoservletjava23.domain.forms.UserRegisterForm;
import bstorm.be.demoservletjava23.services.UserService;
import bstorm.be.demoservletjava23.services.UserServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "register", urlPatterns = "/register")
public class RegisterServlet extends HttpServlet {

    UserService userService = new UserServiceImpl();

    @Override
    public void init() {
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.getRequestDispatcher("WEB-INF/pages/register.jsp").forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");
        UserRegisterForm userForm = new UserRegisterForm(username, email, password, confirmPassword);

        try {
            if(password.equals(confirmPassword)){

                User user = userService.register(userForm.toEntity());
                request.getSession(true).setAttribute("connectedUser", ConnectedUserDTO.fromEntity(user));
                response.sendRedirect(request.getContextPath() + "/");
                return;
            }
            request.setAttribute("errorMessage", "Password not the same");
        } catch (RuntimeException e) {
            request.setAttribute("errorMessage", e.getMessage());
        }
        request.setAttribute("username", username);
        request.setAttribute("email", email);
        request.getRequestDispatcher("WEB-INF/pages/register.jsp").forward(request, response);

    }

    @Override
    public void destroy() {
    }
}
package bstorm.be.demoservletjava23.servlets;

import bstorm.be.demoservletjava23.domain.entities.Author;
import bstorm.be.demoservletjava23.domain.forms.AuthorForm;
import bstorm.be.demoservletjava23.domain.forms.BookForm;
import bstorm.be.demoservletjava23.services.AuthorService;
import bstorm.be.demoservletjava23.services.AuthorServiceImpl;
import bstorm.be.demoservletjava23.services.BookService;
import bstorm.be.demoservletjava23.services.BookServiceImpl;
import bstorm.be.demoservletjava23.utils.DatabaseConnectionManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "addBook", urlPatterns = "/addBook")
public class AddBookServlet extends HttpServlet {

    AuthorService authorService = new AuthorServiceImpl();
    BookService bookService = new BookServiceImpl();

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<Author> authors = authorService.getMany();
        request.setAttribute("authors",authors);

        request.getRequestDispatcher("WEB-INF/pages/bookForm.jsp").forward(request,response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        BookForm bookForm;
        String title = request.getParameter("title");
        String description = request.getParameter("description");
        request.setAttribute("title",title);
        request.setAttribute("description",description);
        if(request.getParameter("wantToAddAuthor") != null){
            String firstname = request.getParameter("firstname");
            String lastname = request.getParameter("lastname");
            String pseudo = request.getParameter("pseudo");
            request.setAttribute("firstname",firstname);
            request.setAttribute("lastname",lastname);
            request.setAttribute("pseudo",pseudo);
            AuthorForm authorForm = new AuthorForm(firstname,lastname,pseudo);
            bookForm = new BookForm(title,description,authorForm);
        }else {
            if(request.getParameter("authorId") == ""){

                doGet(request,response);
            }
            int authorId = Integer.parseInt(request.getParameter("authorId"));
            request.setAttribute("authorId",authorId);
            bookForm = new BookForm(title,description,authorId);
        }

        try{

            bookService.add(bookForm.toEntity());
            response.sendRedirect(request.getContextPath() + "/book");
        }catch (RuntimeException e){
            System.out.println(e.getMessage());
            try {
                DatabaseConnectionManager.closeConnection();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            doGet(request,response);
        }

    }

}
package bstorm.be.demoservletjava23.servlets;

import bstorm.be.demoservletjava23.domain.entities.Author;
import bstorm.be.demoservletjava23.domain.entities.Book;
import bstorm.be.demoservletjava23.domain.forms.AuthorForm;
import bstorm.be.demoservletjava23.domain.forms.BookForm;
import bstorm.be.demoservletjava23.services.AuthorService;
import bstorm.be.demoservletjava23.services.AuthorServiceImpl;
import bstorm.be.demoservletjava23.services.BookService;
import bstorm.be.demoservletjava23.services.BookServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "modifyBook", urlPatterns = "/modifyBook")
public class ModifyBookServlet extends HttpServlet {

    AuthorService authorService = new AuthorServiceImpl();
    BookService bookService = new BookServiceImpl();

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        request.setAttribute("id",id);
        List<Author> authors = authorService.getMany();
        request.setAttribute("authors",authors);
        try{
            Book book = bookService.getOne(id);
            request.setAttribute("title",book.getTitle());
            request.setAttribute("description",book.getDescription());
            request.setAttribute("authorId",book.getAuthorId());
            request.getRequestDispatcher("WEB-INF/pages/bookForm.jsp").forward(request,response);
        }catch (RuntimeException e){

            System.out.println("tu fou de mon gueulle?");
            response.sendRedirect(request.getContextPath() + "/login");
        }

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        BookForm bookForm;
        String title = request.getParameter("title");
        String description = request.getParameter("description");
        request.setAttribute("title",title);
        request.setAttribute("description",description);

        if(request.getParameter("authorId") == ""){

            doGet(request,response);
        }
        int authorId = Integer.parseInt(request.getParameter("authorId"));
        bookForm = new BookForm(title,description,authorId);


        try{
            int id = Integer.parseInt(request.getParameter("id"));
            bookService.update(id,bookForm.toEntity());
            response.sendRedirect(request.getContextPath() + "/book");
        }catch (RuntimeException e){
            System.out.println(e.getMessage());
            doGet(request,response);
        }
    }
}
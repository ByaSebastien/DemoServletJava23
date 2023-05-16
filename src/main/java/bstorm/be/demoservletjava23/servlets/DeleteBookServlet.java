package bstorm.be.demoservletjava23.servlets;

import bstorm.be.demoservletjava23.services.BookService;
import bstorm.be.demoservletjava23.services.BookServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "deleteBook", urlPatterns = "/deleteBook")
public class DeleteBookServlet extends HttpServlet {

    BookService bookService = new BookServiceImpl();

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));

        try{
            bookService.delete(id);
        }catch(RuntimeException e){

            System.out.println(e.getMessage());
        }
        response.sendRedirect(request.getContextPath() + "/book");
    }
}
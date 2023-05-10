package bstorm.be.demoservletjava23.servlets;

import bstorm.be.demoservletjava23.domain.dtos.BookDTO;
import bstorm.be.demoservletjava23.domain.entities.Book;
import bstorm.be.demoservletjava23.services.BookService;
import bstorm.be.demoservletjava23.services.BookServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(name = "book", urlPatterns = "/book")
public class BookServlet extends HttpServlet {

    BookService bookService = new BookServiceImpl();

    @Override
    public void init() {

    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<BookDTO> books = bookService.getMany()
                .stream()
                .map(BookDTO::fromEntity)
                .collect(Collectors.toList());
        request.setAttribute("books",books);
        request.getRequestDispatcher("WEB-INF/pages/book.jsp").forward(request,response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    public void destroy() {

    }
}
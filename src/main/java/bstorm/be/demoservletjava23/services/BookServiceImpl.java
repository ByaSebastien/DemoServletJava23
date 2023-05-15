package bstorm.be.demoservletjava23.services;

import bstorm.be.demoservletjava23.domain.entities.Book;
import bstorm.be.demoservletjava23.repositories.AuthorRepository;
import bstorm.be.demoservletjava23.repositories.AuthorRepositoryImpl;
import bstorm.be.demoservletjava23.repositories.BookRepository;
import bstorm.be.demoservletjava23.repositories.BookRepositoryImpl;
import bstorm.be.demoservletjava23.utils.DatabaseConnectionManager;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class BookServiceImpl implements BookService{

    BookRepository bookRepository = new BookRepositoryImpl();
    AuthorRepository authorRepository = new AuthorRepositoryImpl();

    @Override
    public List<Book> getMany() {

        return bookRepository.getMany();
    }

    @Override
    public Book add(Book book) {

        try {
            Connection conn = DatabaseConnectionManager.openConnection();
            conn.setAutoCommit(false);
            if(book.getAuthor() != null){
                int id = authorRepository.add(book.getAuthor()).getId();
                book.setAuthorId(id);
            }

            Book newBook = bookRepository.add(book);

            DatabaseConnectionManager.closeConnection();
            conn.commit();
            conn.setAutoCommit(true);
            return newBook;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}

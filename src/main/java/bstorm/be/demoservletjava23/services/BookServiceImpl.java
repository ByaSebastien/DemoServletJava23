package bstorm.be.demoservletjava23.services;

import bstorm.be.demoservletjava23.domain.entities.Book;
import bstorm.be.demoservletjava23.repositories.BookRepository;
import bstorm.be.demoservletjava23.repositories.BookRepositoryImpl;

import java.util.List;

public class BookServiceImpl implements BookService{

    BookRepository bookRepository = new BookRepositoryImpl();

    @Override
    public List<Book> getMany() {

        return bookRepository.getMany();
    }
}

package bstorm.be.demoservletjava23.services;

import bstorm.be.demoservletjava23.domain.entities.Book;

import java.util.List;

public interface BookService {

    List<Book> getMany();

    Book getOne(Integer id);

    Book add(Book book);

    boolean update(Integer id,Book book);
}

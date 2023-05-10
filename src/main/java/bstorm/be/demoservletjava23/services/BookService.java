package bstorm.be.demoservletjava23.services;

import bstorm.be.demoservletjava23.domain.entities.Book;

import java.util.List;

public interface BookService {

    List<Book> getMany();
}

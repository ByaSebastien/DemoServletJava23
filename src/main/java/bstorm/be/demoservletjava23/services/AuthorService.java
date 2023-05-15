package bstorm.be.demoservletjava23.services;

import bstorm.be.demoservletjava23.domain.entities.Author;

import java.util.List;

public interface AuthorService {

    List<Author> getMany();
}

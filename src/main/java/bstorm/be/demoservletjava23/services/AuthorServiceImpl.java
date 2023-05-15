package bstorm.be.demoservletjava23.services;

import bstorm.be.demoservletjava23.domain.entities.Author;
import bstorm.be.demoservletjava23.repositories.AuthorRepository;
import bstorm.be.demoservletjava23.repositories.AuthorRepositoryImpl;

import java.util.List;

public class AuthorServiceImpl implements AuthorService{

    AuthorRepository authorRepository;

    public AuthorServiceImpl() {
        this.authorRepository = new AuthorRepositoryImpl();
    }

    @Override
    public List<Author> getMany() {

        return authorRepository.getMany();
    }
}

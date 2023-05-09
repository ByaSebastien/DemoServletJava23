package bstorm.be.demoservletjava23.services;

import bstorm.be.demoservletjava23.domain.entities.User;
import bstorm.be.demoservletjava23.domain.forms.UserLoginForm;
import bstorm.be.demoservletjava23.exceptions.EntityNotFoundException;
import bstorm.be.demoservletjava23.exceptions.InvalidPasswordUserException;
import bstorm.be.demoservletjava23.repositories.UserRepository;
import bstorm.be.demoservletjava23.repositories.UserRepositoryImpl;
import org.mindrot.jbcrypt.BCrypt;

public class UserServiceImpl implements UserService{

    private UserRepository userRepository;

    public UserServiceImpl() {
        this.userRepository = new UserRepositoryImpl();
    }

    @Override
    public User login(User newUser) {

        String login = newUser.getUsername();

        User user = userRepository.findByLogin(login);

        if (user == null) {
            throw new EntityNotFoundException();
        }

        if (!BCrypt.checkpw(newUser.getPassword(), user.getPassword())) {
            throw new InvalidPasswordUserException();
        }

        return user;
    }

    @Override
    public User register(User user) {

        if (user.getUsername().trim().equals(""))
            throw new RuntimeException();
        if (user.getPassword().trim().equals(""))
            throw new RuntimeException();

        user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));

        return userRepository.add(user);
    }
}

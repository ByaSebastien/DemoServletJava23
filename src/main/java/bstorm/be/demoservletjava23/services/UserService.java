package bstorm.be.demoservletjava23.services;

import bstorm.be.demoservletjava23.domain.entities.User;
import bstorm.be.demoservletjava23.domain.forms.UserLoginForm;

public interface UserService {

    User login (User newUser);

    User register(User user);
}

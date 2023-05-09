package bstorm.be.demoservletjava23.repositories;

import bstorm.be.demoservletjava23.domain.entities.User;

public interface UserRepository extends BaseRepository<User> {

    User findByLogin(String login);
}

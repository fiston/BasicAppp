package rw.viden.basiapp.service;


import rw.viden.basiapp.models.User;

import java.util.Collection;
import java.util.Optional;

public interface UserService {

    Optional<User> getUserById(long id);

    Optional<User> getUserByUsername(String username);

    Collection<User> getAllUsers();

    User create(User form);


}

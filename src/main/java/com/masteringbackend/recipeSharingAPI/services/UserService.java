package com.masteringbackend.recipeSharingAPI.services;

import com.masteringbackend.recipeSharingAPI.entities.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public interface UserService {

    //methods' names show their functionality
    User registerUser(User user);
    User getUserById(Long id);
    Optional<User> getUserByEmail(String email);
    Optional<User> getUserByUsername(String username);

    User updateUser(Long id, User updatedUser);
    void deleteUser(Long id);
    User assignRolesToUser(Long userId, List<String> roles);


}

package com.masteringbackend.recipeSharingAPI.services.impls;

import com.masteringbackend.recipeSharingAPI.entities.Role;
import com.masteringbackend.recipeSharingAPI.entities.User;
import com.masteringbackend.recipeSharingAPI.repos.RoleRepository;
import com.masteringbackend.recipeSharingAPI.repos.UserRepository;
import com.masteringbackend.recipeSharingAPI.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepo;
    private final RoleRepository roleRepo;


    @Override
    public User registerUser(User user) {
        // encrypt password
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));

        // save new user
        return userRepo.save(user);
    }

    @Override
    public User getUserById(Long id) {
        // find user by id
        return userRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        // find user by email
        return userRepo.findByEmail(email);
    }

    @Override
    public Optional<User> getUserByUsername(String username) {
        return userRepo.findByUsername(username);
    }

    @Override
    public User updateUser(Long id, User updatedUser) {
        // check if user exists
        User existingUser = userRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));

        // update existing user
        existingUser.setUsername(updatedUser.getUsername());
        existingUser.setEmail(updatedUser.getEmail());

        // if the password is changed, update password
        if (updatedUser.getPassword() != null && !updatedUser.getPassword().isEmpty()) {
            existingUser.setPassword(new BCryptPasswordEncoder().encode(updatedUser.getPassword()));
        }

        return userRepo.save(existingUser);
    }

    @Override
    public void deleteUser(Long id) {
        // delete user
        if (!userRepo.existsById(id)) {
            throw new RuntimeException("User not found with id: " + id);
        }
        userRepo.deleteById(id);
    }

    @Override
    public User assignRolesToUser(Long userId, List<String> roles) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));

        Set<Role> roleSet = roles.stream()
                .map(roleName -> roleRepo.findByName(roleName)
                        .orElseThrow(() -> new RuntimeException("Role not found: " + roleName)))
                .collect(Collectors.toSet());

        user.setRoles(roleSet);
        return userRepo.save(user);    }
}

package com.masteringbackend.recipeSharingAPI.controllers;

import com.masteringbackend.recipeSharingAPI.entities.Role;
import com.masteringbackend.recipeSharingAPI.entities.User;
import com.masteringbackend.recipeSharingAPI.repos.RoleRepository;
import com.masteringbackend.recipeSharingAPI.services.UserService;
import jakarta.annotation.security.PermitAll;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;
    private final RoleRepository roleRepo;

    @PostMapping("/register")
    @PermitAll
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        User registeredUser = userService.registerUser(user);
        return new ResponseEntity<>(registeredUser, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        User user = userService.getUserById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/email")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<User> getUserByEmail(@RequestParam String email) {
        Optional<User> user = userService.getUserByEmail(email);
        return user.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    @PreAuthorize("#id == authentication.principal.id or hasRole('ADMIN')")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User updatedUser) {
        User user = userService.updateUser(id, updatedUser);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/roles")
    public ResponseEntity<Role> createRole(@RequestBody Role role) {
        // If any existing role matches new role name, then return error

        Optional<Role> existingRole = roleRepo.findByName(role.getName());
        if (existingRole.isPresent()) {
            return new ResponseEntity<>(HttpStatus.CONFLICT); // 409 Conflict
        }


        Role savedRole = roleRepo.save(role);
        return new ResponseEntity<>(savedRole, HttpStatus.CREATED); // 201 Created
    }

    @PutMapping("/{id}/roles")
    @PreAuthorize("hasRole('ADMIN')") // Sadece admin kullanıcılar erişebilir
    public ResponseEntity<User> assignRolesToUser(
            @PathVariable Long id,
            @RequestBody List<String> roles) {
        User updatedUser = userService.assignRolesToUser(id, roles);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }
}

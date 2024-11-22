package com.masteringbackend.recipeSharingAPI.repos;

import com.masteringbackend.recipeSharingAPI.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email); // for to find user by email
    Optional<User> findByUsername(String username); // for to find user by username

}

package com.masteringbackend.recipeSharingAPI.controllers;

import com.masteringbackend.recipeSharingAPI.entities.User;
import com.masteringbackend.recipeSharingAPI.security.AuthenticationRequest;
import com.masteringbackend.recipeSharingAPI.security.AuthenticationResponse;
import com.masteringbackend.recipeSharingAPI.security.JwtUtil;
import com.masteringbackend.recipeSharingAPI.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final JwtUtil jwtUtil;


    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthenticationRequest request) {
        Optional<User> user = userService.getUserByEmail(request.getEmail());

        if (user.isPresent() && new BCryptPasswordEncoder().matches(request.getPassword(), user.get().getPassword())) {
            String token = jwtUtil.generateToken(user.get());
            return ResponseEntity.ok(new AuthenticationResponse(token));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}

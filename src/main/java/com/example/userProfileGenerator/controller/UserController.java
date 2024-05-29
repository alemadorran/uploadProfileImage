package com.example.userProfileGenerator.controller;

import com.example.userProfileGenerator.model.User;
import com.example.userProfileGenerator.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public List<User> getAllUsuarios() {
        return userRepository.findAll();
    }

    @PostMapping
    public User createUsuario(@RequestBody User usuario) {
        return userRepository.save(usuario);
    }
}


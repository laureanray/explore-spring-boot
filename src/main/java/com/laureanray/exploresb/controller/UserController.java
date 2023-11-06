package com.laureanray.exploresb.controller;

import com.laureanray.exploresb.exceptions.ResourceNotFoundException;
import com.laureanray.exploresb.model.User;
import com.laureanray.exploresb.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public List<User> getAllUsers() {
        return this.userRepository.findAll();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable(value = "id") long userId) {
        return this.userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Couldn't find user with that ID"));
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        return this.userRepository.save(user);
    }

    @PutMapping("/{id}")
    public User updateUser(@RequestBody User user, @PathVariable("id") long userId) {
        User existingUser = this.userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Couldn't find user with that ID"));

        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setEmail(user.getEmail());

        return this.userRepository.save(existingUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable("id") long userId) {
        User existingUser = this.userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Couldn't find user with that ID"));

        this.userRepository.delete(existingUser);

        return ResponseEntity.ok().build();
    }
}

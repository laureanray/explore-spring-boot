package com.laureanray.exploresb.controller;

import com.laureanray.exploresb.exceptions.ResourceNotFoundException;
import com.laureanray.exploresb.model.User;
import com.laureanray.exploresb.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getAllUsers() {
        return this.userService.findAllUsers();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable(value = "id") long userId) {
        return this.userService.findById(userId);
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        return this.userService.createUser(user);
    }

    @PutMapping("/{id}")
    public User updateUser(@RequestBody User user, @PathVariable("id") long userId) {
        return this.userService.updateUser(user, userId);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable("id") long userId) {
        // TODO: We might not need this?
        User deleted = this.userService.deleteUser(userId);

        if (deleted != null) {
            return ResponseEntity.ok().build();
        } 

        return ResponseEntity.badRequest().build();
    }
}

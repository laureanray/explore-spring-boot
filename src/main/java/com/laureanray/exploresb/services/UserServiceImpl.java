package com.laureanray.exploresb.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.laureanray.exploresb.exceptions.ResourceNotFoundException;
import com.laureanray.exploresb.model.User;
import com.laureanray.exploresb.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;


    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User findById(long userId) {
        return this.userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Couldn't find user with that ID"));
    }

    @Override
    public List<User> findAllUsers() {
        return this.userRepository.findAll();
    }

    @Override
    public User createUser(User user) {
        return this.userRepository.save(user);
    }

    @Override
    public User updateUser(User user, long userId) {
        User existingUser = this.userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Couldn't find user with that ID"));

        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setEmail(user.getEmail());

        return this.userRepository.save(existingUser);
    }

    @Override
    public User deleteUser(long userId) {
        User toBeDeleted =  this.userRepository.findById(userId)
            .orElseThrow(() -> new ResourceNotFoundException("Couldn't find user with that ID"));
        this.userRepository.delete(toBeDeleted);
        return toBeDeleted;
    }

}

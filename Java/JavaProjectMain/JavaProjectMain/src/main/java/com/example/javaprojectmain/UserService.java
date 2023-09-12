package com.example.javaprojectmain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.mindrot.jbcrypt.BCrypt;

import java.util.List;
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User createUser(User user) {
        // Check if the user already exists
        User existingUser = userRepository.findByUsername(user.getUsername());
        if (existingUser != null) {
            return null; //User already exists
        }

        // Save the new user
        return userRepository.save(user);
    }

    public boolean authenticateUser(String userName, String userPassword) {
        User user = userRepository.findByUsername(userName);

        return user != null && BCrypt.checkpw(userPassword, user.getPassword()); // User exists and password matches if not then authentication failed
    }

    public boolean adminCheck(String userName) {
        User user = userRepository.findByUsername(userName);

        return user != null && user.getIsAdmin(); // User exists and is admin or user doesn't exist or isn't admin
    }

    public void deleteUser(String userName) {
        User user = userRepository.findByUsername(userName);
        if (user!=null) {
            userRepository.deleteByUsername(userName);
        }
    }

    public boolean checkIfExist(String userName) {
        User user = userRepository.findByUsername(userName);

        return user != null;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
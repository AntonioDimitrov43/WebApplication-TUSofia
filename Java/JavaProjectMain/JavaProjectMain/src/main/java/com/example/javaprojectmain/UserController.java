package com.example.javaprojectmain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.mindrot.jbcrypt.BCrypt;

import java.util.List;

@RestController
@RequestMapping("/siteusers")
public class UserController {

    @Autowired
    private UserService userService;
    String StoredSalt = "Random Passwords"; //Temporarily preset

    @PostMapping("/addUser")
    public boolean createUser(@RequestBody User user) {
        User createdUser = userService.createUser(user);
        String hashedPassword = BCrypt.hashpw(user.getPassword(),StoredSalt);

        return createdUser != null;
    }

    @PostMapping("/authUser")
    public boolean authenticateUser(@RequestBody User user) {

        return userService.authenticateUser(user.getUsername(), user.getPassword());
    }

    @PostMapping("/adminCheck")
    public boolean adminCheck(@RequestBody User user) {

        return userService.adminCheck(user.getUsername());
    }

    @DeleteMapping("/deleteUser")
    public boolean deleteUser(String userName) {
        if (userService.checkIfExist(userName))
        {
            userService.deleteUser(userName);
            return true;
        }
        else {
            return false;
        }

    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }
}
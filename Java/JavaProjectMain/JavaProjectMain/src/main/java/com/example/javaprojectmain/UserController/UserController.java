package com.example.javaprojectmain.UserController;

import com.example.javaprojectmain.User.User;
import com.example.javaprojectmain.UserService.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.mindrot.jbcrypt.BCrypt;



@RestController
//@RequestMapping("/siteusers")
public class UserController {

    @Autowired
    private UserService userService;

    private final ResourceLoader resourceLoader;

    public UserController(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @GetMapping(value = "/home", produces = MediaType.TEXT_HTML_VALUE)
    public String getIndexHtml() throws IOException {
        // Load the index.html file from the classpath
        Resource resource = resourceLoader.getResource("classpath:/static/LoginPage.html");

        // Read the file and return its contents
        try (InputStream inputStream = resource.getInputStream();
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            StringBuilder content = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line);
            }
            return content.toString();
        }
    }

    String StoredSalt = "Random Passwords"; //Temporarily preset

    @PostMapping("/addUser")
    public boolean createUser(@RequestBody User user) {
        User createdUser = userService.createUser(user);
        //String hashedPassword = BCrypt.hashpw(user.getPassword(),StoredSalt);

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
package com.example.javaprojectmain.UserService;

import com.example.javaprojectmain.User.User;
import com.example.javaprojectmain.UserRepository.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.mindrot.jbcrypt.BCrypt;

import java.util.List;
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private String StoredSalt = "$2a$15$LESFhTgawuYYgBJ.wcU9o.";
    public User createUser(User user) {
        // Check if the user already exists
        User existingUser = userRepository.findByUsername(user.getUsername());
        if (existingUser != null) {
            return null; //User already exists
        }

        // Save the new user
        String hashedPassword = BCrypt.hashpw(user.getPassword(),StoredSalt);
        user.setPassword(hashedPassword);

        return userRepository.save(user);
    }

    public boolean authenticateUser(String userName, String userPassword, HttpSession session) {
        User user = userRepository.findByUsername(userName);
        try{
            BCrypt.checkpw(userPassword, user.getPassword());
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        if(user != null){
            session.setAttribute("userId",user.getId());
            return true;
        }
        return false;
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
        System.out.println(userName);
        return user != null;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserDataByID(Integer userId){
        return userRepository.findByid(userId);
    }
}
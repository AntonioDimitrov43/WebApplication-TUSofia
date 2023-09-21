package com.example.javaprojectmain.UserController;

import com.example.javaprojectmain.User.User;
import com.example.javaprojectmain.UserService.UserService;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.hibernate.annotations.Check;
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
import org.springframework.web.servlet.view.RedirectView;


@RestController
//@RequestMapping("/siteusers")
public class UserController {

    @Autowired
    private UserService userService;

    private final ResourceLoader resourceLoader;

    private String Protocol = "http://";

    public UserController(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }


    @GetMapping(value = "/user/home", produces = MediaType.TEXT_HTML_VALUE)
    public Object DashBoard(HttpServletRequest request) throws IOException{
        HttpSession session = request.getSession();
        if (session.getAttribute("userId")==null) {
            return new RedirectView("/user/login");
        }
        Resource resource = resourceLoader.getResource("classpath:/static/ProfilePage.html");

        // Read the file and return its contents
        try (InputStream inputStream = resource.getInputStream();
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            StringBuilder content = new StringBuilder();
            String line;
            Integer UserId = Integer.parseInt(session.getAttribute("userId").toString());
            User CurrentUser = userService.getUserDataByID(UserId);
            while ((line = reader.readLine()) != null) {
                line = TagsReplace(line,request);
                line = line.replace("{username}", CurrentUser.getUsername());
                line = line.replace("{email}", CurrentUser.getEmail());
                content.append(line);
            }
            return content.toString();
        }
    }

    private String TagsReplace(String line, HttpServletRequest request) throws IOException {
        String URL = this.GetSiteURL(request,"");
        line = line.replace("{URL}", URL);
        line=line.replace("{menu}",GetMenu(request));
        System.out.println(GetMenu(request));

        return line;
    }

    private String GetSiteURL(HttpServletRequest request, String Path){
       String URL = this.Protocol + request.getServerName() + ":" + request.getServerPort() + Path;
        System.out.println(request.getServerName());
        System.out.println(URL);
        return URL;
    }

    @PostMapping("/user/addUser")
    public boolean createUser(@RequestBody User user) {
        User createdUser = userService.createUser(user);

        return createdUser != null;
    }

    @PostMapping("/user/authUser")
    public Object authenticateUser(@RequestBody User user, HttpServletRequest request) {
        HttpSession session = request.getSession();
        boolean CheckUserLogin=userService.authenticateUser(user.getUsername(), user.getPassword(),session);

        if (CheckUserLogin) {
            System.out.println(session.getAttribute("userId"));
            return true;
        }
        return false;
    }

    @PostMapping("/user/adminCheck")
    public boolean adminCheck(@RequestBody User user) {
        return userService.adminCheck(user.getUsername());
    }

    @DeleteMapping("/user/deleteUser")
    public boolean deleteUser(HttpServletRequest request) {
        String userName=request.getParameter("username");
        System.out.println(userName);
        if (userName!=null) {
            if (userService.checkIfExist(userName)) {
                userService.deleteUser(userName);
                return true;
            } else {
                return false;
            }
        }
        return false;

    }

    @GetMapping("/user/getUsers")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping(value = "/user/login", produces = MediaType.TEXT_HTML_VALUE)
    public Object UserLogin(HttpServletRequest request) throws IOException{
        HttpSession session = request.getSession();
        if (session.getAttribute("userId")!=null) {
            return new RedirectView("/user/home");
        }
        Resource resource = resourceLoader.getResource("classpath:/static/LoginPage.html");

        // Read the file and return its contents
        try (InputStream inputStream = resource.getInputStream();
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            StringBuilder content = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                line = TagsReplace(line,request);
                content.append(line);
            }
            return content.toString();
        }
    }

    @GetMapping(value = "/menu", produces = MediaType.TEXT_HTML_VALUE)
    private String GetMenu(HttpServletRequest request) throws IOException{
        HttpSession session = request.getSession();
        Resource resource = resourceLoader.getResource("classpath:/static/Menu.html");
        try (InputStream inputStream = resource.getInputStream();
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            StringBuilder content = new StringBuilder();
            String line;
            Boolean AdminCheck=false;
            if (session.getAttribute("userId")!=null) {
                Integer UserId = Integer.parseInt(session.getAttribute("userId").toString());
                System.out.println(userService.getUserDataByID(UserId).getIsAdmin());
                AdminCheck=userService.getUserDataByID(UserId).getIsAdmin();
            }

            while ((line = reader.readLine()) != null) {

                if(session.getAttribute("userId")!=null){
                    if (AdminCheck){
                        line = line.replace("{admin}", "<a href=\"/user/admin\" class=\"Nav-Btn\" id=\"AdminBtn\">Админ</a>");
                    }
                    else{
                        line=line.replace("{admin}","");
                    }
                }
                else{
                    line=line.replace("{admin}","");
                }
                content.append(line);
            }
            return content.toString();
        }
    }

    @GetMapping(value = "/user/logout", produces = MediaType.TEXT_HTML_VALUE)
    public Object UserLogout(HttpServletRequest request) throws IOException{
        HttpSession session = request.getSession();
        if (session.getAttribute("userId")!=null) {
            session.setAttribute("userId",null);
        }
        return new RedirectView("/user/login");
    }

    @GetMapping(value = "/user/main", produces = MediaType.TEXT_HTML_VALUE)
    public Object MainPage(HttpServletRequest request) throws IOException{
        HttpSession session = request.getSession();
        if (session.getAttribute("userId")==null) {
            return new RedirectView("/user/login");
        }
        Resource resource = resourceLoader.getResource("classpath:/static/HomePage.html");

        // Read the file and return its contents
        try (InputStream inputStream = resource.getInputStream();
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            StringBuilder content = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                line = TagsReplace(line,request);
                content.append(line);
            }
            return content.toString();
        }
    }

    @GetMapping(value = "/user/contact", produces = MediaType.TEXT_HTML_VALUE)
    public Object Contacts(HttpServletRequest request) throws IOException{
        HttpSession session = request.getSession();
        if (session.getAttribute("userId")==null) {
            return new RedirectView("/user/login");
        }
        Resource resource = resourceLoader.getResource("classpath:/static/ContactInfo.html");

        // Read the file and return its contents
        try (InputStream inputStream = resource.getInputStream();
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            StringBuilder content = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                line = TagsReplace(line,request);
                content.append(line);
            }
            return content.toString();
        }
    }

    @GetMapping(value = "/user/admin", produces = MediaType.TEXT_HTML_VALUE)
    public Object AdminMenu(HttpServletRequest request) throws IOException{
        HttpSession session = request.getSession();
        if (session.getAttribute("userId")==null) {
            return new RedirectView("/user/login");
        }
        Resource resource = resourceLoader.getResource("classpath:/static/AdminMenu.html");

        // Read the file and return its contents
        try (InputStream inputStream = resource.getInputStream();
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            StringBuilder content = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                line = TagsReplace(line,request);
                content.append(line);
            }
            return content.toString();
        }
    }

    @GetMapping(value = "/user/about", produces = MediaType.TEXT_HTML_VALUE)
    public Object AboutUs(HttpServletRequest request) throws IOException{
        HttpSession session = request.getSession();
        if (session.getAttribute("userId")==null) {
            return new RedirectView("/user/login");
        }
        Resource resource = resourceLoader.getResource("classpath:/static/AboutUs.html");

        // Read the file and return its contents
        try (InputStream inputStream = resource.getInputStream();
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            StringBuilder content = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                line = TagsReplace(line,request);
                content.append(line);
            }
            return content.toString();
        }
    }
}
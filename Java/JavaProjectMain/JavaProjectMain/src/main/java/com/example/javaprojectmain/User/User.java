package com.example.javaprojectmain.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "siteusers")
public class User {
    @Id
    @Column (
            name = "Id",
            nullable = false
    )
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private int id;
    @Column(
            name = "username",
            nullable = false
    )
    private String username;
    @Column(
            name = "password",
            nullable = false
    )
    private String password;
    @Column(
            name = "email",
            nullable = false
    )
    private String email;
    @Column(
            name = "isAdmin"
    )
    private boolean IsAdmin;

    public User() {
    }

    public User(String username, String password, String email, boolean isAdmin) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.IsAdmin = isAdmin;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getId() { return this.id; }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) { this.email = email; }

    public boolean getIsAdmin() { return this.IsAdmin; }

    public void setIsAdmin() {this.IsAdmin = true;}
}
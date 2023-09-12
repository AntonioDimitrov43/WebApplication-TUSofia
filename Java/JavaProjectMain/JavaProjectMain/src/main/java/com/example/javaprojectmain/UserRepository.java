package com.example.javaprojectmain;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    boolean existsByUsername(String username);

    User getByUsername(String username);

    User findByUsername(String username);

    User deleteByUsername(String username);

    List<User> findAllByUsername(String username);
}
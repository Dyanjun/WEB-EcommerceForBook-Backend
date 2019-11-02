package com.example.demo.dao;

import java.util.List;
import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface UserDao extends JpaRepository<User,Long> {

    public User findByUsernameAndPassword(String username, String password);

    public User findByUsername(String username);

    public User findById(long Id);

    public List<User> findByRole(String role);




}

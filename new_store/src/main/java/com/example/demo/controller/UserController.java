package com.example.demo.controller;


import com.example.demo.dao.CartDao;
import com.example.demo.dao.UserDao;
import com.example.demo.entity.User;

import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.HashMap;
import java.util.List;
import java.util.Map;



@RestController
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;



    @PostMapping("register")
    public Map<String,Object> register(@RequestBody User user){

        String name=user.getUsername();
        String password=user.getPassword();
        String email=user.getEmail();

        return userService.register(name,password,email);
    }



    @PostMapping("login")
    public Map<String,Object> login(@RequestBody User user){

        String name=user.getUsername();
        String password=user.getPassword();

        return userService.login(name,password);
    }

    @PostMapping("unlogin")
    public Map<String,Object> unlogin(@RequestBody User user){

        String name=user.getUsername();
       return userService.unlogin(name);
    }


    @GetMapping("users")
    @ResponseBody
    @CrossOrigin
    public List<User> UserList() {
        return userService.findUsers();
    }

    @PostMapping("ban_user")
    public String ban_user(@RequestBody User user){

        long id=user.getId();
        return userService.ban_user(id);
    }

    @PostMapping("unban_user")
    public String unban_user(@RequestBody User user){

        long id=user.getId();
        return userService.unban_user(id);
    }



}
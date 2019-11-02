package com.example.demo.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.demo.dao.UserDao;
import com.example.demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserService {

    @Autowired
    UserDao userDao;

    public User findByUsernameAndPassword(String username, String password) {
        return userDao.findByUsernameAndPassword(username, password);
    }
    public void save(User user1) {
        userDao.save(user1);
    }
    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }
    public User findById(long Id) {
        return userDao.findById(Id);
    }

    public boolean isEmail(String email){
        String regex = "[a-zA-Z0-9_]+[a-zA-Z0-9_]*@[a-zA-Z0-9]+[.][a-zA-Z0-9]+";
        return email.matches(regex);
    }
    public boolean isAdmin(String username){
        String admin1="admin[0-9]";
        String admin2="admin[1-9][0-9]+";

        return username.matches(admin1)||username.matches(admin2);
    }
    public Map<String,Object> register(String name,String password,String email){
        Map<String,Object> map = new HashMap<>(6) ;
        if(!isEmail(email)){
            map.put("flag","false") ;
            map.put("msg","邮箱格式不正确") ;
            return map;
        }
        User user1 = findByUsername(name);
        if(user1==null){
            User userEntity = new User();
            userEntity.setUsername(name);
            userEntity.setPassword(password);
            userEntity.setEmail(email);
            userEntity.setAble("yes");
            if(isAdmin(name)) {
                userEntity.setRole("admin");
                map.put("role", "admin");
                System.out.print("admin");
                map.put("flag","false") ;
                map.put("msg","请重新设置用户名") ;
                return map;
            }
            else {
                map.put("role", "user");
                userEntity.setRole("user");
                System.out.print("user");
            }
            save(userEntity);
            map.put("flag", true);
            map.put("msg", "注册成功");
            map.put("username", name);
            return map;
        }

        map.put("flag","false") ;
        map.put("msg","用户已存在") ;
        return  map;
    }

    public Map<String,Object> login(String name,String password){
        Map<String,Object> map = new HashMap<>(6) ;
        User user1 = findByUsernameAndPassword(name, password);
        if(user1 !=null) {
            if (user1.getAble().equals("yes")) {
                map.put("role", user1.getRole());
                map.put("flag", true);
                map.put("msg", "登录成功");
                map.put("username", name);
                map.put("able","yes");
                return map;
            }
            map.put("flag", true);
            map.put("able","no");
            map.put("msg","账号已被禁用") ;
            return map;
        }
        map.put("flag","false") ;
        map.put("msg","用户名或密码错误") ;
        return map ;
    }

    public Map<String,Object> unlogin(String name){
        Map<String,Object> map = new HashMap<>(6) ;
        User user1 = findByUsername(name);

        if(user1 !=null){

            map.put("flag", true);
            map.put("msg", "退出登录成功");
            return map;
        }
        map.put("flag","false") ;
        map.put("msg","退出登录失败") ;
        return map ;
    }

    public List<User> findUsers(){
        return userDao.findByRole("user");
    }

    public String ban_user(long id){
        User user=userDao.findById(id);
        if(user!=null){
            user.setAble("no");
            userDao.flush();
            return "r";
        }
        return "w";
    }

    public String unban_user(long id){
        User user=userDao.findById(id);
        System.out.print(user.getUsername());
        System.out.print(user.getAble());
        if(user!=null){
            user.setAble("yes");
            userDao.flush();
            return "r";
        }
        return "w";
    }
}
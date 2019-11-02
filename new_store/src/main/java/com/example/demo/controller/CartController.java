package com.example.demo.controller;



import com.example.demo.dao.BookDao;
import com.example.demo.dao.CartDao;
import com.example.demo.dao.UserDao;
import com.example.demo.entity.Book;
import com.example.demo.entity.Cart;
import com.example.demo.entity.Orders;
import com.example.demo.entity.User;
import com.example.demo.service.BookService;
import com.example.demo.service.CartService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@CrossOrigin
@RestController
public class CartController {
    @Autowired
    private UserService userService;

    @Autowired
    private CartService cartService;

    @Autowired
    private BookService bookService;

    @PostMapping("whichcart")
    @CrossOrigin
    public List<Cart> whichcart(@RequestBody Cart cart) {

        String username=cart.getUsername();
        User user1 = userService.findByUsername(username);
        if (user1 != null) {
            List<Cart> cart1;
            cart1= cartService.findByUsername(username);
            return cart1;
        }
        return null;
    }

    @PostMapping("addtocart")
    @CrossOrigin
    public String addtocart(@RequestBody Cart cart) {

        long bookId = cart.getBookId();
        Integer count = cart.getCount();
        Long bookprice=cart.getBookprice();
        String bookname=cart.getBookname();
        String username=cart.getUsername();

        return cartService.addtocart(bookId,count,bookprice,bookname,username);

    }

    @PostMapping("deletecart")
    @CrossOrigin
    public List<Cart> deletecart(@RequestBody Cart cart){
        Long bookId = cart.getBookId();
        String username=cart.getUsername();

        return cartService.deletecart(username,bookId);
    }

    @PostMapping("reducecart")
    @CrossOrigin
    public List<Cart> reducecart(@RequestBody Cart cart){
        Long bookId = cart.getBookId();
        String username=cart.getUsername();
        return cartService.reducecart(username,bookId);
    }

    @PostMapping("addcart")
    @CrossOrigin
    public List<Cart> addcart(@RequestBody Cart cart){
        long bookId = cart.getBookId();
        String username=cart.getUsername();

        return cartService.addcart(username,bookId);
    }

    @PostMapping("pay")//勾选加入订单
    @CrossOrigin
    public String pay(@RequestBody String listJSON){
        return cartService.pay(listJSON);
    }

    @PostMapping("payall")//清空购物车
    @CrossOrigin
    public String payall(@RequestBody Cart cart){
        String username=cart.getUsername();
        return cartService.payall(username);
    }
}

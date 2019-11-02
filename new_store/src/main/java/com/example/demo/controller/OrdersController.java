package com.example.demo.controller;


import com.example.demo.entity.*;
import com.example.demo.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin
public class OrdersController {



    @Autowired
    private CartService cartService;

    @Autowired
    private OrdersService ordersService;

    @Autowired
    private Order_itemService order_itemService;

    @Autowired
    private BookService bookService;

    @Autowired
    private TimeService timeService;


    @PostMapping("order")
    @CrossOrigin
    public  List<Orders> getOrders(@RequestBody Orders orders){
        String username=orders.getUsername();
        return ordersService.findByUsername(username);
    }


    @PostMapping("order_item")
    @CrossOrigin
    public List<Order_item> getOrder_item(@RequestBody Order_item order_item){
        String username=order_item.getUsername();
        return order_itemService.findByUsername(username);
    }

    @PostMapping("alreadypay")//订单中现在付款
    @CrossOrigin
    public String alreadypay(@RequestBody Orders orders){
        String time=timeService.getTime();
        String username=orders.getUsername();
        long Id=orders.getId();

        return ordersService.alreadypay(Id,time);

    }


    @PostMapping("delete_order")
    @CrossOrigin
    public String delete_order(@RequestBody Orders orders) {
        long Id = orders.getId();
        return  ordersService.delete_order(Id);
    }

    @PostMapping("payforbook")
    @CrossOrigin
    public String payforbook(@RequestBody Cart cart){
        long bookId = cart.getBookId();
        Integer count = cart.getCount();
        String username = cart.getUsername();
        Long bookprice = cart.getBookprice();
        String bookname = cart.getBookname();
        return ordersService.payforbook(bookId,count,username,bookprice,bookname);

    }

    @GetMapping("/order_item_admin")
    @ResponseBody
    public List<Order_item> findAllOrder_item_admin() {
        return order_itemService.findAll();
    }

    @GetMapping("/order_admin")
    @ResponseBody
    public List<Orders> findDoneOrder_admin() {

        return ordersService.findByDone("yes");
    }
}
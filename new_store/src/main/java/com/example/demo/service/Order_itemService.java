package com.example.demo.service;
import com.example.demo.dao.CartDao;
import com.example.demo.dao.Order_itemDao;
import com.example.demo.entity.Cart;
import com.example.demo.entity.Order_item;
import com.example.demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Order_itemService {
    @Autowired
    Order_itemDao order_itemDao;

    public Order_item findById(long Id) {
        return order_itemDao.findById(Id);
    }
    public List<Order_item> findByOrderId(long orderId) {
        return order_itemDao.findByOrderId(orderId);
    }
    public void save(Order_item order_item1) {
        order_itemDao.save(order_item1);
    }
    public List<Order_item> findByUsername(String username) {
        return order_itemDao.findByUsername(username);
    }
    public  List<Order_item> findAll(){return order_itemDao.findAll();}
}

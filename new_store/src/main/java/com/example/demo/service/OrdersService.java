package com.example.demo.service;

import com.example.demo.dao.BookDao;
import com.example.demo.dao.Order_itemDao;
import com.example.demo.dao.OrdersDao;
import com.example.demo.entity.Book;
import com.example.demo.entity.Cart;
import com.example.demo.entity.Order_item;
import com.example.demo.entity.Orders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrdersService {
    @Autowired
    OrdersDao ordersDao;
    @Autowired
    Order_itemDao order_itemDao;
    @Autowired
    BookDao bookDao;

    public Orders findById(long Id) {
        return ordersDao.findById(Id);
    }
    public void save(Orders order1) {
        ordersDao.save(order1);
    }
    public List<Orders> findByUsername(String username) {
        return ordersDao.findByUsername(username);
    }

    public  List<Orders> findByDone(String done){
        return  ordersDao.findByDone(done);
    }
    public String alreadypay(long Id,String time){
        Orders orders1=ordersDao.findById(Id);
        if(orders1!=null) {
            orders1.setDone("yes");
            //time
            orders1.setTime(time);
            ordersDao.flush();
        }

        List<Order_item> order_item=order_itemDao.findByOrderId(Id);
        for( Order_item item : order_item){
            long count=item.getCount();
            long bookId=item.getBookId();
            Book book=bookDao.findById(bookId);
            long inventory=book.getInventory();
            if(inventory<count) {
                delete_order(Id);
                return "unenough";
            }
            book.setInventory(inventory-count);
        }

        bookDao.flush();
        return "r";
    }

    public String delete_order(long Id){
        Orders order1=ordersDao.findById(Id);

        if(order1!=null){
            ordersDao.deleteOrdersById(Id);
            ordersDao.flush();
            List<Order_item> order_items=order_itemDao.findByOrderId(Id);
            for (Order_item order_item : order_items) {
                order_itemDao.delete(order_item);
            }
            order_itemDao.flush();
            return "r";
        }

        return "w";
    }

    public String payforbook(long bookId,Integer count,String username,Long bookprice,String bookname) {
        Book book1=bookDao.findById(bookId);

        if(book1.getInventory()< count){
            return "u";
        }
        //设置orders
        Orders ordersEntity = new Orders();
        ordersEntity.setUsername(username);
        ordersEntity.setDone("no");
        ordersEntity.setTotprice(count*bookprice);
        ordersDao.save(ordersEntity);

        Long orderId = ordersEntity.getId();
        System.out.print(orderId);

        //设置order_items
        Order_item order_itemEntity = new Order_item();
        order_itemEntity.setBookId(bookId);
        order_itemEntity.setBookname(bookname);
        order_itemEntity.setBookprice(bookprice);
        order_itemEntity.setCount(count);
        order_itemEntity.setOrderId(orderId);
        order_itemEntity.setUsername(username);


        ordersDao.flush();
        order_itemDao.save(order_itemEntity);

        return "r";

    }



}
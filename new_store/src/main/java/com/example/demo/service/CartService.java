package com.example.demo.service;
import com.example.demo.dao.*;
import com.example.demo.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {
    @Autowired
    CartDao cartDao;
    @Autowired
    UserDao userDao;
    @Autowired
    BookDao bookDao;
    @Autowired
    OrdersDao ordersDao;
    @Autowired
    Order_itemDao order_itemDao;

    public Cart findById(long Id) {
        return cartDao.findById(Id);
    }
    public void save(Cart cart1) {
        cartDao.save(cart1);
    }
    public  List<Cart> findByUsername(String username) {
        return cartDao.findByUsername(username);
    }
    public String addtocart(long bookId,Integer count,Long bookprice,String bookname,String username){
        User user1 = userDao.findByUsername(username);


        Book book1=bookDao.findById(bookId);

        if(book1.getInventory()< count){
            return "u";
        }

        if (user1 != null) {
            Cart cart1=cartDao.findByUsernameAndBookId(user1.getUsername(),bookId);
            if (cart1!=null) {
                Integer count_now=cart1.getCount();
                Integer count_after=count_now+count;
                if(count_after>book1.getInventory()){
                    return String.valueOf(book1.getInventory()-count_now);
                }
                cart1.addBookIds(count);
                cartDao.flush();
                return "r";
            }
            Cart cartEntity = new Cart();
            cartEntity.setBookId(bookId);
            cartEntity.setUsername(user1.getUsername());
            cartEntity.setCount(count);
            cartEntity.setBookprice(bookprice);
            cartEntity.setBookname(bookname);
            cartDao.save(cartEntity);
            return "r";
        }
        return "w";
    }

    public List<Cart> deletecart(String username,Long bookId ){
        cartDao.deleteCartByUsernameAndBookId(username,bookId);
        cartDao.flush();
        List<Cart> cart1 = cartDao.findByUsername(username);
        return cart1;
    }

    public List<Cart> reducecart(String username,Long bookId){
        User user1 = userDao.findByUsername(username);
        Cart cart1=cartDao.findByUsernameAndBookId(user1.getUsername(),bookId);
        cart1.reduceBookIds();
        cartDao.flush();
        List<Cart> cart2 = cartDao.findByUsername(user1.getUsername());
        return cart2;
    }

    public List<Cart> addcart(String username,long bookId){
        User user1 = userDao.findByUsername(username);

        Cart cart1=cartDao.findByUsernameAndBookId(user1.getUsername(),bookId);

        Integer count=cart1.getCount();

        Book book1=bookDao.findById(bookId);
        System.out.print(book1.getInventory());
        if(book1.getInventory()<=count){
            return cartDao.findByUsername(user1.getUsername());
        }

        //改变cart
        cart1.addBookIds(1);
        cartDao.flush();
        List<Cart> cart2 = cartDao.findByUsername(user1.getUsername());
        return cart2;
    }

    public String pay(String listJSON){
        System.out.print(listJSON);
        int begin,end;
        begin=listJSON.indexOf("[");
        end=listJSON.indexOf("]");

        String tmp1=listJSON.substring(begin+1,end);

        String [] tmp2=tmp1.split(",");
        Long [] books = new Long[tmp2.length];
        for (int i=0;i<tmp2.length;i++) {
            //System.out.print(tmp2[i]);
            books[i]=Long.parseLong(tmp2[i]);
            // System.out.print(books[i]);
        }

        //books中储存着想要加入订单的bookId
        begin=end+15;

        end=listJSON.indexOf("}");
        String username=listJSON.substring(begin+1,end);
        System.out.print(username);



        //设置orders
        Orders ordersEntity = new Orders();
        ordersEntity.setUsername(username);
        ordersEntity.setDone("no");
        ordersEntity.setTotprice(0L);
        ordersDao.save(ordersEntity);
      
        for (Long bookId : books) {

            //从cart中读取count和bookprice
            Cart cart1 = cartDao.findByUsernameAndBookId(username, bookId);
            Integer count = cart1.getCount();
            Long bookprice = cart1.getBookprice();
            String bookname=cart1.getBookname();

            ordersEntity.addTotprice(bookprice*count);



            Long orderId=ordersEntity.getId();
            System.out.print(orderId);
            //设置order_items
            Order_item order_itemEntity=new Order_item();
            order_itemEntity.setBookId(bookId);
            order_itemEntity.setBookname(bookname);
            order_itemEntity.setBookprice(bookprice);
            order_itemEntity.setCount(count);
            order_itemEntity.setOrderId(orderId);
            order_itemEntity.setUsername(username);

            //删除对应cart
            cartDao.delete(cart1);
            cartDao.flush();

            ordersDao.flush();
            order_itemDao.save(order_itemEntity);
        }
        return "r";
    }

    public String payall(String username){
        //设置orders
        List<Cart> cartall=cartDao.findByUsername(username);
        if(cartall.size()==0) return("购物车为空");
        Orders ordersEntity = new Orders();
        ordersEntity.setUsername(username);
        ordersEntity.setDone("no");
        ordersEntity.setTotprice(0L);

        ordersDao.save(ordersEntity);

        for (Cart cart1 :cartall) {

            //从cart中读取count和bookprice
            Integer count = cart1.getCount();
            Long bookprice = cart1.getBookprice();
            String bookname=cart1.getBookname();
            Long bookId=cart1.getBookId();
            ordersEntity.addTotprice(bookprice*count);

            Long orderId=ordersEntity.getId();
            System.out.print(orderId);

            //设置order_items
            Order_item order_itemEntity=new Order_item();
            order_itemEntity.setBookId(bookId);
            order_itemEntity.setBookname(bookname);
            order_itemEntity.setBookprice(bookprice);
            order_itemEntity.setCount(count);
            order_itemEntity.setOrderId(orderId);
            order_itemEntity.setUsername(username);

            //删除对应cart
            cartDao.delete(cart1);
            cartDao.flush();

            ordersDao.flush();
            order_itemDao.save(order_itemEntity);
        }
        return "t";
    }


}

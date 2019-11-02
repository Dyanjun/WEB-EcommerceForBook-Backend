package com.example.demo.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity(name = "order_item")
public class Order_item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Long orderId;//订单号

    private  Long bookId;

    @NotNull
    private  String username;

    private String bookname;
    private Integer count;

    private Long bookprice;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrderId() {
        return orderId;
    }
    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getUsername(){return username;}
    public void setUsername( String username){this.username=username;}

    public Long getBookId(){return this.bookId;}
    public void setBookId(Long bookId){this.bookId=bookId;}

    public  Integer getCount(){return  this.count;}
    public  void setCount(Integer count){this.count=count;}

    public Long getBookprice() {
        return this.bookprice;
    }
    public void setBookprice(Long bookprice) {
        this.bookprice = bookprice;
    }

    public String getBookname(){return bookname;}
    public void setBookname( String bookname){this.bookname=bookname;}
}

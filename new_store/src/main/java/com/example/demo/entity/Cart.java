package com.example.demo.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import java.util.Map;

@Entity(name = "cart")
public class Cart {




    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String username;

    @NotNull
    private Long bookId;

    private Integer count;

    private Long bookprice;

    private String bookname;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername(){return username;}
    public void setUsername( String username){this.username=username;}

    public Long getBookId(){return this.bookId;}
    public void setBookId(Long bookId){this.bookId=bookId;}

    public  Integer getCount(){return  this.count;}
    public  void setCount(Integer count){this.count=count;}
    public void addBookIds(Integer count){
        this.count+=count;
    }
    public void reduceBookIds(){
        if(this.count>=1)
        this.count--;
    }

    public Long getBookprice() {
        return this.bookprice;
    }
    public void setBookprice(Long bookprice) {
        this.bookprice = bookprice;
    }

    public String getBookname(){return bookname;}
    public void setBookname( String bookname){this.bookname=bookname;}
}

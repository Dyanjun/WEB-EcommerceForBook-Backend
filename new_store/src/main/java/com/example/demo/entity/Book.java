package com.example.demo.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String bookname;
    private String author;
    private Long price;
    private String isbn;
    private Long inventory;
    private  String image;
    private Long sale;
    private  String publishtime;
    private String brief;


    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getBookname() {
        return bookname;
    }
    public void setBookname(String bookname) {
        this.bookname = bookname;
    }

    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }

    public Long getPrice() {
        return price;
    }
    public void setPrice(Long price) {
        this.price = price;
    }

    public String getIsbn() {
        return isbn;
    }
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Long getInventory() {
        return inventory;
    }
    public void setInventory(Long inventory) {
        this.inventory = inventory;
    }

    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }

    public Long getSale() {
        return sale;
    }
    public void setSale(Long sale) {
        this.sale = sale;
    }

    public String getPublishtime() {
        return publishtime;
    }
    public void setPublishtime(String publishtime) {
        this.publishtime = publishtime;
    }

    public String getBrief() {
        return brief;
    }
    public void setBrief(String brief) {
        this.brief = brief;
    }
}

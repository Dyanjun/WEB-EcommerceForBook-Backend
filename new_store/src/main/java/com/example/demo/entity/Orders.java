package com.example.demo.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity(name = "orders")
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String username;

    private String done;

    private  Long totprice;

    private  String time;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getDone(){ return done;}
    public void setDone(String done) { this.done = done; }

    public Long getTotprice() {
        return totprice;
    }
    public void setTotprice(Long totprice) {
        this.totprice = totprice;
    }
    public void addTotprice(Long price){this.totprice +=price;}

    public String getTime(){return  time;}
    public void  setTime(String time){this.time=time;}
}

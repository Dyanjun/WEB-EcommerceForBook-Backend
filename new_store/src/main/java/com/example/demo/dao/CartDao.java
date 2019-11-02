package com.example.demo.dao;

import java.util.List;
import java.util.Optional;

import com.example.demo.entity.Book;
import com.example.demo.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
public interface CartDao extends JpaRepository<Cart,Long> {


    public  List<Cart> findByUsername(String username);

    @Modifying
    @Transactional
    public void deleteCartByUsernameAndBookId(String username,Long bookId);

    public Cart findById(long Id);

    public Cart findByUsernameAndBookId(String username,Long bookId);

}

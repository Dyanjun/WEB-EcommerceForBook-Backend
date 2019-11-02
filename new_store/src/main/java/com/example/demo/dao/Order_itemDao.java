package com.example.demo.dao;

import java.util.List;
import java.util.Optional;


import com.example.demo.entity.Order_item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
public interface Order_itemDao extends JpaRepository<Order_item,Long> {


    public  List<Order_item> findByUsername(String username);

    public Order_item findById(long Id);

    public  List<Order_item> findByOrderId(Long orderId);

}

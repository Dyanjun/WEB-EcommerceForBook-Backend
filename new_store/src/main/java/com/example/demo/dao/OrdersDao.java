package com.example.demo.dao;



import com.example.demo.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
public interface OrdersDao extends JpaRepository<Orders,Long> {

    public List<Orders> findByUsername(String username);

    public  Orders findById(long Id) ;

    @Modifying
    @Transactional
    public void deleteOrdersById(long bookId);

    public List<Orders> findByDone(String done);

}

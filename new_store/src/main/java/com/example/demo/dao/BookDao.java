package com.example.demo.dao;


import java.util.List;
import com.example.demo.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface BookDao extends JpaRepository<Book,Long>{
    public List<Book> findByBookname(String bookname);

    public Book findById(long Id);
    public List<Book> findByIsbn( String Isbn);
}

package com.example.demo.controller;

import javax.servlet.http.HttpServletRequest;

import com.example.demo.dao.BookDao;
import com.example.demo.entity.Book;
import com.example.demo.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("/books/list")
    @ResponseBody
    public List<Book> booksList() {
          return bookService.findAll();
    }

    @GetMapping("/books/admin")
    @ResponseBody
    public List<Book> booksAdmin() {
        return bookService.findAll();
    }

    @PostMapping("addbook")
    public Map<String,Object> addbook(@RequestBody Book book){
        return bookService.addbook(book);
    }

    @PostMapping("deletebook")
    public Map<String,Object> deletebook(@RequestBody Book book){
        Long id=book.getId();
        return bookService.deletebook(id);
    }

    @PostMapping("changebook")
    public Map<String,Object> changebook(@RequestBody Book book){

        return bookService.changebook(book);
    }

}


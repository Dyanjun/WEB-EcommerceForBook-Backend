package com.example.demo.service;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.example.demo.dao.BookDao;
import com.example.demo.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    @Autowired
    BookDao bookDao;



    public void save(Book book1) {
        bookDao.save(book1);
    }
    public List<Book> findByUsername(String bookname) {
        return bookDao.findByBookname(bookname);
    }
    public Book findById(long Id) {
        return bookDao.findById(Id);
    }
    public List<Book> findByIsbn(String isbn){return  bookDao.findByIsbn(isbn);}
    public List<Book> findAll(){return  bookDao.findAll();}
    public Map<String,Object> addbook(Book book){
        Map<String,Object> map = new HashMap<>(6) ;

        String publishtime=book.getPublishtime();
        String p="[0-9]{4}-(0[1-9]|1[0-2])";
        if(!publishtime.matches(p)){
            map.put("flag",false);
            map.put("msc","出版时间格式不正确");
            return map;
        }
        Long price=book.getPrice();
        if(price<0){
            map.put("flag",false);
            map.put("msc","价格不正确");
            return map;
        }
        Long inventory=book.getInventory();
        if(inventory<0){
            map.put("flag",false);
            map.put("msc","库存不正确");
            return map;
        }
        System.out.print(book.getId());
        String isbn=book.getIsbn();
        System.out.print(isbn+'\n');
        List<Book> bookl=findByIsbn(isbn);
        if(bookl.size()!=0){
            map.put("flag",false);
            map.put("msc","书籍已存在");
            return map;
        }
        Book newbook=new Book();
        newbook.setAuthor(book.getAuthor());
        newbook.setBookname(book.getBookname());
        newbook.setBrief(book.getBrief());
        newbook.setInventory(book.getInventory());
        newbook.setIsbn(isbn);
        newbook.setPrice(book.getPrice());
        newbook.setPublishtime(book.getPublishtime());
        newbook.setImage(book.getImage());
        newbook.setSale(0L);
        book.setImage("");
        save(newbook);
        bookDao.flush();
        map.put("flag",true);
        return map;
    }

    public Map<String,Object> deletebook(Long id){
        Map<String,Object> map = new HashMap<>(6) ;
        Book book=findById(id);
        String image=book.getImage();

        bookDao.delete(book);
        bookDao.flush();
        map.put("flag",true);
        return map;
    }

    public Map<String,Object> changebook(Book book) {
        Map<String, Object> map = new HashMap<>(6);
        Long price=book.getPrice();
        if(price<0){
            map.put("flag",false);
            map.put("msc","价格不正确");
            return map;
        }
        Long inventory=book.getInventory();
        if(inventory<0){
            map.put("flag",false);
            map.put("msc","库存不正确");
            return map;
        }
        Long sale=book.getSale();
        if(sale<0){
            map.put("flag",false);
            map.put("msc","月售不正确");
            return map;
        }
        String publishtime=book.getPublishtime();
        String p="[0-9]{4}-(0[1-9]|1[0-2])";
        if(!publishtime.matches(p)){
            map.put("flag",false);
            map.put("msc","出版时间格式不正确");
            return map;
        }
        Long id=book.getId();
        Book book1=findById(id);
        book1.setBookname(book.getBookname());
        book1.setAuthor(book.getAuthor());
        book1.setBrief(book.getBrief());
        book1.setInventory(book.getInventory());
        book1.setIsbn(book.getIsbn());
        book1.setPrice(book.getPrice());
        book1.setPublishtime(book.getPublishtime());
        book1.setSale(book.getSale());
        book1.setImage(book.getImage());
        bookDao.flush();
        map.put("flag",true);
        return map;
    }

}

package com.zxc.service;

import com.zxc.pojo.Book;
import com.zxc.pojo.Page;

import java.util.List;

/**
 * @author zhu
 * @create 2021-08-23 20:52
 */
public interface BookService {
    void addBook(Book book);

    void deleteBookById(Integer id);

    void updateBook(Book book);

    Book queryBookById(Integer id);

    List<Book> queryBooks();
    Page page(int pageNo, int pageSize);
    Page pageByPrice(int pageNo,int pageSize,int min,int max);
}

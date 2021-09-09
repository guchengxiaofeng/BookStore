package com.zxc.dao;

import com.zxc.pojo.Book;

import java.util.List;

/**
 * @author zhu
 * @create 2021-08-23 15:26
 */
public interface BookDao {

     int addBook(Book book);
     int deleteBookById(Integer id);
     int updateBook(Book book);
     Book queryBookById(Integer id);
     List<Book> queryBooks();
     int queryForPageTotalCount();
     int queryForPageTotalCountByPrice(int min,int max);
     List<Book> queryForItems(int begin,int pageSize);
     List<Book> queryForItemsByPrice(int begin,int pageSize,int min,int max);

}

package com.zxc.test;

import com.zxc.dao.impl.BookDaoImpl;
import com.zxc.pojo.Book;

import java.math.BigDecimal;

/**
 * @author zhu
 * @create 2021-08-20 16:55
 */
public class testUser {
    public static void main(String[] args) {
        BookDaoImpl bookDao = new BookDaoImpl();
        Book book = new Book(null,"a","aa",new BigDecimal(9999),15,200,"");
        bookDao.addBook(book);

        System.out.println(bookDao.queryBooks());
    }
}

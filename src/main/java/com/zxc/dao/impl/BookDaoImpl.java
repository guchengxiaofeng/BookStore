package com.zxc.dao.impl;

import com.zxc.dao.BaseDao;
import com.zxc.dao.BookDao;
import com.zxc.pojo.Book;

import java.util.List;

/**
 * @author zhu
 * @create 2021-08-23 15:29
 */
public class BookDaoImpl extends BaseDao implements BookDao {

    @Override
    public int addBook(Book book) {
        String sql="insert into t_book(name,author,price,sales,stock,img_path) values(?,?,?,?,?,?)";
        return update(sql,book.getName(),book.getAuthor(),book.getPrice(),book.getSales(),book.getStock(),book.getImgPath());
    }

    @Override
    public int deleteBookById(Integer id) {
        String sql="delete from t_book where id=?";
        return update(sql,id);
    }

    @Override
    public int updateBook(Book book) {
        String sql="update t_book set name=?,author=?,price=?,sales=?,stock=?,img_path=? where id=?";
        return update(sql,book.getName(),book.getAuthor(),book.getPrice(),book.getSales(),book.getStock(),book.getImgPath(),book.getId());
    }

    @Override
    public Book queryBookById(Integer id) {
        String sql="select id,name,author,price,sales,stock,img_path imgPath from t_book where id=?";
        return queryForOne(Book.class,sql,id);
    }

    @Override
    public List<Book> queryBooks() {
        String sql="select id,name,author,price,sales,stock,img_path imgPath from t_book";
        return queryForList(Book.class,sql);
    }

    @Override
    public int queryForPageTotalCount() {
        String sql="select count(*) from t_book";
        return ((Long)queryForValue(sql)).intValue();
    }

    @Override
    public List<Book> queryForItems(int begin, int pageSize) {
        String sql="select id,name,author,price,sales,stock,img_path imgPath from t_book limit ?,?";
        return queryForList(Book.class,sql,begin,pageSize);
    }

    @Override
    public List<Book> queryForItemsByPrice(int begin, int pageSize, int min, int max) {
        String sql="select id,name,author,price,sales,stock,img_path imgPath from t_book where price>=? and price<=? limit ?,?";
        return queryForList(Book.class,sql,min,max,begin,pageSize);
    }

    @Override
    public int queryForPageTotalCountByPrice(int min,int max) {
        String sql="select count(*) from t_book where price between ? and ?";
        return ((Long)queryForValue(sql,min,max)).intValue();
    }
}

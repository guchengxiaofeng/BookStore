package com.zxc.service.impl;

import com.zxc.dao.BookDao;
import com.zxc.dao.impl.BookDaoImpl;
import com.zxc.pojo.Book;
import com.zxc.pojo.Page;
import com.zxc.service.BookService;

import java.util.List;

/**
 * @author zhu
 * @create 2021-08-23 20:54
 */
public class BookServiceImpl implements BookService {
    private BookDao bookDao=new BookDaoImpl();
    @Override
    public void addBook(Book book) {
        bookDao.addBook(book);
    }

    @Override
    public void deleteBookById(Integer id) {
        bookDao.deleteBookById(id);
    }

    @Override
    public void updateBook(Book book) {
        bookDao.updateBook(book);
    }

    @Override
    public Book queryBookById(Integer id) {
        return bookDao.queryBookById(id);
    }

    @Override
    public List<Book> queryBooks() {
        return bookDao.queryBooks();
    }

    @Override
    public Page page(int pageNo, int pageSize) {
        Page<Book> page = new Page();
        page.setPageTotalCount(bookDao.queryForPageTotalCount());
        page.setPageSize(pageSize);
        page.setPageTotal();
        page.setPageNo(pageNo);
        page.setItems(bookDao.queryForItems((page.getPageNo()-1)*pageSize,pageSize));

        return page;
    }

    @Override
    public Page pageByPrice(int pageNo, int pageSize, int min, int max) {
        Page<Book> page = new Page();
        page.setPageTotalCount(bookDao.queryForPageTotalCountByPrice(min,max));
        page.setPageSize(pageSize);
        page.setPageTotal();
        page.setPageNo(pageNo);
        page.setItems(bookDao.queryForItemsByPrice((page.getPageNo()-1)*pageSize,pageSize,min,max));
        return page;
    }
}

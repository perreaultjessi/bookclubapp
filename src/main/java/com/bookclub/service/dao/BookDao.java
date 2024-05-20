package com.bookclub.service.dao;

import com.bookclub.model.Book;
import com.bookclub.service.GenericDao;

import java.util.List;

public interface BookDao extends GenericDao<Book, String> {
    List<Book> list(String key);
}


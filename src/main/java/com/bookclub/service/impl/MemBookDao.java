package com.bookclub.service.impl;

import com.bookclub.model.Book;
import com.bookclub.service.dao.BookDao;

import java.util.ArrayList;
import java.util.List;

public class MemBookDao implements BookDao {
    // Private variable to hold books
    private List<Book> books;

    // Constructor for book list with 5 instances of the "book" object
    public MemBookDao() {
        books = new ArrayList<>();
        // Add five new Book objects to the books list
        books.add(new Book("ISBN1", "Title1", "Description1", 200, null));
        books.add(new Book("ISBN2", "Title2", "Description2", 250, null));
        books.add(new Book("ISBN3", "Title3", "Description3", 180, null));
        books.add(new Book("ISBN4", "Title4", "Description4", 300, null));
        books.add(new Book("ISBN5", "Title5", "Description5", 220, null));

    }

    //Override the list() method to return the books list
    @Override
    public List<Book> list() {
        return this.books;
    }

    //Override the find() method to search for a book by ISBN value
    @Override
    public Book find(String key) {
        for (Book book : books) {
            if (book.getIsbn().equals(key)) {
                return book;
            }
        }
        return new Book();
    }
}


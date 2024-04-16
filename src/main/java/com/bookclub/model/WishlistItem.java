package com.bookclub.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import org.springframework.data.annotation.Id; //Import for @Id

public class WishlistItem {
    @Id //Decorator for the id property
    private String id;

    @NotNull
    @NotEmpty(message = "ISBN is a required field.")
    private String isbn;

    @NotNull
    @NotEmpty(message = "Title is a required field.")
    private String title;

    //default constructor
    public WishlistItem() {
    }

    //constructor with parameters
    public WishlistItem(String isbn, String title) {
        this.isbn = isbn;
        this.title = title;
    }

    //getter and setter methods for id
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    // Override toString method
    @Override
    public String toString() {
        return String.format("WishlistItem{id='%s', isbn='%s', title='%s'}", id, isbn, title);
    }
}


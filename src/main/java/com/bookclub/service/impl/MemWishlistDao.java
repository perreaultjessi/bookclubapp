package com.bookclub.service.impl;

import com.bookclub.model.WishlistItem;
import com.bookclub.service.dao.WishlistDao;

import java.util.ArrayList;
import java.util.List;

public class MemWishlistDao implements WishlistDao {
    //Private variable to hold wishlist books
    private List<WishlistItem> wishlist;

    //Constructor
    public MemWishlistDao() {
        wishlist = new ArrayList<>();
        // Populate wishlist with initial items
        wishlist.add(new WishlistItem("ISBN1", "Title1"));
        wishlist.add(new WishlistItem("ISBN2", "Title2"));
        wishlist.add(new WishlistItem("ISBN3", "Title3"));
        wishlist.add(new WishlistItem("ISBN4", "Title4"));
        wishlist.add(new WishlistItem("ISBN5", "Title5"));
    }

    //Override the list() method to return the wishlist
    @Override
    public List<WishlistItem> list() {
        return this.wishlist;
    }

    //Override the find() method to search for a wishlist book by ISBN value
    @Override
    public WishlistItem find(String isbn) {
        for (WishlistItem item : wishlist) {
            if (item.getIsbn().equals(isbn)) {
                return item;
            }
        }
        return null; //no item with matching ISBN is found
    }
}

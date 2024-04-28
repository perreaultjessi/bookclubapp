package com.bookclub.web;

import com.bookclub.model.WishlistItem;
import com.bookclub.service.dao.WishlistDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/wishlist", produces = "application/json")
@CrossOrigin(origins = "*")
public class WishlistRestController {

    private WishlistDao wishlistDao;

    @Autowired
    public void setWishlistDao(WishlistDao wishlistDao) {
        this.wishlistDao = wishlistDao;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<WishlistItem> showWishlist() {
        return wishlistDao.list();
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public WishlistItem findById(@PathVariable String id) {
        return wishlistDao.find(id);
    }
}


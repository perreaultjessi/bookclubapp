package com.bookclub.web;

import com.bookclub.model.WishlistItem;
import com.bookclub.service.dao.WishlistDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/wishlist")
public class WishlistController {

    private WishlistDao wishlistDao;

    @Autowired
    public void setWishlistDao(WishlistDao wishlistDao) {
        this.wishlistDao = wishlistDao;
    }

    @GetMapping("/list")
    public String showWishlist(Model model) {
        List<WishlistItem> wishlist = wishlistDao.list();
        model.addAttribute("wishlist", wishlist);
        return "wishlist/list";
    }

    @GetMapping("/new")
    public String wishlistForm(Model model) {
        model.addAttribute("wishlistItem", new WishlistItem());
        return "wishlist/new";
    }

    @PostMapping
    public String addWishlistItem(@Valid @ModelAttribute("wishlistItem") WishlistItem wishlistItem, BindingResult bindingResult) {
        System.out.println(wishlistItem.toString()); // Printing the item for debugging
        if (bindingResult.hasErrors()) {
            return "wishlist/new";
        }
        wishlistDao.add(wishlistItem); // Add the record to MongoDB
        return "redirect:/wishlist";
    }
}



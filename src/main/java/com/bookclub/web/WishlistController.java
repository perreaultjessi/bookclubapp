package com.bookclub.web;

import com.bookclub.model.WishlistItem;
import com.bookclub.service.dao.WishlistDao;
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

    public WishlistController(WishlistDao wishlistDao) {
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
        if (bindingResult.hasErrors()) {
            return "wishlist/new";
        }
        return "redirect:/wishlist";
    }
}


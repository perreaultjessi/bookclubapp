package com.bookclub.web;

import com.bookclub.model.WishlistItem;
import com.bookclub.service.dao.WishlistDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/wishlist")
public class WishlistController
{

    private WishlistDao wishlistDao;

    @Autowired
    public void setWishlistDao(WishlistDao wishlistDao)
    {
        this.wishlistDao = wishlistDao;
    }

    @GetMapping("/list")
    public String showWishlist(Model model)
    {
        List<WishlistItem> wishlist = wishlistDao.list();
        model.addAttribute("wishlist", wishlist);
        return "wishlist/list";
    }

    @GetMapping("/new")
    public String wishlistForm(Model model)
    {
        model.addAttribute("wishlistItem", new WishlistItem());
        return "wishlist/new";
    }

    @PostMapping
    public String addWishlistItem(@Valid @ModelAttribute("wishlistItem") WishlistItem wishlistItem, BindingResult bindingResult)
    {
        if (bindingResult.hasErrors())
        {
            return "wishlist/new";
        }
        wishlistDao.add(wishlistItem);
        return "redirect:/wishlist";
    }

    @GetMapping("/{id}")
    public String showWishlistItem(@PathVariable String id, Model model)
    {
        WishlistItem wishlistItem = wishlistDao.find(id);
        model.addAttribute("wishlistItem", wishlistItem);
        return "wishlist/view";
    }

    @GetMapping("/edit/{id}")
    public String editWishlistItem(@PathVariable String id, Model model)
    {
        WishlistItem wishlistItem = wishlistDao.find(id);
        model.addAttribute("wishlistItem", wishlistItem);
        return "wishlist/edit";
    }

    @PostMapping("/update")
    public String updateWishlistItem(@Valid WishlistItem wishlistItem, BindingResult bindingResult, Authentication authentication)
    {
        wishlistItem.setUsername(authentication.getName());

        if (bindingResult.hasErrors())
        {
            return "wishlist/view";
        }

        wishlistDao.update(wishlistItem);

        return "redirect:/wishlist";
    }

    @GetMapping("/remove/{id}")
    public String removeWishlistItem(@PathVariable String id)
    {
        wishlistDao.remove(id);
        return "redirect:/wishlist";
    }
}

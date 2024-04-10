package com.bookclub.web;

import com.bookclub.model.Book;
import com.bookclub.service.dao.BookDao;
import com.bookclub.service.impl.MemBookDao;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/")
public class HomeController {

    private final BookDao bookDao;

    public HomeController(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String showHome(Model model) {
        // Call list() method to retrieve list of books from bookDao
        List<Book> books = bookDao.list();

        // Add books attribute to the model
        model.addAttribute("books", books);

        return "index";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public String getMonthlyBook(@PathVariable("id") String id, Model model) {
        String isbn = id;
        System.out.println(id);

        MemBookDao bookDao = new MemBookDao();
        Book book = bookDao.find(isbn);

        System.out.println(book);

        model.addAttribute("book", book);
        return "monthly-books/view";
    }

    @RequestMapping(method = RequestMethod.GET, path = "/about")
    public String showAboutUs(Model model) {
        return "about";
    }

    @RequestMapping(method = RequestMethod.GET, path = "/contact")
    public String showContactUs(Model model) {
        return "contact";
    }
}



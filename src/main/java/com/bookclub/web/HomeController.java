package com.bookclub.web;

import com.bookclub.model.Book;
import com.bookclub.model.BookOfTheMonth;
import com.bookclub.service.dao.BookOfTheMonthDao;
import com.bookclub.service.dao.RestBookDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Controller
public class HomeController
{

    private BookOfTheMonthDao bookOfTheMonthDao;

    @Autowired
    public void setBookOfTheMonthDao(BookOfTheMonthDao bookOfTheMonthDao)
    {
        this.bookOfTheMonthDao = bookOfTheMonthDao;
    }

    @GetMapping("/home")
    public String showHome(Model model)
    {
        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int calMonth = cal.get(Calendar.MONTH) + 1;

        List<BookOfTheMonth> monthlyBooks = bookOfTheMonthDao.list(Integer.toString(calMonth));

        StringBuilder isbnBuilder = new StringBuilder();
        isbnBuilder.append("ISBN:");

        for (BookOfTheMonth monthlyBook : monthlyBooks)
        {
            isbnBuilder.append(monthlyBook.getIsbn()).append(",");
        }

        String isbnString = isbnBuilder.toString().substring(0, isbnBuilder.toString().length() - 1);

        //instantiate RestBookDao and fetch books based on isbnString
        RestBookDao bookDao = new RestBookDao();
        List<Book> books = bookDao.list(isbnString);

        model.addAttribute("monthlyBooks", monthlyBooks);
        model.addAttribute("books", books);
        model.addAttribute("isbnString", isbnString);
        return "home";
    }
}
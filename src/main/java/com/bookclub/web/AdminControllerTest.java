package com.bookclub.web;

import com.bookclub.model.BookOfTheMonth;
import com.bookclub.service.dao.BookOfTheMonthDao;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AdminControllerTest {

    @Mock
    private BookOfTheMonthDao bookOfTheMonthDao;

    @InjectMocks
    private AdminController adminController;

    @Mock
    private Model model;

    //web test Book of the Month
    @Test
    public void testShowBookOfTheMonth() {
        List<BookOfTheMonth> books = Collections.singletonList(new BookOfTheMonth());
        when(bookOfTheMonthDao.list("999")).thenReturn(books);

        String viewName = adminController.showBookOfTheMonth(model);

        assertEquals("monthly-books/list", viewName);
        verify(model).addAttribute("books", books);
    }

    //JUnit test GetMonths
    @Test
    public void testGetMonths() {
        AdminController adminController = new AdminController();
        assertEquals(12, adminController.getMonths().size());
        assertEquals("January", adminController.getMonths().get(1));
    }
}

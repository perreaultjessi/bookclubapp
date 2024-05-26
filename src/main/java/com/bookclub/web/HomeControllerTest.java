package com.bookclub.web;
package com.bookclub.web;

import com.bookclub.model.BookOfTheMonth;
import com.bookclub.service.dao.BookOfTheMonthDao;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class HomeControllerTest {

    @Mock
    private BookOfTheMonthDao bookOfTheMonthDao;

    @InjectMocks
    private HomeController homeController;

    @Mock
    private Model model;

    //web test unique case book
    @Test
    public void testShowHomeWithMonthlyBooks() {
        //simulate list of monthly books
        List<BookOfTheMonth> monthlyBooks = new ArrayList<>();
        //add the fellowship of the ring as a monthly book
        monthlyBooks.add(new BookOfTheMonth("9780618640157"));

        when(bookOfTheMonthDao.list("5")).thenReturn(monthlyBooks);

        String viewName = homeController.showHome(model);

        //verify
        assertEquals("home", viewName);
        verify(model).addAttribute("monthlyBooks", monthlyBooks); // Verify that monthlyBooks attribute is added
    }

    //JUnit test unique case month
    @Test
    public void testGetMonths() {

        HomeController homeController = new HomeController();

        //verify
        assertEquals(12, homeController.getMonths().size());
        assertEquals("January", homeController.getMonths().get(1));
    }
}


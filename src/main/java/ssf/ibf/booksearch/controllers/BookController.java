package ssf.ibf.booksearch.controllers;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import ssf.ibf.booksearch.interfaces.BookService;
import ssf.ibf.booksearch.models.Book;

@Controller
@RequestMapping(path = "/book", produces = MediaType.TEXT_HTML_VALUE)
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping(path = "{worksID}")
    public String getBookById(@PathVariable("worksID") String worksID, Model model) {
        try {
            Book book = bookService.getBook(worksID);
            model.addAttribute("book", book);
            return "book";
        } catch (IOException e) {
            e.printStackTrace();
            return "error";
        }
    }
}

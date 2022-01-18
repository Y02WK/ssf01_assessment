package ssf.ibf.booksearch.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ssf.ibf.booksearch.models.Book;
import ssf.ibf.booksearch.services.BookService;

@Controller
@RequestMapping(path = "/search", produces = MediaType.TEXT_HTML_VALUE)
public class SearchController {

    @Autowired
    private BookService bookService;

    @GetMapping
    public String searchBooks(@RequestParam(name = "title") String searchQuery, Model model) {
        List<Book> bookResults = bookService.search(searchQuery);
        model.addAttribute("results", bookResults);
        return "results";
    }
}
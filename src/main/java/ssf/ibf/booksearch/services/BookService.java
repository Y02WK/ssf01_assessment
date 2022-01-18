package ssf.ibf.booksearch.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import ssf.ibf.booksearch.models.Book;

@Service
public class BookService {

    public List<Book> search(String searchTerm) {
        return new ArrayList<>();
    }
}

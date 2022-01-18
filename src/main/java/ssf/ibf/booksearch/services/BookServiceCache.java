package ssf.ibf.booksearch.services;

import static ssf.ibf.booksearch.constants.Constants.DELIMITER;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ssf.ibf.booksearch.interfaces.BookService;
import ssf.ibf.booksearch.models.Book;
import ssf.ibf.booksearch.models.QueryResult;
import ssf.ibf.booksearch.repositories.BookRepository;

@Service("BOOK_CACHE")
public class BookServiceCache implements BookService {
    @Autowired
    private BookServiceImpl delegate;

    @Autowired
    private BookRepository bookRepository;

    @Override
    public List<QueryResult> search(String searchTerm) throws IOException {
        return delegate.search(searchTerm);
    }

    @Override
    public Book getBook(String worksID) throws IOException {
        Book book;
        String bookAsString = bookRepository.getBook(worksID);
        if (bookAsString == null) {
            book = delegate.getBook(worksID);
            bookRepository.save(worksID, book.toString());
        } else {
            String[] bookParams = bookAsString.split(DELIMITER);
            book = new Book(bookParams);
        }
        return book;
    }

}

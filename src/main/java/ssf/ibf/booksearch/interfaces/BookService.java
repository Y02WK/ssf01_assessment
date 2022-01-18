package ssf.ibf.booksearch.interfaces;

import java.io.IOException;
import java.util.List;

import ssf.ibf.booksearch.models.Book;
import ssf.ibf.booksearch.models.QueryResult;

public interface BookService {
    public List<QueryResult> search(String searchTerm) throws IOException;

    public Book getBook(String worksID) throws IOException;
}

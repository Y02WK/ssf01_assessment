package ssf.ibf.booksearch.constants;

public class Constants {
    // for Redis
    public final static String REDIS_TEMPLATE = "REDIS_TEMPLATE";

    // for BookService search()
    public final static String SEARCH_LIMIT = "20";
    public final static String SEARCH_FIELDS = "key+title";
    public final static String SEARCH_ENDPOINT = "http://openlibrary.org/search.json";

    // for BookService getBook()
    public final static String BOOK_ENDPOINT = "https://openlibrary.org/works";

    // for BookService buildRequest()
    public final static String BOOK = "BOOK";
    public final static String SEARCH = "SEARCH";
}

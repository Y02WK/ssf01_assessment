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

    // for joining and splitting books data stored in Redis
    public final static String DELIMITER = "\\|";

    // for use in Book model
    public final static String NOT_FOUND = "NOT_FOUND";

    // for BookUtil
    public final static Integer RCS_LIMIT = 10;
    public final static String DESC_ERROR = "No description found! \u2639";
    public final static String EXCRP_ERROR = "No excerpt found! \u2639";
    public final static String TITLE_ERROR = "No title found! \u2639";
    public final static String[] DESC_KEYS = { "description", "value" };
    public final static String[] EXCRP_KEYS = { "excerpts", "excerpt", "value" };
    public final static String[] TITLE_KEYS = { "title", "value" };
}

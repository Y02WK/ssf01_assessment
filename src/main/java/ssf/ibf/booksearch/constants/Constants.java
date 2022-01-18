package ssf.ibf.booksearch.constants;

public class Constants {
    // for Redis
    public final static String REDIS_TEMPLATE = "REDIS_TEMPLATE";

    // for BookService
    public final static String SEARCH_LIMIT = "20";
    public final static String SEARCH_FIELDS = "key+title";
    public final static String SEARCH_ENDPOINT = "http://openlibrary.org/search.json";
}

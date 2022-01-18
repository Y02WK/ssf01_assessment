package ssf.ibf.booksearch.services;

import static ssf.ibf.booksearch.constants.Constants.BOOK;
import static ssf.ibf.booksearch.constants.Constants.BOOK_ENDPOINT;
import static ssf.ibf.booksearch.constants.Constants.SEARCH;
import static ssf.ibf.booksearch.constants.Constants.SEARCH_ENDPOINT;
import static ssf.ibf.booksearch.constants.Constants.SEARCH_FIELDS;
import static ssf.ibf.booksearch.constants.Constants.SEARCH_LIMIT;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import ssf.ibf.booksearch.interfaces.BookService;
import ssf.ibf.booksearch.models.Book;
import ssf.ibf.booksearch.models.QueryResult;
import ssf.ibf.booksearch.utils.BookUtil;

@Service // Non caching
@Primary
public class BookServiceImpl implements BookService {
    @Autowired
    private BookUtil bookUtil;

    private Logger logger = Logger.getLogger(BookServiceImpl.class.getName());

    public List<QueryResult> search(String searchTerm) throws IOException {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> resp = restTemplate.exchange(buildRequest(searchTerm, SEARCH), String.class);
        JsonObject jsonResp = readJsonObject(resp);
        return convertToModel(jsonResp);
    }

    public Book getBook(String worksID) throws IOException {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> resp = restTemplate.exchange(buildRequest(worksID, BOOK), String.class);
        JsonObject jsonResp = readJsonObject(resp);
        return convertToBook(jsonResp);
    }

    private RequestEntity<Void> buildRequest(String searchTerm, String type) {
        String url = "";
        if (type == BOOK) {
            url = UriComponentsBuilder
                    .fromUriString(BOOK_ENDPOINT)
                    .pathSegment(searchTerm + ".json")
                    .toUriString();
        } else if (type == SEARCH) {
            url = UriComponentsBuilder
                    .fromUriString(SEARCH_ENDPOINT)
                    .queryParam("title", searchTerm)
                    .queryParam("fields", SEARCH_FIELDS)
                    .queryParam("limit", SEARCH_LIMIT)
                    .toUriString();
        }

        RequestEntity<Void> req = RequestEntity.get(url).build();
        return req;
    }

    private JsonObject readJsonObject(ResponseEntity<String> response) throws IOException {
        try (InputStream is = new ByteArrayInputStream(response.getBody().getBytes())) {
            JsonReader reader = Json.createReader(is);
            JsonObject data = reader.readObject();
            return data;
        }
    }

    private List<QueryResult> convertToModel(JsonObject jsonObject) {
        JsonArray searchResults = jsonObject.getJsonArray("docs");
        return searchResults.stream()
                .map(b -> (JsonObject) b)
                .map(QueryResult::create)
                .collect(Collectors.toList());
    }

    private Book convertToBook(JsonObject jsonObject) {
        Book book = new Book();
        book.setTitle(jsonObject.getString("title"));

        String desc = bookUtil.checkForDesc(jsonObject);
        if (!desc.isEmpty()) {
            book.setDesc(desc);
        }

        String excerpt = bookUtil.checkForExcerpt(jsonObject);
        if (!excerpt.isEmpty()) {
            book.setExcerpt(excerpt);
        }

        if (jsonObject.containsKey("covers")) {
            book.setCover(jsonObject.getJsonArray("covers").get(0).toString());
        }
        return book;
    }
}

package ssf.ibf.booksearch.services;

import static ssf.ibf.booksearch.constants.Constants.SEARCH_ENDPOINT;
import static ssf.ibf.booksearch.constants.Constants.SEARCH_FIELDS;
import static ssf.ibf.booksearch.constants.Constants.SEARCH_LIMIT;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import ssf.ibf.booksearch.models.QueryResult;

@Service // Non caching
public class BookService {

    public List<QueryResult> search(String searchTerm) throws IOException {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> resp = restTemplate.exchange(buildRequest(searchTerm), String.class);
        JsonObject jsonResp = readJsonObject(resp);
        return convertToModel(jsonResp);
    }

    private RequestEntity<Void> buildRequest(String searchTerm) {
        String url = UriComponentsBuilder
                .fromUriString(SEARCH_ENDPOINT)
                .queryParam("title", searchTerm)
                .queryParam("fields", SEARCH_FIELDS)
                .queryParam("limit", SEARCH_LIMIT)
                .toUriString();

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
        List<QueryResult> resultsList = new LinkedList<>();

        return resultsList = searchResults.stream()
                .map(b -> (JsonObject) b)
                .map(QueryResult::create)
                .collect(Collectors.toList());
    }
}

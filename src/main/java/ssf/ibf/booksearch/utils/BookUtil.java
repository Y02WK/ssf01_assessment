package ssf.ibf.booksearch.utils;

import org.springframework.stereotype.Component;

import jakarta.json.JsonObject;

@Component
public class BookUtil {
    public String checkForDesc(JsonObject jsonObj) {
        if (jsonObj.containsKey("description")) {
            return jsonObj.getValueType().toString();
        }
        return "";
    }
}

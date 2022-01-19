package ssf.ibf.booksearch.utils;

import org.springframework.stereotype.Component;

import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonValue;
import jakarta.json.JsonValue.ValueType;

@Component
public class BookUtil {

    // recursive implementation to get description
    public String getDesc(JsonObject jsonObj) {
        ValueType value = null;
        String key = null;

        if (jsonObj.containsKey("description")) {
            value = jsonObj.get("description").getValueType();
            key = "description";
        } else if (jsonObj.containsKey("value")) {
            value = jsonObj.get("value").getValueType();
            key = "value";
        }

        if (value == ValueType.STRING) {
            return jsonObj.getString(key);
        } else if (value == ValueType.OBJECT) {
            return getDesc(jsonObj.getJsonObject(key));
        } else if (value == ValueType.ARRAY) {
            return getDesc(jsonObj.getJsonArray(key));
        } else {
            return "";
        }
    }

    private String getDesc(JsonArray jsonArray) {
        if (jsonArray.size() >= 1) {
            for (JsonValue obj : jsonArray) {
                String excerpt = getDesc((JsonObject) obj);
                if (!excerpt.isEmpty()) {
                    return excerpt;
                }
            }
        }
        return "";
    }

    // recursive implementation to get excerpt
    public String getExcerpt(JsonObject jsonObj) {
        ValueType value = null;
        String key = null;

        if (jsonObj.containsKey("excerpt")) {
            value = jsonObj.get("excerpt").getValueType();
            key = "excerpt";
        } else if (jsonObj.containsKey("excerpts")) {
            value = jsonObj.get("excerpts").getValueType();
            key = "excerpts";
        } else if (jsonObj.containsKey("value")) {
            value = jsonObj.get("value").getValueType();
            key = "value";
        }

        if (value == ValueType.STRING) {
            return jsonObj.getString(key);
        } else if (value == ValueType.OBJECT) {
            return getExcerpt(jsonObj.getJsonObject(key));
        } else if (value == ValueType.ARRAY) {
            return getExcerpt(jsonObj.getJsonArray(key));
        } else {
            return "";
        }
    }

    private String getExcerpt(JsonArray jsonArray) {
        if (jsonArray.size() >= 1) {
            for (JsonValue obj : jsonArray) {
                String excerpt = getExcerpt((JsonObject) obj);
                if (!excerpt.isEmpty()) {
                    return excerpt;
                }
            }
        }
        return "";
    }
}

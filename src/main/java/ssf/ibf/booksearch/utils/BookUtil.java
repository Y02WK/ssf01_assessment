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
        ValueType valueType = null;
        String key = null;

        if (jsonObj.containsKey("description")) {
            valueType = jsonObj.get("description").getValueType();
            key = "description";
        } else if (jsonObj.containsKey("value")) {
            valueType = jsonObj.get("value").getValueType();
            key = "value";
        }

        if (valueType == ValueType.STRING) {
            return jsonObj.getString(key);
        } else if (valueType == ValueType.OBJECT) {
            return getDesc(jsonObj.getJsonObject(key));
        } else if (valueType == ValueType.ARRAY) {
            return getDesc(jsonObj.getJsonArray(key));
        } else {
            return "No description found! \u2639";
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
        return "No description found! \u2639";
    }

    // recursive implementation to get excerpt
    public String getExcerpt(JsonObject jsonObj) {
        ValueType valueType = null;
        String key = null;

        if (jsonObj.containsKey("excerpt")) {
            valueType = jsonObj.get("excerpt").getValueType();
            key = "excerpt";
        } else if (jsonObj.containsKey("excerpts")) {
            valueType = jsonObj.get("excerpts").getValueType();
            key = "excerpts";
        } else if (jsonObj.containsKey("value")) {
            valueType = jsonObj.get("value").getValueType();
            key = "value";
        }

        if (valueType == ValueType.STRING) {
            return jsonObj.getString(key);
        } else if (valueType == ValueType.OBJECT) {
            return getExcerpt(jsonObj.getJsonObject(key));
        } else if (valueType == ValueType.ARRAY) {
            return getExcerpt(jsonObj.getJsonArray(key));
        } else {
            return "No excerpt found! \u2639";
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
        return "No excerpt found! \u2639";
    }
}

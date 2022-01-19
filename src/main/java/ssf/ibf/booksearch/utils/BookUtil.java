package ssf.ibf.booksearch.utils;

import static ssf.ibf.booksearch.constants.Constants.DESC_ERROR;
import static ssf.ibf.booksearch.constants.Constants.EXCRP_ERROR;

import org.springframework.stereotype.Component;

import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonValue;
import jakarta.json.JsonValue.ValueType;

@Component
public class BookUtil {

    public String getDesc(JsonObject jsonObject) {
        return getDesc(jsonObject, 0);
    }

    private String getDesc(JsonObject jsonObj, Integer count) {
        if (count == 10) {
            return DESC_ERROR;
        }
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
            return getDesc(jsonObj.getJsonObject(key), count + 1);
        } else if (valueType == ValueType.ARRAY) {
            return getDesc(jsonObj.getJsonArray(key), count + 1);
        } else {
            return DESC_ERROR;
        }
    }

    private String getDesc(JsonArray jsonArray, Integer count) {
        if (jsonArray.size() >= 1 && count < 10) {
            for (JsonValue obj : jsonArray) {
                String excerpt = getDesc((JsonObject) obj, count + 1);
                if (!excerpt.isEmpty()) {
                    return excerpt;
                }
            }
        }
        return DESC_ERROR;
    }

    public String getExcerpt(JsonObject jsonObj) {
        return getExcerpt(jsonObj, 0);
    }

    // recursive implementation to get excerpt
    private String getExcerpt(JsonObject jsonObj, Integer count) {
        if (count == 10) {
            return EXCRP_ERROR;
        }

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
            return getExcerpt(jsonObj.getJsonObject(key), count + 1);
        } else if (valueType == ValueType.ARRAY) {
            return getExcerpt(jsonObj.getJsonArray(key), count + 1);
        } else {
            return EXCRP_ERROR;
        }
    }

    private String getExcerpt(JsonArray jsonArray, Integer count) {
        if (jsonArray.size() >= 1 && count < 10) {
            for (JsonValue obj : jsonArray) {
                String excerpt = getExcerpt((JsonObject) obj);
                if (!excerpt.isEmpty()) {
                    return excerpt;
                }
            }
        }
        return EXCRP_ERROR;
    }
}

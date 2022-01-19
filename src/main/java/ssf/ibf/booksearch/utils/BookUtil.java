package ssf.ibf.booksearch.utils;

import java.util.logging.Logger;

import org.springframework.stereotype.Component;

import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonValue;
import jakarta.json.JsonValue.ValueType;

@Component
public class BookUtil {
    private Logger logger = Logger.getLogger(BookUtil.class.getName());

    public String checkForDesc(JsonObject jsonObj) {
        String desc = "";
        if (jsonObj.containsKey("description")) {
            try {
                desc = jsonObj.getString("description");
            } catch (ClassCastException e) {
                try {
                    if (jsonObj.getJsonObject("description").containsKey("value")) {
                        JsonObject descObj = jsonObj.getJsonObject("description");
                        desc = descObj.getString("value");
                    }
                } catch (ClassCastException e2) {
                    logger.info("Book description not found");
                }
            }
        }
        logger.info("Book Description: " + desc);
        return desc;
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
                if (!getExcerpt((JsonObject) obj).isEmpty()) {
                    return getExcerpt((JsonObject) obj);
                }
            }
        }
        return "";
    }
}

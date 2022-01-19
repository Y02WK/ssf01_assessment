package ssf.ibf.booksearch.utils;

import static ssf.ibf.booksearch.constants.Constants.EXCRP_ERROR;
import static ssf.ibf.booksearch.constants.Constants.RCS_LIMIT;

import org.springframework.stereotype.Component;

import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonValue;
import jakarta.json.JsonValue.ValueType;

@Component
public class BookUtil {
    public String getDetails(JsonObject jsonObject, String[] keyArray, String defaultError) {
        return getDetails(jsonObject, keyArray, defaultError, 0);
    }

    // method to handle JsonObject
    private String getDetails(JsonObject jsonObject,
            String[] keyArray,
            String defaultError,
            Integer count) {

        // base case #1: if recursion count hits 10
        if (count == RCS_LIMIT) {
            return defaultError;
        }

        ValueType valueType = null;
        String key = null;

        for (String s : keyArray) {
            if (jsonObject.containsKey(s)) {
                valueType = jsonObject.get(s).getValueType();
                key = s;
            }
        }
        // Base case #2: String value found
        if (valueType == ValueType.STRING) {
            return jsonObject.getString(key);
        } else if (valueType == ValueType.OBJECT) {
            return getDetails(jsonObject.getJsonObject(key), keyArray, defaultError, count + 1);
        } else if (valueType == ValueType.ARRAY) {
            return getDetails(jsonObject.getJsonArray(key), keyArray, defaultError, count + 1);
            // Base case #3: Nothing found
        } else {
            return defaultError;
        }
    }

    // method to handle JsonArray
    private String getDetails(JsonArray jsonArray, String[] keyArray,
            String defaultError, Integer count) {
        if (jsonArray.size() >= 1 && count < 10) {
            for (JsonValue obj : jsonArray) {
                String excerpt = getDetails((JsonObject) obj, keyArray, defaultError, count + 1);
                if (!excerpt.equals(defaultError)) {
                    return excerpt;
                }
            }
        }
        return EXCRP_ERROR;
    }
}

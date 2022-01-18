package ssf.ibf.booksearch.utils;

import java.util.logging.Logger;

import org.springframework.stereotype.Component;

import jakarta.json.JsonObject;

@Component
public class BookUtil {
    private Logger logger = Logger.getLogger(BookUtil.class.getName());

    public String checkForDesc(JsonObject jsonObj) {
        String desc = "";
        if (jsonObj.containsKey("description")) {
            try {
                desc = jsonObj.getString("description");
            } catch (ClassCastException e) {
                if (jsonObj.getJsonObject("description").containsKey("value")) {
                    JsonObject descObj = jsonObj.getJsonObject("description");
                    desc = descObj.getString("value");
                }
            }
        }
        return desc;
    }
}

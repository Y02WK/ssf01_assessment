package ssf.ibf.booksearch.utils;

import java.util.logging.Logger;

import org.springframework.stereotype.Component;

import jakarta.json.JsonArray;
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
                try {
                    if (jsonObj.getJsonObject("description").containsKey("value")) {
                        JsonObject descObj = jsonObj.getJsonObject("description");
                        desc = descObj.getString("value");
                    }
                } catch (ClassCastException e2) {
                    return "";
                }
            }
        }
        logger.info("Book Description: " + desc);
        return desc;
    }

    public String checkForExcerpt(JsonObject jsonObj) {
        if (jsonObj.containsKey("excerpts") && jsonObj.getJsonArray("excerpts").size() >= 1) {
            try {
                JsonArray excerpts = jsonObj.getJsonArray("excerpts");
                JsonObject firstExcerpt = excerpts.getJsonObject(0);
                logger.info("Book Excerpt: " + firstExcerpt.getString("excerpt"));
                return firstExcerpt.getString("excerpt");
            } catch (ClassCastException e) {
                logger.info("Book excerpt not found");
                return "";
            }
        }
        return "";
    }
}

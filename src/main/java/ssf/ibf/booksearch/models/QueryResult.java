package ssf.ibf.booksearch.models;

import jakarta.json.JsonObject;

public class QueryResult {
    private String title;
    private String worksId;

    public static QueryResult create(JsonObject jsonObject) {
        final QueryResult result = new QueryResult();
        result.setTitle(jsonObject.getString("title"));
        result.setWorksId(jsonObject.getString("key").replace("works", "book"));
        return result;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getWorksId() {
        return this.worksId;
    }

    public void setWorksId(String worksId) {
        this.worksId = worksId;
    }
}

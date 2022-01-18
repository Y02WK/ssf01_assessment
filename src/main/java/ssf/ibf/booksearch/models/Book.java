package ssf.ibf.booksearch.models;

public class Book {
    private String title;
    private String desc;
    private String excerpt;
    private boolean isCached;

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return this.desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getExcerpt() {
        return this.excerpt;
    }

    public void setExcerpt(String excerpt) {
        this.excerpt = excerpt;
    }

    public boolean isIsCached() {
        return this.isCached;
    }

    public boolean getIsCached() {
        return this.isCached;
    }

    public void setIsCached(boolean isCached) {
        this.isCached = isCached;
    }

}

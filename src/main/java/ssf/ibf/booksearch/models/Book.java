package ssf.ibf.booksearch.models;

public class Book {
    private String title;
    private String desc;
    private String excerpt;
    private boolean isCached = false;

    public Book(String[] bookParams) {
        this.title = bookParams[0];
        this.desc = bookParams[1];
        this.excerpt = bookParams[2];
        this.isCached = true;
    }

    public Book() {
    }

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

    @Override
    public String toString() {
        return String.join("|", this.title, this.desc, this.excerpt);
    }
}

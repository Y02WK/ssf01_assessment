package ssf.ibf.booksearch.models;

import static ssf.ibf.booksearch.constants.Constants.NOT_FOUND;

public class Book {
    private String title;
    private String desc = NOT_FOUND;
    private String excerpt = NOT_FOUND;
    private String cover = NOT_FOUND;
    private boolean cached = false;

    public Book(String[] bookParams) {
        this.title = bookParams[0];
        this.desc = bookParams[1];
        this.excerpt = bookParams[2];
        this.cover = bookParams[3];
        this.cached = true;
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

    public boolean isCached() {
        return this.cached;
    }

    public void setIsCached(boolean cached) {
        this.cached = cached;
    }

    public String getCover() {
        return this.cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    @Override
    public String toString() {
        return String.join("|", this.title, this.desc, this.excerpt, this.cover);
    }
}

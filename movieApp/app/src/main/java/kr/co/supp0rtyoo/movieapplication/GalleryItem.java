package kr.co.supp0rtyoo.movieapplication;

public class GalleryItem {
    private String url;
    private int type;

    public GalleryItem(String url, int type) {
        this.url = url;
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public int getType() {
        return type;
    }
}

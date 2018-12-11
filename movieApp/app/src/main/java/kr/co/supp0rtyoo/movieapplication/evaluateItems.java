package kr.co.supp0rtyoo.movieapplication;

public class evaluateItems {
    private String writerID;
    private String contents;
    private float rating;
    private String writeTime;
    private int recommend;

    public int getRecommend() {
        return recommend;
    }

    public void setRecommend(int recommend) {
        this.recommend = recommend;
    }

    public evaluateItems(String writerID, String contents, float rating, String writeTime, int recommend) {
        this.writerID = writerID;
        this.contents = contents;
        this.rating = rating;
        this.writeTime = writeTime;
        this.recommend = recommend;
    }

    public String getWriterID() {
        return writerID;
    }

    public String getWriteTime() {
        return writeTime;
    }

    public void setWriteTime(String writeTime) {
        this.writeTime = writeTime;
    }

    public void setWriterID(String writerID) {
        this.writerID = writerID;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public float getRating() {

        return rating;
    }

    public String getContents() {
        return contents;

    }

}

package kr.co.supp0rtyoo.movieapplication;

public class evaluateItems {
    private String writerID;
    private String contents;
    private float rating;
    private int writeTime;

    public evaluateItems(String writerID, String contents, float rating, int writeTime) {
        this.writerID = writerID;
        this.contents = contents;
        this.rating = rating;
        this.writeTime = writeTime;
    }

    public String getWriterID() {
        return writerID;
    }

    public int getWriteTime() {
        return writeTime;
    }

    public void setWriteTime(int writeTime) {
        this.writeTime = writeTime;
    }

    public void setWriterID(String writerID) {
        this.writerID = writerID;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    @Override
    public String toString() {
        return "evaluateItems{" +
                "writerID='" + writerID + '\'' +
                ", contents='" + contents + '\'' +
                ", rating=" + rating +
                ", writeTime='" + writeTime + '\'' +
                '}';
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

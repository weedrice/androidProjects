package kr.co.supp0rtyoo.movieapplication;

public class evaluateItems {
    private String writerID;
    private String contents;

    public evaluateItems(String writerID, String contents) {
        this.writerID = writerID;
        this.contents = contents;
    }

    public String getWriterID() {
        return writerID;
    }

    public void setWriterID(String writerID) {
        this.writerID = writerID;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public String getContents() {
        return contents;

    }

    @Override
    public String toString() {
        return "evaluateItems{" +
                "writerID=" + writerID +
                ", contents=" + contents +
                '}';
    }
}

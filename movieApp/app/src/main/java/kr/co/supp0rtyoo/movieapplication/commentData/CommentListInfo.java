package kr.co.supp0rtyoo.movieapplication.commentData;

public class CommentListInfo {
    int total;
    String writer;
    String review_id;
    String writer_image;
    String time;
    String timestamp;
    float rating;
    String contents;
    int recommend;

    @Override
    public String toString() {
        return "CommentListInfo{" +
                "total=" + total +
                ", writer='" + writer + '\'' +
                ", review_id='" + review_id + '\'' +
                ", writer_image='" + writer_image + '\'' +
                ", time='" + time + '\'' +
                ", timestamp='" + timestamp + '\'' +
                ", rating=" + rating +
                ", contents='" + contents + '\'' +
                ", recommend=" + recommend +
                '}';
    }

    public int getTotal() {
        return total;
    }

    public String getWriter() {
        return writer;
    }

    public String getReview_id() {
        return review_id;
    }

    public String getWriter_image() {
        return writer_image;
    }

    public String getTime() {
        return time;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public float getRating() {
        return rating;
    }

    public String getContents() {
        return contents;
    }

    public int getRecommend() {
        return recommend;
    }
}

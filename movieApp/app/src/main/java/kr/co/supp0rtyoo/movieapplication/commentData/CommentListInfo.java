package kr.co.supp0rtyoo.movieapplication.commentData;

public class CommentListInfo {
    int id;
    String writer;
    String movieId;
    String writer_image;
    String time;
    String timestamp;
    float rating;
    String contents;
    int recommend;

    public void setId(int id) {
        this.id = id;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public void setWriter_image(String writer_image) {
        this.writer_image = writer_image;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public void setRecommend(int recommend) {
        this.recommend = recommend;
    }

    @Override
    public String toString() {
        return "CommentListInfo{" +
                "id=" + id +
                ", writer='" + writer + '\'' +
                ", movieId='" + movieId + '\'' +
                ", writer_image='" + writer_image + '\'' +
                ", time='" + time + '\'' +
                ", timestamp='" + timestamp + '\'' +
                ", rating=" + rating +
                ", contents='" + contents + '\'' +
                ", recommend=" + recommend +
                '}';
    }

    public int getId() {
        return id;
    }

    public String getWriter() {
        return writer;
    }

    public String getMovieId() {
        return movieId;
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

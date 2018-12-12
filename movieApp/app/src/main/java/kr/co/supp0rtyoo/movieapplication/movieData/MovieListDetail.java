package kr.co.supp0rtyoo.movieapplication.movieData;

public class MovieListDetail {
    int id;
    String title;
    String title_eng;
    String date;
    float audience_rating;
    float user_rating;
    float reviewer_rating;
    float reservation_rate;
    int reservation_grade;
    int grade;
    String thumb;
    String image;

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setTitle_eng(String title_eng) {
        this.title_eng = title_eng;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setAudience_rating(float audience_rating) {
        this.audience_rating = audience_rating;
    }

    public void setUser_rating(float user_rating) {
        this.user_rating = user_rating;
    }

    public void setReviewer_rating(float reviewer_rating) {
        this.reviewer_rating = reviewer_rating;
    }

    public void setReservation_rate(float reservation_rate) {
        this.reservation_rate = reservation_rate;
    }

    public void setReservation_grade(int reservation_grade) {
        this.reservation_grade = reservation_grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "MovieListDetail{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", title_eng='" + title_eng + '\'' +
                ", date='" + date + '\'' +
                ", audience_rating=" + audience_rating +
                ", user_rating=" + user_rating +
                ", reviewer_rating=" + reviewer_rating +
                ", reservation_rate=" + reservation_rate +
                ", reservation_grade=" + reservation_grade +
                ", grade=" + grade +
                ", thumb='" + thumb + '\'' +
                ", image='" + image + '\'' +
                '}';
    }

    public String getTitle() {
        return title;
    }

    public String getDate() {
        return date;
    }

    public int getId() {
        return id;
    }

    public String getTitle_eng() {
        return title_eng;
    }

    public float getAudience_rating() {
        return audience_rating;
    }

    public float getUser_rating() {
        return user_rating;
    }

    public float getReviewer_rating() {
        return reviewer_rating;
    }

    public float getReservation_rate() {
        return reservation_rate;
    }

    public int getReservation_grade() {
        return reservation_grade;
    }

    public int getGrade() {
        return grade;
    }

    public String getThumb() {
        return thumb;
    }

    public String getImage() {
        return image;
    }
}

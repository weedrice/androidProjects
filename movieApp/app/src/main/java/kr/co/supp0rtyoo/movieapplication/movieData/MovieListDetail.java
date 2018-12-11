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

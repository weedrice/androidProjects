package kr.co.supp0rtyoo.movieapplication.movieData;

import java.util.ArrayList;

public class MovieDetail {
    String message;
    int code;
    String resultType;
    ArrayList<MovieDetailInfo> result;

    public ArrayList<MovieDetailInfo> getResult() {
        return result;
    }
}

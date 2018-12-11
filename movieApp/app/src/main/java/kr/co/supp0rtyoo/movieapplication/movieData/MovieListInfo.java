package kr.co.supp0rtyoo.movieapplication.movieData;

import java.util.ArrayList;

public class MovieListInfo {
    String message;
    int code;
    String resultType;
    ArrayList<MovieListDetail> result = new ArrayList<MovieListDetail>();

    @Override
    public String toString() {
        return "MovieListInfo{" +
                "message='" + message + '\'' +
                ", code=" + code +
                ", resultType='" + resultType + '\'' +
                ", result=" + result +
                '}';
    }

    public ArrayList<MovieListDetail> getResult() {
        return result;
    }
}

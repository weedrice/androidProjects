package kr.co.supp0rtyoo.movieapplication.commentData;

import java.util.ArrayList;

public class CommentList {
    String message;
    int code;
    String resultType;
    ArrayList<CommentListInfo> result;

    public ArrayList<CommentListInfo> getResult() {
        return result;
    }
}

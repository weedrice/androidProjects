package kr.co.supp0rtyoo.movieapplication.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

import kr.co.supp0rtyoo.movieapplication.commentData.CommentList;
import kr.co.supp0rtyoo.movieapplication.commentData.CommentListInfo;

public class CommentListDatabaseManager {
    public static SQLiteDatabase commentListDatabase;
    private static final String TAG = "commentListDataManger";
    private static String tableName = "COMMENT_LIST";

    public static void createDatabase(Context context, String databaseName) {
        commentListDatabase = context.openOrCreateDatabase(databaseName, Context.MODE_PRIVATE, null);
        if(commentListDatabase != null)
            Log.d(TAG, "database created.");

        createTable();
    }

    public static void createTable() {
        String makeTable = "CREATE TABLE IF NOT EXISTS "+ tableName +
                "(" +
                " _ID integer primary key autoincrement, " +
                " id integer, " +
                " writer text, " +
                " movieId integer, " +
                " writer_image text, " +
                " time text, " +
                " timestamp text, " +
                " rating float, " +
                " contents text, " +
                " recommend integer" +
                ")"
                ;

        commentListDatabase.execSQL(makeTable);
        Log.d(TAG, "table created");
    }

    public static void deleteData(Context context, String databaseName) {
        context.deleteDatabase(databaseName);
    }

    public static void insertData(CommentList commentList) {
        String insert = "INSERT INTO " + tableName +
                " ( id, writer, movieId, writer_image, time, timestamp, rating, contents, recommend )" +
                " VALUES (?, ?, ?, ?, ?, ?, ? ,?, ?)";

        ArrayList<CommentListInfo> commentListInfo = commentList.getResult();

        for(int i=0;i<commentListInfo.size();i++) {
            CommentListInfo commentInfo = commentListInfo.get(i);
            String exist = "SELECT movieId FROM " + tableName + " WHERE id = " + commentInfo.getId() + " AND movieId = " + commentInfo.getMovieId();
            Cursor cursor = commentListDatabase.rawQuery(exist, null);

            Log.d(TAG, "현재 ID: " + String.valueOf(commentInfo.getId()) + "현재 MovieID: " + String.valueOf(commentInfo.getMovieId()));

            if(cursor.getCount() > 0) {
                Log.d(TAG, "already Exist data");
            } else {
                Object[] params = {commentInfo.getId(), commentInfo.getWriter(), commentInfo.getMovieId(),
                        commentInfo.getWriter_image(), commentInfo.getTime(), commentInfo.getTimestamp(),
                        commentInfo.getRating(), commentInfo.getContents(), commentInfo.getRecommend() };
                commentListDatabase.execSQL(insert, params);
                Log.d(TAG, "inserted success");
            }

            cursor.close();
        }

    }

    public static ArrayList<CommentListInfo> getDataFromDatabase(int id) {
        String selectQuery = "SELECT id, writer, movieId, writer_image, time," +
                " timestamp, rating, contents, recommend" +
                " FROM " + tableName +
                " WHERE movieId = " + id;

        Log.d(TAG, "before get data " + "id: " + String.valueOf(id));
        Cursor cursor = commentListDatabase.rawQuery(selectQuery, null);
        Log.d(TAG, "cursor size: " + String.valueOf(cursor.getCount()));

        ArrayList<CommentListInfo> commentListInfo = new ArrayList<CommentListInfo>();
        for(int i=0;i<cursor.getCount();i++) {
            cursor.moveToNext();

            CommentListInfo commentInfo = new CommentListInfo();
            commentInfo.setId(cursor.getInt(0));
            commentInfo.setWriter(cursor.getString(1));
            commentInfo.setMovieId(cursor.getString(2));
            commentInfo.setWriter_image(cursor.getString(3));
            commentInfo.setTime(cursor.getString(4));
            commentInfo.setTimestamp(cursor.getString(5));
            commentInfo.setRating(cursor.getInt(6));
            commentInfo.setContents(cursor.getString(7));
            commentInfo.setRecommend(cursor.getInt(8));
            Log.d(TAG,commentInfo.toString());
            commentListInfo.add(commentInfo);
        }

        cursor.close();

        return commentListInfo;
    }
}

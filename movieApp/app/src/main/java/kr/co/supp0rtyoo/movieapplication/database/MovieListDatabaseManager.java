package kr.co.supp0rtyoo.movieapplication.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

import kr.co.supp0rtyoo.movieapplication.movieData.MovieListDetail;

public class MovieListDatabaseManager {
    public static SQLiteDatabase movieListDatabase;
    private static final String TAG = "movieListManager";
    private static String tableName = "MOVIE_LIST";

    public static void createDatabase(Context context, String databaseName) {
        movieListDatabase = context.openOrCreateDatabase(databaseName, Context.MODE_PRIVATE, null);
        if(movieListDatabase != null)
            Log.d(TAG, "database created.");

        createTable();
    }

    public static void insertData(int id, String title, String title_eng, String opening, float user_rating, float audience_rating,
                                  float reviewer_rating, float reservation_rate, int reservation_grade, int grade, String thumbURL, String imageURL) {
        String insert = "INSERT INTO " + tableName +
                "( id, title, title_eng, opening, user_rating, audience_rating, reviewer_rating, reservation_rate, " +
                " reservation_grade, grade, thumbURL, imageURL )" +
                " VALUES (?, ?, ?, ?, ?, ?, ? ,?, ?, ?, ?, ?)";

        String exist = "SELECT id FROM " + tableName + " WHERE id = " + id;
        Cursor cursor = movieListDatabase.rawQuery(exist, null);

        if(cursor.getCount() > 0) {
            Log.d(TAG, "already Exist data");
        } else {
            Object[] params = {id, title, title_eng, opening, user_rating, audience_rating, reviewer_rating, reservation_rate, reservation_grade, grade, thumbURL, imageURL};
            movieListDatabase.execSQL(insert, params);
            Log.d(TAG, "inserted success");
        }
    }

    /*
    public static void seeAllDatabase() {
        String selectAll = "SELECT * FROM " + tableName;
        Cursor cursor = movieListDatabase.rawQuery(selectAll, null);
        for(int i=0;i<cursor.getCount();i++) {
            cursor.moveToNext();
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(cursor.getInt(0));
            stringBuffer.append(", " + cursor.getString(1));
            stringBuffer.append(", " + cursor.getString(2));
            stringBuffer.append(", " + cursor.getString(3));
            stringBuffer.append(", " + cursor.getString(4));
            Log.d(TAG, stringBuffer.toString());
        }
    }
    */

    public static ArrayList<MovieListDetail> getDataFromDatabase() {
        String selectQuery = "SELECT id, title, reservation_rate, grade " +
                "FROM " + tableName;
        Cursor cursor = movieListDatabase.rawQuery(selectQuery, null);
        ArrayList<MovieListDetail> movieListDetails = new ArrayList<MovieListDetail>();
        for(int i=0;i<cursor.getCount();i++) {
            cursor.moveToNext();
            MovieListDetail movieListDetail = new MovieListDetail();
            movieListDetail.setId(cursor.getInt(0));
            movieListDetail.setTitle(cursor.getString(1));
            movieListDetail.setReservation_rate(cursor.getFloat(2));
            movieListDetail.setGrade(cursor.getInt(3));
            movieListDetails.add(movieListDetail);
        }

        return movieListDetails;
    }

    public static void createTable() {
        String makeTable = "CREATE TABLE IF NOT EXISTS "+ tableName +
                "(" +
                " _ID integer primary key autoincrement, " +
                " id integer, " +
                " title text, " +
                " title_eng text, " +
                " opening text, " +
                " user_rating float, " +
                " audience_rating float, " +
                " reviewer_rating float, " +
                " reservation_rate float, " +
                " reservation_grade integer, " +
                " grade text, " +
                " thumbURL text, " +
                " imageURL text " +
                ")"
                ;


        movieListDatabase.execSQL(makeTable);
        Log.d(TAG, "table created");
    }
}

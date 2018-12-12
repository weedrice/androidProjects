package kr.co.supp0rtyoo.movieapplication.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;

import kr.co.supp0rtyoo.movieapplication.movieData.MovieDetail;
import kr.co.supp0rtyoo.movieapplication.movieData.MovieDetailInfo;

public class MovieDetailDatabaseManager {
    public static SQLiteDatabase movieDetailDatabase;
    private static final String TAG = "movieDetailManager";
    private static String tableName = "MOVIE_DETAIL";

    public static void createDatabase(Context context, String databaseName) {
        movieDetailDatabase = context.openOrCreateDatabase(databaseName, Context.MODE_PRIVATE, null);
        if(movieDetailDatabase != null)
            Log.d(TAG, "database created.");

        createTable();
    }

    public static void deleteData(Context context, String databaseName) {
        context.deleteDatabase(databaseName);
    }

    public static void insertData(MovieDetail movieDetail) {
        String insert = "INSERT INTO " + tableName +
                " ( id, title, opening, user_rating, audience_rating, reservation_rating, reservation_grade, grade, thumbURL, genre," +
                " duration, audience, synopsis, director, actor, thumbsup, thumbsdown )" +
                " VALUES (?, ?, ?, ?, ?, ?, ? ,?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        MovieDetailInfo movieDetailInfo = movieDetail.getResult().get(0);

        String exist = "SELECT id FROM " + tableName + " WHERE id = " + movieDetailInfo.getId();
        Cursor cursor = movieDetailDatabase.rawQuery(exist, null);

        if(cursor.getCount() > 0) {
            Log.d(TAG, "already Exist data");
        } else {
            Object[] params = {movieDetailInfo.getId(), movieDetailInfo.getTitle(), movieDetailInfo.getDate(),
                    movieDetailInfo.getUser_rating(), movieDetailInfo.getAudience_rating(), movieDetailInfo.getReservation_rate(),
                    movieDetailInfo.getReservation_grade(), movieDetailInfo.getGrade(), movieDetailInfo.getThumb(),
                    movieDetailInfo.getGenre(), movieDetailInfo.getDuration(), movieDetailInfo.getAudience(),
                    movieDetailInfo.getSynopsis(), movieDetailInfo.getDirector(), movieDetailInfo.getActor(),
                    movieDetailInfo.getLike(), movieDetailInfo.getDislike()};
            movieDetailDatabase.execSQL(insert, params);
            Log.d(TAG, "inserted success");
        }

        cursor.close();
    }

    public static Bundle getDataFromDatabase(int id) {
        String selectQuery = "SELECT id, title, opening, user_rating, audience_rating," +
                " reservation_rating, reservation_grade, grade, thumbURL, genre," +
                " duration, audience, synopsis, director, actor, thumbsup, thumbsdown " +
                " FROM " + tableName +
                " WHERE id = " + id;

        Cursor cursor = movieDetailDatabase.rawQuery(selectQuery, null);
        cursor.moveToNext();

        Bundle bundle = new Bundle();
        bundle.putInt("id", cursor.getInt(0));
        bundle.putString("title", cursor.getString(1));
        bundle.putString("date", cursor.getString(2));
        bundle.putFloat("user_rating", cursor.getFloat(3));
        bundle.putFloat("audience_rating", cursor.getFloat(4));
        bundle.putFloat("reservation_rate", cursor.getFloat(5));
        bundle.putInt("reservation_grade", cursor.getInt(6));
        bundle.putInt("grade", cursor.getInt(7));
        bundle.putString("thumb", cursor.getString(8));
        bundle.putString("genre", cursor.getString(9));
        bundle.putInt("duration", cursor.getInt(10));
        bundle.putInt("audience", cursor.getInt(11));
        bundle.putString("synopsis", cursor.getString(12));
        bundle.putString("director", cursor.getString(13));
        bundle.putString("actor", cursor.getString(14));
        bundle.putInt("like", cursor.getInt(15));
        bundle.putInt("dislike", cursor.getInt(16));

        cursor.close();

        return bundle;
    }

    public static void createTable() {
        String makeTable = "CREATE TABLE IF NOT EXISTS "+ tableName +
                "(" +
                " _ID integer primary key autoincrement, " +
                " id integer, " +
                " title text, " +
                " opening text, " +
                " user_rating float, " +
                " audience_rating float, " +
                " reservation_rating float, " +
                " reservation_grade integer, " +
                " grade text, " +
                " thumbURL text, " +
                " genre text, " +
                " duration int, " +
                " audience int, " +
                " synopsis text, " +
                " director text, " +
                " actor text, " +
                " thumbsup int, " +
                " thumbsdown int " +
                ")"
                ;


        movieDetailDatabase.execSQL(makeTable);
        Log.d(TAG, "table created");
    }
}

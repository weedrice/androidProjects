package kr.co.supp0rtyoo.movieapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

import kr.co.supp0rtyoo.movieapplication.movieData.MovieDetail;
import kr.co.supp0rtyoo.movieapplication.movieData.MovieDetailInfo;

public class writeComment extends AppCompatActivity {

    TextView movieTitle;
    ImageView grade;
    RatingBar ratingBar;
    EditText commentContent;
    Button saveCommentBtn;
    Button cancelCommentBtn;

    int movieID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_comment);

        Intent intent = getIntent();
        movieID = intent.getExtras().getInt("id");

        movieTitle = (TextView)findViewById(R.id.writeCommentMovieTitle);
        grade = (ImageView)findViewById(R.id.writecommentGrade);
        ratingBar = (RatingBar)findViewById(R.id.commentRatingbar);
        commentContent = (EditText)findViewById(R.id.writeCommentContents);
        saveCommentBtn = (Button)findViewById(R.id.saveComment);
        cancelCommentBtn = (Button)findViewById(R.id.cancelComment);

        getMovieInfoFromAPI();

        saveCommentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveComment();
            }
        });

        cancelCommentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cancelComment();
            }
        });

    }

    public void getMovieInfoFromAPI() {
        String url = AppHelper.getUrl();
        int port = AppHelper.getPort();
        String apiUrl = "/movie/readMovie";
        int requestCode = movieID;
        String requestURL = "http://" + url + ":" + port + apiUrl + "?id=" + requestCode;

        StringRequest request = new StringRequest(
                Request.Method.GET,
                requestURL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        processMovieDetailResponse(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                        Log.d("Error: ", String.valueOf(error));
                    }
                }
        );

        request.setShouldCache(false);
        AppHelper.requestQueue.add(request);
    }

    private void processMovieDetailResponse(String response) {
        Gson gson = new Gson();
        MovieDetail movieDetail = gson.fromJson(response, MovieDetail.class);
        MovieDetailInfo detailInfo = movieDetail.getResult().get(0);

        movieTitle.setText(detailInfo.getTitle());
        switch(detailInfo.getGrade()) {
            case 12:
                grade.setImageResource(R.drawable.ic_12);
                break;
            case 15:
                grade.setImageResource(R.drawable.ic_15);
                break;
            case 19:
                grade.setImageResource(R.drawable.ic_19);
                break;
            default:
                grade.setImageResource(R.drawable.ic_all);
                break;
        }
    }

    private void saveComment() {
        saveCommentToServer();

        finish();
    }

    public void saveCommentToServer() {
        String url = AppHelper.getUrl();
        int port = AppHelper.getPort();
        String apiUrl = "/movie/createComment";
        int requestCode = movieID;
        final String writer = "supp0rtyoo";
        String requestURL = "http://" + url + ":" + port + apiUrl;

        StringRequest request = new StringRequest(
                Request.Method.POST,
                requestURL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        processMovieDetailResponse(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                        Log.d("Error: ", String.valueOf(error));
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("id", String.valueOf(movieID));
                params.put("writer", writer);
                params.put("rating", String.valueOf(ratingBar.getRating()));
                params.put("contents", commentContent.getText().toString());

                return params;
            }
        };

        request.setShouldCache(false);
        AppHelper.requestQueue.add(request);
    }

    private void cancelComment() {
        finish();
    }

}
